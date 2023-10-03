package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.hardware.biometrics.BiometricSourceType;
import android.metrics.LogMaker;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.LatencyTracker;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.Dependency;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.statusbar.NotificationMediaManager;
import java.io.PrintWriter;

public class BiometricUnlockController extends KeyguardUpdateMonitorCallback {
    private final Context mContext;
    private DozeScrimController mDozeScrimController;
    private boolean mFadedAwayAfterWakeAndUnlock;
    private final Handler mHandler;
    /* access modifiers changed from: private */
    public boolean mHasScreenTurnedOnSinceAuthenticating;
    private final KeyguardBypassController mKeyguardBypassController;
    private KeyguardViewMediator mKeyguardViewMediator;
    private final NotificationMediaManager mMediaManager;
    private final MetricsLogger mMetricsLogger;
    private int mMode;
    private BiometricSourceType mPendingAuthenticatedBioSourceType;
    private int mPendingAuthenticatedUserId;
    /* access modifiers changed from: private */
    public boolean mPendingShowBouncer;
    private final PowerManager mPowerManager;
    private final Runnable mReleaseBiometricWakeLockRunnable;
    private final ScreenLifecycle.Observer mScreenObserver;
    private ScrimController mScrimController;
    private StatusBar mStatusBar;
    private StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    /* access modifiers changed from: private */
    public final StatusBarWindowController mStatusBarWindowController;
    private final UnlockMethodCache mUnlockMethodCache;
    private final KeyguardUpdateMonitor mUpdateMonitor;
    private PowerManager.WakeLock mWakeLock;
    private final int mWakeUpDelay;
    @VisibleForTesting
    final WakefulnessLifecycle.Observer mWakefulnessObserver;

    public BiometricUnlockController(Context context, DozeScrimController dozeScrimController, KeyguardViewMediator keyguardViewMediator, ScrimController scrimController, StatusBar statusBar, UnlockMethodCache unlockMethodCache, Handler handler, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardBypassController keyguardBypassController) {
        this(context, dozeScrimController, keyguardViewMediator, scrimController, statusBar, unlockMethodCache, handler, keyguardUpdateMonitor, context.getResources().getInteger(17694937), keyguardBypassController);
    }

    @VisibleForTesting
    protected BiometricUnlockController(Context context, DozeScrimController dozeScrimController, KeyguardViewMediator keyguardViewMediator, ScrimController scrimController, StatusBar statusBar, UnlockMethodCache unlockMethodCache, Handler handler, KeyguardUpdateMonitor keyguardUpdateMonitor, int i, KeyguardBypassController keyguardBypassController) {
        this.mPendingAuthenticatedUserId = -1;
        this.mPendingAuthenticatedBioSourceType = null;
        this.mMetricsLogger = (MetricsLogger) Dependency.get(MetricsLogger.class);
        this.mReleaseBiometricWakeLockRunnable = new Runnable() {
            public void run() {
                Log.i("BiometricUnlockController", "biometric wakelock: TIMEOUT!!");
                BiometricUnlockController.this.releaseBiometricWakeLock();
            }
        };
        this.mWakefulnessObserver = new WakefulnessLifecycle.Observer() {
            public void onFinishedWakingUp() {
                if (BiometricUnlockController.this.mPendingShowBouncer) {
                    BiometricUnlockController.this.showBouncer();
                }
            }
        };
        this.mScreenObserver = new ScreenLifecycle.Observer() {
            public void onScreenTurnedOn() {
                boolean unused = BiometricUnlockController.this.mHasScreenTurnedOnSinceAuthenticating = true;
            }
        };
        this.mContext = context;
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mUpdateMonitor = keyguardUpdateMonitor;
        this.mUpdateMonitor.registerCallback(this);
        this.mMediaManager = (NotificationMediaManager) Dependency.get(NotificationMediaManager.class);
        ((WakefulnessLifecycle) Dependency.get(WakefulnessLifecycle.class)).addObserver(this.mWakefulnessObserver);
        ((ScreenLifecycle) Dependency.get(ScreenLifecycle.class)).addObserver(this.mScreenObserver);
        this.mStatusBarWindowController = (StatusBarWindowController) Dependency.get(StatusBarWindowController.class);
        this.mDozeScrimController = dozeScrimController;
        this.mKeyguardViewMediator = keyguardViewMediator;
        this.mScrimController = scrimController;
        this.mStatusBar = statusBar;
        this.mUnlockMethodCache = unlockMethodCache;
        this.mHandler = handler;
        this.mWakeUpDelay = i;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mKeyguardBypassController.setUnlockController(this);
    }

