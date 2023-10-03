package com.android.systemui.navigation.pulse;

import java.util.ArrayDeque;

class FFTAverage {
    private float average;
    private ArrayDeque<Float> window = new ArrayDeque<>(2);

    FFTAverage() {
    }

    /* access modifiers changed from: package-private */
    public int average(int i) {
        Float pollFirst;
        if (this.window.size() >= 2 && (pollFirst = this.window.pollFirst()) != null) {
            this.average -= pollFirst.floatValue();
        }
        float f = ((float) i) / 2.0f;
        this.average += f;
        this.window.offerLast(Float.valueOf(f));
        return Math.round(this.average);
    }
}
