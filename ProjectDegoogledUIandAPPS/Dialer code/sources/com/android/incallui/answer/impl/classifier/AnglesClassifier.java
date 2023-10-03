package com.android.incallui.answer.impl.classifier;

import android.util.ArrayMap;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class AnglesClassifier extends StrokeClassifier {
    private Map<Stroke, Data> strokeMap = new ArrayMap();

    private static class Data {
        private float anglesCount = 0.0f;
        private float biggestAngle = 0.0f;
        private float count = 1.0f;
        private float firstAngleVariance = 0.0f;
        private float firstLength = 0.0f;
        private List<Point> lastThreePoints = new ArrayList();
        private float leftAngles = 0.0f;
        private float length = 0.0f;
        private float previousAngle = 3.1415927f;
        private float rightAngles = 0.0f;
        private float secondCount = 1.0f;
        private float secondSum = 0.0f;
        private float secondSumSquares = 0.0f;
        private float straightAngles = 0.0f;
        private float sum = 0.0f;
        private float sumSquares = 0.0f;

        public void addPoint(Point point) {
            if (!this.lastThreePoints.isEmpty()) {
                List<Point> list = this.lastThreePoints;
                if (!list.get(list.size() - 1).equals(point)) {
                    List<Point> list2 = this.lastThreePoints;
                    if (list2.get(list2.size() - 1).dist(point) <= 0.01f) {
                        return;
                    }
                } else {
                    return;
                }
            }
            if (!this.lastThreePoints.isEmpty()) {
                float f = this.length;
                List<Point> list3 = this.lastThreePoints;
                this.length = f + list3.get(list3.size() - 1).dist(point);
            }
            this.lastThreePoints.add(point);
            if (this.lastThreePoints.size() == 4) {
                this.lastThreePoints.remove(0);
                float angle = this.lastThreePoints.get(1).getAngle(this.lastThreePoints.get(0), this.lastThreePoints.get(2));
                this.anglesCount += 1.0f;
                double d = (double) angle;
                if (d < 2.9845130165391645d) {
                    this.leftAngles += 1.0f;
                } else if (d <= 3.298672290640422d) {
                    this.straightAngles += 1.0f;
                } else {
                    this.rightAngles += 1.0f;
                }
                float f2 = angle - this.previousAngle;
                if (this.biggestAngle < angle) {
                    this.biggestAngle = angle;
                    this.firstLength = this.length;
                    this.firstAngleVariance = getAnglesVariance(this.sumSquares, this.sum, this.count);
                    this.secondSumSquares = 0.0f;
                    this.secondSum = 0.0f;
                    this.secondCount = 1.0f;
                } else {
                    this.secondSum += f2;
                    this.secondSumSquares = (f2 * f2) + this.secondSumSquares;
                    this.secondCount += 1.0f;
                }
                this.sum += f2;
                this.sumSquares = (f2 * f2) + this.sumSquares;
                this.count += 1.0f;
                this.previousAngle = angle;
            }
        }

        public float getAnglesPercentage() {
            if (this.anglesCount == 0.0f) {
                return 1.0f;
            }
            return (Math.max(this.leftAngles, this.rightAngles) + this.straightAngles) / this.anglesCount;
        }

        public float getAnglesVariance() {
            float anglesVariance = getAnglesVariance(this.sumSquares, this.sum, this.count);
            return this.firstLength < this.length / 2.0f ? Math.min(anglesVariance, this.firstAngleVariance + getAnglesVariance(this.secondSumSquares, this.secondSum, this.secondCount)) : anglesVariance;
        }

        public float getAnglesVariance(float f, float f2, float f3) {
            float f4 = f2 / f3;
            return (f / f3) - (f4 * f4);
        }
    }

    public AnglesClassifier(ClassifierData classifierData) {
        this.classifierData = classifierData;
    }

    public float getFalseTouchEvaluation(Stroke stroke) {
        Data data = this.strokeMap.get(stroke);
        double anglesVariance = (double) data.getAnglesVariance();
        float f = 0.0f;
        float f2 = anglesVariance > 0.05d ? 1.0f : 0.0f;
        if (anglesVariance > 0.1d) {
            f2 += 1.0f;
        }
        if (anglesVariance > 0.2d) {
            f2 += 1.0f;
        }
        if (anglesVariance > 0.4d) {
            f2 += 1.0f;
        }
        if (anglesVariance > 0.8d) {
            f2 += 1.0f;
        }
        if (anglesVariance > 1.5d) {
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
        if (motionEvent.getActionMasked() == 0) {
            this.strokeMap.clear();
        }
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            Stroke stroke = this.classifierData.getStroke(motionEvent.getPointerId(i));
            if (this.strokeMap.get(stroke) == null) {
                this.strokeMap.put(stroke, new Data());
            }
            this.strokeMap.get(stroke).addPoint(stroke.getPoints().get(stroke.getPoints().size() - 1));
        }
    }
}
