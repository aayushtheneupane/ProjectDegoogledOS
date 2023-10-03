package p000;

import android.view.animation.Interpolator;

/* renamed from: nt */
/* compiled from: PG */
final class C0377nt implements Interpolator {
    public final float getInterpolation(float f) {
        float f2 = f - 4.0f;
        return (f2 * f2 * f2 * f2 * f2) + 1.0f;
    }
}
