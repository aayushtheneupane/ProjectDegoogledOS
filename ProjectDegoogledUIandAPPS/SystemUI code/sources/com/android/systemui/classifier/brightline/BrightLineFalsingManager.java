package com.android.systemui.classifier.brightline;

import android.hardware.biometrics.BiometricSourceType;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.util.ProximitySensor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BrightLineFalsingManager implements FalsingManager {
    private final List<FalsingClassifier> mClassifiers;
    private final FalsingDataProvider mDataProvider;
    private int mIsFalseTouchCalls;
    /* access modifiers changed from: private */
    public boolean mJustUnlockedWithFace;
    private final KeyguardUpdateMonitorCallback mKeyguardUpdateCallback = new KeyguardUpdateMonitorCallback() {
        public void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType) {
            if (i == KeyguardUpdateMonitor.getCurrentUser() && biometricSourceType == BiometricSourceType.FACE) {
                boolean unused = BrightLineFalsingManager.this.mJustUnlockedWithFace = true;
            }
        }
    };
    private final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    private MetricsLogger mMetricsLogger;
    private final ProximitySensor mProximitySensor;
    private boolean mScreenOn;
    private ProximitySensor.ProximitySensorListener mSensorEventListener = new ProximitySensor.ProximitySensorListener() {
        public final void onProximitySensorEvent(ProximitySensor.ProximityEvent proximityEvent) {
            BrightLineFalsingManager.this.onProximityEvent(proximityEvent);
        }
    };
    private boolean mSessionStarted;
    private boolean mShowingAod;

    public void dump(PrintWriter printWriter) {
    }

    public boolean isClassiferEnabled() {
        return true;
    }

    public boolean isReportingEnabled() {
        return false;
    }

    public boolean isUnlockingDisabled() {
        return false;
    }

    public void onAffordanceSwipingAborted() {
    }

    public void onBouncerHidden() {
    }

    public void onBouncerShown() {
    }

    public void onCameraHintStarted() {
    }

    public void onCameraOn() {
    }

    public void onExpansionFromPulseStopped() {
    }

    public void onLeftAffordanceHintStarted() {
    }

    public void onLeftAffordanceOn() {
    }

    public void onNotificationActive() {
    }

    public void onNotificationDismissed() {
    }

    public void onNotificationDoubleTap(boolean z, float f, float f2) {
    }

    public void onNotificatonStopDismissing() {
    }

    public void onNotificatonStopDraggingDown() {
    }

    public void onTrackingStopped() {
    }

    public void onUnlockHintStarted() {
    }

    public Uri reportRejectedTouch() {
        return null;
    }

    public void setNotificationExpanded() {
    }

    public void setQsExpanded(boolean z) {
    }

    public boolean shouldEnforceBouncer() {
        return false;
    }

    public BrightLineFalsingManager(FalsingDataProvider falsingDataProvider, KeyguardUpdateMonitor keyguardUpdateMonitor, ProximitySensor proximitySensor) {
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mDataProvider = falsingDataProvider;
        this.mProximitySensor = proximitySensor;
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateCallback);
        this.mMetricsLogger = new MetricsLogger();
        this.mClassifiers = new ArrayList();
        DistanceClassifier distanceClassifier = new DistanceClassifier(this.mDataProvider);
        ProximityClassifier proximityClassifier = new ProximityClassifier(distanceClassifier, this.mDataProvider);
        this.mClassifiers.add(new PointerCountClassifier(this.mDataProvider));
        this.mClassifiers.add(new TypeClassifier(this.mDataProvider));
        this.mClassifiers.add(new DiagonalClassifier(this.mDataProvider));
        this.mClassifiers.add(distanceClassifier);
        this.mClassifiers.add(proximityClassifier);
        this.mClassifiers.add(new ZigZagClassifier(this.mDataProvider));
    }

    private void registerSensors() {
        this.mProximitySensor.register(this.mSensorEventListener);
    }

    private void unregisterSensors() {
        this.mProximitySensor.unregister(this.mSensorEventListener);
    }

    private void sessionStart() {
        if (!this.mSessionStarted && !this.mShowingAod && this.mScreenOn) {
            this.mSessionStarted = true;
            this.mJustUnlockedWithFace = false;
            registerSensors();
            this.mClassifiers.forEach($$Lambda$HclOlu42IVtKALxwbwHP3Y1rdRk.INSTANCE);
        }
    }

    private void sessionEnd() {
        if (this.mSessionStarted) {
            this.mSessionStarted = false;
            unregisterSensors();
            this.mDataProvider.onSessionEnd();
            this.mClassifiers.forEach($$Lambda$47wU6WxQ76Gt_ecwypSCrFl04Q.INSTANCE);
            int i = this.mIsFalseTouchCalls;
            if (i != 0) {
                this.mMetricsLogger.histogram("falsing_failure_after_attempts", i);
                this.mIsFalseTouchCalls = 0;
            }
        }
    }

    private void updateInteractionType(int i) {
        "InteractionType: " + i;
        this.mClassifiers.forEach(new Consumer(i) {
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final void accept(Object obj) {
                ((FalsingClassifier) obj).setInteractionType(this.f$0);
            }
        });
    }

    public boolean isFalseTouch() {
        boolean z = !this.mJustUnlockedWithFace && this.mClassifiers.stream().anyMatch($$Lambda$BrightLineFalsingManager$Hwyy_7VqHdYEMuILU__cqMTjCOk.INSTANCE);
        "Is false touch? " + z;
        return z;
    }

    static /* synthetic */ boolean lambda$isFalseTouch$1(FalsingClassifier falsingClassifier) {
        boolean isFalseTouch = falsingClassifier.isFalseTouch();
        if (isFalseTouch) {
            logInfo(falsingClassifier.getClass().getName() + ": true");
        } else {
            falsingClassifier.getClass().getName() + ": false";
        }
        return isFalseTouch;
    }

    public void onTouchEvent(MotionEvent motionEvent, int i, int i2) {
        this.mDataProvider.onMotionEvent(motionEvent);
        this.mClassifiers.forEach(new Consumer(motionEvent) {
            private final /* synthetic */ MotionEvent f$0;

            {
                this.f$0 = r1;
            }

            public final void accept(Object obj) {
                ((FalsingClassifier) obj).onTouchEvent(this.f$0);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onProximityEvent(ProximitySensor.ProximityEvent proximityEvent) {
        this.mClassifiers.forEach(new Consumer() {
            public final void accept(Object obj) {
                ((FalsingClassifier) obj).onProximityEvent(ProximitySensor.ProximityEvent.this);
            }
        });
    }

    public void onSucccessfulUnlock() {
        int i = this.mIsFalseTouchCalls;
        if (i != 0) {
            this.mMetricsLogger.histogram("falsing_success_after_attempts", i);
            this.mIsFalseTouchCalls = 0;
        }
        sessionEnd();
    }

    public void setShowingAod(boolean z) {
        this.mShowingAod = z;
        if (z) {
            sessionEnd();
        } else {
            sessionStart();
        }
    }

    public void onNotificatonStartDraggingDown() {
        updateInteractionType(2);
    }

    public void onQsDown() {
        updateInteractionType(0);
    }

    public void onTrackingStarted(boolean z) {
        updateInteractionType(z ? 8 : 4);
    }

    public void onAffordanceSwipingStarted(boolean z) {
        updateInteractionType(z ? 6 : 5);
    }

    public void onStartExpandingFromPulse() {
        updateInteractionType(9);
    }

    public void onScreenOnFromTouch() {
        onScreenTurningOn();
    }

    public void onScreenTurningOn() {
        this.mScreenOn = true;
        sessionStart();
    }

    public void onScreenOff() {
        this.mScreenOn = false;
        sessionEnd();
    }

    public void onNotificatonStartDismissing() {
        updateInteractionType(1);
    }

    public void cleanup() {
        unregisterSensors();
        this.mKeyguardUpdateMonitor.removeCallback(this.mKeyguardUpdateCallback);
    }

    static void logInfo(String str) {
        Log.i("FalsingManagerPlugin", str);
    }
}
