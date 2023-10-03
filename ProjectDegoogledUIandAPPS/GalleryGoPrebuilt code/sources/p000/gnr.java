package p000;

import java.util.concurrent.Executor;

/* renamed from: gnr */
/* compiled from: PG */
public final class gnr implements hgr {

    /* renamed from: a */
    public final hfq f11700a;

    /* renamed from: b */
    public final gnw f11701b;

    /* renamed from: c */
    private final glp f11702c;

    /* renamed from: d */
    private final iek f11703d;

    public gnr(glp glp, iek iek, hfq hfq, gnw gnw) {
        this.f11702c = glp;
        this.f11703d = iek;
        this.f11700a = hfq;
        this.f11701b = gnw;
    }

    /* renamed from: a */
    public final ieh mo6866a() {
        glp glp = this.f11702c;
        ife.m12898e((Object) gtf.f12011a);
        return ibv.m12658a(ibv.m12657a(glp.f11583a.mo6359a(), gml.f11626a, (Executor) idh.f13918a), (icf) new gno(this), (Executor) this.f11703d);
    }
}
