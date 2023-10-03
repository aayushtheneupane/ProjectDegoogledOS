package com.android.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.view.View;
import android.view.ViewParent;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import java.util.Objects;

public class StatusBarRemoteInputCallback implements NotificationRemoteInputManager.Callback, CommandQueue.Callbacks, StatusBarStateController.StateListener {
    private final ActivityIntentHelper mActivityIntentHelper;
    private final ActivityStarter mActivityStarter = ((ActivityStarter) Dependency.get(ActivityStarter.class));
    protected BroadcastReceiver mChallengeReceiver = new ChallengeReceiver();
    private final CommandQueue mCommandQueue;
    private final Context mContext;
    private int mDisabled2;
    private final NotificationGroupManager mGroupManager;
    private KeyguardManager mKeyguardManager;
    private final KeyguardMonitor mKeyguardMonitor = ((KeyguardMonitor) Dependency.get(KeyguardMonitor.class));
    /* access modifiers changed from: private */
    public final NotificationLockscreenUserManager mLockscreenUserManager = ((NotificationLockscreenUserManager) Dependency.get(NotificationLockscreenUserManager.class));
    private Handler mMainHandler = new Handler();
    private View mPendingRemoteInputView;
    private View mPendingWorkRemoteInputView;
    private final ShadeController mShadeController = ((ShadeController) Dependency.get(ShadeController.class));
    private final SysuiStatusBarStateController mStatusBarStateController = ((SysuiStatusBarStateController) Dependency.get(StatusBarStateController.class));

    public StatusBarRemoteInputCallback(Context context, NotificationGroupManager notificationGroupManager) {
        this.mContext = context;
        this.mContext.registerReceiverAsUser(this.mChallengeReceiver, UserHandle.ALL, new IntentFilter("android.intent.action.DEVICE_LOCKED_CHANGED"), (String) null, (Handler) null);
        this.mStatusBarStateController.addCallback(this);
        this.mKeyguardManager = (KeyguardManager) context.getSystemService(KeyguardManager.class);
        this.mCommandQueue = (CommandQueue) SysUiServiceProvider.getComponent(context, CommandQueue.class);
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
        this.mActivityIntentHelper = new ActivityIntentHelper(this.mContext);
        this.mGroupManager = notificationGroupManager;
    }

    public void onStateChanged(int i) {
        boolean z = this.mPendingRemoteInputView != null;
        if (i != 0) {
            return;
        }
        if ((this.mStatusBarStateController.leaveOpenOnKeyguardHide() || z) && !this.mStatusBarStateController.isKeyguardRequested()) {
            if (z) {
                Handler handler = this.mMainHandler;
                View view = this.mPendingRemoteInputView;
                Objects.requireNonNull(view);
                handler.post(new Runnable(view) {
                    private final /* synthetic */ View f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void run() {
                        this.f$0.callOnClick();
                    }
                });
            }
            this.mPendingRemoteInputView = null;
        }
    }

    public void onLockedRemoteInput(ExpandableNotificationRow expandableNotificationRow, View view) {
        if (!expandableNotificationRow.isPinned()) {
            this.mStatusBarStateController.setLeaveOpenOnKeyguardHide(true);
        }
        this.mShadeController.showBouncer(true);
        this.mPendingRemoteInputView = view;
    }

    /* access modifiers changed from: protected */
    public void onWorkChallengeChanged() {
        this.mLockscreenUserManager.updatePublicMode();
        if (this.mPendingWorkRemoteInputView != null && !this.mLockscreenUserManager.isAnyProfilePublicMode()) {
            this.mShadeController.postOnShadeExpanded(new Runnable() {
                public final void run() {
                    StatusBarRemoteInputCallback.this.lambda$onWorkChallengeChanged$2$StatusBarRemoteInputCallback();
                }
            });
            this.mShadeController.instantExpandNotificationsPanel();
        }
    }

