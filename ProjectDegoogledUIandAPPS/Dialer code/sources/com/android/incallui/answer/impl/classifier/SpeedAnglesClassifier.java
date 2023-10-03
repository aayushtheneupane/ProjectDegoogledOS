package com.android.incallui.answer.impl.classifier;

import android.util.ArrayMap;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class SpeedAnglesClassifier extends StrokeClassifier {
    private Map<Stroke, Data> strokeMap = new ArrayMap();

    private static class Data {
        private float acceleratingAngles = 0.0f;
        private float anglesCount = 0.0f;
        private float count = 1.0f;
        private float dist = 0.0f;
        private List<Point> lastThreePoints = new ArrayList();
        private float previousAngle = 3.1415927f;
        private Point previousPoint = null;
        private float sum = 0.0f;
        private float sumSquares = 0.0f;

        public void addPoint(Point point) {
            Point point2 = this.previousPoint;
            if (point2 != null) {
                this.dist += point2.dist(point);
            }
            this.previousPoint = point;
            Point point3 = new Point(((float) point.timeOffsetNano) / 1.0E8f, this.dist / 1.0f);
            if (!this.lastThreePoints.isEmpty()) {
                List<Point> list = this.lastThreePoints;
                if (list.get(list.size() - 1).equals(point3)) {
                    return;
                }
            }
            this.lastThreePoints.add(point3);
            if (this.lastThreePoints.size() == 4) {
                this.lastThreePoints.remove(0);
                float angle = this.lastThreePoints.get(1).getAngle(this.lastThreePoints.get(0), this.lastThreePoints.get(2));
                this.anglesCount += 1.0f;
                if (angle >= 2.8274336f) {
                    this.acceleratingAngles += 1.0f;
                }
                float f = angle - this.previousAngle;
                this.sum += f;
                this.sumSquares = (f * f) + this.sumSquares;
                this.count += 1.0f;
                this.previousAngle = angle;
            }
        }

        public float getAnglesPercentage() {
            float f = this.anglesCount;
            if (f == 0.0f) {
                return 1.0f;
            }
            return this.acceleratingAngles / f;
        }

        public float getAnglesVariance() {
            float f = this.sumSquares;
            float f2 = this.count;
            float f3 = this.sum;
            return (f / f2) - ((f3 / f2) * (f3 / f2));
        }
    }

    public SpeedAnglesClassifier(ClassifierData classifierData) {
        this.classifierData = classifierData;
    }

    public float getFalseTouchEvaluation(Stroke stroke) {
        Data data = this.strokeMap.get(stroke);
        double anglesVariance = (double) data.getAnglesVariance();
        float f = 0.0f;
        float f2 = anglesVariance > 0.06d ? 1.0f : 0.0f;
        if (anglesVariance > 0.15d) {
            f2 += 1.0f;
        }
        if (anglesVariance > 0.3d) {
            f2 += 1.0f;
        }
        if (anglesVariance > 0.6d) {
            f2 += 1.0f;
        }
        double anglesPercentage = (double) data.getAnglesPercentage();
        if (anglesPercentage < 1.0d) {
            f = 1.0f;
        }
        if (anglesPercentage < 0.9d) {
            f += 1.0f;
        }
        if (anglesPercentage < 0.7d) {
            f += 1.0f;
        }
        return f2 + f;
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.strokeMap.clear();
        }
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            Stroke stroke = this.classifierData.getStroke(motionEvent.getPointerId(i));
            if (this.strokeMap.get(stroke) == null) {
                this.strokeMap.put(stroke, new Data());
            }
            if (!(actionMasked == 1 || actionMasked == 3 || (actionMasked == 6 && i == motionEvent.getActionIndex()))) {
                this.strokeMap.get(stroke).addPoint(stroke.getPoints().get(stroke.getPoints().size() - 1));
            }
        }
    }
}
