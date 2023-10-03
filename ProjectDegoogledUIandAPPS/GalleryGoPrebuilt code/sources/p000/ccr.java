package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: ccr */
/* compiled from: PG */
final /* synthetic */ class ccr implements hpr {

    /* renamed from: a */
    private final ccy f4070a;

    public ccr(ccy ccy) {
        this.f4070a = ccy;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        ccy ccy = this.f4070a;
        List list = (List) obj;
        synchronized (ccy.f4077a) {
            ccy.f4084h.f4066b = Optional.m16285of(list);
        }
        return list;
    }
}
