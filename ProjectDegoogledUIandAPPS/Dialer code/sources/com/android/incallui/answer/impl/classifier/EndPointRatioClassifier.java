package com.android.incallui.answer.impl.classifier;

class EndPointRatioClassifier extends StrokeClassifier {
    public EndPointRatioClassifier(ClassifierData classifierData) {
        this.classifierData = classifierData;
    }

    public float getFalseTouchEvaluation(Stroke stroke) {
        float f;
        float f2 = 0.0f;
        if (stroke.getTotalLength() == 0.0f) {
            f = 1.0f;
        } else {
            f = stroke.getEndPointLength() / stroke.getTotalLength();
        }
        double d = (double) f;
        if (d < 0.85d) {
            f2 = 1.0f;
        }
        if (d < 0.75d) {
            f2 += 1.0f;
        }
        if (d < 0.65d) {
            f2 += 1.0f;
        }
        if (d < 0.55d) {
            f2 += 1.0f;
        }
        if (d < 0.45d) {
            f2 += 1.0f;
        }
        return d < 0.35d ? f2 + 1.0f : f2;
    }
}
