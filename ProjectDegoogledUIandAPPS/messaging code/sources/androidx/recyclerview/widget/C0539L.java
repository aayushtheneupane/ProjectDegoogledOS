package androidx.recyclerview.widget;

import android.view.animation.Interpolator;

/* renamed from: androidx.recyclerview.widget.L */
final class C0539L implements Interpolator {
    C0539L() {
    }

    public float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * f2 * f2 * f2 * f2) + 1.0f;
    }
}
