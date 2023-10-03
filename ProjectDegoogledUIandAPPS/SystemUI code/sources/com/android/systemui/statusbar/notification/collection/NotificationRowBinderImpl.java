package com.android.systemui.statusbar.notification.collection;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.util.NotificationMessagingUtil;
import com.android.internal.util.Preconditions;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationPresenter;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationUiAdjustment;
import com.android.systemui.statusbar.notification.InflationException;
import com.android.systemui.statusbar.notification.NotificationClicker;
import com.android.systemui.statusbar.notification.NotificationInterruptionStateProvider;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentInflater;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.row.RowInflaterTask;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.NotificationGroupManager;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import java.util.Objects;

public class NotificationRowBinderImpl implements NotificationRowBinder {
    private final boolean mAllowLongPress;
    private BindRowCallback mBindRowCallback;
    private final Context mContext;
    private final ExpandableNotificationRow.ExpansionLogger mExpansionLogger = new ExpandableNotificationRow.ExpansionLogger() {
        public final void logNotificationExpansion(String str, boolean z, boolean z2) {
            NotificationRowBinderImpl.this.logNotificationExpansion(str, z, z2);
        }
    };
    private final NotificationGroupManager mGroupManager = ((NotificationGroupManager) Dependency.get(NotificationGroupManager.class));
    private final NotificationGutsManager mGutsManager = ((NotificationGutsManager) Dependency.get(NotificationGutsManager.class));
    private HeadsUpManager mHeadsUpManager;
    private NotificationContentInflater.InflationCallback mInflationCallback;
    private final KeyguardBypassController mKeyguardBypassController;
    private NotificationListContainer mListContainer;
    private final NotificationMessagingUtil mMessagingUtil;
    private NotificationClicker mNotificationClicker;
    private final NotificationInterruptionStateProvider mNotificationInterruptionStateProvider = ((NotificationInterruptionStateProvider) Dependency.get(NotificationInterruptionStateProvider.class));
    private final NotificationLogger mNotificationLogger = ((NotificationLogger) Dependency.get(NotificationLogger.class));
    private ExpandableNotificationRow.OnAppOpsClickListener mOnAppOpsClickListener;
    private NotificationPresenter mPresenter;
    private NotificationRemoteInputManager mRemoteInputManager;
    private final StatusBarStateController mStatusBarStateController;
    private final UiOffloadThread mUiOffloadThread = ((UiOffloadThread) Dependency.get(UiOffloadThread.class));

    public interface BindRowCallback {
        void onBindRow(NotificationEntry notificationEntry, PackageManager packageManager, StatusBarNotification statusBarNotification, ExpandableNotificationRow expandableNotificationRow);
    }

    public NotificationRowBinderImpl(Context context, boolean z, KeyguardBypassController keyguardBypassController, StatusBarStateController statusBarStateController) {
        this.mContext = context;
        this.mMessagingUtil = new NotificationMessagingUtil(context);
        this.mAllowLongPress = z;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mStatusBarStateController = statusBarStateController;
    }

    private NotificationRemoteInputManager getRemoteInputManager() {
        if (this.mRemoteInputManager == null) {
            this.mRemoteInputManager = (NotificationRemoteInputManager) Dependency.get(NotificationRemoteInputManager.class);
        }
        return this.mRemoteInputManager;
    }

    public void setUpWithPresenter(NotificationPresenter notificationPresenter, NotificationListContainer notificationListContainer, HeadsUpManager headsUpManager, NotificationContentInflater.InflationCallback inflationCallback, BindRowCallback bindRowCallback) {
        this.mPresenter = notificationPresenter;
        this.mListContainer = notificationListContainer;
        this.mHeadsUpManager = headsUpManager;
        this.mInflationCallback = inflationCallback;
        this.mBindRowCallback = bindRowCallback;
        NotificationGutsManager notificationGutsManager = this.mGutsManager;
        Objects.requireNonNull(notificationGutsManager);
        this.mOnAppOpsClickListener = new ExpandableNotificationRow.OnAppOpsClickListener() {
            public final boolean onClick(View view, int i, int i2, NotificationMenuRowPlugin.MenuItem menuItem) {
                return NotificationGutsManager.this.openGuts(view, i, i2, menuItem);
            }
        };
    }

