package com.android.incallui.answer.impl.classifier;

class SpeedClassifier extends StrokeClassifier {
    public float getFalseTouchEvaluation(Stroke stroke) {
        float durationSeconds = stroke.getDurationSeconds();
        if (durationSeconds == 0.0f) {
            return AnglesPercentageEvaluator.evaluate8(0.0f);
        }
        return AnglesPercentageEvaluator.evaluate8(stroke.getTotalLength() / durationSeconds);
    }
}
