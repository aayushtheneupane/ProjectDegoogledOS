package p000;

import java.util.List;

/* renamed from: crr */
/* compiled from: PG */
final /* synthetic */ class crr implements hpr {

    /* renamed from: a */
    private final cru f5508a;

    /* renamed from: b */
    private final crp f5509b;

    public crr(cru cru, crp crp) {
        this.f5508a = cru;
        this.f5509b = crp;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        cru cru = this.f5508a;
        crp crp = this.f5509b;
        List list = (List) obj;
        synchronized (cru.f5513a) {
            crp.mo3790b(!list.isEmpty());
            crp.mo3789a(!list.isEmpty());
        }
        return null;
    }
}
