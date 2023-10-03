package p000;

import java.util.List;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dcx */
/* compiled from: PG */
public final class dcx extends iox {

    /* renamed from: b */
    private final ioq f6311b;

    /* renamed from: c */
    private final ioq f6312c;

    /* renamed from: d */
    private final ioq f6313d;

    /* renamed from: e */
    private final ioq f6314e;

    public dcx(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4) {
        super(iqk2, iph.m14288a(dcx.class), iqk);
        this.f6311b = ipd.m14274a(ioq);
        this.f6312c = ipd.m14274a(ioq2);
        this.f6313d = ipd.m14274a(ioq3);
        this.f6314e = ipd.m14274a(ioq4);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        Optional optional = (Optional) list.get(0);
        Optional optional2 = (Optional) list.get(1);
        cyr cyr = (cyr) list.get(2);
        iek iek = (iek) list.get(3);
        if (!optional2.isPresent() || !optional.isPresent()) {
            return ife.m12820a((Object) false);
        }
        cyg cyg = (cyg) optional.get();
        cyf M = ((cyg) optional2.get()).mo3906M();
        M.mo3970c(cyg.mo3930u());
        M.mo3974d(cyg.mo3931v());
        M.mo3982f(cyg.mo3933x());
        M.mo3987h(cyg.mo3934y());
        M.mo3988i(cyg.mo3935z());
        M.mo3985g(cyg.mo3932w());
        M.mo3979f(cyg.mo3926r());
        return gte.m10770a(cyr.f6045a.mo2656a(new cyj(M.mo3966c())), dcl.f6259a, (Executor) iek);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6311b.mo9044b(), this.f6312c.mo9044b(), this.f6313d.mo9044b(), this.f6314e.mo9044b());
    }
}
