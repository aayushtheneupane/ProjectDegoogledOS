package p000;

import java.util.concurrent.Callable;

/* renamed from: hfx */
/* compiled from: PG */
public final class hfx {

    /* renamed from: a */
    public final hfq f12683a;

    /* renamed from: b */
    private final iek f12684b;

    /* renamed from: c */
    private final fxr f12685c;

    public hfx(iek iek, hfq hfq, fxr fxr) {
        this.f12684b = iek;
        this.f12683a = hfq;
        this.f12685c = fxr;
    }

    /* renamed from: a */
    public final fzx mo7385a(hfv hfv) {
        fzx a = gbz.m9993a(hfv.mo7373a(), this.f12684b.mo5933a((Callable) new hfw(this, hfv)), hfv.mo7374b(), this.f12684b, hfv.mo7377e(), hfv.mo7379f(), this.f12685c);
        if (!hfv.mo7376d().isEmpty()) {
            a.mo6361a(new fzm(hfv.mo7376d(), this.f12684b));
        }
        return a;
    }
}
