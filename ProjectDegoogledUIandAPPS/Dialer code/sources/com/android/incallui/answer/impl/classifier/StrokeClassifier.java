package com.android.incallui.answer.impl.classifier;

abstract class StrokeClassifier extends Classifier {
    StrokeClassifier() {
    }

    public abstract float getFalseTouchEvaluation(Stroke stroke);
}
