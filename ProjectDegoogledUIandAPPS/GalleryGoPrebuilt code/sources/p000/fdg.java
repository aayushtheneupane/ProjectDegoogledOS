package p000;

import java.util.concurrent.Executor;

/* renamed from: fdg */
/* compiled from: PG */
final /* synthetic */ class fdg implements icf {

    /* renamed from: a */
    private final ikf f9304a;

    /* renamed from: b */
    private final hpr f9305b;

    /* renamed from: c */
    private final String f9306c;

    /* renamed from: d */
    private final fdd f9307d;

    public fdg(fdd fdd, ikf ikf, hpr hpr, String str) {
        this.f9307d = fdd;
        this.f9304a = ikf;
        this.f9305b = hpr;
        this.f9306c = str;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        fdd fdd = this.f9307d;
        ikf ikf = this.f9304a;
        String str = this.f9306c;
        fcm fcm = (fcm) obj;
        exr a = fdd.mo5508a(fcm).mo5381a(ikf.mo8512ag());
        a.mo5378a(str);
        int i = fcm.f9269b - 1;
        if (i == 0) {
            a.mo5379b(fcm.f9268a);
        } else if (i == 1) {
            a.mo5379b((String) null);
        }
        eyn a2 = a.mo5377a();
        iev f = iev.m12774f();
        a2.mo5404a(new ezo(f));
        idb a3 = idb.m12699a((ieh) f).mo8397a(ezp.f9221a, (Executor) idh.f13918a);
        a3.mo8398a().mo53a(new ezq(f, a2), idh.f13918a);
        return ibv.m12657a((ieh) a3.mo8401b(), ife.m12906g((Object) null), (Executor) idh.f13918a);
    }
}
