package p000;

import p003j$.util.Optional;

/* renamed from: crt */
/* compiled from: PG */
final /* synthetic */ class crt implements hpr {

    /* renamed from: a */
    private final cru f5511a;

    /* renamed from: b */
    private final crp f5512b;

    public crt(cru cru, crp crp) {
        this.f5511a = cru;
        this.f5512b = crp;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        cru cru = this.f5511a;
        crp crp = this.f5512b;
        dkj dkj = (dkj) obj;
        synchronized (cru.f5513a) {
            crp.f5503a = Optional.m16285of(dkj);
        }
        return null;
    }
}
