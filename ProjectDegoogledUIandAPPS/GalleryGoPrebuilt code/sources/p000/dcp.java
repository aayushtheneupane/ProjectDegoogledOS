package p000;

import java.util.List;

/* renamed from: dcp */
/* compiled from: PG */
public final class dcp extends iox {

    /* renamed from: b */
    private final ioq f6266b;

    /* renamed from: c */
    private final ioq f6267c;

    /* renamed from: d */
    private final ioq f6268d;

    public dcp(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(dcp.class), iqk);
        this.f6266b = ipd.m14274a(ioq);
        this.f6267c = ipd.m14274a(ioq2);
        this.f6268d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        boolean booleanValue = ((Boolean) list.get(0)).booleanValue();
        ble ble = (ble) list.get(1);
        iek iek = (iek) list.get(2);
        if (booleanValue) {
            return iek.mo5932a(hmq.m11748a((Runnable) new dby(ble)), bip.f2457a);
        }
        return ife.m12820a((Object) bip.f2457a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6266b.mo9044b(), this.f6267c.mo9044b(), this.f6268d.mo9044b());
    }
}
