package p000;

import java.util.List;

/* renamed from: dfk */
/* compiled from: PG */
public final class dfk extends iox {

    /* renamed from: b */
    private final ioq f6443b;

    /* renamed from: c */
    private final ioq f6444c;

    /* renamed from: d */
    private final ioq f6445d;

    public dfk(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(dfk.class), iqk);
        this.f6443b = ipd.m14274a(ioq);
        this.f6444c = ipd.m14274a(ioq2);
        this.f6445d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        ((Boolean) list.get(0)).booleanValue();
        ((Boolean) list.get(1)).booleanValue();
        return dfj.m6030a((cjh) list.get(2));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6443b.mo9044b(), this.f6444c.mo9044b(), this.f6445d.mo9044b());
    }
}
