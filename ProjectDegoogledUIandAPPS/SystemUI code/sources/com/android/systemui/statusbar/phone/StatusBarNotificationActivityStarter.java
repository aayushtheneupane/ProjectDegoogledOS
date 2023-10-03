package com.android.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.dreams.IDreamManager;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.view.RemoteAnimationAdapter;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.Dependency;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.bubbles.BubbleController;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationPresenter;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.notification.ActivityLaunchAnimator;
import com.android.systemui.statusbar.notification.NotificationActivityStarter;
import com.android.systemui.statusbar.notification.NotificationEntryListener;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.NotificationInterruptionStateProvider;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import java.util.Objects;

public class StatusBarNotificationActivityStarter implements NotificationActivityStarter {
    protected static final boolean DEBUG = Log.isLoggable("NotificationClickHandler", 3);
    private final ActivityIntentHelper mActivityIntentHelper;
    private final ActivityLaunchAnimator mActivityLaunchAnimator;
    private final ActivityStarter mActivityStarter;
    private final AssistManager mAssistManager;
    private final Handler mBackgroundHandler;
    private final IStatusBarService mBarService;
    private final BubbleController mBubbleController;
    private final CommandQueue mCommandQueue;
    private final Context mContext;
    private final IDreamManager mDreamManager;
    private final NotificationEntryManager mEntryManager;
    private final NotificationGroupManager mGroupManager;
    private final HeadsUpManagerPhone mHeadsUpManager;
    private boolean mIsCollapsingToShowActivityOverLockscreen;
    private final KeyguardManager mKeyguardManager;
    private final KeyguardMonitor mKeyguardMonitor;
    private final LockPatternUtils mLockPatternUtils;
    private final NotificationLockscreenUserManager mLockscreenUserManager;
    private final Handler mMainThreadHandler;
    private final MetricsLogger mMetricsLogger;
    private final NotificationInterruptionStateProvider mNotificationInterruptionStateProvider;
    private final NotificationPanelView mNotificationPanel;
    private final NotificationPresenter mPresenter;
    private final NotificationRemoteInputManager mRemoteInputManager;
    private final ShadeController mShadeController;
    private final StatusBarRemoteInputCallback mStatusBarRemoteInputCallback;
    private final StatusBarStateController mStatusBarStateController;

    public StatusBarNotificationActivityStarter(Context context, CommandQueue commandQueue, AssistManager assistManager, NotificationPanelView notificationPanelView, NotificationPresenter notificationPresenter, NotificationEntryManager notificationEntryManager, HeadsUpManagerPhone headsUpManagerPhone, ActivityStarter activityStarter, ActivityLaunchAnimator activityLaunchAnimator, IStatusBarService iStatusBarService, StatusBarStateController statusBarStateController, KeyguardManager keyguardManager, IDreamManager iDreamManager, NotificationRemoteInputManager notificationRemoteInputManager, StatusBarRemoteInputCallback statusBarRemoteInputCallback, NotificationGroupManager notificationGroupManager, NotificationLockscreenUserManager notificationLockscreenUserManager, ShadeController shadeController, KeyguardMonitor keyguardMonitor, NotificationInterruptionStateProvider notificationInterruptionStateProvider, MetricsLogger metricsLogger, LockPatternUtils lockPatternUtils, Handler handler, Handler handler2, ActivityIntentHelper activityIntentHelper, BubbleController bubbleController) {
        this.mContext = context;
        this.mNotificationPanel = notificationPanelView;
        this.mPresenter = notificationPresenter;
        this.mHeadsUpManager = headsUpManagerPhone;
        this.mActivityLaunchAnimator = activityLaunchAnimator;
        this.mBarService = iStatusBarService;
        this.mCommandQueue = commandQueue;
        this.mKeyguardManager = keyguardManager;
        this.mDreamManager = iDreamManager;
        this.mRemoteInputManager = notificationRemoteInputManager;
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mShadeController = shadeController;
        this.mKeyguardMonitor = keyguardMonitor;
        this.mActivityStarter = activityStarter;
        this.mEntryManager = notificationEntryManager;
        this.mStatusBarStateController = statusBarStateController;
        this.mNotificationInterruptionStateProvider = notificationInterruptionStateProvider;
        this.mMetricsLogger = metricsLogger;
        this.mAssistManager = assistManager;
        this.mGroupManager = notificationGroupManager;
        this.mLockPatternUtils = lockPatternUtils;
        this.mBackgroundHandler = handler2;
        this.mEntryManager.addNotificationEntryListener(new NotificationEntryListener() {
            public void onPendingEntryAdded(NotificationEntry notificationEntry) {
                StatusBarNotificationActivityStarter.this.handleFullScreenIntent(notificationEntry);
            }
        });
        this.mStatusBarRemoteInputCallback = statusBarRemoteInputCallback;
        this.mMainThreadHandler = handler;
        this.mActivityIntentHelper = activityIntentHelper;
        this.mBubbleController = bubbleController;
    }

