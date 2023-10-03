package p000;

import java.util.List;
import java.util.concurrent.Callable;
import p003j$.util.Optional;

/* renamed from: dcr */
/* compiled from: PG */
public final class dcr extends iox {

    /* renamed from: b */
    private final ioq f6272b;

    /* renamed from: c */
    private final ioq f6273c;

    /* renamed from: d */
    private final ioq f6274d;

    /* renamed from: e */
    private final ioq f6275e;

    /* renamed from: f */
    private final ioq f6276f;

    /* renamed from: g */
    private final ioq f6277g;

    public dcr(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4, ioq ioq5, ioq ioq6) {
        super(iqk2, iph.m14288a(dcr.class), iqk);
        this.f6272b = ipd.m14274a(ioq);
        this.f6273c = ipd.m14274a(ioq2);
        this.f6274d = ipd.m14274a(ioq3);
        this.f6275e = ipd.m14274a(ioq4);
        this.f6276f = ipd.m14274a(ioq5);
        this.f6277g = ipd.m14274a(ioq6);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        return ((iek) list.get(5)).mo5933a(hmq.m11749a((Callable) new dce((Optional) list.get(1), (dbo) list.get(2), (ebi) list.get(0), (ddj) list.get(3), ((Boolean) list.get(4)).booleanValue())));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6272b.mo9044b(), this.f6273c.mo9044b(), this.f6274d.mo9044b(), this.f6275e.mo9044b(), this.f6276f.mo9044b(), this.f6277g.mo9044b());
    }
}
