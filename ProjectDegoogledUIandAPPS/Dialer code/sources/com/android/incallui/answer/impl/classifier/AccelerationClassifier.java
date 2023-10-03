package com.android.incallui.answer.impl.classifier;

import android.util.ArrayMap;
import android.view.MotionEvent;
import java.util.Map;

class AccelerationClassifier extends StrokeClassifier {
    private final Map<Stroke, Data> strokeMap = new ArrayMap();

    private static class Data {
        float maxSpeedRatio = 0.0f;
        Point previousPoint;
        float previousSpeed = 0.0f;

        public Data(Point point) {
            this.previousPoint = point;
        }
    }

    public AccelerationClassifier(ClassifierData classifierData) {
        this.classifierData = classifierData;
    }

    public float getFalseTouchEvaluation(Stroke stroke) {
        float f = this.strokeMap.get(stroke).maxSpeedRatio;
        float f2 = 0.0f;
        if (f != 0.0f) {
            double d = (double) f;
            if (d <= 1.0d) {
                f2 = 1.0f;
            }
            if (d <= 0.5d) {
                f2 += 1.0f;
            }
            if (d > 9.0d) {
                f2 += 1.0f;
            }
            if (d > 18.0d) {
                f2 += 1.0f;
            }
        }
        return f2 * 2.0f;
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.strokeMap.clear();
        }
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            Stroke stroke = this.classifierData.getStroke(motionEvent.getPointerId(i));
            Point point = stroke.getPoints().get(stroke.getPoints().size() - 1);
            if (this.strokeMap.get(stroke) == null) {
                this.strokeMap.put(stroke, new Data(point));
            } else {
                Data data = this.strokeMap.get(stroke);
                float dist = data.previousPoint.dist(point);
                float f = (float) ((point.timeOffsetNano - data.previousPoint.timeOffsetNano) + 1);
                float f2 = dist / f;
                if (f > 2.0E7f || f < 5000000.0f) {
                    data.previousSpeed = 0.0f;
                    data.previousPoint = point;
                } else {
                    float f3 = data.previousSpeed;
                    if (f3 != 0.0f) {
                        data.maxSpeedRatio = Math.max(data.maxSpeedRatio, f2 / f3);
                    }
                    data.previousSpeed = f2;
                    data.previousPoint = point;
                }
            }
        }
    }
}