    public /* synthetic */ void lambda$onWorkChallengeChanged$2$StatusBarRemoteInputCallback() {
        View view = this.mPendingWorkRemoteInputView;
        if (view != null) {
            ViewParent parent = view.getParent();
            while (!(parent instanceof ExpandableNotificationRow)) {
                if (parent != null) {
                    parent = parent.getParent();
                } else {
                    return;
                }
            }
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) parent;
            ViewParent parent2 = expandableNotificationRow.getParent();
            if (parent2 instanceof NotificationStackScrollLayout) {
                expandableNotificationRow.makeActionsVisibile();
                expandableNotificationRow.post(new Runnable((NotificationStackScrollLayout) parent2, expandableNotificationRow) {
                    private final /* synthetic */ NotificationStackScrollLayout f$1;
                    private final /* synthetic */ ExpandableNotificationRow f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        StatusBarRemoteInputCallback.this.lambda$onWorkChallengeChanged$1$StatusBarRemoteInputCallback(this.f$1, this.f$2);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onWorkChallengeChanged$1$StatusBarRemoteInputCallback(NotificationStackScrollLayout notificationStackScrollLayout, ExpandableNotificationRow expandableNotificationRow) {
        C1309x35deece0 r0 = new Runnable(notificationStackScrollLayout) {
            private final /* synthetic */ NotificationStackScrollLayout f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StatusBarRemoteInputCallback.this.lambda$onWorkChallengeChanged$0$StatusBarRemoteInputCallback(this.f$1);
            }
        };
        if (notificationStackScrollLayout.scrollTo(expandableNotificationRow)) {
            notificationStackScrollLayout.setFinishScrollingCallback(r0);
        } else {
            r0.run();
        }
    }

    public /* synthetic */ void lambda$onWorkChallengeChanged$0$StatusBarRemoteInputCallback(NotificationStackScrollLayout notificationStackScrollLayout) {
        this.mPendingWorkRemoteInputView.callOnClick();
        this.mPendingWorkRemoteInputView = null;
        notificationStackScrollLayout.setFinishScrollingCallback((Runnable) null);
    }

    public void onMakeExpandedVisibleForRemoteInput(ExpandableNotificationRow expandableNotificationRow, View view) {
        if (this.mKeyguardMonitor.isShowing()) {
            onLockedRemoteInput(expandableNotificationRow, view);
            return;
        }
        if (expandableNotificationRow.isChildInGroup() && !expandableNotificationRow.areChildrenExpanded()) {
            this.mGroupManager.toggleGroupExpansion(expandableNotificationRow.getStatusBarNotification());
        }
        expandableNotificationRow.setUserExpanded(true);
        NotificationContentView privateLayout = expandableNotificationRow.getPrivateLayout();
        Objects.requireNonNull(view);
        privateLayout.setOnExpandedVisibleListener(new Runnable(view) {
            private final /* synthetic */ View f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                this.f$0.performClick();
            }
        });
    }

    public void onLockedWorkRemoteInput(int i, ExpandableNotificationRow expandableNotificationRow, View view) {
        this.mCommandQueue.animateCollapsePanels();
        startWorkChallengeIfNecessary(i, (IntentSender) null, (String) null);
        this.mPendingWorkRemoteInputView = view;
    }

    /* access modifiers changed from: package-private */
    public boolean startWorkChallengeIfNecessary(int i, IntentSender intentSender, String str) {
        this.mPendingWorkRemoteInputView = null;
        Intent createConfirmDeviceCredentialIntent = this.mKeyguardManager.createConfirmDeviceCredentialIntent((CharSequence) null, (CharSequence) null, i);
        if (createConfirmDeviceCredentialIntent == null) {
            return false;
        }
        Intent intent = new Intent("com.android.systemui.statusbar.work_challenge_unlocked_notification_action");
        intent.putExtra("android.intent.extra.INTENT", intentSender);
        intent.putExtra("android.intent.extra.INDEX", str);
        intent.setPackage(this.mContext.getPackageName());
        createConfirmDeviceCredentialIntent.putExtra("android.intent.extra.INTENT", PendingIntent.getBroadcast(this.mContext, 0, intent, 1409286144).getIntentSender());
        try {
            ActivityManager.getService().startConfirmDeviceCredentialIntent(createConfirmDeviceCredentialIntent, (Bundle) null);
            return true;
        } catch (RemoteException unused) {
            return true;
        }
    }

    public boolean shouldHandleRemoteInput(View view, PendingIntent pendingIntent) {
        return (this.mDisabled2 & 4) != 0;
    }

    public boolean handleRemoteViewClick(View view, PendingIntent pendingIntent, NotificationRemoteInputManager.ClickHandler clickHandler) {
        if (!pendingIntent.isActivity()) {
            return clickHandler.handleClick();
        }
        this.mActivityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction(clickHandler) {
            private final /* synthetic */ NotificationRemoteInputManager.ClickHandler f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onDismiss() {
                return StatusBarRemoteInputCallback.this.lambda$handleRemoteViewClick$3$StatusBarRemoteInputCallback(this.f$1);
            }
        }, (Runnable) null, this.mActivityIntentHelper.wouldLaunchResolverActivity(pendingIntent.getIntent(), this.mLockscreenUserManager.getCurrentUserId()));
        return true;
    }

    public /* synthetic */ boolean lambda$handleRemoteViewClick$3$StatusBarRemoteInputCallback(NotificationRemoteInputManager.ClickHandler clickHandler) {
        try {
            ActivityManager.getService().resumeAppSwitches();
        } catch (RemoteException unused) {
        }
        return clickHandler.handleClick() && this.mShadeController.closeShadeIfOpen();
    }

    public void disable(int i, int i2, int i3, boolean z) {
        if (i == this.mContext.getDisplayId()) {
            this.mDisabled2 = i3;
        }
    }

    protected class ChallengeReceiver extends BroadcastReceiver {
        protected ChallengeReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int intExtra = intent.getIntExtra("android.intent.extra.user_handle", -10000);
            if ("android.intent.action.DEVICE_LOCKED_CHANGED".equals(action) && intExtra != StatusBarRemoteInputCallback.this.mLockscreenUserManager.getCurrentUserId() && StatusBarRemoteInputCallback.this.mLockscreenUserManager.isCurrentProfile(intExtra)) {
                StatusBarRemoteInputCallback.this.onWorkChallengeChanged();
            }
        }
    }
}
