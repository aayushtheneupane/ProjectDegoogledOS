package p000;

import android.graphics.RectF;
import java.util.Arrays;

/* renamed from: ggm */
/* compiled from: PG */
public final class ggm implements ggo {

    /* renamed from: a */
    private final float f11269a;

    public ggm(float f) {
        this.f11269a = f;
    }

    /* renamed from: a */
    public final float mo6621a(RectF rectF) {
        return this.f11269a;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ggm) && this.f11269a == ((ggm) obj).f11269a;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f11269a)});
    }
}
