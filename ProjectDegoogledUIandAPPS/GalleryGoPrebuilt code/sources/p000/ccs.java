package p000;

import p003j$.util.Optional;

/* renamed from: ccs */
/* compiled from: PG */
final /* synthetic */ class ccs implements hpr {

    /* renamed from: a */
    private final ccy f4071a;

    public ccs(ccy ccy) {
        this.f4071a = ccy;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        ccy ccy = this.f4071a;
        Boolean bool = (Boolean) obj;
        synchronized (ccy.f4077a) {
            ccy.f4084h.f4067c = Optional.m16285of(Boolean.valueOf(bool.booleanValue()));
        }
        return bool;
    }
}
