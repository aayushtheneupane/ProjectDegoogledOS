package p000;

import java.util.concurrent.Executor;

/* renamed from: dvd */
/* compiled from: PG */
public final /* synthetic */ class dvd implements gpd {

    /* renamed from: a */
    private final dvf f7448a;

    public dvd(dvf dvf) {
        this.f7448a = dvf;
    }

    /* renamed from: a */
    public final gpc mo2675a() {
        bpt bpt = this.f7448a.f7450a.f4490a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT pt.a, pt.d, pt.b, pt.f, pt.e, pt.g,  COUNT(DISTINCT ft.c) AS fc FROM pt");
        hgn.mo7409a(" JOIN ft ON ");
        hgn.mo7409a("pt.d = ft.h");
        hgn.mo7409a(" WHERE pt.e IS NOT NULL ");
        hgn.mo7409a(" GROUP BY ft.h");
        hgn.mo7409a(" HAVING fc >= 3");
        hgn.mo7409a(" ORDER BY fc DESC, pt.a DESC ");
        return gpc.m10577a(ibv.m12657a(bpt.mo2655a(hgn.mo7407a(), cjc.f4485a).mo6899b(), hmq.m11742a(dve.f7449a), (Executor) idh.f13918a));
    }
}
