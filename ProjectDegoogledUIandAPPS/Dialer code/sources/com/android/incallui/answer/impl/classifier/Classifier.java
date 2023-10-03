package com.android.incallui.answer.impl.classifier;

import android.hardware.SensorEvent;
import android.view.MotionEvent;

abstract class Classifier {
    protected ClassifierData classifierData;

    Classifier() {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
    }

    public void onTouchEvent(MotionEvent motionEvent) {
    }
}