    public void setNotificationClicker(NotificationClicker notificationClicker) {
        this.mNotificationClicker = notificationClicker;
    }

    public void inflateViews(NotificationEntry notificationEntry, Runnable runnable) throws InflationException {
        ViewGroup viewParentForNotification = this.mListContainer.getViewParentForNotification(notificationEntry);
        PackageManager packageManagerForUser = StatusBar.getPackageManagerForUser(this.mContext, notificationEntry.notification.getUser().getIdentifier());
        StatusBarNotification statusBarNotification = notificationEntry.notification;
        if (notificationEntry.rowExists()) {
            notificationEntry.updateIcons(this.mContext, statusBarNotification);
            notificationEntry.reset();
            updateNotification(notificationEntry, packageManagerForUser, statusBarNotification, notificationEntry.getRow());
            notificationEntry.getRow().setOnDismissRunnable(runnable);
            return;
        }
        notificationEntry.createIcons(this.mContext, statusBarNotification);
        new RowInflaterTask().inflate(this.mContext, viewParentForNotification, notificationEntry, new RowInflaterTask.RowInflationFinishedListener(notificationEntry, packageManagerForUser, statusBarNotification, runnable) {
            private final /* synthetic */ NotificationEntry f$1;
            private final /* synthetic */ PackageManager f$2;
            private final /* synthetic */ StatusBarNotification f$3;
            private final /* synthetic */ Runnable f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onInflationFinished(ExpandableNotificationRow expandableNotificationRow) {
                NotificationRowBinderImpl.this.lambda$inflateViews$0$NotificationRowBinderImpl(this.f$1, this.f$2, this.f$3, this.f$4, expandableNotificationRow);
            }
        });
    }

    public /* synthetic */ void lambda$inflateViews$0$NotificationRowBinderImpl(NotificationEntry notificationEntry, PackageManager packageManager, StatusBarNotification statusBarNotification, Runnable runnable, ExpandableNotificationRow expandableNotificationRow) {
        bindRow(notificationEntry, packageManager, statusBarNotification, expandableNotificationRow, runnable);
        updateNotification(notificationEntry, packageManager, statusBarNotification, expandableNotificationRow);
    }

    private void bindRow(NotificationEntry notificationEntry, PackageManager packageManager, StatusBarNotification statusBarNotification, ExpandableNotificationRow expandableNotificationRow, Runnable runnable) {
        expandableNotificationRow.setExpansionLogger(this.mExpansionLogger, notificationEntry.notification.getKey());
        expandableNotificationRow.setBypassController(this.mKeyguardBypassController);
        expandableNotificationRow.setStatusBarStateController(this.mStatusBarStateController);
        expandableNotificationRow.setGroupManager(this.mGroupManager);
        expandableNotificationRow.setHeadsUpManager(this.mHeadsUpManager);
        expandableNotificationRow.setOnExpandClickListener(this.mPresenter);
        expandableNotificationRow.setInflationCallback(this.mInflationCallback);
        if (this.mAllowLongPress) {
            NotificationGutsManager notificationGutsManager = this.mGutsManager;
            Objects.requireNonNull(notificationGutsManager);
            expandableNotificationRow.setLongPressListener(new ExpandableNotificationRow.LongPressListener() {
                public final boolean onLongPress(View view, int i, int i2, NotificationMenuRowPlugin.MenuItem menuItem) {
                    return NotificationGutsManager.this.openGuts(view, i, i2, menuItem);
                }
            });
        }
        this.mListContainer.bindRow(expandableNotificationRow);
        getRemoteInputManager().bindRow(expandableNotificationRow);
        String packageName = statusBarNotification.getPackageName();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 8704);
            if (applicationInfo != null) {
                packageName = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        expandableNotificationRow.setAppName(packageName);
        expandableNotificationRow.setOnDismissRunnable(runnable);
        expandableNotificationRow.setDescendantFocusability(393216);
        if (NotificationRemoteInputManager.ENABLE_REMOTE_INPUT) {
            expandableNotificationRow.setDescendantFocusability(131072);
        }
        expandableNotificationRow.setAppOpsOnClickListener(this.mOnAppOpsClickListener);
        this.mBindRowCallback.onBindRow(notificationEntry, packageManager, statusBarNotification, expandableNotificationRow);
    }

