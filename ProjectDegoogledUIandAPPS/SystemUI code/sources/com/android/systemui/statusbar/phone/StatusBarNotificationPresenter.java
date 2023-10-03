package com.android.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.service.vr.IVrManager;
import android.service.vr.IVrStateCallbacks;
import android.text.TextUtils;
import android.util.Slog;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.internal.widget.MessagingGroup;
import com.android.internal.widget.MessagingMessage;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1778R$integer;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.ForegroundServiceNotificationListener;
import com.android.systemui.InitController;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationPresenter;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationViewHierarchyManager;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.AboveShelfObserver;
import com.android.systemui.statusbar.notification.ActivityLaunchAnimator;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.NotificationAlertingManager;
import com.android.systemui.statusbar.notification.NotificationEntryListener;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.NotificationInterruptionStateProvider;
import com.android.systemui.statusbar.notification.VisualStabilityManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.NotificationRowBinderImpl;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.row.NotificationInfo;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class StatusBarNotificationPresenter implements NotificationPresenter, ConfigurationController.ConfigurationListener, NotificationRowBinderImpl.BindRowCallback {
    private final AboveShelfObserver mAboveShelfObserver;
    private final AccessibilityManager mAccessibilityManager;
    private final ActivityLaunchAnimator mActivityLaunchAnimator;
    private final ActivityStarter mActivityStarter = ((ActivityStarter) Dependency.get(ActivityStarter.class));
    ActivityManager mAm;
    /* access modifiers changed from: private */
    public final IStatusBarService mBarService;
    private ArrayList<String> mBlacklist = new ArrayList<>();
    private final NotificationInfo.CheckSaveListener mCheckSaveListener = new NotificationInfo.CheckSaveListener() {
        public void checkSave(Runnable runnable, StatusBarNotification statusBarNotification) {
            if (!StatusBarNotificationPresenter.this.mLockscreenUserManager.isLockscreenPublicMode(statusBarNotification.getUser().getIdentifier()) || !StatusBarNotificationPresenter.this.mKeyguardManager.isKeyguardLocked()) {
                runnable.run();
            } else {
                StatusBarNotificationPresenter.this.onLockedNotificationImportanceChange(new ActivityStarter.OnDismissAction(runnable) {
                    private final /* synthetic */ Runnable f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final boolean onDismiss() {
                        return this.f$0.run();
                    }
                });
            }
        }
    };
    private final CommandQueue mCommandQueue;
    private final Context mContext;
    private boolean mDispatchUiModeChangeOnUserSwitched;
    private final DozeScrimController mDozeScrimController;
    private final DynamicPrivacyController mDynamicPrivacyController;
    private final NotificationEntryManager mEntryManager = ((NotificationEntryManager) Dependency.get(NotificationEntryManager.class));
    private final NotificationGutsManager mGutsManager = ((NotificationGutsManager) Dependency.get(NotificationGutsManager.class));
    private final HeadsUpManagerPhone mHeadsUpManager;
    /* access modifiers changed from: private */
    public final KeyguardManager mKeyguardManager;
    private final KeyguardMonitor mKeyguardMonitor = ((KeyguardMonitor) Dependency.get(KeyguardMonitor.class));
    private final LockscreenGestureLogger mLockscreenGestureLogger = ((LockscreenGestureLogger) Dependency.get(LockscreenGestureLogger.class));
    /* access modifiers changed from: private */
    public final NotificationLockscreenUserManager mLockscreenUserManager = ((NotificationLockscreenUserManager) Dependency.get(NotificationLockscreenUserManager.class));
    private final int mMaxAllowedKeyguardNotifications;
    private int mMaxKeyguardNotifications;
    private final NotificationMediaManager mMediaManager = ((NotificationMediaManager) Dependency.get(NotificationMediaManager.class));
    private final NotificationInterruptionStateProvider mNotificationInterruptionStateProvider = ((NotificationInterruptionStateProvider) Dependency.get(NotificationInterruptionStateProvider.class));
    private final NotificationPanelView mNotificationPanel;
    private final NotificationGutsManager.OnSettingsClickListener mOnSettingsClickListener = new NotificationGutsManager.OnSettingsClickListener() {
        public void onSettingsClick(String str) {
            try {
                StatusBarNotificationPresenter.this.mBarService.onNotificationSettingsViewed(str);
            } catch (RemoteException unused) {
            }
        }
    };
    private boolean mReinflateNotificationsOnUserSwitched;
    private final ScrimController mScrimController;
    /* access modifiers changed from: private */
    public final ShadeController mShadeController = ((ShadeController) Dependency.get(ShadeController.class));
    private StatusBar mStatusBar;
    private final SysuiStatusBarStateController mStatusBarStateController = ((SysuiStatusBarStateController) Dependency.get(StatusBarStateController.class));
    private ArrayList<String> mStoplist = new ArrayList<>();
    private final UnlockMethodCache mUnlockMethodCache;
    private final NotificationViewHierarchyManager mViewHierarchyManager = ((NotificationViewHierarchyManager) Dependency.get(NotificationViewHierarchyManager.class));
    private final VisualStabilityManager mVisualStabilityManager = ((VisualStabilityManager) Dependency.get(VisualStabilityManager.class));
    protected boolean mVrMode;
    private final IVrStateCallbacks mVrStateCallbacks = new IVrStateCallbacks.Stub() {
        public void onVrStateChanged(boolean z) {
            StatusBarNotificationPresenter.this.mVrMode = z;
        }
    };

    static /* synthetic */ boolean lambda$onExpandClicked$1() {
        return false;
    }

    public StatusBarNotificationPresenter(Context context, NotificationPanelView notificationPanelView, HeadsUpManagerPhone headsUpManagerPhone, StatusBarWindowView statusBarWindowView, ViewGroup viewGroup, DozeScrimController dozeScrimController, ScrimController scrimController, ActivityLaunchAnimator activityLaunchAnimator, DynamicPrivacyController dynamicPrivacyController, NotificationAlertingManager notificationAlertingManager, NotificationRowBinderImpl notificationRowBinderImpl) {
        this.mContext = context;
        this.mNotificationPanel = notificationPanelView;
        this.mHeadsUpManager = headsUpManagerPhone;
        this.mDynamicPrivacyController = dynamicPrivacyController;
        this.mCommandQueue = (CommandQueue) SysUiServiceProvider.getComponent(context, CommandQueue.class);
        this.mAboveShelfObserver = new AboveShelfObserver(viewGroup);
        this.mActivityLaunchAnimator = activityLaunchAnimator;
        this.mAboveShelfObserver.setListener((AboveShelfObserver.HasViewAboveShelfChangedListener) statusBarWindowView.findViewById(C1777R$id.notification_container_parent));
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        this.mDozeScrimController = dozeScrimController;
        this.mScrimController = scrimController;
        this.mUnlockMethodCache = UnlockMethodCache.getInstance(this.mContext);
        this.mKeyguardManager = (KeyguardManager) context.getSystemService(KeyguardManager.class);
        this.mMaxAllowedKeyguardNotifications = context.getResources().getInteger(C1778R$integer.keyguard_max_notification_count);
        this.mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
        this.mAm = (ActivityManager) this.mContext.getSystemService("activity");
        IVrManager asInterface = IVrManager.Stub.asInterface(ServiceManager.getService("vrmanager"));
        if (asInterface != null) {
            try {
                asInterface.registerListener(this.mVrStateCallbacks);
            } catch (RemoteException e) {
                Slog.e("StatusBarNotificationPresenter", "Failed to register VR mode state listener: " + e);
            }
        }
        NotificationRemoteInputManager notificationRemoteInputManager = (NotificationRemoteInputManager) Dependency.get(NotificationRemoteInputManager.class);
        notificationRemoteInputManager.setUpWithCallback((NotificationRemoteInputManager.Callback) Dependency.get(NotificationRemoteInputManager.Callback.class), this.mNotificationPanel.createRemoteInputDelegate());
        notificationRemoteInputManager.getController().addCallback((RemoteInputController.Callback) Dependency.get(StatusBarWindowController.class));
        ((InitController) Dependency.get(InitController.class)).addPostInitTask(new Runnable((NotificationListContainer) viewGroup, notificationRemoteInputManager, notificationRowBinderImpl) {
            private final /* synthetic */ NotificationListContainer f$1;
            private final /* synthetic */ NotificationRemoteInputManager f$2;
            private final /* synthetic */ NotificationRowBinderImpl f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                StatusBarNotificationPresenter.this.lambda$new$0$StatusBarNotificationPresenter(this.f$1, this.f$2, this.f$3);
            }
        });
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
        notificationAlertingManager.setHeadsUpManager(this.mHeadsUpManager);
        setHeadsUpStoplist();
        setHeadsUpBlacklist();
    }

    public /* synthetic */ void lambda$new$0$StatusBarNotificationPresenter(NotificationListContainer notificationListContainer, NotificationRemoteInputManager notificationRemoteInputManager, NotificationRowBinderImpl notificationRowBinderImpl) {
        C14881 r0 = new NotificationEntryListener() {
            public void onNotificationAdded(NotificationEntry notificationEntry) {
                StatusBarNotificationPresenter.this.mShadeController.updateAreThereNotifications();
            }

            public void onPostEntryUpdated(NotificationEntry notificationEntry) {
                StatusBarNotificationPresenter.this.mShadeController.updateAreThereNotifications();
            }

            public void onEntryRemoved(NotificationEntry notificationEntry, NotificationVisibility notificationVisibility, boolean z) {
                StatusBarNotificationPresenter.this.onNotificationRemoved(notificationEntry.key, notificationEntry.notification);
                if (z) {
                    StatusBarNotificationPresenter.this.maybeEndAmbientPulse();
                }
            }
        };
        this.mViewHierarchyManager.setUpWithPresenter(this, notificationListContainer);
        this.mEntryManager.setUpWithPresenter(this, notificationListContainer, this.mHeadsUpManager);
        this.mEntryManager.addNotificationEntryListener(r0);
        this.mEntryManager.addNotificationLifetimeExtender(this.mHeadsUpManager);
        this.mEntryManager.addNotificationLifetimeExtender(this.mGutsManager);
        this.mEntryManager.addNotificationLifetimeExtenders(notificationRemoteInputManager.getLifetimeExtenders());
        notificationRowBinderImpl.setUpWithPresenter(this, notificationListContainer, this.mHeadsUpManager, this.mEntryManager, this);
        this.mNotificationInterruptionStateProvider.setUpWithPresenter(this, this.mHeadsUpManager, new NotificationInterruptionStateProvider.HeadsUpSuppressor() {
            public final boolean canHeadsUp(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification) {
                return StatusBarNotificationPresenter.this.canHeadsUp(notificationEntry, statusBarNotification);
            }
        });
        this.mLockscreenUserManager.setUpWithPresenter(this);
        this.mMediaManager.setUpWithPresenter(this);
        this.mVisualStabilityManager.setUpWithPresenter(this);
        this.mGutsManager.setUpWithPresenter(this, notificationListContainer, this.mCheckSaveListener, this.mOnSettingsClickListener);
        Dependency.get(ForegroundServiceNotificationListener.class);
        onUserSwitched(this.mLockscreenUserManager.getCurrentUserId());
    }

    public boolean isMediaPlayerNotification(NotificationEntry notificationEntry) {
        return this.mMediaManager.isMediaPlayerNotification(notificationEntry);
    }

    public void addCallback(StatusBar statusBar) {
        this.mStatusBar = statusBar;
    }

    public void onDensityOrFontScaleChanged() {
        MessagingMessage.dropCache();
        MessagingGroup.dropCache();
        if (!KeyguardUpdateMonitor.getInstance(this.mContext).isSwitchingUser()) {
            updateNotificationsOnDensityOrFontScaleChanged();
        } else {
            this.mReinflateNotificationsOnUserSwitched = true;
        }
    }

    public void onUiModeChanged() {
        if (!KeyguardUpdateMonitor.getInstance(this.mContext).isSwitchingUser()) {
            updateNotificationOnUiModeChanged();
        } else {
            this.mDispatchUiModeChangeOnUserSwitched = true;
        }
    }

    public void onOverlayChanged() {
        onDensityOrFontScaleChanged();
    }

    private void updateNotificationOnUiModeChanged() {
        ArrayList<NotificationEntry> notificationsForCurrentUser = this.mEntryManager.getNotificationData().getNotificationsForCurrentUser();
        for (int i = 0; i < notificationsForCurrentUser.size(); i++) {
            ExpandableNotificationRow row = notificationsForCurrentUser.get(i).getRow();
            if (row != null) {
                row.onUiModeChanged();
            }
        }
    }

    private void updateNotificationsOnDensityOrFontScaleChanged() {
        ArrayList<NotificationEntry> notificationsForCurrentUser = this.mEntryManager.getNotificationData().getNotificationsForCurrentUser();
        for (int i = 0; i < notificationsForCurrentUser.size(); i++) {
            NotificationEntry notificationEntry = notificationsForCurrentUser.get(i);
            notificationEntry.onDensityOrFontScaleChanged();
            if (notificationEntry.areGutsExposed()) {
                this.mGutsManager.onDensityOrFontScaleChanged(notificationEntry);
            }
        }
    }

    public boolean isCollapsing() {
        return this.mNotificationPanel.isCollapsing() || this.mActivityLaunchAnimator.isAnimationPending() || this.mActivityLaunchAnimator.isAnimationRunning();
    }

    /* access modifiers changed from: private */
    public void maybeEndAmbientPulse() {
        if (this.mNotificationPanel.hasPulsingNotifications() && !this.mHeadsUpManager.hasNotifications()) {
            this.mDozeScrimController.pulseOutNow();
        }
    }

    public void updateNotificationViews() {
        if (this.mScrimController != null) {
            if (isCollapsing()) {
                this.mShadeController.addPostCollapseAction(new Runnable() {
                    public final void run() {
                        StatusBarNotificationPresenter.this.updateNotificationViews();
                    }
                });
                return;
            }
            this.mViewHierarchyManager.updateNotificationViews();
            this.mNotificationPanel.updateNotificationViews();
        }
    }

    public void onNotificationRemoved(String str, StatusBarNotification statusBarNotification) {
        Ticker ticker;
        if (statusBarNotification != null) {
            StatusBar statusBar = this.mStatusBar;
            if (!(statusBar == null || (ticker = statusBar.mTicker) == null || !statusBar.mTickerEnabled)) {
                try {
                    ticker.removeEntry(statusBarNotification);
                } catch (Exception unused) {
                }
            }
            if (!hasActiveNotifications() && !this.mNotificationPanel.isTracking() && !this.mNotificationPanel.isQsExpanded()) {
                if (this.mStatusBarStateController.getState() == 0) {
                    this.mCommandQueue.animateCollapsePanels();
                } else if (this.mStatusBarStateController.getState() == 2 && !isCollapsing()) {
                    this.mShadeController.goToKeyguard();
                }
            }
        }
        this.mShadeController.updateAreThereNotifications();
    }

    public boolean hasActiveNotifications() {
        return !this.mEntryManager.getNotificationData().getActiveNotifications().isEmpty();
    }

    public boolean canHeadsUp(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification) {
        List<ActivityManager.RunningTaskInfo> runningTasks = this.mAm.getRunningTasks(1);
        if ((runningTasks != null && !runningTasks.isEmpty() && isPackageInStoplist(runningTasks.get(0).topActivity.getPackageName()) && !isDialerApp(statusBarNotification.getPackageName())) || isPackageBlacklisted(statusBarNotification.getPackageName())) {
            return false;
        }
        if (this.mShadeController.isOccluded()) {
            NotificationLockscreenUserManager notificationLockscreenUserManager = this.mLockscreenUserManager;
            boolean z = notificationLockscreenUserManager.isLockscreenPublicMode(notificationLockscreenUserManager.getCurrentUserId()) || this.mLockscreenUserManager.isLockscreenPublicMode(statusBarNotification.getUserId());
            boolean needsRedaction = this.mLockscreenUserManager.needsRedaction(notificationEntry);
            if (z && needsRedaction) {
                return false;
            }
        }
        if (!this.mCommandQueue.panelsEnabled()) {
            return false;
        }
        if (statusBarNotification.getNotification().fullScreenIntent == null) {
            return true;
        }
        if (this.mAccessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        if (!this.mKeyguardMonitor.isShowing() || this.mShadeController.isOccluded()) {
            return true;
        }
        return false;
    }

    private boolean isPackageInStoplist(String str) {
        return this.mStoplist.contains(str);
    }

    private boolean isPackageBlacklisted(String str) {
        return this.mBlacklist.contains(str);
    }

    private boolean isDialerApp(String str) {
        return str.equals("com.android.dialer") || str.equals("com.google.android.dialer");
    }

    private void splitAndAddToArrayList(ArrayList<String> arrayList, String str, String str2) {
        arrayList.clear();
        if (str != null) {
            for (String trim : TextUtils.split(str, str2)) {
                arrayList.add(trim.trim());
            }
        }
    }

    public void setHeadsUpStoplist() {
        splitAndAddToArrayList(this.mStoplist, Settings.System.getString(this.mContext.getContentResolver(), "heads_up_stoplist_values"), "\\|");
    }

    public void setHeadsUpBlacklist() {
        splitAndAddToArrayList(this.mBlacklist, Settings.System.getString(this.mContext.getContentResolver(), "heads_up_blacklist_values"), "\\|");
    }

    public void onUserSwitched(int i) {
        this.mHeadsUpManager.setUser(i);
        this.mCommandQueue.animateCollapsePanels();
        if (this.mReinflateNotificationsOnUserSwitched) {
            updateNotificationsOnDensityOrFontScaleChanged();
            this.mReinflateNotificationsOnUserSwitched = false;
        }
        if (this.mDispatchUiModeChangeOnUserSwitched) {
            updateNotificationOnUiModeChanged();
            this.mDispatchUiModeChangeOnUserSwitched = false;
        }
        updateNotificationViews();
        this.mMediaManager.clearCurrentMediaNotification();
        this.mShadeController.setLockscreenUser(i);
        updateMediaMetaData(true, false);
    }

    public void onBindRow(NotificationEntry notificationEntry, PackageManager packageManager, StatusBarNotification statusBarNotification, ExpandableNotificationRow expandableNotificationRow) {
        expandableNotificationRow.setAboveShelfChangedListener(this.mAboveShelfObserver);
        UnlockMethodCache unlockMethodCache = this.mUnlockMethodCache;
        Objects.requireNonNull(unlockMethodCache);
        expandableNotificationRow.setSecureStateProvider(new BooleanSupplier() {
            public final boolean getAsBoolean() {
                return UnlockMethodCache.this.canSkipBouncer();
            }
        });
    }

    public boolean isPresenterFullyCollapsed() {
        return this.mNotificationPanel.isFullyCollapsed();
    }

    public void onActivated(ActivatableNotificationView activatableNotificationView) {
        onActivated();
        if (activatableNotificationView != null) {
            this.mNotificationPanel.setActivatedChild(activatableNotificationView);
        }
    }

    public void onActivated() {
        this.mLockscreenGestureLogger.write(192, 0, 0);
        this.mNotificationPanel.showTransientIndication(C1784R$string.notification_tap_again);
        ActivatableNotificationView activatedChild = this.mNotificationPanel.getActivatedChild();
        if (activatedChild != null) {
            activatedChild.makeInactive(true);
        }
    }

    public void onActivationReset(ActivatableNotificationView activatableNotificationView) {
        if (activatableNotificationView == this.mNotificationPanel.getActivatedChild()) {
            this.mNotificationPanel.setActivatedChild((ActivatableNotificationView) null);
            this.mShadeController.onActivationReset();
        }
    }

    public void updateMediaMetaData(boolean z, boolean z2) {
        this.mMediaManager.updateMediaMetaData(z, z2);
    }

    public int getMaxNotificationsWhileLocked(boolean z) {
        if (!z) {
            return this.mMaxKeyguardNotifications;
        }
        this.mMaxKeyguardNotifications = Math.max(1, this.mNotificationPanel.computeMaxKeyguardNotifications(this.mMaxAllowedKeyguardNotifications));
        return this.mMaxKeyguardNotifications;
    }

    public void onUpdateRowStates() {
        this.mNotificationPanel.onUpdateRowStates();
    }

    public void onExpandClicked(NotificationEntry notificationEntry, boolean z) {
        this.mHeadsUpManager.setExpanded(notificationEntry, z);
        if (!z) {
            return;
        }
        if (this.mStatusBarStateController.getState() == 1) {
            this.mShadeController.goToLockedShade(notificationEntry.getRow());
        } else if (notificationEntry.isSensitive() && this.mDynamicPrivacyController.isInLockedDownShade()) {
            this.mStatusBarStateController.setLeaveOpenOnKeyguardHide(true);
            this.mActivityStarter.dismissKeyguardThenExecute(C1306xc0ee9f6.INSTANCE, (Runnable) null, false);
        }
    }

    public boolean isDeviceInVrMode() {
        return this.mVrMode;
    }

    /* access modifiers changed from: private */
    public void onLockedNotificationImportanceChange(ActivityStarter.OnDismissAction onDismissAction) {
        this.mStatusBarStateController.setLeaveOpenOnKeyguardHide(true);
        this.mActivityStarter.dismissKeyguardThenExecute(onDismissAction, (Runnable) null, true);
    }
}
