package com.android.incallui.answer.impl.classifier;

import android.hardware.SensorEvent;
import android.view.MotionEvent;
import java.util.concurrent.TimeUnit;

class ProximityClassifier extends GestureClassifier {
    private float averageNear;
    private long gestureStartTimeNano;
    private boolean near;
    private long nearDuration;
    private long nearStartTimeNano;

    private void update(boolean z, long j) {
        long j2 = this.nearStartTimeNano;
        if (j > j2) {
            if (this.near) {
                this.nearDuration = (j - j2) + this.nearDuration;
            }
            if (z) {
                this.nearStartTimeNano = j;
            }
        }
        this.near = z;
    }

    public float getFalseTouchEvaluation() {
        return this.averageNear >= 0.1f ? 2.0f : 0.0f;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 8) {
            boolean z = false;
            if (sensorEvent.values[0] < sensorEvent.sensor.getMaximumRange()) {
                z = true;
            }
            update(z, sensorEvent.timestamp);
        }
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.gestureStartTimeNano = TimeUnit.MILLISECONDS.toNanos(motionEvent.getEventTime());
            this.nearStartTimeNano = TimeUnit.MILLISECONDS.toNanos(motionEvent.getEventTime());
            this.nearDuration = 0;
        }
        if (actionMasked == 1 || actionMasked == 3) {
            update(this.near, TimeUnit.MILLISECONDS.toNanos(motionEvent.getEventTime()));
            long nanos = TimeUnit.MILLISECONDS.toNanos(motionEvent.getEventTime()) - this.gestureStartTimeNano;
            if (nanos == 0) {
                this.averageNear = this.near ? 1.0f : 0.0f;
            } else {
                this.averageNear = ((float) this.nearDuration) / ((float) nanos);
            }
        }
    }
}
