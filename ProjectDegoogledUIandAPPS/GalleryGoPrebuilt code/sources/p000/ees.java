package p000;

import java.util.concurrent.Executor;

/* renamed from: ees */
/* compiled from: PG */
public final class ees {

    /* renamed from: a */
    public final iel f8115a;

    /* renamed from: b */
    public final gus f8116b;

    /* renamed from: c */
    public grf f8117c;

    /* renamed from: d */
    private final een f8118d;

    /* renamed from: e */
    private final edx f8119e;

    public ees(een een, iel iel, edx edx, gus gus) {
        this.f8118d = een;
        this.f8115a = iel;
        this.f8119e = edx;
        this.f8116b = gus;
        this.f8117c = new grf(new eeo(this), iel);
    }

    /* renamed from: a */
    public final ieh mo4756a(boolean z) {
        ieh ieh;
        if (z) {
            een een = this.f8118d;
            bpt bpt = een.f8107b;
            hgn hgn = new hgn();
            hgn.mo7409a("SELECT COUNT(*) FROM at");
            ieh = gte.m10771a(gte.m10771a(bpt.mo2655a(hgn.mo7407a(), eed.f8094a).mo6899b(), (icf) new eec(een), (Executor) idh.f13918a), (icf) new eef(een), (Executor) een.f8108c);
        } else {
            ieh = this.f8118d.mo4749a();
        }
        edx edx = this.f8119e;
        edx.getClass();
        return gte.m10770a(ieh, (hpr) new eeq(edx), (Executor) this.f8115a);
    }

    /* renamed from: a */
    public final ieh mo4755a() {
        return this.f8117c.mo6948a();
    }
}
