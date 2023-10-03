package p000;

/* renamed from: crs */
/* compiled from: PG */
final /* synthetic */ class crs implements hpr {

    /* renamed from: a */
    private final cru f5510a;

    public crs(cru cru) {
        this.f5510a = cru;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        cru cru = this.f5510a;
        ehh ehh = (ehh) obj;
        synchronized (cru.f5513a) {
            crw crw = cru.f5514b.f5521e;
            int a = eke.m7659a(ehh.f8293c);
            if (a == 0) {
                a = 1;
            }
            int i = crw.f5526c;
            if (i == 0) {
                throw null;
            } else if (i == 4) {
                crw.f5526c = a;
            }
        }
        return null;
    }
}
