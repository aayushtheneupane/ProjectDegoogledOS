package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.hardware.biometrics.BiometricSourceType;
import android.os.Build;
import android.os.Trace;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class UnlockMethodCache {
    private static UnlockMethodCache sInstance;
    private final KeyguardUpdateMonitorCallback mCallback = new KeyguardUpdateMonitorCallback() {
        public void onUserSwitchComplete(int i) {
            UnlockMethodCache.this.update(false);
        }

        public void onTrustChanged(int i) {
            UnlockMethodCache.this.update(false);
        }

        public void onTrustManagedChanged(int i) {
            UnlockMethodCache.this.update(false);
        }

        public void onStartedWakingUp() {
            UnlockMethodCache.this.update(false);
        }

        public void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType) {
            Trace.beginSection("KeyguardUpdateMonitorCallback#onBiometricAuthenticated");
            if (!UnlockMethodCache.this.mKeyguardUpdateMonitor.isUnlockingWithBiometricAllowed()) {
                Trace.endSection();
                return;
            }
            UnlockMethodCache.this.update(false);
            Trace.endSection();
        }

        public void onFaceUnlockStateChanged(boolean z, int i) {
            UnlockMethodCache.this.update(false);
        }

        public void onStrongAuthStateChanged(int i) {
            UnlockMethodCache.this.update(false);
        }

        public void onScreenTurnedOff() {
            UnlockMethodCache.this.update(false);
        }

        public void onKeyguardVisibilityChanged(boolean z) {
            UnlockMethodCache.this.update(false);
        }

        public void onBiometricsCleared() {
            UnlockMethodCache.this.update(false);
        }
    };
    private boolean mCanSkipBouncer;
    private boolean mDebugUnlocked = false;
    private boolean mFaceAuthEnabled;
    /* access modifiers changed from: private */
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    private final ArrayList<OnUnlockMethodChangedListener> mListeners = new ArrayList<>();
    private final LockPatternUtils mLockPatternUtils;
    private boolean mSecure;
    private boolean mTrustManaged;
    private boolean mTrusted;

    public interface OnUnlockMethodChangedListener {
        void onUnlockMethodStateChanged();
    }

    private UnlockMethodCache(Context context) {
        this.mLockPatternUtils = new LockPatternUtils(context);
        this.mKeyguardUpdateMonitor = KeyguardUpdateMonitor.getInstance(context);
        KeyguardUpdateMonitor.getInstance(context).registerCallback(this.mCallback);
        update(true);
        boolean z = Build.IS_DEBUGGABLE;
    }

    public static UnlockMethodCache getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UnlockMethodCache(context);
        }
        return sInstance;
    }

    public boolean isMethodSecure() {
        return this.mSecure;
    }

    public boolean canSkipBouncer() {
        return this.mCanSkipBouncer;
    }

    public void addListener(OnUnlockMethodChangedListener onUnlockMethodChangedListener) {
        this.mListeners.add(onUnlockMethodChangedListener);
    }

    public void removeListener(OnUnlockMethodChangedListener onUnlockMethodChangedListener) {
        this.mListeners.remove(onUnlockMethodChangedListener);
    }

    public boolean isFaceAuthEnabled() {
        return this.mFaceAuthEnabled;
    }

    /* access modifiers changed from: private */
    public void update(boolean z) {
        boolean z2;
        Trace.beginSection("UnlockMethodCache#update");
        int currentUser = KeyguardUpdateMonitor.getCurrentUser();
        boolean isSecure = this.mLockPatternUtils.isSecure(currentUser);
        boolean z3 = false;
        if (!isSecure || this.mKeyguardUpdateMonitor.getUserCanSkipBouncer(currentUser)) {
            z2 = true;
        } else {
            boolean z4 = Build.IS_DEBUGGABLE;
            z2 = false;
        }
        boolean userTrustIsManaged = this.mKeyguardUpdateMonitor.getUserTrustIsManaged(currentUser);
        boolean userHasTrust = this.mKeyguardUpdateMonitor.getUserHasTrust(currentUser);
        boolean isFaceAuthEnabledForUser = this.mKeyguardUpdateMonitor.isFaceAuthEnabledForUser(currentUser);
        if (!(isSecure == this.mSecure && z2 == this.mCanSkipBouncer && userTrustIsManaged == this.mTrustManaged && this.mFaceAuthEnabled == isFaceAuthEnabledForUser)) {
            z3 = true;
        }
        if (z3 || z) {
            this.mSecure = isSecure;
            this.mCanSkipBouncer = z2;
            this.mTrusted = userHasTrust;
            this.mTrustManaged = userTrustIsManaged;
            this.mFaceAuthEnabled = isFaceAuthEnabledForUser;
            notifyListeners();
        }
        Trace.endSection();
    }

    private void notifyListeners() {
        Iterator<OnUnlockMethodChangedListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onUnlockMethodStateChanged();
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("UnlockMethodCache");
        printWriter.println("  mSecure: " + this.mSecure);
        printWriter.println("  mCanSkipBouncer: " + this.mCanSkipBouncer);
        printWriter.println("  mTrustManaged: " + this.mTrustManaged);
        printWriter.println("  mTrusted: " + this.mTrusted);
        printWriter.println("  mDebugUnlocked: " + this.mDebugUnlocked);
        printWriter.println("  mFaceAuthEnabled: " + this.mFaceAuthEnabled);
    }
}
