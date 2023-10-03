package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: esw */
/* compiled from: PG */
public final class esw {

    /* renamed from: a */
    private long f8960a = -1;

    /* renamed from: b */
    private long f8961b = -1;

    /* renamed from: a */
    public final long mo5223a() {
        abj.m116b(this.f8961b != -1);
        return TimeUnit.NANOSECONDS.toMillis(m8131c() - this.f8961b);
    }

    /* renamed from: c */
    private final long m8131c() {
        long j = this.f8960a;
        if (j == -1) {
            return System.nanoTime();
        }
        this.f8960a = -1;
        return j;
    }

    /* renamed from: b */
    public final void mo5224b() {
        this.f8961b = m8131c();
    }
}
