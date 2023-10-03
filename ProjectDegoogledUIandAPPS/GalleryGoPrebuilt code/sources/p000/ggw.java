package p000;

import android.graphics.RectF;
import java.util.Arrays;

/* renamed from: ggw */
/* compiled from: PG */
public final class ggw implements ggo {

    /* renamed from: a */
    private final float f11317a;

    public ggw(float f) {
        this.f11317a = f;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ggw) && this.f11317a == ((ggw) obj).f11317a;
        }
        return true;
    }

    /* renamed from: a */
    public final float mo6621a(RectF rectF) {
        return this.f11317a * rectF.height();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f11317a)});
    }
}
