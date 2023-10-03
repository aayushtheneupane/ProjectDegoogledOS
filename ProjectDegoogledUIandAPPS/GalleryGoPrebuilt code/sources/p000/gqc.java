package p000;

import java.util.concurrent.Executor;

/* renamed from: gqc */
/* compiled from: PG */
final /* synthetic */ class gqc implements gpd {

    /* renamed from: a */
    private final gqo f11827a;

    /* renamed from: b */
    private final eyi f11828b;

    /* renamed from: c */
    private final eyh f11829c;

    /* renamed from: d */
    private final Executor f11830d;

    public gqc(gqo gqo, eyi eyi, eyh eyh, Executor executor) {
        this.f11827a = gqo;
        this.f11828b = eyi;
        this.f11829c = eyh;
        this.f11830d = executor;
    }

    /* renamed from: a */
    public final gpc mo2675a() {
        gqo gqo = this.f11827a;
        eyi eyi = this.f11828b;
        eyh eyh = this.f11829c;
        Executor executor = this.f11830d;
        if (gqo.mo6032b()) {
            fch a = gqo.mo6030a();
            iev f = iev.m12774f();
            a.mo5483a((Executor) idh.f13918a, (fcg) new ezu(f));
            a.mo5482a((Executor) idh.f13918a, (fcf) new ezv(f));
            return gpc.m10577a((ieh) f);
        }
        eyi.mo5391a(eyh);
        eym a2 = eyi.mo5392a();
        ieh a3 = gqr.m10643a(a2);
        gqo.getClass();
        gpc a4 = gqr.m10641a(a2, a3, (hpr) new gqd(gqo), executor);
        gqo.getClass();
        return a4.mo6897b((hpr) new gqe(gqo), (Executor) idh.f13918a);
    }
}