    public void onNotificationClicked(StatusBarNotification statusBarNotification, ExpandableNotificationRow expandableNotificationRow) {
        PendingIntent pendingIntent;
        RemoteInputController controller = this.mRemoteInputManager.getController();
        if (!controller.isRemoteInputActive(expandableNotificationRow.getEntry()) || TextUtils.isEmpty(expandableNotificationRow.getActiveRemoteInputText())) {
            Notification notification = statusBarNotification.getNotification();
            PendingIntent pendingIntent2 = notification.contentIntent;
            if (pendingIntent2 != null) {
                pendingIntent = pendingIntent2;
            } else {
                pendingIntent = notification.fullScreenIntent;
            }
            boolean isBubble = expandableNotificationRow.getEntry().isBubble();
            if (pendingIntent != null || isBubble) {
                String key = statusBarNotification.getKey();
                boolean z = pendingIntent != null && pendingIntent.isActivity() && !isBubble;
                boolean z2 = z && this.mActivityIntentHelper.wouldLaunchResolverActivity(pendingIntent.getIntent(), this.mLockscreenUserManager.getCurrentUserId());
                boolean isOccluded = this.mShadeController.isOccluded();
                boolean z3 = this.mKeyguardMonitor.isShowing() && pendingIntent != null && this.mActivityIntentHelper.wouldShowOverLockscreen(pendingIntent.getIntent(), this.mLockscreenUserManager.getCurrentUserId());
                C1303x60093a17 r0 = new ActivityStarter.OnDismissAction(statusBarNotification, expandableNotificationRow, controller, pendingIntent, key, z, isOccluded, z3) {
                    private final /* synthetic */ StatusBarNotification f$1;
                    private final /* synthetic */ ExpandableNotificationRow f$2;
                    private final /* synthetic */ RemoteInputController f$3;
                    private final /* synthetic */ PendingIntent f$4;
                    private final /* synthetic */ String f$5;
                    private final /* synthetic */ boolean f$6;
                    private final /* synthetic */ boolean f$7;
                    private final /* synthetic */ boolean f$8;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                        this.f$5 = r6;
                        this.f$6 = r7;
                        this.f$7 = r8;
                        this.f$8 = r9;
                    }

                    public final boolean onDismiss() {
                        return StatusBarNotificationActivityStarter.this.mo16541x7ba4f48c(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
                    }
                };
                if (z3) {
                    this.mIsCollapsingToShowActivityOverLockscreen = true;
                    r0.onDismiss();
                    return;
                }
                this.mActivityStarter.dismissKeyguardThenExecute(r0, (Runnable) null, z2);
                return;
            }
            Log.e("NotificationClickHandler", "onNotificationClicked called for non-clickable notification!");
            return;
        }
        controller.closeRemoteInputs();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0093 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* renamed from: handleNotificationClickAfterKeyguardDismissed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo16541x7ba4f48c(android.service.notification.StatusBarNotification r14, com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r15, com.android.systemui.statusbar.RemoteInputController r16, android.app.PendingIntent r17, java.lang.String r18, boolean r19, boolean r20, boolean r21) {
        /*
            r13 = this;
            r10 = r13
            r2 = r14
            com.android.systemui.statusbar.phone.HeadsUpManagerPhone r0 = r10.mHeadsUpManager
            r11 = 1
            if (r0 == 0) goto L_0x0027
            r6 = r18
            boolean r0 = r0.isAlerting(r6)
            if (r0 == 0) goto L_0x0025
            com.android.systemui.statusbar.NotificationPresenter r0 = r10.mPresenter
            boolean r0 = r0.isPresenterFullyCollapsed()
            r3 = r15
            if (r0 == 0) goto L_0x001b
            com.android.systemui.statusbar.policy.HeadsUpUtil.setIsClickedHeadsUpNotification(r15, r11)
        L_0x001b:
            com.android.systemui.statusbar.phone.HeadsUpManagerPhone r0 = r10.mHeadsUpManager
            java.lang.String r1 = r14.getKey()
            r0.removeNotification(r1, r11)
            goto L_0x002a
        L_0x0025:
            r3 = r15
            goto L_0x002a
        L_0x0027:
            r3 = r15
            r6 = r18
        L_0x002a:
            r0 = 0
            boolean r1 = shouldAutoCancel(r14)
            if (r1 == 0) goto L_0x0049
            com.android.systemui.statusbar.phone.NotificationGroupManager r1 = r10.mGroupManager
            boolean r1 = r1.isOnlyChildInGroup(r14)
            if (r1 == 0) goto L_0x0049
            com.android.systemui.statusbar.phone.NotificationGroupManager r1 = r10.mGroupManager
            com.android.systemui.statusbar.notification.collection.NotificationEntry r1 = r1.getLogicalGroupSummary(r14)
            android.service.notification.StatusBarNotification r1 = r1.notification
            boolean r4 = shouldAutoCancel(r1)
            if (r4 == 0) goto L_0x0049
            r9 = r1
            goto L_0x004a
        L_0x0049:
            r9 = r0
        L_0x004a:
            com.android.systemui.statusbar.phone.-$$Lambda$StatusBarNotificationActivityStarter$H4WWHLQEcsZwa09U0GneoOwngZE r12 = new com.android.systemui.statusbar.phone.-$$Lambda$StatusBarNotificationActivityStarter$H4WWHLQEcsZwa09U0GneoOwngZE
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r18
            r7 = r19
            r8 = r20
            r0.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            if (r21 == 0) goto L_0x006a
            com.android.systemui.statusbar.phone.ShadeController r0 = r10.mShadeController
            r0.addPostCollapseAction(r12)
            com.android.systemui.statusbar.phone.ShadeController r0 = r10.mShadeController
            r0.collapsePanel(r11)
            goto L_0x008a
        L_0x006a:
            com.android.systemui.statusbar.policy.KeyguardMonitor r0 = r10.mKeyguardMonitor
            boolean r0 = r0.isShowing()
            if (r0 == 0) goto L_0x0085
            com.android.systemui.statusbar.phone.ShadeController r0 = r10.mShadeController
            boolean r0 = r0.isOccluded()
            if (r0 == 0) goto L_0x0085
            com.android.systemui.statusbar.phone.ShadeController r0 = r10.mShadeController
            r0.addAfterKeyguardGoneRunnable(r12)
            com.android.systemui.statusbar.phone.ShadeController r0 = r10.mShadeController
            r0.collapsePanel()
            goto L_0x008a
        L_0x0085:
            android.os.Handler r0 = r10.mBackgroundHandler
            r0.postAtFrontOfQueue(r12)
        L_0x008a:
            com.android.systemui.statusbar.phone.NotificationPanelView r0 = r10.mNotificationPanel
            boolean r0 = r0.isFullyCollapsed()
            if (r0 != 0) goto L_0x0093
            goto L_0x0094
        L_0x0093:
            r11 = 0
        L_0x0094:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter.mo16541x7ba4f48c(android.service.notification.StatusBarNotification, com.android.systemui.statusbar.notification.row.ExpandableNotificationRow, com.android.systemui.statusbar.RemoteInputController, android.app.PendingIntent, java.lang.String, boolean, boolean, boolean):boolean");
    }

    /* access modifiers changed from: private */
    /* renamed from: handleNotificationClickAfterPanelCollapsed */
    public void mo16540x2579c40c(StatusBarNotification statusBarNotification, ExpandableNotificationRow expandableNotificationRow, RemoteInputController remoteInputController, PendingIntent pendingIntent, String str, boolean z, boolean z2, StatusBarNotification statusBarNotification2) {
        String str2 = str;
        StatusBarNotification statusBarNotification3 = statusBarNotification2;
        try {
            ActivityManager.getService().resumeAppSwitches();
        } catch (RemoteException unused) {
        }
        if (z) {
            int identifier = pendingIntent.getCreatorUserHandle().getIdentifier();
            if (this.mLockPatternUtils.isSeparateProfileChallengeEnabled(identifier) && this.mKeyguardManager.isDeviceLocked(identifier) && this.mStatusBarRemoteInputCallback.startWorkChallengeIfNecessary(identifier, pendingIntent.getIntentSender(), str)) {
                collapseOnMainThread();
                return;
            }
        }
        NotificationEntry entry = expandableNotificationRow.getEntry();
        boolean isBubble = entry.isBubble();
        Intent intent = null;
        CharSequence charSequence = !TextUtils.isEmpty(entry.remoteInputText) ? entry.remoteInputText : null;
        if (!TextUtils.isEmpty(charSequence)) {
            RemoteInputController remoteInputController2 = remoteInputController;
            if (!remoteInputController.isSpinning(entry.key)) {
                intent = new Intent().putExtra("android.remoteInputDraft", charSequence.toString());
            }
        }
        if (isBubble) {
            expandBubbleStackOnMainThread(str);
        } else {
            startNotificationIntent(pendingIntent, intent, expandableNotificationRow, z2, z);
        }
        if (z || isBubble) {
            this.mAssistManager.hideAssist();
        }
        if (shouldCollapse()) {
            collapseOnMainThread();
        }
        try {
            this.mBarService.onNotificationClick(str, NotificationVisibility.obtain(str, this.mEntryManager.getNotificationData().getRank(str), this.mEntryManager.getNotificationData().getActiveNotifications().size(), true, NotificationLogger.getNotificationLocation(this.mEntryManager.getNotificationData().get(str))));
        } catch (RemoteException unused2) {
        }
        if (!isBubble) {
            if (statusBarNotification3 != null) {
                removeNotification(statusBarNotification3);
            }
            if (shouldAutoCancel(statusBarNotification) || this.mRemoteInputManager.isNotificationKeptForRemoteInputHistory(str)) {
                removeNotification(statusBarNotification);
            }
        }
        this.mIsCollapsingToShowActivityOverLockscreen = false;
    }

    private void expandBubbleStackOnMainThread(String str) {
        if (Looper.getMainLooper().isCurrentThread()) {
            this.mBubbleController.expandStackAndSelectBubble(str);
        } else {
            this.mMainThreadHandler.post(new Runnable(str) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    StatusBarNotificationActivityStarter.this.mo16538xcfcf1363(this.f$1);
                }
            });
        }
    }

