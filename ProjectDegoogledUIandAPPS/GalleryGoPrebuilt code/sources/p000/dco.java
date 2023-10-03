package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: dco */
/* compiled from: PG */
public final class dco extends iox {

    /* renamed from: b */
    private final ioq f6263b;

    /* renamed from: c */
    private final ioq f6264c;

    /* renamed from: d */
    private final ioq f6265d;

    public dco(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(dco.class), iqk);
        this.f6263b = ipd.m14274a(ioq);
        this.f6264c = ipd.m14274a(ioq2);
        this.f6265d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        return dcm.m5895a((Optional) list.get(0), (cyr) list.get(1), (iek) list.get(2));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6263b.mo9044b(), this.f6264c.mo9044b(), this.f6265d.mo9044b());
    }
}
