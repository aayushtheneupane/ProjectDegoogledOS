package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: hgx */
/* compiled from: PG */
public final class hgx {

    /* renamed from: a */
    public hgz f12717a;

    /* renamed from: b */
    private long f12718b = -1;

    private hgx() {
    }

    public /* synthetic */ hgx(byte[] bArr) {
    }

    /* renamed from: a */
    public final hgy mo7433a() {
        return new hgt(this.f12717a, this.f12718b);
    }

    /* renamed from: a */
    public final void mo7434a(long j, TimeUnit timeUnit) {
        ife.m12890c(true);
        this.f12718b = TimeUnit.MILLISECONDS.convert(j, timeUnit);
    }
}