    /* renamed from: lambda$expandBubbleStackOnMainThread$2$StatusBarNotificationActivityStarter */
    public /* synthetic */ void mo16538xcfcf1363(String str) {
        this.mBubbleController.expandStackAndSelectBubble(str);
    }

    private void startNotificationIntent(PendingIntent pendingIntent, Intent intent, ExpandableNotificationRow expandableNotificationRow, boolean z, boolean z2) {
        RemoteAnimationAdapter launchAnimation = this.mActivityLaunchAnimator.getLaunchAnimation(expandableNotificationRow, z);
        if (launchAnimation != null) {
            try {
                ActivityTaskManager.getService().registerRemoteAnimationForNextActivityStart(pendingIntent.getCreatorPackage(), launchAnimation);
            } catch (PendingIntent.CanceledException | RemoteException e) {
                Log.w("NotificationClickHandler", "Sending contentIntent failed: " + e);
                return;
            }
        }
        this.mActivityLaunchAnimator.setLaunchResult(pendingIntent.sendAndReturnResult(this.mContext, 0, intent, (PendingIntent.OnFinished) null, (Handler) null, (String) null, StatusBar.getActivityOptions(launchAnimation)), z2);
    }

    public void startNotificationGutsIntent(Intent intent, int i, ExpandableNotificationRow expandableNotificationRow) {
        this.mActivityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction(intent, expandableNotificationRow, i) {
            private final /* synthetic */ Intent f$1;
            private final /* synthetic */ ExpandableNotificationRow f$2;
            private final /* synthetic */ int f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final boolean onDismiss() {
                return StatusBarNotificationActivityStarter.this.mo16546x977eacee(this.f$1, this.f$2, this.f$3);
            }
        }, (Runnable) null, false);
    }

    /* renamed from: lambda$startNotificationGutsIntent$5$StatusBarNotificationActivityStarter */
    public /* synthetic */ boolean mo16546x977eacee(Intent intent, ExpandableNotificationRow expandableNotificationRow, int i) {
        AsyncTask.execute(new Runnable(intent, expandableNotificationRow, i) {
            private final /* synthetic */ Intent f$1;
            private final /* synthetic */ ExpandableNotificationRow f$2;
            private final /* synthetic */ int f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                StatusBarNotificationActivityStarter.this.mo16545x362c104f(this.f$1, this.f$2, this.f$3);
            }
        });
        return true;
    }

    /* renamed from: lambda$startNotificationGutsIntent$4$StatusBarNotificationActivityStarter */
    public /* synthetic */ void mo16545x362c104f(Intent intent, ExpandableNotificationRow expandableNotificationRow, int i) {
        this.mActivityLaunchAnimator.setLaunchResult(TaskStackBuilder.create(this.mContext).addNextIntentWithParentStack(intent).startActivities(StatusBar.getActivityOptions(this.mActivityLaunchAnimator.getLaunchAnimation(expandableNotificationRow, this.mShadeController.isOccluded())), new UserHandle(UserHandle.getUserId(i))), true);
        if (shouldCollapse()) {
            this.mMainThreadHandler.post(new Runnable() {
                public final void run() {
                    StatusBarNotificationActivityStarter.this.mo16544xd4d973b0();
                }
            });
        }
    }

    /* renamed from: lambda$startNotificationGutsIntent$3$StatusBarNotificationActivityStarter */
    public /* synthetic */ void mo16544xd4d973b0() {
        this.mCommandQueue.animateCollapsePanels(2, true);
    }

    /* access modifiers changed from: private */
    public void handleFullScreenIntent(NotificationEntry notificationEntry) {
        if (!this.mNotificationInterruptionStateProvider.shouldLaunchFullScreenIntentWhenAdded(notificationEntry)) {
            return;
        }
        if (shouldSuppressFullScreenIntent(notificationEntry)) {
            if (DEBUG) {
                Log.d("NotificationClickHandler", "No Fullscreen intent: suppressed by DND: " + notificationEntry.key);
            }
        } else if (notificationEntry.importance >= 4) {
            ((UiOffloadThread) Dependency.get(UiOffloadThread.class)).submit(new Runnable() {
                public final void run() {
                    StatusBarNotificationActivityStarter.this.mo16539xd1b1b5ea();
                }
            });
            if (DEBUG) {
                Log.d("NotificationClickHandler", "Notification has fullScreenIntent; sending fullScreenIntent");
            }
            try {
                EventLog.writeEvent(36002, notificationEntry.key);
                notificationEntry.notification.getNotification().fullScreenIntent.send();
                notificationEntry.notifyFullScreenIntentLaunched();
                this.mMetricsLogger.count("note_fullscreen", 1);
            } catch (PendingIntent.CanceledException unused) {
            }
        } else if (DEBUG) {
            Log.d("NotificationClickHandler", "No Fullscreen intent: not important enough: " + notificationEntry.key);
        }
    }

    /* renamed from: lambda$handleFullScreenIntent$6$StatusBarNotificationActivityStarter */
    public /* synthetic */ void mo16539xd1b1b5ea() {
        try {
            this.mDreamManager.awaken();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isCollapsingToShowActivityOverLockscreen() {
        return this.mIsCollapsingToShowActivityOverLockscreen;
    }

    private static boolean shouldAutoCancel(StatusBarNotification statusBarNotification) {
        int i = statusBarNotification.getNotification().flags;
        return (i & 16) == 16 && (i & 64) == 0;
    }

    private void collapseOnMainThread() {
        if (Looper.getMainLooper().isCurrentThread()) {
            this.mShadeController.collapsePanel();
            return;
        }
        Handler handler = this.mMainThreadHandler;
        ShadeController shadeController = this.mShadeController;
        Objects.requireNonNull(shadeController);
        handler.post(new Runnable() {
            public final void run() {
                ShadeController.this.collapsePanel();
            }
        });
    }

    private boolean shouldCollapse() {
        return this.mStatusBarStateController.getState() != 0 || !this.mActivityLaunchAnimator.isAnimationPending();
    }

    private boolean shouldSuppressFullScreenIntent(NotificationEntry notificationEntry) {
        if (this.mPresenter.isDeviceInVrMode()) {
            return true;
        }
        return notificationEntry.shouldSuppressFullScreenIntent();
    }

    private void removeNotification(StatusBarNotification statusBarNotification) {
        this.mMainThreadHandler.post(new Runnable(statusBarNotification) {
            private final /* synthetic */ StatusBarNotification f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StatusBarNotificationActivityStarter.this.lambda$removeNotification$8$StatusBarNotificationActivityStarter(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$removeNotification$8$StatusBarNotificationActivityStarter(StatusBarNotification statusBarNotification) {
        C1295x893acf3a r0 = new Runnable(statusBarNotification) {
            private final /* synthetic */ StatusBarNotification f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StatusBarNotificationActivityStarter.this.lambda$removeNotification$7$StatusBarNotificationActivityStarter(this.f$1);
            }
        };
        if (this.mPresenter.isCollapsing()) {
            this.mShadeController.addPostCollapseAction(r0);
        } else {
            r0.run();
        }
    }

    public /* synthetic */ void lambda$removeNotification$7$StatusBarNotificationActivityStarter(StatusBarNotification statusBarNotification) {
        this.mEntryManager.performRemoveNotification(statusBarNotification, 1);
    }
}
