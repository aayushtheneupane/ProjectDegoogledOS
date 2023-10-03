package p000;

import android.view.animation.Interpolator;

/* renamed from: xc */
/* compiled from: PG */
public final class C0630xc implements Interpolator {
    public final float getInterpolation(float f) {
        float f2 = f - 4.0f;
        return (f2 * f2 * f2 * f2 * f2) + 1.0f;
    }
}
