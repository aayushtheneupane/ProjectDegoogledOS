package com.android.keyguard;

import android.graphics.Bitmap;
import android.hardware.biometrics.BiometricSourceType;
import android.os.SystemClock;
import com.android.internal.telephony.IccCardConstants;
import com.android.keyguard.KeyguardUpdateMonitor;
import java.util.TimeZone;

public class KeyguardUpdateMonitorCallback {
    private static final long VISIBILITY_CHANGED_COLLAPSE_MS = 1000;
    private boolean mShowing;
    private long mVisibilityChangedCalled;

    public void onBiometricAcquired(BiometricSourceType biometricSourceType) {
    }

    public void onBiometricAuthFailed(BiometricSourceType biometricSourceType) {
    }

    public void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType) {
    }

    public void onBiometricError(int i, String str, BiometricSourceType biometricSourceType) {
    }

    public void onBiometricHelp(int i, String str, BiometricSourceType biometricSourceType) {
    }

    public void onBiometricRunningStateChanged(boolean z, BiometricSourceType biometricSourceType) {
    }

    public void onBiometricsCleared() {
    }

    public void onBootCompleted() {
    }

    public void onClockVisibilityChanged() {
    }

    public void onDevicePolicyManagerStateChanged() {
    }

    public void onDeviceProvisioned() {
    }

    public void onDreamingStateChanged(boolean z) {
    }

    public void onEmergencyCallAction() {
    }

    public void onFaceUnlockStateChanged(boolean z, int i) {
    }

    @Deprecated
    public void onFinishedGoingToSleep(int i) {
    }

    public void onHasLockscreenWallpaperChanged(boolean z) {
    }

    public void onKeyguardBouncerChanged(boolean z) {
    }

    public void onKeyguardVisibilityChanged(boolean z) {
    }

    public void onLogoutEnabledChanged() {
    }

    public void onPhoneStateChanged(int i) {
    }

    public void onPulsing(boolean z) {
    }

    public void onRefreshBatteryInfo(KeyguardUpdateMonitor.BatteryStatus batteryStatus) {
    }

    public void onRefreshCarrierInfo() {
    }

    public void onRingerModeChanged(int i) {
    }

    @Deprecated
    public void onScreenTurnedOff() {
    }

    @Deprecated
    public void onScreenTurnedOn() {
    }

    public void onSetBackground(Bitmap bitmap) {
    }

    public void onSimStateChanged(int i, int i2, IccCardConstants.State state) {
    }

    @Deprecated
    public void onStartedGoingToSleep(int i) {
    }

    @Deprecated
    public void onStartedWakingUp() {
    }

    public void onStrongAuthStateChanged(int i) {
    }

    public void onTelephonyCapable(boolean z) {
    }

    public void onTimeChanged() {
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
    }

    public void onTrustAgentErrorMessage(CharSequence charSequence) {
    }

    public void onTrustChanged(int i) {
    }

    public void onTrustGrantedWithFlags(int i, int i2) {
    }

    public void onTrustManagedChanged(int i) {
    }

    public void onUserInfoChanged(int i) {
    }

    public void onUserSwitchComplete(int i) {
    }

    public void onUserSwitching(int i) {
    }

    public void onUserUnlocked() {
    }

    public void onKeyguardVisibilityChangedRaw(boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (z != this.mShowing || elapsedRealtime - this.mVisibilityChangedCalled >= VISIBILITY_CHANGED_COLLAPSE_MS) {
            onKeyguardVisibilityChanged(z);
            this.mVisibilityChangedCalled = elapsedRealtime;
            this.mShowing = z;
        }
    }
}