    public void setStatusBarKeyguardViewManager(StatusBarKeyguardViewManager statusBarKeyguardViewManager) {
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
    }

    /* access modifiers changed from: private */
    public void releaseBiometricWakeLock() {
        if (this.mWakeLock != null) {
            this.mHandler.removeCallbacks(this.mReleaseBiometricWakeLockRunnable);
            Log.i("BiometricUnlockController", "releasing biometric wakelock");
            this.mWakeLock.release();
            this.mWakeLock = null;
        }
    }

    public void onBiometricAcquired(BiometricSourceType biometricSourceType) {
        Trace.beginSection("BiometricUnlockController#onBiometricAcquired");
        releaseBiometricWakeLock();
        if (!this.mUpdateMonitor.isDeviceInteractive()) {
            if (LatencyTracker.isEnabled(this.mContext)) {
                int i = 2;
                if (biometricSourceType == BiometricSourceType.FACE) {
                    i = 6;
                }
                LatencyTracker.getInstance(this.mContext).onActionStart(i);
            }
            this.mWakeLock = this.mPowerManager.newWakeLock(1, "wake-and-unlock wakelock");
            Trace.beginSection("acquiring wake-and-unlock");
            this.mWakeLock.acquire();
            Trace.endSection();
            Log.i("BiometricUnlockController", "biometric acquired, grabbing biometric wakelock");
            this.mHandler.postDelayed(this.mReleaseBiometricWakeLockRunnable, 15000);
        }
        Trace.endSection();
    }

    private boolean pulsingOrAod() {
        ScrimState state = this.mScrimController.getState();
        return state == ScrimState.AOD || state == ScrimState.PULSING;
    }

    /* renamed from: onBiometricAuthenticated */
    public void lambda$onFinishedGoingToSleep$1$BiometricUnlockController(int i, BiometricSourceType biometricSourceType) {
        Trace.beginSection("BiometricUnlockController#onBiometricAuthenticated");
        if (this.mUpdateMonitor.isGoingToSleep()) {
            this.mPendingAuthenticatedUserId = i;
            this.mPendingAuthenticatedBioSourceType = biometricSourceType;
            Trace.endSection();
            return;
        }
        this.mMetricsLogger.write(new LogMaker(1697).setType(10).setSubtype(toSubtype(biometricSourceType)));
        if (this.mKeyguardBypassController.onBiometricAuthenticated(biometricSourceType)) {
            this.mKeyguardViewMediator.userActivity();
            startWakeAndUnlock(biometricSourceType);
            return;
        }
        Log.d("BiometricUnlockController", "onBiometricAuthenticated aborted by bypass controller");
    }

    public void startWakeAndUnlock(BiometricSourceType biometricSourceType) {
        startWakeAndUnlock(calculateMode(biometricSourceType));
    }

