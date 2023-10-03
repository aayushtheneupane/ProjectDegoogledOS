package com.android.messaging.util;

import android.view.animation.Interpolator;

/* renamed from: com.android.messaging.util.x */
public class C1483x implements Interpolator {

    /* renamed from: RJ */
    private final float f2348RJ;

    /* renamed from: SJ */
    private final float f2349SJ;

    /* renamed from: TJ */
    private final float f2350TJ;

    /* renamed from: UJ */
    private final float f2351UJ;

    public C1483x(float f, float f2, float f3, float f4) {
        this.f2348RJ = f;
        this.f2349SJ = f2;
        this.f2350TJ = f3;
        this.f2351UJ = f4;
    }

    /* renamed from: a */
    private float m3832a(float f, float f2, float f3) {
        if (f == 0.0f || f == 1.0f) {
            return f;
        }
        float b = m3833b(0.0f, f2, f);
        float b2 = m3833b(f2, f3, f);
        return m3833b(m3833b(b, b2, f), m3833b(b2, m3833b(f3, 1.0f, f), f), f);
    }

    /* renamed from: b */
    private float m3833b(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }

    /* renamed from: e */
    private float m3834e(float f) {
        return m3832a(f, this.f2348RJ, this.f2350TJ);
    }

    public float getInterpolation(float f) {
        float f2;
        float f3 = 0.0f;
        if (f > 0.0f) {
            float f4 = 1.0f;
            if (f < 1.0f) {
                int i = 0;
                float f5 = 0.0f;
                float f6 = 0.0f;
                float f7 = 1.0f;
                int i2 = 0;
                f4 = f;
                while (true) {
                    if (i2 >= 8) {
                        break;
                    }
                    f6 = m3834e(f4);
                    double e = (double) ((m3834e(f4 + 1.0E-6f) - f6) / 1.0E-6f);
                    float f8 = f6 - f;
                    if (Math.abs(f8) < 1.0E-6f) {
                        break;
                    } else if (Math.abs(e) < 9.999999974752427E-7d) {
                        break;
                    } else {
                        if (f6 < f) {
                            f5 = f4;
                        } else {
                            f7 = f4;
                        }
                        f4 = (float) (((double) f4) - (((double) f8) / e));
                        i2++;
                    }
                }
                f3 = f4;
                while (Math.abs(f6 - f) > 1.0E-6f && i < 8) {
                    if (f6 < f) {
                        f2 = (f3 + f7) / 2.0f;
                        f5 = f3;
                    } else {
                        f2 = (f3 + f5) / 2.0f;
                        f7 = f3;
                    }
                    f3 = f2;
                    f6 = m3834e(f3);
                    i++;
                }
            }
            f3 = f4;
        }
        return m3832a(f3, this.f2349SJ, this.f2351UJ);
    }
}
