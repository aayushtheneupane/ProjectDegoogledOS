package com.android.systemui.classifier;

import android.content.Context;
import android.database.ContentObserver;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.biometrics.BiometricSourceType;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.Dependency;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.analytics.DataCollector;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.StatusBarState;
import com.android.systemui.util.AsyncSensorManager;
import java.io.PrintWriter;

public class FalsingManagerImpl implements FalsingManager {
    private static final int[] CLASSIFIER_SENSORS = {8};
    private static final int[] COLLECTOR_SENSORS = {1, 4, 8, 5, 11};
    private final AccessibilityManager mAccessibilityManager;
    private boolean mBouncerOffOnDown = false;
    private boolean mBouncerOn = false;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final DataCollector mDataCollector;
    private boolean mEnforceBouncer = false;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final HumanInteractionClassifier mHumanInteractionClassifier;
    private int mIsFalseTouchCalls;
    private boolean mIsTouchScreen = true;
    /* access modifiers changed from: private */
    public boolean mJustUnlockedWithFace = false;
    private final KeyguardUpdateMonitorCallback mKeyguardUpdateCallback = new KeyguardUpdateMonitorCallback() {
        public void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType) {
            if (i == KeyguardUpdateMonitor.getCurrentUser() && biometricSourceType == BiometricSourceType.FACE) {
                boolean unused = FalsingManagerImpl.this.mJustUnlockedWithFace = true;
            }
        }
    };
    private MetricsLogger mMetricsLogger;
    private Runnable mPendingWtf;
    private boolean mScreenOn;
    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        public synchronized void onSensorChanged(SensorEvent sensorEvent) {
            FalsingManagerImpl.this.mDataCollector.onSensorChanged(sensorEvent);
            FalsingManagerImpl.this.mHumanInteractionClassifier.onSensorChanged(sensorEvent);
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
            FalsingManagerImpl.this.mDataCollector.onAccuracyChanged(sensor, i);
        }
    };
    private final SensorManager mSensorManager;
    private boolean mSessionActive = false;
    protected final ContentObserver mSettingsObserver = new ContentObserver(this.mHandler) {
        public void onChange(boolean z) {
            FalsingManagerImpl.this.updateConfiguration();
        }
    };
    private boolean mShowingAod;
    /* access modifiers changed from: private */
    public int mState = 0;
    public StatusBarStateController.StateListener mStatusBarStateListener = new StatusBarStateController.StateListener() {
        public void onStateChanged(int i) {
            if (FalsingLog.ENABLED) {
                FalsingLog.m19i("setStatusBarState", "from=" + StatusBarState.toShortString(FalsingManagerImpl.this.mState) + " to=" + StatusBarState.toShortString(i));
            }
            int unused = FalsingManagerImpl.this.mState = i;
            FalsingManagerImpl.this.updateSessionActive();
        }
    };
    private final UiOffloadThread mUiOffloadThread;

    FalsingManagerImpl(Context context) {
        this.mContext = context;
        this.mSensorManager = (SensorManager) Dependency.get(AsyncSensorManager.class);
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        this.mDataCollector = DataCollector.getInstance(this.mContext);
        this.mHumanInteractionClassifier = HumanInteractionClassifier.getInstance(this.mContext);
        this.mUiOffloadThread = (UiOffloadThread) Dependency.get(UiOffloadThread.class);
        this.mScreenOn = ((PowerManager) context.getSystemService(PowerManager.class)).isInteractive();
        this.mMetricsLogger = new MetricsLogger();
        this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("falsing_manager_enforce_bouncer"), false, this.mSettingsObserver, -1);
        updateConfiguration();
        ((StatusBarStateController) Dependency.get(StatusBarStateController.class)).addCallback(this.mStatusBarStateListener);
        KeyguardUpdateMonitor.getInstance(context).registerCallback(this.mKeyguardUpdateCallback);
    }

    /* access modifiers changed from: private */
    public void updateConfiguration() {
        boolean z = false;
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "falsing_manager_enforce_bouncer", 0) != 0) {
            z = true;
        }
        this.mEnforceBouncer = z;
    }

    private boolean shouldSessionBeActive() {
        boolean z = FalsingLog.ENABLED;
        return isEnabled() && this.mScreenOn && this.mState == 1 && !this.mShowingAod;
    }

    private boolean sessionEntrypoint() {
        if (this.mSessionActive || !shouldSessionBeActive()) {
            return false;
        }
        onSessionStart();
        return true;
    }

    private void sessionExitpoint(boolean z) {
        if (!this.mSessionActive) {
            return;
        }
        if (z || !shouldSessionBeActive()) {
            this.mSessionActive = false;
            if (this.mIsFalseTouchCalls != 0) {
                if (FalsingLog.ENABLED) {
                    FalsingLog.m19i("isFalseTouchCalls", "Calls before failure: " + this.mIsFalseTouchCalls);
                }
                this.mMetricsLogger.histogram("falsing_failure_after_attempts", this.mIsFalseTouchCalls);
                this.mIsFalseTouchCalls = 0;
            }
            this.mUiOffloadThread.submit(new Runnable() {
                public final void run() {
                    FalsingManagerImpl.this.lambda$sessionExitpoint$0$FalsingManagerImpl();
                }
            });
        }
    }

    public /* synthetic */ void lambda$sessionExitpoint$0$FalsingManagerImpl() {
        this.mSensorManager.unregisterListener(this.mSensorEventListener);
    }

    public void updateSessionActive() {
        if (shouldSessionBeActive()) {
            sessionEntrypoint();
        } else {
            sessionExitpoint(false);
        }
    }

    private void onSessionStart() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onSessionStart", "classifierEnabled=" + isClassiferEnabled());
            clearPendingWtf();
        }
        this.mBouncerOn = false;
        this.mSessionActive = true;
        this.mJustUnlockedWithFace = false;
        this.mIsFalseTouchCalls = 0;
        if (this.mHumanInteractionClassifier.isEnabled()) {
            registerSensors(CLASSIFIER_SENSORS);
        }
        if (this.mDataCollector.isEnabledFull()) {
            registerSensors(COLLECTOR_SENSORS);
        }
        if (this.mDataCollector.isEnabled()) {
            this.mDataCollector.onFalsingSessionStarted();
        }
    }

    private void registerSensors(int[] iArr) {
        for (int defaultSensor : iArr) {
            Sensor defaultSensor2 = this.mSensorManager.getDefaultSensor(defaultSensor);
            if (defaultSensor2 != null) {
                this.mUiOffloadThread.submit(new Runnable(defaultSensor2) {
                    private final /* synthetic */ Sensor f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        FalsingManagerImpl.this.lambda$registerSensors$1$FalsingManagerImpl(this.f$1);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$registerSensors$1$FalsingManagerImpl(Sensor sensor) {
        this.mSensorManager.registerListener(this.mSensorEventListener, sensor, 1);
    }

    public boolean isClassiferEnabled() {
        return this.mHumanInteractionClassifier.isEnabled();
    }

    private boolean isEnabled() {
        return this.mHumanInteractionClassifier.isEnabled() || this.mDataCollector.isEnabled();
    }

    public boolean isUnlockingDisabled() {
        return this.mDataCollector.isUnlockingDisabled();
    }

    public boolean isFalseTouch() {
        if (FalsingLog.ENABLED && !this.mSessionActive && ((PowerManager) this.mContext.getSystemService(PowerManager.class)).isInteractive() && this.mPendingWtf == null) {
            boolean isEnabled = isEnabled();
            boolean z = this.mScreenOn;
            String shortString = StatusBarState.toShortString(this.mState);
            Throwable th = new Throwable("here");
            FalsingLog.wLogcat("isFalseTouch", "Session is not active, yet there's a query for a false touch." + " enabled=" + (isEnabled ? 1 : 0) + " mScreenOn=" + (z ? 1 : 0) + " mState=" + shortString + ". Escalating to WTF if screen does not turn on soon.");
            this.mPendingWtf = new Runnable(isEnabled, z, shortString, th) {
                private final /* synthetic */ int f$1;
                private final /* synthetic */ int f$2;
                private final /* synthetic */ String f$3;
                private final /* synthetic */ Throwable f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                public final void run() {
                    FalsingManagerImpl.this.lambda$isFalseTouch$2$FalsingManagerImpl(this.f$1, this.f$2, this.f$3, this.f$4);
                }
            };
            this.mHandler.postDelayed(this.mPendingWtf, 1000);
        }
        if (this.mAccessibilityManager.isTouchExplorationEnabled() || !this.mIsTouchScreen || this.mJustUnlockedWithFace) {
            return false;
        }
        this.mIsFalseTouchCalls++;
        boolean isFalseTouch = this.mHumanInteractionClassifier.isFalseTouch();
        if (!isFalseTouch) {
            if (FalsingLog.ENABLED) {
                FalsingLog.m19i("isFalseTouchCalls", "Calls before success: " + this.mIsFalseTouchCalls);
            }
            this.mMetricsLogger.histogram("falsing_success_after_attempts", this.mIsFalseTouchCalls);
            this.mIsFalseTouchCalls = 0;
        }
        return isFalseTouch;
    }

    public /* synthetic */ void lambda$isFalseTouch$2$FalsingManagerImpl(int i, int i2, String str, Throwable th) {
        FalsingLog.wtf("isFalseTouch", "Session did not become active after query for a false touch." + " enabled=" + i + '/' + (isEnabled() ? 1 : 0) + " mScreenOn=" + i2 + '/' + (this.mScreenOn ? 1 : 0) + " mState=" + str + '/' + StatusBarState.toShortString(this.mState) + ". Look for warnings ~1000ms earlier to see root cause.", th);
    }

    private void clearPendingWtf() {
        Runnable runnable = this.mPendingWtf;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
            this.mPendingWtf = null;
        }
    }

    public boolean shouldEnforceBouncer() {
        return this.mEnforceBouncer;
    }

    public void setShowingAod(boolean z) {
        this.mShowingAod = z;
        updateSessionActive();
    }

    public void onScreenTurningOn() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onScreenTurningOn", "from=" + (this.mScreenOn ? 1 : 0));
            clearPendingWtf();
        }
        this.mScreenOn = true;
        if (sessionEntrypoint()) {
            this.mDataCollector.onScreenTurningOn();
        }
    }

    public void onScreenOnFromTouch() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onScreenOnFromTouch", "from=" + (this.mScreenOn ? 1 : 0));
        }
        this.mScreenOn = true;
        if (sessionEntrypoint()) {
            this.mDataCollector.onScreenOnFromTouch();
        }
    }

    public void onScreenOff() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onScreenOff", "from=" + (this.mScreenOn ? 1 : 0));
        }
        this.mDataCollector.onScreenOff();
        this.mScreenOn = false;
        sessionExitpoint(false);
    }

    public void onSucccessfulUnlock() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onSucccessfulUnlock", "");
        }
        this.mDataCollector.onSucccessfulUnlock();
    }

    public void onBouncerShown() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onBouncerShown", "from=" + (this.mBouncerOn ? 1 : 0));
        }
        if (!this.mBouncerOn) {
            this.mBouncerOn = true;
            this.mDataCollector.onBouncerShown();
        }
    }

    public void onBouncerHidden() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onBouncerHidden", "from=" + (this.mBouncerOn ? 1 : 0));
        }
        if (this.mBouncerOn) {
            this.mBouncerOn = false;
            this.mDataCollector.onBouncerHidden();
        }
    }

    public void onQsDown() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onQsDown", "");
        }
        this.mHumanInteractionClassifier.setType(0);
        this.mDataCollector.onQsDown();
    }

    public void setQsExpanded(boolean z) {
        this.mDataCollector.setQsExpanded(z);
    }

    public void onTrackingStarted(boolean z) {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onTrackingStarted", "");
        }
        this.mHumanInteractionClassifier.setType(z ? 8 : 4);
        this.mDataCollector.onTrackingStarted();
    }

    public void onTrackingStopped() {
        this.mDataCollector.onTrackingStopped();
    }

    public void onNotificationActive() {
        this.mDataCollector.onNotificationActive();
    }

    public void onNotificationDoubleTap(boolean z, float f, float f2) {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onNotificationDoubleTap", "accepted=" + z + " dx=" + f + " dy=" + f2 + " (px)");
        }
        this.mDataCollector.onNotificationDoubleTap();
    }

    public void setNotificationExpanded() {
        this.mDataCollector.setNotificationExpanded();
    }

    public void onNotificatonStartDraggingDown() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onNotificatonStartDraggingDown", "");
        }
        this.mHumanInteractionClassifier.setType(2);
        this.mDataCollector.onNotificatonStartDraggingDown();
    }

    public void onStartExpandingFromPulse() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onStartExpandingFromPulse", "");
        }
        this.mHumanInteractionClassifier.setType(9);
        this.mDataCollector.onStartExpandingFromPulse();
    }

    public void onNotificatonStopDraggingDown() {
        this.mDataCollector.onNotificatonStopDraggingDown();
    }

    public void onExpansionFromPulseStopped() {
        this.mDataCollector.onExpansionFromPulseStopped();
    }

    public void onNotificationDismissed() {
        this.mDataCollector.onNotificationDismissed();
    }

    public void onNotificatonStartDismissing() {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onNotificatonStartDismissing", "");
        }
        this.mHumanInteractionClassifier.setType(1);
        this.mDataCollector.onNotificatonStartDismissing();
    }

    public void onNotificatonStopDismissing() {
        this.mDataCollector.onNotificatonStopDismissing();
    }

    public void onCameraOn() {
        this.mDataCollector.onCameraOn();
    }

    public void onLeftAffordanceOn() {
        this.mDataCollector.onLeftAffordanceOn();
    }

    public void onAffordanceSwipingStarted(boolean z) {
        if (FalsingLog.ENABLED) {
            FalsingLog.m19i("onAffordanceSwipingStarted", "");
        }
        if (z) {
            this.mHumanInteractionClassifier.setType(6);
        } else {
            this.mHumanInteractionClassifier.setType(5);
        }
        this.mDataCollector.onAffordanceSwipingStarted(z);
    }

    public void onAffordanceSwipingAborted() {
        this.mDataCollector.onAffordanceSwipingAborted();
    }

    public void onUnlockHintStarted() {
        this.mDataCollector.onUnlockHintStarted();
    }

    public void onCameraHintStarted() {
        this.mDataCollector.onCameraHintStarted();
    }

    public void onLeftAffordanceHintStarted() {
        this.mDataCollector.onLeftAffordanceHintStarted();
    }

    public void onTouchEvent(MotionEvent motionEvent, int i, int i2) {
        if (motionEvent.getAction() == 0) {
            this.mIsTouchScreen = motionEvent.isFromSource(4098);
            this.mBouncerOffOnDown = !this.mBouncerOn;
        }
        if (this.mSessionActive) {
            if (!this.mBouncerOn) {
                this.mDataCollector.onTouchEvent(motionEvent, i, i2);
            }
            if (this.mBouncerOffOnDown) {
                this.mHumanInteractionClassifier.onTouchEvent(motionEvent);
            }
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("FALSING MANAGER");
        printWriter.print("classifierEnabled=");
        printWriter.println(isClassiferEnabled() ? 1 : 0);
        printWriter.print("mSessionActive=");
        printWriter.println(this.mSessionActive ? 1 : 0);
        printWriter.print("mBouncerOn=");
        printWriter.println(this.mSessionActive ? 1 : 0);
        printWriter.print("mState=");
        printWriter.println(StatusBarState.toShortString(this.mState));
        printWriter.print("mScreenOn=");
        printWriter.println(this.mScreenOn ? 1 : 0);
        printWriter.println();
    }

    public void cleanup() {
        this.mSensorManager.unregisterListener(this.mSensorEventListener);
        this.mContext.getContentResolver().unregisterContentObserver(this.mSettingsObserver);
        ((StatusBarStateController) Dependency.get(StatusBarStateController.class)).removeCallback(this.mStatusBarStateListener);
        KeyguardUpdateMonitor.getInstance(this.mContext).removeCallback(this.mKeyguardUpdateCallback);
    }

    public Uri reportRejectedTouch() {
        if (this.mDataCollector.isEnabled()) {
            return this.mDataCollector.reportRejectedTouch();
        }
        return null;
    }

    public boolean isReportingEnabled() {
        return this.mDataCollector.isReportingEnabled();
    }
}
