package com.android.systemui.doze;

public interface DozeHost {

    public interface Callback {
        void onNotificationAlerted(Runnable runnable) {
        }

        void onPowerSaveChanged(boolean z) {
        }

        void toggleFlashlightProximityCheck() {
        }

        void wakeUpFromDoubleTap(int i) {
        }
    }

    public interface PulseCallback {
        void onPulseFinished();

        void onPulseStarted();
    }

    void addCallback(Callback callback);

    void dozeTimeTick();

    void extendPulse(int i);

    boolean isBlockingDoze();

    boolean isPowerSaveActive();

    boolean isProvisioned();

    boolean isPulsingBlocked();

    void onIgnoreTouchWhilePulsing(boolean z);

    void onSlpiTap(float f, float f2, int i);

    void performToggleFlashlight();

    void pulseWhileDozing(PulseCallback pulseCallback, int i);

    void removeCallback(Callback callback);

    void setAnimateScreenOff(boolean z);

    void setAnimateWakeup(boolean z);

    void setAodDimmingScrim(float f) {
    }

    void setDozeScreenBrightness(int i);

    void startDozing();

    void stopDozing();
}
