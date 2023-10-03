package com.android.incallui.answer.impl.classifier;

class EndPointLengthClassifier extends StrokeClassifier {
    public EndPointLengthClassifier(ClassifierData classifierData) {
        this.classifierData = classifierData;
    }

    public float getFalseTouchEvaluation(Stroke stroke) {
        double endPointLength = (double) stroke.getEndPointLength();
        float f = endPointLength < 0.05d ? 2.0f : 0.0f;
        if (endPointLength < 0.1d) {
            f += 2.0f;
        }
        if (endPointLength < 0.2d) {
            f += 2.0f;
        }
        if (endPointLength < 0.3d) {
            f += 2.0f;
        }
        if (endPointLength < 0.4d) {
            f += 2.0f;
        }
        return endPointLength < 0.5d ? f + 2.0f : f;
    }
}