    public void startWakeAndUnlock(int i) {
        Log.v("BiometricUnlockController", "startWakeAndUnlock(" + i + ")");
        boolean isDeviceInteractive = this.mUpdateMonitor.isDeviceInteractive();
        this.mMode = i;
        this.mHasScreenTurnedOnSinceAuthenticating = false;
        if (this.mMode == 2 && pulsingOrAod()) {
            if (this.mStatusBar.wallpaperSupportsAmbientMode()) {
                this.mMode = 1;
            } else {
                this.mStatusBarWindowController.setForceDozeBrightness(true);
            }
        }
        boolean z = i == 1 && DozeParameters.getInstance(this.mContext).getAlwaysOn() && this.mWakeUpDelay > 0;
        $$Lambda$BiometricUnlockController$eARUOiIHQidy4dPvrf3UVu6gsv0 r2 = new Runnable(isDeviceInteractive, z) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                BiometricUnlockController.this.lambda$startWakeAndUnlock$0$BiometricUnlockController(this.f$1, this.f$2);
            }
        };
        if (!z && this.mMode != 0) {
            r2.run();
        }
        int i2 = this.mMode;
        switch (i2) {
            case 1:
            case 2:
            case 6:
                if (i2 == 2) {
                    Trace.beginSection("MODE_WAKE_AND_UNLOCK_PULSING");
                    this.mMediaManager.updateMediaMetaData(false, true);
                } else if (i2 == 1) {
                    Trace.beginSection("MODE_WAKE_AND_UNLOCK");
                } else {
                    Trace.beginSection("MODE_WAKE_AND_UNLOCK_FROM_DREAM");
                    this.mUpdateMonitor.awakenFromDream();
                }
                this.mStatusBarWindowController.setStatusBarFocusable(false);
                if (z) {
                    this.mHandler.postDelayed(r2, (long) this.mWakeUpDelay);
                } else {
                    this.mKeyguardViewMediator.onWakeAndUnlocking();
                }
                if (this.mStatusBar.getNavigationBarView() != null) {
                    this.mStatusBar.getNavigationBarView().setWakeAndUnlocking(true);
                }
                Trace.endSection();
                break;
            case 3:
            case 5:
                Trace.beginSection("MODE_UNLOCK_COLLAPSING or MODE_SHOW_BOUNCER");
                if (!isDeviceInteractive) {
                    this.mPendingShowBouncer = true;
                } else {
                    showBouncer();
                }
                Trace.endSection();
                break;
            case 7:
            case 8:
                Trace.beginSection("MODE_DISMISS_BOUNCER or MODE_UNLOCK_FADING");
                this.mStatusBarKeyguardViewManager.notifyKeyguardAuthenticated(false);
                Trace.endSection();
                break;
        }
        this.mStatusBar.notifyBiometricAuthModeChanged();
        Trace.endSection();
    }

    public /* synthetic */ void lambda$startWakeAndUnlock$0$BiometricUnlockController(boolean z, boolean z2) {
        if (!z) {
            Log.i("BiometricUnlockController", "bio wakelock: Authenticated, waking up...");
            this.mPowerManager.wakeUp(SystemClock.uptimeMillis(), 4, "android.policy:BIOMETRIC");
        }
        if (z2) {
            this.mKeyguardViewMediator.onWakeAndUnlocking();
        }
        Trace.beginSection("release wake-and-unlock");
        releaseBiometricWakeLock();
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void showBouncer() {
        if (this.mMode == 3) {
            this.mStatusBarKeyguardViewManager.showBouncer(false);
        }
        this.mStatusBarKeyguardViewManager.animateCollapsePanels(1.1f);
        this.mPendingShowBouncer = false;
    }

    public void onStartedGoingToSleep(int i) {
        resetMode();
        this.mFadedAwayAfterWakeAndUnlock = false;
        this.mPendingAuthenticatedUserId = -1;
        this.mPendingAuthenticatedBioSourceType = null;
    }

    public void onFinishedGoingToSleep(int i) {
        Trace.beginSection("BiometricUnlockController#onFinishedGoingToSleep");
        BiometricSourceType biometricSourceType = this.mPendingAuthenticatedBioSourceType;
        int i2 = this.mPendingAuthenticatedUserId;
        if (!(i2 == -1 || biometricSourceType == null)) {
            this.mHandler.post(new Runnable(i2, biometricSourceType) {
                private final /* synthetic */ int f$1;
                private final /* synthetic */ BiometricSourceType f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    BiometricUnlockController.this.lambda$onFinishedGoingToSleep$1$BiometricUnlockController(this.f$1, this.f$2);
                }
            });
        }
        this.mPendingAuthenticatedUserId = -1;
        this.mPendingAuthenticatedBioSourceType = null;
        Trace.endSection();
    }

    public boolean hasPendingAuthentication() {
        return this.mPendingAuthenticatedUserId != -1 && this.mUpdateMonitor.isUnlockingWithBiometricAllowed() && this.mPendingAuthenticatedUserId == KeyguardUpdateMonitor.getCurrentUser();
    }

    public int getMode() {
        return this.mMode;
    }

    private int calculateMode(BiometricSourceType biometricSourceType) {
        if (biometricSourceType == BiometricSourceType.FACE || biometricSourceType == BiometricSourceType.IRIS) {
            return calculateModeForPassiveAuth();
        }
        return calculateModeForFingerprint();
    }

    private int calculateModeForFingerprint() {
        boolean isUnlockingWithBiometricAllowed = this.mUpdateMonitor.isUnlockingWithBiometricAllowed();
        boolean isDreaming = this.mUpdateMonitor.isDreaming();
        if (!this.mUpdateMonitor.isDeviceInteractive()) {
            if (!this.mStatusBarKeyguardViewManager.isShowing()) {
                return 4;
            }
            if (this.mDozeScrimController.isPulsing() && isUnlockingWithBiometricAllowed) {
                return 2;
            }
            if (isUnlockingWithBiometricAllowed || !this.mUnlockMethodCache.isMethodSecure()) {
                return 1;
            }
            return 3;
        } else if (isUnlockingWithBiometricAllowed && isDreaming) {
            return 6;
        } else {
            if (!this.mStatusBarKeyguardViewManager.isShowing()) {
                return 0;
            }
            if (this.mStatusBarKeyguardViewManager.bouncerIsOrWillBeShowing() && isUnlockingWithBiometricAllowed) {
                return 8;
            }
            if (isUnlockingWithBiometricAllowed) {
                return 5;
            }
            if (!this.mStatusBarKeyguardViewManager.isBouncerShowing()) {
                return 3;
            }
            return 0;
        }
    }

    private int calculateModeForPassiveAuth() {
        boolean isUnlockingWithBiometricAllowed = this.mUpdateMonitor.isUnlockingWithBiometricAllowed();
        boolean isDreaming = this.mUpdateMonitor.isDreaming();
        boolean bypassEnabledBiometric = this.mKeyguardBypassController.getBypassEnabledBiometric();
        if (!this.mUpdateMonitor.isDeviceInteractive()) {
            if (!this.mStatusBarKeyguardViewManager.isShowing()) {
                if (bypassEnabledBiometric) {
                    return 1;
                }
                return 4;
            } else if (!isUnlockingWithBiometricAllowed) {
                if (bypassEnabledBiometric) {
                    return 3;
                }
                return 0;
            } else if (this.mDozeScrimController.isPulsing()) {
                return bypassEnabledBiometric ? 2 : 0;
            } else {
                if (bypassEnabledBiometric) {
                    return 2;
                }
                return 4;
            }
        } else if (!isUnlockingWithBiometricAllowed || !isDreaming) {
            if (!this.mStatusBarKeyguardViewManager.isShowing()) {
                return 0;
            }
            if (!this.mStatusBarKeyguardViewManager.bouncerIsOrWillBeShowing() || !isUnlockingWithBiometricAllowed) {
                if (isUnlockingWithBiometricAllowed) {
                    if (bypassEnabledBiometric) {
                        return 7;
                    }
                    return 0;
                } else if (bypassEnabledBiometric) {
                    return 3;
                } else {
                    return 0;
                }
            } else if (!bypassEnabledBiometric || !this.mKeyguardBypassController.canPlaySubtleWindowAnimations()) {
                return 8;
            } else {
                return 7;
            }
        } else if (bypassEnabledBiometric) {
            return 6;
        } else {
            return 4;
        }
    }

    public void onBiometricAuthFailed(BiometricSourceType biometricSourceType) {
        this.mMetricsLogger.write(new LogMaker(1697).setType(11).setSubtype(toSubtype(biometricSourceType)));
        cleanup();
    }

    public void onBiometricError(int i, String str, BiometricSourceType biometricSourceType) {
        this.mMetricsLogger.write(new LogMaker(1697).setType(15).setSubtype(toSubtype(biometricSourceType)).addTaggedData(1741, Integer.valueOf(i)));
        cleanup();
    }

    private void cleanup() {
        releaseBiometricWakeLock();
    }

    public void startKeyguardFadingAway() {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                BiometricUnlockController.this.mStatusBarWindowController.setForceDozeBrightness(false);
            }
        }, 96);
    }

    public void finishKeyguardFadingAway() {
        if (isWakeAndUnlock()) {
            this.mFadedAwayAfterWakeAndUnlock = true;
        }
        resetMode();
    }

    private void resetMode() {
        this.mMode = 0;
        this.mStatusBarWindowController.setForceDozeBrightness(false);
        if (this.mStatusBar.getNavigationBarView() != null) {
            this.mStatusBar.getNavigationBarView().setWakeAndUnlocking(false);
        }
        this.mStatusBar.notifyBiometricAuthModeChanged();
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println(" BiometricUnlockController:");
        printWriter.print("   mMode=");
        printWriter.println(this.mMode);
        printWriter.print("   mWakeLock=");
        printWriter.println(this.mWakeLock);
    }

    public boolean isWakeAndUnlock() {
        int i = this.mMode;
        return i == 1 || i == 2 || i == 6;
    }

    public boolean unlockedByWakeAndUnlock() {
        return isWakeAndUnlock() || this.mFadedAwayAfterWakeAndUnlock;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r1.mMode;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isBiometricUnlock() {
        /*
            r1 = this;
            boolean r0 = r1.isWakeAndUnlock()
            if (r0 != 0) goto L_0x0011
            int r1 = r1.mMode
            r0 = 5
            if (r1 == r0) goto L_0x0011
            r0 = 7
            if (r1 != r0) goto L_0x000f
            goto L_0x0011
        L_0x000f:
            r1 = 0
            goto L_0x0012
        L_0x0011:
            r1 = 1
        L_0x0012:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.BiometricUnlockController.isBiometricUnlock():boolean");
    }

    /* renamed from: com.android.systemui.statusbar.phone.BiometricUnlockController$5 */
    static /* synthetic */ class C13235 {
        static final /* synthetic */ int[] $SwitchMap$android$hardware$biometrics$BiometricSourceType = new int[BiometricSourceType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                android.hardware.biometrics.BiometricSourceType[] r0 = android.hardware.biometrics.BiometricSourceType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$hardware$biometrics$BiometricSourceType = r0
                int[] r0 = $SwitchMap$android$hardware$biometrics$BiometricSourceType     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.hardware.biometrics.BiometricSourceType r1 = android.hardware.biometrics.BiometricSourceType.FINGERPRINT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$android$hardware$biometrics$BiometricSourceType     // Catch:{ NoSuchFieldError -> 0x001f }
                android.hardware.biometrics.BiometricSourceType r1 = android.hardware.biometrics.BiometricSourceType.FACE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$android$hardware$biometrics$BiometricSourceType     // Catch:{ NoSuchFieldError -> 0x002a }
                android.hardware.biometrics.BiometricSourceType r1 = android.hardware.biometrics.BiometricSourceType.IRIS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.BiometricUnlockController.C13235.<clinit>():void");
        }
    }

    private int toSubtype(BiometricSourceType biometricSourceType) {
        int i = C13235.$SwitchMap$android$hardware$biometrics$BiometricSourceType[biometricSourceType.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i != 2) {
            return i != 3 ? 3 : 2;
        }
        return 1;
    }
}
