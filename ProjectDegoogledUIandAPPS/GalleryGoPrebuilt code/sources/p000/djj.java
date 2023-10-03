package p000;

import java.util.concurrent.Executor;

/* renamed from: djj */
/* compiled from: PG */
final /* synthetic */ class djj implements ice {

    /* renamed from: a */
    private final djn f6665a;

    public djj(djn djn) {
        this.f6665a = djn;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        djn djn = this.f6665a;
        bpt bpt = djn.f6669a.f6664a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT DISTINCT(ak) FROM mt");
        return gte.m10771a(bpt.mo2655a(hgn.mo7407a(), dje.f6661a).mo6899b(), (icf) new djk(djn), (Executor) djn.f6671c);
    }
}
