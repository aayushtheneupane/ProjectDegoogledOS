package p000;

import java.util.concurrent.Executor;

/* renamed from: gyx */
/* compiled from: PG */
final /* synthetic */ class gyx implements ice {

    /* renamed from: a */
    private final gzn f12328a;

    public gyx(gzn gzn) {
        this.f12328a = gzn;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        gzn gzn = this.f12328a;
        ieh a = gzn.f12355a.mo6359a();
        ieh a2 = gqb.m10617a(a, hmq.m11743a((ice) new gzk(gzn, a)), (Executor) idh.f13918a);
        ieh a3 = ibv.m12657a(a2, gyy.f12329a, (Executor) idh.f13918a);
        gzn.mo7223a(a3);
        gzn.m11083a(ibv.m12658a(a3, hmq.m11744a((icf) new gzj(gzn)), (Executor) gzn.f12361g), "Failed to fetch after encountering old config");
        return ibv.m12657a(a2, hmq.m11742a((hpr) new gyz(gzn)), (Executor) idh.f13918a);
    }
}
