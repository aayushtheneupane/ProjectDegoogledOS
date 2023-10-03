package com.android.incallui.answer.impl.classifier;

import android.hardware.SensorEvent;
import android.view.MotionEvent;

public class DirectionClassifier extends StrokeClassifier {
    public float getFalseTouchEvaluation(Stroke stroke) {
        Point point = stroke.getPoints().get(0);
        Point point2 = stroke.getPoints().get(stroke.getPoints().size() - 1);
        return Math.abs(point2.f41y - point.f41y) < Math.abs(point2.f40x - point.f40x) ? 5.5f : 0.0f;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
    }

    public void onTouchEvent(MotionEvent motionEvent) {
    }
}
