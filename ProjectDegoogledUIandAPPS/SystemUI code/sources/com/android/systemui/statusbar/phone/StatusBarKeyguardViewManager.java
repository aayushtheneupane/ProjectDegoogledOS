package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.StatsLog;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.WindowManagerGlobal;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.ViewMediatorCallback;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dependency;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.dock.DockManager;
import com.android.systemui.keyguard.DismissCallbackRegistry;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.ViewGroupFadeHelper;
import com.android.systemui.statusbar.phone.KeyguardBouncer;
import com.android.systemui.statusbar.phone.NavigationModeController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.KeyguardMonitorImpl;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StatusBarKeyguardViewManager implements RemoteInputController.Callback, StatusBarStateController.StateListener, ConfigurationController.ConfigurationListener, PanelExpansionListener, NavigationModeController.ModeChangedListener {
    private ActivityStarter.OnDismissAction mAfterKeyguardGoneAction;
    private final ArrayList<Runnable> mAfterKeyguardGoneRunnables = new ArrayList<>();
    private BiometricUnlockController mBiometricUnlockController;
    protected KeyguardBouncer mBouncer;
    private KeyguardBypassController mBypassController;
    /* access modifiers changed from: private */
    public ViewGroup mContainer;
    protected final Context mContext;
    private final DockManager.DockEventListener mDockEventListener = new DockManager.DockEventListener() {
    };
    private final DockManager mDockManager;
    private boolean mDozing;
    private final KeyguardBouncer.BouncerExpansionCallback mExpansionCallback = new KeyguardBouncer.BouncerExpansionCallback() {
        public void onFullyShown() {
            StatusBarKeyguardViewManager.this.updateStates();
            StatusBarKeyguardViewManager.this.mStatusBar.wakeUpIfDozing(SystemClock.uptimeMillis(), StatusBarKeyguardViewManager.this.mContainer, "BOUNCER_VISIBLE");
            StatusBarKeyguardViewManager.this.updateLockIcon();
            StatusBarKeyguardViewManager.this.onKeyguardBouncerFullyShownChanged(true);
        }

        public void onStartingToHide() {
            StatusBarKeyguardViewManager.this.updateStates();
        }

        public void onStartingToShow() {
            StatusBarKeyguardViewManager.this.updateStates();
            StatusBarKeyguardViewManager.this.updateLockIcon();
        }

        public void onFullyHidden() {
            StatusBarKeyguardViewManager.this.updateStates();
            StatusBarKeyguardViewManager.this.updateLockIcon();
            StatusBarKeyguardViewManager.this.onKeyguardBouncerFullyShownChanged(false);
        }
    };
    protected boolean mFirstUpdate = true;
    private boolean mGesturalNav;
    private boolean mGoingToSleepVisibleNotOccluded;
    private boolean mIsDocked;
    private Runnable mKeyguardGoneCancelAction;
    private final KeyguardMonitorImpl mKeyguardMonitor = ((KeyguardMonitorImpl) Dependency.get(KeyguardMonitor.class));
    private int mLastBiometricMode;
    private boolean mLastBouncerDismissible;
    private boolean mLastBouncerInTransit;
    private boolean mLastBouncerShowing;
    private boolean mLastDozing;
    private boolean mLastGesturalNav;
    private boolean mLastIsDocked;
    private boolean mLastLockVisible;
    protected boolean mLastOccluded;
    private boolean mLastPulsing;
    protected boolean mLastRemoteInputActive;
    protected boolean mLastShowing;
    private ViewGroup mLockIconContainer;
    protected LockPatternUtils mLockPatternUtils;
    private Runnable mMakeNavigationBarVisibleRunnable = new Runnable() {
        public void run() {
            StatusBarKeyguardViewManager.this.mStatusBar.getNavigationBarView().getRootView().setVisibility(0);
        }
    };
    private final NotificationMediaManager mMediaManager = ((NotificationMediaManager) Dependency.get(NotificationMediaManager.class));
    private View mNotificationContainer;
    private NotificationPanelView mNotificationPanelView;
    protected boolean mOccluded;
    private DismissWithActionRequest mPendingWakeupAction;
    private boolean mPulsing;
    protected boolean mRemoteInputActive;
    protected boolean mShowing;
    protected StatusBar mStatusBar;
    private final SysuiStatusBarStateController mStatusBarStateController = ((SysuiStatusBarStateController) Dependency.get(StatusBarStateController.class));
    /* access modifiers changed from: private */
    public final StatusBarWindowController mStatusBarWindowController;
    private final KeyguardUpdateMonitorCallback mUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() {
        public void onEmergencyCallAction() {
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = StatusBarKeyguardViewManager.this;
            if (statusBarKeyguardViewManager.mOccluded) {
                statusBarKeyguardViewManager.reset(true);
            }
        }
    };
    protected ViewMediatorCallback mViewMediatorCallback;

    public void onCancelClicked() {
    }

    public void onScreenTurnedOn() {
    }

    public void onScreenTurningOn() {
    }

    public void onStartedWakingUp() {
    }

    /* access modifiers changed from: protected */
    public boolean shouldDestroyViewOnReset() {
        return false;
    }

    public StatusBarKeyguardViewManager(Context context, ViewMediatorCallback viewMediatorCallback, LockPatternUtils lockPatternUtils) {
        this.mContext = context;
        this.mViewMediatorCallback = viewMediatorCallback;
        this.mLockPatternUtils = lockPatternUtils;
        this.mStatusBarWindowController = (StatusBarWindowController) Dependency.get(StatusBarWindowController.class);
        KeyguardUpdateMonitor.getInstance(context).registerCallback(this.mUpdateMonitorCallback);
        this.mStatusBarStateController.addCallback(this);
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
        this.mGesturalNav = QuickStepContract.isGesturalMode(((NavigationModeController) Dependency.get(NavigationModeController.class)).addListener(this));
        this.mDockManager = (DockManager) Dependency.get(DockManager.class);
        DockManager dockManager = this.mDockManager;
        if (dockManager != null) {
            dockManager.addListener(this.mDockEventListener);
            this.mIsDocked = this.mDockManager.isDocked();
        }
    }

    public void registerStatusBar(StatusBar statusBar, ViewGroup viewGroup, NotificationPanelView notificationPanelView, BiometricUnlockController biometricUnlockController, DismissCallbackRegistry dismissCallbackRegistry, ViewGroup viewGroup2, View view, KeyguardBypassController keyguardBypassController, FalsingManager falsingManager) {
        NotificationPanelView notificationPanelView2 = notificationPanelView;
        this.mStatusBar = statusBar;
        this.mContainer = viewGroup;
        this.mLockIconContainer = viewGroup2;
        ViewGroup viewGroup3 = this.mLockIconContainer;
        if (viewGroup3 != null) {
            this.mLastLockVisible = viewGroup3.getVisibility() == 0;
        }
        this.mBiometricUnlockController = biometricUnlockController;
        this.mBouncer = SystemUIFactory.getInstance().createKeyguardBouncer(this.mContext, this.mViewMediatorCallback, this.mLockPatternUtils, viewGroup, dismissCallbackRegistry, this.mExpansionCallback, falsingManager, keyguardBypassController);
        this.mNotificationPanelView = notificationPanelView2;
        notificationPanelView.addExpansionListener(this);
        this.mBypassController = keyguardBypassController;
        this.mNotificationContainer = view;
    }

    public void onPanelExpansionChanged(float f, boolean z) {
        if (this.mNotificationPanelView.isUnlockHintRunning()) {
            this.mBouncer.setExpansion(1.0f);
        } else if (bouncerNeedsScrimming()) {
            this.mBouncer.setExpansion(0.0f);
        } else if (this.mShowing) {
            if (!isWakeAndUnlocking() && !this.mStatusBar.isInLaunchTransition()) {
                this.mBouncer.setExpansion(f);
            }
            if (f != 1.0f && z && this.mStatusBar.isKeyguardCurrentlySecure() && !this.mBouncer.isShowing() && !this.mBouncer.isAnimatingAway()) {
                this.mBouncer.show(false, false);
            }
        } else if (this.mPulsing && f == 0.0f) {
            this.mStatusBar.wakeUpIfDozing(SystemClock.uptimeMillis(), this.mContainer, "BOUNCER_VISIBLE");
        }
    }

    public void onQsExpansionChanged(float f) {
        updateLockIcon();
    }

    /* access modifiers changed from: private */
    public void updateLockIcon() {
        if (this.mLockIconContainer != null) {
            boolean z = true;
            boolean z2 = this.mStatusBarStateController.getState() == 1 && !this.mNotificationPanelView.isQsExpanded();
            if ((!this.mBouncer.isShowing() && !z2) || this.mBouncer.isAnimatingAway() || this.mKeyguardMonitor.isKeyguardFadingAway()) {
                z = false;
            }
            if (this.mLastLockVisible != z) {
                this.mLastLockVisible = z;
                if (z) {
                    CrossFadeHelper.fadeIn((View) this.mLockIconContainer, 220, 0);
                } else {
                    CrossFadeHelper.fadeOut(this.mLockIconContainer, needsBypassFading() ? 67 : 110, 0, (Runnable) null);
                }
            }
        }
    }

    public void show(Bundle bundle) {
        this.mShowing = true;
        this.mStatusBarWindowController.setKeyguardShowing(true);
        KeyguardMonitorImpl keyguardMonitorImpl = this.mKeyguardMonitor;
        keyguardMonitorImpl.notifyKeyguardState(this.mShowing, keyguardMonitorImpl.isSecure(), this.mKeyguardMonitor.isOccluded());
        reset(true);
        StatsLog.write(62, 2);
    }

    /* access modifiers changed from: protected */
    public void showBouncerOrKeyguard(boolean z) {
        if (!this.mBouncer.needsFullscreenBouncer() || this.mDozing) {
            this.mStatusBar.showKeyguard();
            if (z) {
                hideBouncer(shouldDestroyViewOnReset());
                this.mBouncer.prepare();
            }
        } else {
            this.mStatusBar.hideKeyguard();
            this.mBouncer.show(true);
        }
        updateStates();
    }

    /* access modifiers changed from: package-private */
    public void hideBouncer(boolean z) {
        if (this.mBouncer != null) {
            if (this.mShowing) {
                this.mAfterKeyguardGoneAction = null;
                Runnable runnable = this.mKeyguardGoneCancelAction;
                if (runnable != null) {
                    runnable.run();
                    this.mKeyguardGoneCancelAction = null;
                }
            }
            this.mBouncer.hide(z);
            cancelPendingWakeupAction();
        }
    }

    public void showBouncer(boolean z) {
        if (this.mShowing && !this.mBouncer.isShowing()) {
            this.mBouncer.show(false, z);
        }
        updateStates();
    }

    public void dismissWithAction(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z) {
        dismissWithAction(onDismissAction, runnable, z, (String) null);
    }

    public void dismissWithAction(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z, String str) {
        if (this.mShowing) {
            cancelPendingWakeupAction();
            if (this.mDozing && !isWakeAndUnlocking()) {
                this.mPendingWakeupAction = new DismissWithActionRequest(onDismissAction, runnable, z, str);
                return;
            } else if (!z) {
                this.mBouncer.showWithDismissAction(onDismissAction, runnable);
            } else {
                this.mAfterKeyguardGoneAction = onDismissAction;
                this.mKeyguardGoneCancelAction = runnable;
                this.mBouncer.show(false);
            }
        }
        updateStates();
    }

    private boolean isWakeAndUnlocking() {
        int mode = this.mBiometricUnlockController.getMode();
        return mode == 1 || mode == 2;
    }

    public void addAfterKeyguardGoneRunnable(Runnable runnable) {
        this.mAfterKeyguardGoneRunnables.add(runnable);
    }

    public void reset(boolean z) {
        if (this.mShowing) {
            if (!this.mOccluded || this.mDozing) {
                showBouncerOrKeyguard(z);
            } else {
                this.mStatusBar.hideKeyguard();
                if (z || this.mBouncer.needsFullscreenBouncer()) {
                    hideBouncer(false);
                }
            }
            KeyguardUpdateMonitor.getInstance(this.mContext).sendKeyguardReset();
            updateStates();
        }
    }

    public boolean isGoingToSleepVisibleNotOccluded() {
        return this.mGoingToSleepVisibleNotOccluded;
    }

    public void onStartedGoingToSleep() {
        this.mGoingToSleepVisibleNotOccluded = isShowing() && !isOccluded();
    }

    public void onFinishedGoingToSleep() {
        this.mGoingToSleepVisibleNotOccluded = false;
        this.mBouncer.onScreenTurnedOff();
    }

    public void onRemoteInputActive(boolean z) {
        this.mRemoteInputActive = z;
        updateStates();
    }

    private void setDozing(boolean z) {
        if (this.mDozing != z) {
            this.mDozing = z;
            if (z || this.mBouncer.needsFullscreenBouncer() || this.mOccluded) {
                reset(z);
            }
            updateStates();
            if (!z) {
                launchPendingWakeupAction();
            }
        }
    }

    public void setPulsing(boolean z) {
        if (this.mPulsing != z) {
            this.mPulsing = z;
            updateStates();
        }
    }

    public void setNeedsInput(boolean z) {
        this.mStatusBarWindowController.setKeyguardNeedsInput(z);
    }

    public boolean isUnlockWithWallpaper() {
        return this.mStatusBarWindowController.isShowingWallpaper();
    }

    public void setOccluded(boolean z, boolean z2) {
        this.mStatusBar.setOccluded(z);
        boolean z3 = true;
        if (z && !this.mOccluded && this.mShowing) {
            StatsLog.write(62, 3);
            if (this.mStatusBar.isInLaunchTransition()) {
                this.mOccluded = true;
                this.mStatusBar.fadeKeyguardAfterLaunchTransition((Runnable) null, new Runnable() {
                    public void run() {
                        StatusBarKeyguardViewManager.this.mStatusBarWindowController.setKeyguardOccluded(StatusBarKeyguardViewManager.this.mOccluded);
                        StatusBarKeyguardViewManager.this.reset(true);
                    }
                });
                return;
            }
        } else if (!z && this.mOccluded && this.mShowing) {
            StatsLog.write(62, 2);
        }
        boolean z4 = !this.mOccluded && z;
        this.mOccluded = z;
        if (this.mShowing) {
            NotificationMediaManager notificationMediaManager = this.mMediaManager;
            if (!z2 || z) {
                z3 = false;
            }
            notificationMediaManager.updateMediaMetaData(false, z3);
        }
        this.mStatusBarWindowController.setKeyguardOccluded(z);
        if (!this.mDozing) {
            reset(z4);
        }
        if (z2 && !z && this.mShowing && !this.mBouncer.isShowing()) {
            this.mStatusBar.animateKeyguardUnoccluding();
        }
    }

    public boolean isOccluded() {
        return this.mOccluded;
    }

    public void startPreHideAnimation(Runnable runnable) {
        if (this.mBouncer.isShowing()) {
            this.mBouncer.startPreHideAnimation(runnable);
            this.mNotificationPanelView.onBouncerPreHideAnimation();
        } else if (runnable != null) {
            runnable.run();
        }
        this.mNotificationPanelView.blockExpansionForCurrentTouch();
        updateLockIcon();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void hide(long r19, long r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = 0
            r0.mShowing = r1
            com.android.systemui.statusbar.policy.KeyguardMonitorImpl r2 = r0.mKeyguardMonitor
            boolean r3 = r0.mShowing
            boolean r4 = r2.isSecure()
            com.android.systemui.statusbar.policy.KeyguardMonitorImpl r5 = r0.mKeyguardMonitor
            boolean r5 = r5.isOccluded()
            r2.notifyKeyguardState(r3, r4, r5)
            r18.launchPendingWakeupAction()
            android.content.Context r2 = r0.mContext
            com.android.keyguard.KeyguardUpdateMonitor r2 = com.android.keyguard.KeyguardUpdateMonitor.getInstance(r2)
            boolean r2 = r2.needsSlowUnlockTransition()
            if (r2 == 0) goto L_0x0028
            r2 = 2000(0x7d0, double:9.88E-321)
            goto L_0x002a
        L_0x0028:
            r2 = r21
        L_0x002a:
            long r4 = android.os.SystemClock.uptimeMillis()
            r6 = -48
            long r6 = r19 + r6
            long r6 = r6 - r4
            r4 = 0
            long r6 = java.lang.Math.max(r4, r6)
            com.android.systemui.statusbar.phone.StatusBar r8 = r0.mStatusBar
            boolean r8 = r8.isInLaunchTransition()
            r15 = 1
            if (r8 == 0) goto L_0x0054
            com.android.systemui.statusbar.phone.StatusBar r1 = r0.mStatusBar
            com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$5 r2 = new com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$5
            r2.<init>()
            com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$6 r3 = new com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$6
            r3.<init>()
            r1.fadeKeyguardAfterLaunchTransition(r2, r3)
            r4 = r15
            goto L_0x00eb
        L_0x0054:
            r18.executeAfterKeyguardGoneAction()
            com.android.systemui.statusbar.phone.BiometricUnlockController r8 = r0.mBiometricUnlockController
            int r8 = r8.getMode()
            r9 = 2
            if (r8 != r9) goto L_0x0063
            r16 = r15
            goto L_0x0065
        L_0x0063:
            r16 = r1
        L_0x0065:
            boolean r17 = r18.needsBypassFading()
            if (r17 == 0) goto L_0x006f
            r2 = 67
        L_0x006d:
            r11 = r4
            goto L_0x0075
        L_0x006f:
            if (r16 == 0) goto L_0x0074
            r2 = 240(0xf0, double:1.186E-321)
            goto L_0x006d
        L_0x0074:
            r11 = r6
        L_0x0075:
            com.android.systemui.statusbar.phone.StatusBar r8 = r0.mStatusBar
            r9 = r19
            r13 = r2
            r4 = r15
            r15 = r17
            r8.setKeyguardFadingAway(r9, r11, r13, r15)
            com.android.systemui.statusbar.phone.BiometricUnlockController r5 = r0.mBiometricUnlockController
            r5.startKeyguardFadingAway()
            r0.hideBouncer(r4)
            if (r16 == 0) goto L_0x00a2
            if (r17 == 0) goto L_0x0099
            com.android.systemui.statusbar.phone.NotificationPanelView r5 = r0.mNotificationPanelView
            android.view.View r6 = r0.mNotificationContainer
            com.android.systemui.statusbar.phone.-$$Lambda$StatusBarKeyguardViewManager$aIusP5sgaSr59XXK3nFh48FBNI4 r7 = new com.android.systemui.statusbar.phone.-$$Lambda$StatusBarKeyguardViewManager$aIusP5sgaSr59XXK3nFh48FBNI4
            r7.<init>()
            com.android.systemui.statusbar.notification.ViewGroupFadeHelper.fadeOutAllChildrenExcept(r5, r6, r2, r7)
            goto L_0x009e
        L_0x0099:
            com.android.systemui.statusbar.phone.StatusBar r2 = r0.mStatusBar
            r2.fadeKeyguardWhilePulsing()
        L_0x009e:
            r18.wakeAndUnlockDejank()
            goto L_0x00db
        L_0x00a2:
            com.android.systemui.statusbar.SysuiStatusBarStateController r5 = r0.mStatusBarStateController
            boolean r5 = r5.leaveOpenOnKeyguardHide()
            if (r5 != 0) goto L_0x00cc
            com.android.systemui.statusbar.phone.StatusBarWindowController r5 = r0.mStatusBarWindowController
            r5.setKeyguardFadingAway(r4)
            if (r17 == 0) goto L_0x00be
            com.android.systemui.statusbar.phone.NotificationPanelView r5 = r0.mNotificationPanelView
            android.view.View r6 = r0.mNotificationContainer
            com.android.systemui.statusbar.phone.-$$Lambda$StatusBarKeyguardViewManager$EJI38cHcIk60L5eHmdpMvFRistw r7 = new com.android.systemui.statusbar.phone.-$$Lambda$StatusBarKeyguardViewManager$EJI38cHcIk60L5eHmdpMvFRistw
            r7.<init>()
            com.android.systemui.statusbar.notification.ViewGroupFadeHelper.fadeOutAllChildrenExcept(r5, r6, r2, r7)
            goto L_0x00c3
        L_0x00be:
            com.android.systemui.statusbar.phone.StatusBar r2 = r0.mStatusBar
            r2.hideKeyguard()
        L_0x00c3:
            com.android.systemui.statusbar.phone.StatusBar r2 = r0.mStatusBar
            r2.updateScrimController()
            r18.wakeAndUnlockDejank()
            goto L_0x00db
        L_0x00cc:
            com.android.systemui.statusbar.phone.StatusBar r2 = r0.mStatusBar
            r2.hideKeyguard()
            com.android.systemui.statusbar.phone.StatusBar r2 = r0.mStatusBar
            r2.finishKeyguardFadingAway()
            com.android.systemui.statusbar.phone.BiometricUnlockController r2 = r0.mBiometricUnlockController
            r2.finishKeyguardFadingAway()
        L_0x00db:
            r18.updateLockIcon()
            r18.updateStates()
            com.android.systemui.statusbar.phone.StatusBarWindowController r2 = r0.mStatusBarWindowController
            r2.setKeyguardShowing(r1)
            com.android.keyguard.ViewMediatorCallback r0 = r0.mViewMediatorCallback
            r0.keyguardGone()
        L_0x00eb:
            r0 = 62
            android.util.StatsLog.write(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager.hide(long, long):void");
    }

    public /* synthetic */ void lambda$hide$0$StatusBarKeyguardViewManager() {
        this.mStatusBar.hideKeyguard();
        onKeyguardFadedAway();
    }

    public /* synthetic */ void lambda$hide$1$StatusBarKeyguardViewManager() {
        this.mStatusBar.hideKeyguard();
    }

    private boolean needsBypassFading() {
        if ((this.mBiometricUnlockController.getMode() == 7 || this.mBiometricUnlockController.getMode() == 2 || this.mBiometricUnlockController.getMode() == 1) && this.mBypassController.getBypassEnabled()) {
            return true;
        }
        return false;
    }

    public void onDensityOrFontScaleChanged() {
        hideBouncer(true);
    }

    public void onNavigationModeChanged(int i) {
        boolean isGesturalMode = QuickStepContract.isGesturalMode(i);
        if (isGesturalMode != this.mGesturalNav) {
            this.mGesturalNav = isGesturalMode;
            updateStates();
        }
    }

    public void onThemeChanged() {
        hideBouncer(true);
        this.mBouncer.prepare();
    }

    public /* synthetic */ void lambda$onKeyguardFadedAway$2$StatusBarKeyguardViewManager() {
        this.mStatusBarWindowController.setKeyguardFadingAway(false);
    }

    public void onKeyguardFadedAway() {
        this.mContainer.postDelayed(new Runnable() {
            public final void run() {
                StatusBarKeyguardViewManager.this.lambda$onKeyguardFadedAway$2$StatusBarKeyguardViewManager();
            }
        }, 100);
        ViewGroupFadeHelper.reset(this.mNotificationPanelView);
        this.mStatusBar.finishKeyguardFadingAway();
        this.mBiometricUnlockController.finishKeyguardFadingAway();
        WindowManagerGlobal.getInstance().trimMemory(20);
    }

    private void wakeAndUnlockDejank() {
        if (this.mBiometricUnlockController.getMode() == 1 && LatencyTracker.isEnabled(this.mContext)) {
            DejankUtils.postAfterTraversal(new Runnable() {
                public final void run() {
                    StatusBarKeyguardViewManager.this.lambda$wakeAndUnlockDejank$3$StatusBarKeyguardViewManager();
                }
            });
        }
    }

    public /* synthetic */ void lambda$wakeAndUnlockDejank$3$StatusBarKeyguardViewManager() {
        LatencyTracker.getInstance(this.mContext).onActionEnd(2);
    }

    /* access modifiers changed from: private */
    public void executeAfterKeyguardGoneAction() {
        ActivityStarter.OnDismissAction onDismissAction = this.mAfterKeyguardGoneAction;
        if (onDismissAction != null) {
            onDismissAction.onDismiss();
            this.mAfterKeyguardGoneAction = null;
        }
        this.mKeyguardGoneCancelAction = null;
        for (int i = 0; i < this.mAfterKeyguardGoneRunnables.size(); i++) {
            this.mAfterKeyguardGoneRunnables.get(i).run();
        }
        this.mAfterKeyguardGoneRunnables.clear();
    }

    public void dismissAndCollapse() {
        this.mStatusBar.executeRunnableDismissingKeyguard((Runnable) null, (Runnable) null, true, false, true);
    }

    public boolean isSecure() {
        return this.mBouncer.isSecure();
    }

    public boolean isShowing() {
        return this.mShowing;
    }

    public boolean onBackPressed(boolean z) {
        if (!this.mBouncer.isShowing()) {
            return false;
        }
        this.mStatusBar.endAffordanceLaunch();
        if (!this.mBouncer.isScrimmed() || this.mBouncer.needsFullscreenBouncer()) {
            reset(z);
            return true;
        }
        this.mStatusBar.showKeyguardImpl();
        hideBouncer(false);
        updateStates();
        return true;
    }

    public boolean isBouncerShowing() {
        return this.mBouncer.isShowing();
    }

    public boolean bouncerIsOrWillBeShowing() {
        return this.mBouncer.isShowing() || this.mBouncer.inTransit();
    }

    private long getNavBarShowDelay() {
        if (this.mKeyguardMonitor.isKeyguardFadingAway()) {
            return this.mKeyguardMonitor.getKeyguardFadingAwayDelay();
        }
        return this.mBouncer.isShowing() ? 320 : 0;
    }

    /* access modifiers changed from: private */
    public void onKeyguardBouncerFullyShownChanged(boolean z) {
        KeyguardUpdateMonitor.getInstance(this.mContext).onKeyguardBouncerFullyShown(z);
    }

    /* access modifiers changed from: protected */
    public void updateStates() {
        int systemUiVisibility = this.mContainer.getSystemUiVisibility();
        boolean z = this.mShowing;
        boolean z2 = this.mOccluded;
        boolean isShowing = this.mBouncer.isShowing();
        boolean inTransit = this.mBouncer.inTransit();
        boolean z3 = true;
        boolean z4 = !this.mBouncer.isFullscreenBouncer();
        boolean z5 = this.mRemoteInputActive;
        if ((z4 || !z || z5) != (this.mLastBouncerDismissible || !this.mLastShowing || this.mLastRemoteInputActive) || this.mFirstUpdate) {
            if (z4 || !z || z5) {
                this.mContainer.setSystemUiVisibility(systemUiVisibility & -4194305);
            } else {
                this.mContainer.setSystemUiVisibility(systemUiVisibility | 4194304);
            }
        }
        boolean isNavBarVisible = isNavBarVisible();
        if (isNavBarVisible != getLastNavBarVisible() || this.mFirstUpdate) {
            updateNavigationBarVisibility(isNavBarVisible);
        }
        this.mLockIconContainer.setVisibility((!this.mLastLockVisible || !this.mDozing) ? 0 : 8);
        if (isShowing != this.mLastBouncerShowing || this.mFirstUpdate) {
            this.mStatusBarWindowController.setBouncerShowing(isShowing);
            this.mStatusBar.setBouncerShowing(isShowing);
        }
        KeyguardUpdateMonitor instance = KeyguardUpdateMonitor.getInstance(this.mContext);
        if ((z && !z2) != (this.mLastShowing && !this.mLastOccluded) || this.mFirstUpdate) {
            instance.onKeyguardVisibilityChanged(z && !z2);
        }
        boolean z6 = isShowing || inTransit;
        if (!this.mLastBouncerShowing && !this.mLastBouncerInTransit) {
            z3 = false;
        }
        if (z6 != z3 || this.mFirstUpdate) {
            instance.sendKeyguardBouncerChanged(z6);
        }
        this.mFirstUpdate = false;
        this.mLastShowing = z;
        this.mLastOccluded = z2;
        this.mLastBouncerShowing = isShowing;
        this.mLastBouncerInTransit = inTransit;
        this.mLastBouncerDismissible = z4;
        this.mLastRemoteInputActive = z5;
        this.mLastDozing = this.mDozing;
        this.mLastPulsing = this.mPulsing;
        this.mLastBiometricMode = this.mBiometricUnlockController.getMode();
        this.mLastGesturalNav = this.mGesturalNav;
        this.mLastIsDocked = this.mIsDocked;
        this.mStatusBar.onKeyguardViewManagerStatesUpdated();
    }

    /* access modifiers changed from: protected */
    public void updateNavigationBarVisibility(boolean z) {
        if (this.mStatusBar.getNavigationBarView() == null) {
            return;
        }
        if (z) {
            long navBarShowDelay = getNavBarShowDelay();
            if (navBarShowDelay == 0) {
                this.mMakeNavigationBarVisibleRunnable.run();
            } else {
                this.mContainer.postOnAnimationDelayed(this.mMakeNavigationBarVisibleRunnable, navBarShowDelay);
            }
        } else {
            this.mContainer.removeCallbacks(this.mMakeNavigationBarVisibleRunnable);
            this.mStatusBar.getNavigationBarView().getRootView().setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isNavBarVisible() {
        int mode = this.mBiometricUnlockController.getMode();
        boolean z = this.mShowing && !this.mOccluded;
        boolean z2 = this.mDozing && mode != 2;
        boolean z3 = ((z && !this.mDozing) || (this.mPulsing && !this.mIsDocked)) && this.mGesturalNav;
        if ((z || z2) && !this.mBouncer.isShowing() && !this.mRemoteInputActive && !z3) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getLastNavBarVisible() {
        boolean z = this.mLastShowing && !this.mLastOccluded;
        boolean z2 = this.mLastDozing && this.mLastBiometricMode != 2;
        boolean z3 = ((z && !this.mLastDozing) || (this.mLastPulsing && !this.mLastIsDocked)) && this.mLastGesturalNav;
        if ((z || z2) && !this.mLastBouncerShowing && !this.mLastRemoteInputActive && !z3) {
            return false;
        }
        return true;
    }

    public boolean shouldDismissOnMenuPressed() {
        return this.mBouncer.shouldDismissOnMenuPressed();
    }

    public boolean interceptMediaKey(KeyEvent keyEvent) {
        return this.mBouncer.interceptMediaKey(keyEvent);
    }

    public void readyForKeyguardDone() {
        this.mViewMediatorCallback.readyForKeyguardDone();
    }

    public boolean shouldDisableWindowAnimationsForUnlock() {
        return this.mStatusBar.isInLaunchTransition();
    }

    public boolean shouldSubtleWindowAnimationsForUnlock() {
        return needsBypassFading();
    }

    public boolean isGoingToNotificationShade() {
        return ((SysuiStatusBarStateController) Dependency.get(StatusBarStateController.class)).leaveOpenOnKeyguardHide();
    }

    public void keyguardGoingAway() {
        this.mStatusBar.keyguardGoingAway();
    }

    public void animateCollapsePanels(float f) {
        this.mStatusBar.animateCollapsePanels(0, true, false, f);
    }

    public void notifyKeyguardAuthenticated(boolean z) {
        this.mBouncer.notifyKeyguardAuthenticated(z);
    }

    public void showBouncerMessage(String str, ColorStateList colorStateList) {
        this.mBouncer.showMessage(str, colorStateList);
    }

    public ViewRootImpl getViewRootImpl() {
        return this.mStatusBar.getStatusBarView().getViewRootImpl();
    }

    public void launchPendingWakeupAction() {
        DismissWithActionRequest dismissWithActionRequest = this.mPendingWakeupAction;
        this.mPendingWakeupAction = null;
        if (dismissWithActionRequest == null) {
            return;
        }
        if (this.mShowing) {
            dismissWithAction(dismissWithActionRequest.dismissAction, dismissWithActionRequest.cancelAction, dismissWithActionRequest.afterKeyguardGone, dismissWithActionRequest.message);
            return;
        }
        ActivityStarter.OnDismissAction onDismissAction = dismissWithActionRequest.dismissAction;
        if (onDismissAction != null) {
            onDismissAction.onDismiss();
        }
    }

    public void cancelPendingWakeupAction() {
        Runnable runnable;
        DismissWithActionRequest dismissWithActionRequest = this.mPendingWakeupAction;
        this.mPendingWakeupAction = null;
        if (dismissWithActionRequest != null && (runnable = dismissWithActionRequest.cancelAction) != null) {
            runnable.run();
        }
    }

    public boolean bouncerNeedsScrimming() {
        return this.mOccluded || this.mBouncer.willDismissWithAction() || this.mStatusBar.isFullScreenUserSwitcherState() || (this.mBouncer.isShowing() && this.mBouncer.isScrimmed()) || this.mBouncer.isFullscreenBouncer();
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("StatusBarKeyguardViewManager:");
        printWriter.println("  mShowing: " + this.mShowing);
        printWriter.println("  mOccluded: " + this.mOccluded);
        printWriter.println("  mRemoteInputActive: " + this.mRemoteInputActive);
        printWriter.println("  mDozing: " + this.mDozing);
        printWriter.println("  mGoingToSleepVisibleNotOccluded: " + this.mGoingToSleepVisibleNotOccluded);
        printWriter.println("  mAfterKeyguardGoneAction: " + this.mAfterKeyguardGoneAction);
        printWriter.println("  mAfterKeyguardGoneRunnables: " + this.mAfterKeyguardGoneRunnables);
        printWriter.println("  mPendingWakeupAction: " + this.mPendingWakeupAction);
        KeyguardBouncer keyguardBouncer = this.mBouncer;
        if (keyguardBouncer != null) {
            keyguardBouncer.dump(printWriter);
        }
    }

    public void onStateChanged(int i) {
        updateLockIcon();
    }

    public void onDozingChanged(boolean z) {
        setDozing(z);
    }

    private static class DismissWithActionRequest {
        final boolean afterKeyguardGone;
        final Runnable cancelAction;
        final ActivityStarter.OnDismissAction dismissAction;
        final String message;

        DismissWithActionRequest(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z, String str) {
            this.dismissAction = onDismissAction;
            this.cancelAction = runnable;
            this.afterKeyguardGone = z;
            this.message = str;
        }
    }
}
