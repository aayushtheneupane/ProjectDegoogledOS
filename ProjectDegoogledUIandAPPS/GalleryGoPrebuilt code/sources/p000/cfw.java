package p000;

import java.util.List;
import java.util.Set;

/* renamed from: cfw */
/* compiled from: PG */
public final class cfw extends iox {

    /* renamed from: b */
    private final ioq f4293b;

    /* renamed from: c */
    private final ioq f4294c;

    /* renamed from: d */
    private final ioq f4295d;

    public cfw(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(cfw.class), iqk);
        this.f4293b = ipd.m14274a(ioq);
        this.f4294c = ipd.m14274a(ioq2);
        this.f4295d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        Void voidR = (Void) list.get(0);
        return ((cip) list.get(1)).mo3159a((Iterable) (Set) list.get(2));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f4293b.mo9044b(), this.f4294c.mo9044b(), this.f4295d.mo9044b());
    }
}
