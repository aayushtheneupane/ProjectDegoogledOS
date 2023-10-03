package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: dzh */
/* compiled from: PG */
final /* synthetic */ class dzh implements ice {

    /* renamed from: a */
    private final dzl f7711a;

    public dzh(dzl dzl) {
        this.f7711a = dzl;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        dzl dzl = this.f7711a;
        gpi gpi = new gpi(TimeUnit.SECONDS, dzl.f7720b);
        dzi dzi = new dzi(dzl);
        iev f = iev.m12774f();
        ieh a = ife.m12818a((ieh) f, 10, gpi.f11785a, gpi.f11786b);
        f.mo6946a(C0643xp.m15939a((abc) new gpf(dzi)));
        return a;
    }
}
