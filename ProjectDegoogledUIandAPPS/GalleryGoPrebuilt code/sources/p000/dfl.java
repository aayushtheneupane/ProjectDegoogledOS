package p000;

import java.util.List;

/* renamed from: dfl */
/* compiled from: PG */
public final class dfl extends iox {

    /* renamed from: b */
    private final ioq f6446b;

    /* renamed from: c */
    private final ioq f6447c;

    /* renamed from: d */
    private final ioq f6448d;

    /* renamed from: e */
    private final ioq f6449e;

    public dfl(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4) {
        super(iqk2, iph.m14288a(dfl.class), iqk);
        this.f6446b = ipd.m14274a(ioq);
        this.f6447c = ipd.m14274a(ioq2);
        this.f6448d = ipd.m14274a(ioq3);
        this.f6449e = ipd.m14274a(ioq4);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        boolean booleanValue = ((Boolean) list.get(0)).booleanValue();
        boolean booleanValue2 = ((Boolean) list.get(1)).booleanValue();
        boolean booleanValue3 = ((Boolean) list.get(2)).booleanValue();
        bip bip = (bip) list.get(3);
        return ife.m12820a((Object) Boolean.valueOf(dfj.m6042a(booleanValue, booleanValue2, booleanValue3)));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6446b.mo9044b(), this.f6447c.mo9044b(), this.f6448d.mo9044b(), this.f6449e.mo9044b());
    }
}
