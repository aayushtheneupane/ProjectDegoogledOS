package p000;

import android.view.animation.Interpolator;

/* renamed from: acd */
/* compiled from: PG */
class acd implements Interpolator {

    /* renamed from: a */
    private final float[] f175a;

    /* renamed from: b */
    private final float f176b;

    protected acd(float[] fArr) {
        this.f175a = fArr;
        this.f176b = 1.0f / ((float) (fArr.length - 1));
    }

    public final float getInterpolation(float f) {
        float f2 = 1.0f;
        if (f < 1.0f) {
            f2 = 0.0f;
            if (f > 0.0f) {
                int length = this.f175a.length;
                int min = Math.min((int) (((float) (length - 1)) * f), length - 2);
                float f3 = this.f176b;
                float[] fArr = this.f175a;
                float f4 = fArr[min];
                return f4 + (((f - (((float) min) * f3)) / f3) * (fArr[min + 1] - f4));
            }
        }
        return f2;
    }
}
