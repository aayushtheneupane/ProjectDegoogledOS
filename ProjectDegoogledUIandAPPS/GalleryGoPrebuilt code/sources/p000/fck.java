package p000;

import java.util.concurrent.Executor;

@Deprecated
/* renamed from: fck */
/* compiled from: PG */
public final class fck implements fch {

    /* renamed from: a */
    public final hpr f9264a;

    /* renamed from: b */
    private final exb f9265b;

    private fck(exb exb, hpr hpr) {
        this.f9264a = hpr;
        this.f9265b = exb;
    }

    /* renamed from: a */
    public final void mo5482a(Executor executor, fcf fcf) {
        this.f9265b.mo5357a(executor, (eww) new fcj(fcf));
    }

    /* renamed from: a */
    public final void mo5483a(Executor executor, fcg fcg) {
        this.f9265b.mo5358a(executor, (ewz) new fci(this, fcg));
    }

    /* renamed from: a */
    public static fch m8571a(exb exb) {
        return m8572a(exb, (hpr) hpu.f13234a);
    }

    /* renamed from: a */
    public static fch m8572a(exb exb, hpr hpr) {
        return new fck(exb, hpr);
    }
}
