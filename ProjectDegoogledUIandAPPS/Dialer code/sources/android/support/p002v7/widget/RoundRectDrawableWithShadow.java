package android.support.p002v7.widget;

import android.graphics.drawable.Drawable;

/* renamed from: android.support.v7.widget.RoundRectDrawableWithShadow */
class RoundRectDrawableWithShadow extends Drawable {
    private static final double COS_45 = Math.cos(Math.toRadians(45.0d));

    static float calculateHorizontalPadding(float f, float f2, boolean z) {
        if (!z) {
            return f;
        }
        return (float) (((1.0d - COS_45) * ((double) f2)) + ((double) f));
    }

    static float calculateVerticalPadding(float f, float f2, boolean z) {
        if (!z) {
            return f * 1.5f;
        }
        return (float) (((1.0d - COS_45) * ((double) f2)) + ((double) (f * 1.5f)));
    }
}
