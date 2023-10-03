package p000;

import java.util.concurrent.Executor;

/* renamed from: bpt */
/* compiled from: PG */
public final class bpt {

    /* renamed from: a */
    private final hge f3324a;

    /* renamed from: b */
    private final iel f3325b;

    public bpt(hge hge, iel iel) {
        this.f3324a = hge;
        this.f3325b = iel;
    }

    /* renamed from: a */
    public final ieh mo2656a(hga hga) {
        return this.f3324a.mo7394a().mo6898b((icf) new bps(hga), (Executor) idh.f13918a).mo6894a();
    }

    /* renamed from: a */
    public final gpc mo2655a(hgm hgm, hpr hpr) {
        return this.f3324a.mo7394a().mo6893a((gpe) new bpr(hgm), (Executor) idh.f13918a).mo6897b(hpr, (Executor) this.f3325b);
    }
}