    public void onNotificationRankingUpdated(NotificationEntry notificationEntry, Integer num, NotificationUiAdjustment notificationUiAdjustment, NotificationUiAdjustment notificationUiAdjustment2) {
        if (NotificationUiAdjustment.needReinflate(notificationUiAdjustment, notificationUiAdjustment2)) {
            if (notificationEntry.rowExists()) {
                notificationEntry.reset();
                updateNotification(notificationEntry, StatusBar.getPackageManagerForUser(this.mContext, notificationEntry.notification.getUser().getIdentifier()), notificationEntry.notification, notificationEntry.getRow());
            }
        } else if (num != null && notificationEntry.importance != num.intValue() && notificationEntry.rowExists()) {
            notificationEntry.getRow().onNotificationRankingUpdated();
        }
    }

    private void updateNotification(NotificationEntry notificationEntry, PackageManager packageManager, StatusBarNotification statusBarNotification, ExpandableNotificationRow expandableNotificationRow) {
        expandableNotificationRow.setIsLowPriority(notificationEntry.ambient);
        boolean z = false;
        try {
            notificationEntry.targetSdk = packageManager.getApplicationInfo(statusBarNotification.getPackageName(), 0).targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("NotificationViewManager", "Failed looking up ApplicationInfo for " + statusBarNotification.getPackageName(), e);
        }
        int i = notificationEntry.targetSdk;
        expandableNotificationRow.setLegacy(i >= 9 && i < 21);
        notificationEntry.setIconTag(C1777R$id.icon_is_pre_L, Boolean.valueOf(notificationEntry.targetSdk < 21));
        notificationEntry.autoRedacted = notificationEntry.notification.getNotification().publicVersion == null;
        notificationEntry.setRow(expandableNotificationRow);
        expandableNotificationRow.setOnActivatedListener(this.mPresenter);
        boolean isImportantMessaging = this.mMessagingUtil.isImportantMessaging(statusBarNotification, notificationEntry.importance);
        boolean z2 = isImportantMessaging && !this.mPresenter.isPresenterFullyCollapsed();
        expandableNotificationRow.setUseIncreasedCollapsedHeight(isImportantMessaging);
        expandableNotificationRow.setUseIncreasedHeadsUpHeight(z2);
        expandableNotificationRow.setEntry(notificationEntry);
        if (this.mNotificationInterruptionStateProvider.shouldHeadsUp(notificationEntry)) {
            expandableNotificationRow.updateInflationFlag(4, true);
        }
        if (expandableNotificationRow.isAppLocked() || ((NotificationLockscreenUserManager) Dependency.get(NotificationLockscreenUserManager.class)).needsRedaction(notificationEntry)) {
            z = true;
        }
        expandableNotificationRow.setNeedsRedaction(z);
        expandableNotificationRow.inflateViews();
        ((NotificationClicker) Preconditions.checkNotNull(this.mNotificationClicker)).register(expandableNotificationRow, statusBarNotification);
    }

    /* access modifiers changed from: private */
    public void logNotificationExpansion(String str, boolean z, boolean z2) {
        this.mNotificationLogger.onExpansionChanged(str, z, z2);
    }
}
