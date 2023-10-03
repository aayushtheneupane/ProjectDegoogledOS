package p000;

import android.graphics.RectF;
import java.util.Arrays;

/* renamed from: ggn */
/* compiled from: PG */
public final class ggn implements ggo {

    /* renamed from: a */
    private final ggo f11270a;

    /* renamed from: b */
    private final float f11271b;

    public ggn(float f, ggo ggo) {
        while (ggo instanceof ggn) {
            ggo = ((ggn) ggo).f11270a;
            f += ((ggn) ggo).f11271b;
        }
        this.f11270a = ggo;
        this.f11271b = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ggn) {
            ggn ggn = (ggn) obj;
            return this.f11270a.equals(ggn.f11270a) && this.f11271b == ggn.f11271b;
        }
    }

    /* renamed from: a */
    public final float mo6621a(RectF rectF) {
        return Math.max(0.0f, this.f11270a.mo6621a(rectF) + this.f11271b);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f11270a, Float.valueOf(this.f11271b)});
    }
}
