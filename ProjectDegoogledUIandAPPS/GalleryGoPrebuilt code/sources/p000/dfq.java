package p000;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: dfq */
/* compiled from: PG */
public final class dfq extends iox {

    /* renamed from: b */
    private final ioq f6458b;

    /* renamed from: c */
    private final ioq f6459c;

    /* renamed from: d */
    private final ioq f6460d;

    public dfq(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(dfq.class), iqk);
        this.f6458b = ipd.m14274a(ioq);
        this.f6459c = ipd.m14274a(ioq2);
        this.f6460d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        return ife.m12820a((Object) dfj.m6028a((List) list.get(0), (iop) list.get(1), (Map) list.get(2)));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6458b.mo9044b(), ibd.m12604a(ibv.m12657a(this.f6459c.mo9044b(), ipd.f14618a, (Executor) idh.f13918a), Throwable.class, ipd.f14619b, (Executor) idh.f13918a), this.f6460d.mo9044b());
    }
}
