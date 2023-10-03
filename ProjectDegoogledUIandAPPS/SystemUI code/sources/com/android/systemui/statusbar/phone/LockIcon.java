package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.biometrics.BiometricSourceType;
import android.os.Trace;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.telephony.IccCardConstants;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.C1770R$anim;
import com.android.systemui.C1772R$attr;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.dock.DockManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.KeyguardAffordanceView;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.phone.UnlockMethodCache;
import com.android.systemui.statusbar.policy.AccessibilityController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.policy.UserInfoController;
import com.android.systemui.tuner.TunerService;

public class LockIcon extends KeyguardAffordanceView implements UserInfoController.OnUserInfoChangedListener, StatusBarStateController.StateListener, ConfigurationController.ConfigurationListener, UnlockMethodCache.OnUnlockMethodChangedListener, NotificationWakeUpCoordinator.WakeUpListener, ViewTreeObserver.OnPreDrawListener, OnHeadsUpChangedListener, TunerService.Tunable {
    private static final int[][] LOCK_ANIM_RES_IDS = {new int[]{C1770R$anim.lock_to_error, C1770R$anim.lock_unlock, C1770R$anim.lock_lock, C1770R$anim.lock_scanning}, new int[]{C1770R$anim.lock_to_error_circular, C1770R$anim.lock_unlock_circular, C1770R$anim.lock_lock_circular, C1770R$anim.lock_scanning_circular}, new int[]{C1770R$anim.lock_to_error_filled, C1770R$anim.lock_unlock_filled, C1770R$anim.lock_lock_filled, C1770R$anim.lock_scanning_filled}, new int[]{C1770R$anim.lock_to_error_rounded, C1770R$anim.lock_unlock_rounded, C1770R$anim.lock_lock_rounded, C1770R$anim.lock_scanning_rounded}};
    private final AccessibilityController mAccessibilityController;
    /* access modifiers changed from: private */
    public boolean mBlockUpdates;
    private boolean mBouncerShowingScrimmed;
    private final KeyguardBypassController mBypassController;
    private final ConfigurationController mConfigurationController;
    private int mDensity;
    private final DockManager.DockEventListener mDockEventListener = new DockManager.DockEventListener() {
    };
    private final DockManager mDockManager;
    private boolean mDocked;
    private float mDozeAmount;
    private boolean mDozing;
    private boolean mForceUpdate;
    private final HeadsUpManagerPhone mHeadsUpManager;
    private boolean mHideLockIcon;
    private int mIconColor;
    private boolean mIsFaceUnlockState;
    /* access modifiers changed from: private */
    public boolean mKeyguardJustShown;
    /* access modifiers changed from: private */
    public final KeyguardMonitor mKeyguardMonitor;
    private final KeyguardMonitor.Callback mKeyguardMonitorCallback = new KeyguardMonitor.Callback() {
        public void onKeyguardShowingChanged() {
            boolean access$000 = LockIcon.this.mKeyguardShowing;
            LockIcon lockIcon = LockIcon.this;
            boolean unused = lockIcon.mKeyguardShowing = lockIcon.mKeyguardMonitor.isShowing();
            boolean z = false;
            if (!access$000 && LockIcon.this.mKeyguardShowing && LockIcon.this.mBlockUpdates) {
                boolean unused2 = LockIcon.this.mBlockUpdates = false;
                z = true;
            }
            if (!access$000 && LockIcon.this.mKeyguardShowing) {
                boolean unused3 = LockIcon.this.mKeyguardJustShown = true;
            }
            LockIcon.this.update(z);
        }

        public void onKeyguardFadingAwayChanged() {
            if (!LockIcon.this.mKeyguardMonitor.isKeyguardFadingAway() && LockIcon.this.mBlockUpdates) {
                boolean unused = LockIcon.this.mBlockUpdates = false;
                LockIcon.this.update(true);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mKeyguardShowing;
    /* access modifiers changed from: private */
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    private int mLastState = 0;
    private boolean mPulsing;
    private boolean mShowingLaunchAffordance;
    /* access modifiers changed from: private */
    public boolean mSimLocked;
    private final StatusBarStateController mStatusBarStateController;
    private boolean mTransientBiometricsError;
    private final UnlockMethodCache mUnlockMethodCache;
    private final KeyguardUpdateMonitorCallback mUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() {
        public void onSimStateChanged(int i, int i2, IccCardConstants.State state) {
            LockIcon lockIcon = LockIcon.this;
            boolean unused = lockIcon.mSimLocked = lockIcon.mKeyguardUpdateMonitor.isSimPinSecure();
            LockIcon.this.update();
        }

        public void onKeyguardVisibilityChanged(boolean z) {
            LockIcon.this.update();
        }

        public void onBiometricRunningStateChanged(boolean z, BiometricSourceType biometricSourceType) {
            LockIcon.this.update();
        }

        public void onStrongAuthStateChanged(int i) {
            LockIcon.this.update();
        }
    };
    private boolean mUpdatePending;
    private boolean mWakeAndUnlockRunning;
    private final NotificationWakeUpCoordinator mWakeUpCoordinator;

    /* access modifiers changed from: private */
    public boolean doesAnimationLoop(int i) {
        return i == 3;
    }

    private static int getAnimationIndexForTransition(int i, int i2, boolean z, boolean z2, boolean z3) {
        if (z2 && !z) {
            return -1;
        }
        if (i2 == 3) {
            return 0;
        }
        if (i != 1 && i2 == 1) {
            return 1;
        }
        if (i == 1 && i2 == 0 && !z3) {
            return 2;
        }
        return i2 == 2 ? 3 : -1;
    }

    public void setPulsing(boolean z) {
    }

    public LockIcon(Context context, AttributeSet attributeSet, StatusBarStateController statusBarStateController, ConfigurationController configurationController, AccessibilityController accessibilityController, KeyguardBypassController keyguardBypassController, NotificationWakeUpCoordinator notificationWakeUpCoordinator, KeyguardMonitor keyguardMonitor, DockManager dockManager, HeadsUpManagerPhone headsUpManagerPhone) {
        super(context, attributeSet);
        this.mContext = context;
        this.mUnlockMethodCache = UnlockMethodCache.getInstance(context);
        this.mKeyguardUpdateMonitor = KeyguardUpdateMonitor.getInstance(this.mContext);
        this.mAccessibilityController = accessibilityController;
        this.mConfigurationController = configurationController;
        this.mStatusBarStateController = statusBarStateController;
        this.mBypassController = keyguardBypassController;
        this.mWakeUpCoordinator = notificationWakeUpCoordinator;
        this.mKeyguardMonitor = keyguardMonitor;
        this.mDockManager = dockManager;
        this.mHeadsUpManager = headsUpManagerPhone;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mStatusBarStateController.addCallback(this);
        this.mConfigurationController.addCallback(this);
        this.mKeyguardMonitor.addCallback(this.mKeyguardMonitorCallback);
        this.mKeyguardUpdateMonitor.registerCallback(this.mUpdateMonitorCallback);
        this.mUnlockMethodCache.addListener(this);
        this.mWakeUpCoordinator.addListener(this);
        this.mSimLocked = this.mKeyguardUpdateMonitor.isSimPinSecure();
        DockManager dockManager = this.mDockManager;
        if (dockManager != null) {
            dockManager.addListener(this.mDockEventListener);
        }
        ((TunerService) Dependency.get(TunerService.class)).addTunable(this, "sysui_keyguard_show_lock_icon");
        onThemeChanged();
        update();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mStatusBarStateController.removeCallback(this);
        this.mConfigurationController.removeCallback(this);
        this.mKeyguardUpdateMonitor.removeCallback(this.mUpdateMonitorCallback);
        this.mKeyguardMonitor.removeCallback(this.mKeyguardMonitorCallback);
        this.mWakeUpCoordinator.removeListener(this);
        this.mUnlockMethodCache.removeListener(this);
        DockManager dockManager = this.mDockManager;
        if (dockManager != null) {
            dockManager.removeListener(this.mDockEventListener);
        }
        ((TunerService) Dependency.get(TunerService.class)).removeTunable(this);
    }

    public void onThemeChanged() {
        TypedArray obtainStyledAttributes = this.mContext.getTheme().obtainStyledAttributes((AttributeSet) null, new int[]{C1772R$attr.wallpaperTextColor}, 0, 0);
        this.mIconColor = obtainStyledAttributes.getColor(0, -1);
        obtainStyledAttributes.recycle();
        updateDarkTint();
    }

    public void onUserInfoChanged(String str, Drawable drawable, String str2) {
        update();
    }

    public void setTransientBiometricsError(boolean z) {
        this.mTransientBiometricsError = z;
        update();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.densityDpi;
        if (i != this.mDensity) {
            this.mDensity = i;
            update();
        }
    }

    public void update() {
        update(false);
    }

    public void update(boolean z) {
        if (z) {
            this.mForceUpdate = true;
        }
        if (!this.mUpdatePending) {
            this.mUpdatePending = true;
            getViewTreeObserver().addOnPreDrawListener(this);
        }
    }

    public boolean onPreDraw() {
        this.mUpdatePending = false;
        getViewTreeObserver().removeOnPreDrawListener(this);
        final int state = getState();
        int i = this.mLastState;
        boolean z = this.mKeyguardJustShown;
        this.mIsFaceUnlockState = state == 2;
        this.mLastState = state;
        this.mKeyguardJustShown = false;
        boolean z2 = i != state || this.mForceUpdate;
        if (this.mBlockUpdates && canBlockUpdates()) {
            z2 = false;
        }
        if (z2) {
            this.mForceUpdate = false;
            final int animationIndexForTransition = getAnimationIndexForTransition(i, state, this.mPulsing, this.mDozing, z);
            boolean z3 = animationIndexForTransition != -1;
            Drawable drawable = this.mContext.getDrawable(z3 ? getThemedAnimationResId(animationIndexForTransition) : getIconForState(state));
            final AnimatedVectorDrawable animatedVectorDrawable = drawable instanceof AnimatedVectorDrawable ? (AnimatedVectorDrawable) drawable : null;
            setImageDrawable(drawable, false);
            if (this.mIsFaceUnlockState) {
                announceForAccessibility(getContext().getString(C1784R$string.accessibility_scanning_face));
            }
            if (animatedVectorDrawable != null && z3) {
                animatedVectorDrawable.forceAnimationOnUI();
                animatedVectorDrawable.clearAnimationCallbacks();
                animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                    public void onAnimationEnd(Drawable drawable) {
                        if (LockIcon.this.getDrawable() == animatedVectorDrawable && state == LockIcon.this.getState() && LockIcon.this.doesAnimationLoop(animationIndexForTransition)) {
                            animatedVectorDrawable.start();
                        } else {
                            Trace.endAsyncSection("LockIcon#Animation", state);
                        }
                    }
                });
                Trace.beginAsyncSection("LockIcon#Animation", state);
                animatedVectorDrawable.start();
            }
        }
        updateDarkTint();
        updateIconVisibility();
        updateClickability();
        return true;
    }

    private boolean updateIconVisibility() {
        int i = 0;
        boolean z = (this.mDozing && (!this.mPulsing || this.mDocked)) || this.mWakeAndUnlockRunning || this.mShowingLaunchAffordance || this.mHideLockIcon;
        if (this.mBypassController.getBypassEnabled() && !this.mBouncerShowingScrimmed && ((this.mHeadsUpManager.isHeadsUpGoingAway() || this.mHeadsUpManager.hasPinnedHeadsUp() || this.mStatusBarStateController.getState() == 1) && !this.mWakeUpCoordinator.getNotificationsFullyHidden())) {
            z = true;
        }
        if (z == (getVisibility() == 4)) {
            return false;
        }
        if (z) {
            i = 4;
        }
        setVisibility(i);
        animate().cancel();
        if (!z) {
            setScaleX(0.0f);
            setScaleY(0.0f);
            animate().setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN).scaleX(1.0f).scaleY(1.0f).withLayer().setDuration(233).start();
        }
        return true;
    }

