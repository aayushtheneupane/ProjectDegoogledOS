package com.android.incallui.answer.impl.classifier;

class LengthCountClassifier extends StrokeClassifier {
    public float getFalseTouchEvaluation(Stroke stroke) {
        double totalLength = (double) (stroke.getTotalLength() / Math.max(1.0f, (float) (stroke.getCount() - 2)));
        float f = totalLength < 0.09d ? 1.0f : 0.0f;
        if (totalLength < 0.05d) {
            f += 1.0f;
        }
        if (totalLength < 0.02d) {
            f += 1.0f;
        }
        if (totalLength > 0.6d) {
            f += 1.0f;
        }
        if (totalLength > 0.9d) {
            f += 1.0f;
        }
        return totalLength > 1.2d ? f + 1.0f : f;
    }
}
