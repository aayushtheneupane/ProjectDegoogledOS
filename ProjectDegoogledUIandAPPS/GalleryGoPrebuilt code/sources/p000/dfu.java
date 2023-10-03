package p000;

import java.util.List;

/* renamed from: dfu */
/* compiled from: PG */
public final class dfu extends iox {

    /* renamed from: b */
    private final ioq f6469b;

    /* renamed from: c */
    private final ioq f6470c;

    public dfu(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2) {
        super(iqk2, iph.m14288a(dfu.class), iqk);
        this.f6469b = ipd.m14274a(ioq);
        this.f6470c = ipd.m14274a(ioq2);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        return dfj.m6044b((cyr) list.get(0), (dgb) list.get(1));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6469b.mo9044b(), this.f6470c.mo9044b());
    }
}
