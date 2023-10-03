package p000;

import java.util.List;

/* renamed from: dac */
/* compiled from: PG */
public final class dac extends iox {

    /* renamed from: b */
    private final ioq f6140b;

    /* renamed from: c */
    private final ioq f6141c;

    /* renamed from: d */
    private final ioq f6142d;

    public dac(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(dac.class), iqk);
        this.f6140b = ipd.m14274a(ioq);
        this.f6141c = ipd.m14274a(ioq2);
        this.f6142d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        return czu.m5778a((List) list.get(0), (dip) list.get(1), (iek) list.get(2));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6140b.mo9044b(), this.f6141c.mo9044b(), this.f6142d.mo9044b());
    }
}
