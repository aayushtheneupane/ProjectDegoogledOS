package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: dcn */
/* compiled from: PG */
public final class dcn extends iox {

    /* renamed from: b */
    private final ioq f6260b;

    /* renamed from: c */
    private final ioq f6261c;

    /* renamed from: d */
    private final ioq f6262d;

    public dcn(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(dcn.class), iqk);
        this.f6260b = ipd.m14274a(ioq);
        this.f6261c = ipd.m14274a(ioq2);
        this.f6262d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        return dcm.m5895a(((Optional) list.get(0)).map(dcj.f6257a), (cyr) list.get(1), (iek) list.get(2));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6260b.mo9044b(), this.f6261c.mo9044b(), this.f6262d.mo9044b());
    }
}
