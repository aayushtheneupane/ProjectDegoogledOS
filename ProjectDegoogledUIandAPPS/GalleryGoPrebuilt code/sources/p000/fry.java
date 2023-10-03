package p000;

import android.animation.TimeInterpolator;

/* renamed from: fry */
/* compiled from: PG */
public final class fry extends frx implements TimeInterpolator {
    public fry() {
        this.f10337a = 0.52f;
        this.f10338b = 0.3f;
        this.f10339c = 0.12f;
        this.f10340d = 1.0f;
        this.f10342f = 1.0f;
        this.f10341e = 1.0f;
    }

    public final float getInterpolation(float f) {
        float f2;
        float f3 = (f + 0.0f) / (this.f10341e + 0.0f);
        if (f3 <= 0.0f) {
            f3 = 0.0f;
        } else if (f3 < 1.0f) {
            int i = 0;
            int i2 = 0;
            float f4 = 0.0f;
            float f5 = 1.0f;
            float f6 = 0.0f;
            while (true) {
                if (i2 < 8) {
                    f6 = mo6085a(f3);
                    float a = (mo6085a(f3 + 1.0E-6f) - f6) / 1.0E-6f;
                    float f7 = f6 - f;
                    if (Math.abs(f7) >= 1.0E-6f) {
                        if (Math.abs(a) < 1.0E-6f) {
                            break;
                        }
                        if (f6 >= f) {
                            f5 = f3;
                        }
                        if (f6 < f) {
                            f4 = f3;
                        }
                        f3 -= f7 / a;
                        i2++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            while (Math.abs(f6 - f) > 1.0E-6f && i < 8) {
                if (f6 >= f) {
                    f2 = (f3 + f4) / 2.0f;
                    f5 = f3;
                } else {
                    f2 = (f3 + f5) / 2.0f;
                    f4 = f3;
                }
                f3 = f2;
                f6 = mo6085a(f3);
                i++;
            }
        } else {
            f3 = 1.0f;
        }
        if (f3 == 0.0f) {
            return 0.0f;
        }
        if (f3 == 1.0f) {
            return this.f10342f;
        }
        float f8 = this.f10338b;
        float f9 = ((f8 + 0.0f) * f3) + 0.0f;
        float f10 = this.f10340d;
        float f11 = f8 + ((f10 - f8) * f3);
        float f12 = f9 + ((f11 - f9) * f3);
        return f12 + (f3 * ((f11 + (((f10 + ((this.f10342f - f10) * f3)) - f11) * f3)) - f12));
    }
}
