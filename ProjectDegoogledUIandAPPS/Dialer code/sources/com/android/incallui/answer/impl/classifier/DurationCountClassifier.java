package com.android.incallui.answer.impl.classifier;

class DurationCountClassifier extends StrokeClassifier {
    public float getFalseTouchEvaluation(Stroke stroke) {
        double durationSeconds = (double) (stroke.getDurationSeconds() / ((float) stroke.getCount()));
        float f = durationSeconds < 0.0105d ? 1.0f : 0.0f;
        if (durationSeconds < 0.00909d) {
            f += 1.0f;
        }
        if (durationSeconds < 0.00667d) {
            f += 1.0f;
        }
        if (durationSeconds > 0.0333d) {
            f += 1.0f;
        }
        return durationSeconds > 0.05d ? f + 1.0f : f;
    }
}
