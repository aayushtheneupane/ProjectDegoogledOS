package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.hardware.biometrics.BiometricSourceType;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.tuner.TunerService;
import java.io.PrintWriter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KeyguardBypassController.kt */
public final class KeyguardBypassController {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean bouncerShowing;
    private boolean bypassEnabled;
    /* access modifiers changed from: private */
    public boolean bypassEnabledBiometric;
    private boolean hasFaceFeature;
    private boolean isPulseExpanding;
    private boolean launchingAffordance;
    /* access modifiers changed from: private */
    public BiometricSourceType pendingUnlockType;
    private boolean qSExpanded;
    private final StatusBarStateController statusBarStateController;
    public BiometricUnlockController unlockController;
    private final UnlockMethodCache unlockMethodCache;

    public final void setUnlockController(BiometricUnlockController biometricUnlockController) {
        Intrinsics.checkParameterIsNotNull(biometricUnlockController, "<set-?>");
        this.unlockController = biometricUnlockController;
    }

    public final void setPulseExpanding(boolean z) {
        this.isPulseExpanding = z;
    }

    public final boolean getBypassEnabled() {
        return this.bypassEnabled;
    }

    public final boolean getBypassEnabledBiometric() {
        return this.bypassEnabledBiometric && this.unlockMethodCache.isFaceAuthEnabled();
    }

    public final void setBouncerShowing(boolean z) {
        this.bouncerShowing = z;
    }

    public final void setLaunchingAffordance(boolean z) {
        this.launchingAffordance = z;
    }

    public final void setQSExpanded(boolean z) {
        boolean z2 = this.qSExpanded != z;
        this.qSExpanded = z;
        if (z2 && !z) {
            maybePerformPendingUnlock();
        }
    }

    public KeyguardBypassController(Context context, final TunerService tunerService, StatusBarStateController statusBarStateController2, NotificationLockscreenUserManager notificationLockscreenUserManager) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(tunerService, "tunerService");
        Intrinsics.checkParameterIsNotNull(statusBarStateController2, "statusBarStateController");
        Intrinsics.checkParameterIsNotNull(notificationLockscreenUserManager, "lockscreenUserManager");
        UnlockMethodCache instance = UnlockMethodCache.getInstance(context);
        Intrinsics.checkExpressionValueIsNotNull(instance, "UnlockMethodCache.getInstance(context)");
        this.unlockMethodCache = instance;
        this.statusBarStateController = statusBarStateController2;
        this.hasFaceFeature = context.getPackageManager().hasSystemFeature("android.hardware.biometrics.face");
        if (this.hasFaceFeature) {
            statusBarStateController2.addCallback(new StatusBarStateController.StateListener(this) {
                final /* synthetic */ KeyguardBypassController this$0;

                {
                    this.this$0 = r1;
                }

                public void onStateChanged(int i) {
                    if (i != 1) {
                        this.this$0.pendingUnlockType = null;
                    }
                }
            });
            final int i = context.getResources().getBoolean(17891466) ? 1 : 0;
            tunerService.addTunable(new TunerService.Tunable(this) {
                final /* synthetic */ KeyguardBypassController this$0;

                {
                    this.this$0 = r1;
                }

                public void onTuningChanged(String str, String str2) {
                    this.this$0.bypassEnabledBiometric = tunerService.getValue(str, i) != 0;
                }
            }, "face_unlock_dismisses_keyguard");
            notificationLockscreenUserManager.addUserChangedListener(new NotificationLockscreenUserManager.UserChangedListener(this) {
                final /* synthetic */ KeyguardBypassController this$0;

                {
                    this.this$0 = r1;
                }

                public final void onUserChanged(int i) {
                    this.this$0.pendingUnlockType = null;
                }
            });
        }
    }

    public final boolean onBiometricAuthenticated(BiometricSourceType biometricSourceType) {
        Intrinsics.checkParameterIsNotNull(biometricSourceType, "biometricSourceType");
        boolean z = true;
        if (getBypassEnabledBiometric()) {
            if (biometricSourceType == BiometricSourceType.FACE && !canBypass()) {
                z = false;
            }
            if (!z && (this.isPulseExpanding || this.qSExpanded)) {
                this.pendingUnlockType = biometricSourceType;
            }
        }
        return z;
    }

    public final void maybePerformPendingUnlock() {
        BiometricSourceType biometricSourceType = this.pendingUnlockType;
        if (biometricSourceType == null) {
            return;
        }
        if (biometricSourceType == null) {
            Intrinsics.throwNpe();
            throw null;
        } else if (onBiometricAuthenticated(biometricSourceType)) {
            BiometricUnlockController biometricUnlockController = this.unlockController;
            if (biometricUnlockController != null) {
                biometricUnlockController.startWakeAndUnlock(this.pendingUnlockType);
                this.pendingUnlockType = null;
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("unlockController");
            throw null;
        }
    }

    public final boolean canBypass() {
        if (!getBypassEnabledBiometric()) {
            return false;
        }
        if (!this.bouncerShowing && (this.statusBarStateController.getState() != 1 || this.launchingAffordance || this.isPulseExpanding || this.qSExpanded)) {
            return false;
        }
        return true;
    }

    public final boolean canPlaySubtleWindowAnimations() {
        if (!getBypassEnabledBiometric() || this.statusBarStateController.getState() != 1 || this.qSExpanded) {
            return false;
        }
        return true;
    }

    public final void onStartedGoingToSleep() {
        this.pendingUnlockType = null;
    }

    public final void dump(PrintWriter printWriter) {
        Intrinsics.checkParameterIsNotNull(printWriter, "pw");
        printWriter.println("KeyguardBypassController:");
        printWriter.print("  pendingUnlockType: ");
        printWriter.println(this.pendingUnlockType);
        printWriter.print("  bypassEnabledBiometric: ");
        printWriter.println(getBypassEnabledBiometric());
        printWriter.print("  canBypass: ");
        printWriter.println(canBypass());
        printWriter.print("  bouncerShowing: ");
        printWriter.println(this.bouncerShowing);
        printWriter.print("  isPulseExpanding: ");
        printWriter.println(this.isPulseExpanding);
        printWriter.print("  launchingAffordance: ");
        printWriter.println(this.launchingAffordance);
        printWriter.print("  qSExpanded: ");
        printWriter.println(this.qSExpanded);
        printWriter.print("  hasFaceFeature: ");
        printWriter.println(this.hasFaceFeature);
    }

    /* compiled from: KeyguardBypassController.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
