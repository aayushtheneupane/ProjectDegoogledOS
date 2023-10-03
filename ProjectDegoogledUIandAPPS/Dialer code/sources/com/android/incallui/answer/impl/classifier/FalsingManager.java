package com.android.incallui.answer.impl.classifier;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.os.Trace;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityManager;

public class FalsingManager implements SensorEventListener {
    private static final int[] CLASSIFIER_SENSORS = {8};
    private final HumanInteractionClassifier humanInteractionClassifier;
    private boolean screenOn;
    private final SensorManager sensorManager;
    private boolean sessionActive = false;

    public FalsingManager(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService(SensorManager.class);
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        this.humanInteractionClassifier = new HumanInteractionClassifier(context);
        this.screenOn = ((PowerManager) context.getSystemService(PowerManager.class)).isInteractive();
    }

    private boolean shouldSessionBeActive() {
        return this.humanInteractionClassifier.isEnabled() && this.screenOn;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onScreenOff() {
        this.screenOn = false;
        if (this.sessionActive && !shouldSessionBeActive()) {
            this.sessionActive = false;
            this.sensorManager.unregisterListener(this);
        }
    }

    public void onScreenOn() {
        this.screenOn = true;
        if (!this.sessionActive && shouldSessionBeActive()) {
            this.sessionActive = true;
            if (this.humanInteractionClassifier.isEnabled()) {
                int[] iArr = CLASSIFIER_SENSORS;
                Trace.beginSection("FalsingManager.registerSensors");
                for (int i : iArr) {
                    Trace.beginSection("get sensor " + i);
                    Sensor defaultSensor = this.sensorManager.getDefaultSensor(i);
                    Trace.endSection();
                    if (defaultSensor != null) {
                        Trace.beginSection("register");
                        this.sensorManager.registerListener(this, defaultSensor, 1);
                        Trace.endSection();
                    }
                }
                Trace.endSection();
            }
        }
    }

    public synchronized void onSensorChanged(SensorEvent sensorEvent) {
        this.humanInteractionClassifier.onSensorChanged(sensorEvent);
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        if (this.sessionActive) {
            this.humanInteractionClassifier.onTouchEvent(motionEvent);
        }
    }
}