    private boolean canBlockUpdates() {
        return this.mKeyguardShowing || this.mKeyguardMonitor.isKeyguardFadingAway();
    }

    private void updateClickability() {
        if (this.mAccessibilityController != null) {
            boolean z = true;
            boolean z2 = this.mUnlockMethodCache.isMethodSecure() && this.mUnlockMethodCache.canSkipBouncer();
            boolean isAccessibilityEnabled = this.mAccessibilityController.isAccessibilityEnabled();
            setClickable(isAccessibilityEnabled);
            if (!z2 || isAccessibilityEnabled) {
                z = false;
            }
            setLongClickable(z);
            setFocusable(this.mAccessibilityController.isAccessibilityEnabled());
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        boolean isFingerprintDetectionRunning = this.mKeyguardUpdateMonitor.isFingerprintDetectionRunning();
        boolean isUnlockingWithBiometricAllowed = this.mKeyguardUpdateMonitor.isUnlockingWithBiometricAllowed();
        if (isFingerprintDetectionRunning && isUnlockingWithBiometricAllowed) {
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, getContext().getString(C1784R$string.accessibility_unlock_without_fingerprint)));
            accessibilityNodeInfo.setHintText(getContext().getString(C1784R$string.accessibility_waiting_for_fingerprint));
        } else if (this.mIsFaceUnlockState) {
            accessibilityNodeInfo.setClassName(LockIcon.class.getName());
            accessibilityNodeInfo.setContentDescription(getContext().getString(C1784R$string.accessibility_scanning_face));
        }
    }

    private int getIconForState(int i) {
        if (i != 0) {
            if (i == 1) {
                return 17302466;
            }
            if (!(i == 2 || i == 3)) {
                throw new IllegalArgumentException();
            }
        }
        return 17302457;
    }

    public void onFullyHiddenChanged(boolean z) {
        if (this.mBypassController.getBypassEnabled() && updateIconVisibility()) {
            update();
        }
    }

    public void setBouncerShowingScrimmed(boolean z) {
        this.mBouncerShowingScrimmed = z;
        if (this.mBypassController.getBypassEnabled()) {
            update();
        }
    }

    private int getThemedAnimationResId(int i) {
        String emptyIfNull = TextUtils.emptyIfNull(Settings.Secure.getString(getContext().getContentResolver(), "theme_customization_overlay_packages"));
        if (emptyIfNull.contains("com.android.theme.icon_pack.circular.android")) {
            return LOCK_ANIM_RES_IDS[1][i];
        }
        if (emptyIfNull.contains("com.android.theme.icon_pack.filled.android")) {
            return LOCK_ANIM_RES_IDS[2][i];
        }
        if (emptyIfNull.contains("com.android.theme.icon_pack.rounded.android")) {
            return LOCK_ANIM_RES_IDS[3][i];
        }
        return LOCK_ANIM_RES_IDS[0][i];
    }

    /* access modifiers changed from: private */
    public int getState() {
        KeyguardUpdateMonitor instance = KeyguardUpdateMonitor.getInstance(this.mContext);
        if ((this.mUnlockMethodCache.canSkipBouncer() || !this.mKeyguardShowing || this.mKeyguardMonitor.isKeyguardGoingAway()) && !this.mSimLocked) {
            return 1;
        }
        if (this.mTransientBiometricsError) {
            return 3;
        }
        return (!instance.isFaceDetectionRunning() || this.mPulsing) ? 0 : 2;
    }

    public void onDozeAmountChanged(float f, float f2) {
        this.mDozeAmount = f2;
        updateDarkTint();
    }

    public void onDozingChanged(boolean z) {
        this.mDozing = z;
        update();
    }

    private void updateDarkTint() {
        setImageTintList(ColorStateList.valueOf(ColorUtils.blendARGB(this.mIconColor, -1, this.mDozeAmount)));
    }

    public void onDensityOrFontScaleChanged() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = getResources().getDimensionPixelSize(C1775R$dimen.keyguard_lock_width);
            layoutParams.height = getResources().getDimensionPixelSize(C1775R$dimen.keyguard_lock_height);
            setLayoutParams(layoutParams);
            update(true);
        }
    }

    public void onLocaleListChanged() {
        setContentDescription(getContext().getText(C1784R$string.accessibility_unlock_button));
        update(true);
    }

    public void onUnlockMethodStateChanged() {
        update();
    }

    public void onBiometricAuthModeChanged(boolean z, boolean z2) {
        if (z) {
            this.mWakeAndUnlockRunning = true;
        }
        if (z2 && this.mBypassController.getBypassEnabled() && canBlockUpdates()) {
            this.mBlockUpdates = true;
        }
        update();
    }

    public void onShowingLaunchAffordanceChanged(boolean z) {
        this.mShowingLaunchAffordance = z;
        update();
    }

    public void onScrimVisibilityChanged(int i) {
        if (this.mWakeAndUnlockRunning && i == 0) {
            this.mWakeAndUnlockRunning = false;
            update();
        }
    }

    public void onTuningChanged(String str, String str2) {
        if (str.equals("sysui_keyguard_show_lock_icon")) {
            this.mHideLockIcon = str2 != null && str2.equals("0");
        }
    }
}
