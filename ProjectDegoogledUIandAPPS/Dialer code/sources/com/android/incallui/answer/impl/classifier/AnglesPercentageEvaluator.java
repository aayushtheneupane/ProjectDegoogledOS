package com.android.incallui.answer.impl.classifier;

class AnglesPercentageEvaluator {
    public static float evaluate8(float f) {
        double d = (double) f;
        float f2 = d < 4.0d ? 1.0f : 0.0f;
        if (d < 2.2d) {
            f2 += 1.0f;
        }
        if (d > 35.0d) {
            f2 += 1.0f;
        }
        return d > 50.0d ? f2 + 1.0f : f2;
    }
}
