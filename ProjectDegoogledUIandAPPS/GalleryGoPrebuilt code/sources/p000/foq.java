package p000;

import android.os.SystemClock;
import java.util.Random;

/* renamed from: foq */
/* compiled from: PG */
public final class foq {

    /* renamed from: a */
    private final float f10161a;

    /* renamed from: b */
    private final Random f10162b;

    private foq(float f, Random random) {
        boolean z = false;
        if (f >= 0.0f && f <= 1.0f) {
            z = true;
        }
        ife.m12845a(z, (Object) "Sampling rate should be a floating number >= 0 and <= 1.");
        this.f10161a = f;
        this.f10162b = random;
    }

    /* renamed from: a */
    public static foq m9328a(float f) {
        return new foq(f, new Random(SystemClock.elapsedRealtime()));
    }

    /* renamed from: a */
    public final boolean mo6000a() {
        return this.f10162b.nextFloat() < this.f10161a;
    }
}
