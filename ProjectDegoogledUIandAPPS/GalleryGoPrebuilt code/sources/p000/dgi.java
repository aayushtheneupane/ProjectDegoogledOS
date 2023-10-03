package p000;

import java.util.concurrent.Executor;

/* renamed from: dgi */
/* compiled from: PG */
final /* synthetic */ class dgi implements icf {

    /* renamed from: a */
    private final dgo f6501a;

    public dgi(dgo dgo) {
        this.f6501a = dgo;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        ieh ieh;
        dgo dgo = this.f6501a;
        ccc ccc = (ccc) obj;
        fxk.m9836c();
        cxi a = ccc.mo3002a();
        String str = a.f5914f;
        int i = a.f5923o;
        int i2 = a.f5924p;
        int i3 = ccc.mo3002a().f5925q;
        if (str.equals("video/3gpp")) {
            ieh = dgo.f6509b.mo4810a("videotrimming", ".3gp", "video/3gpp", i, i2, i3);
        } else if (str.equals("video/webm")) {
            ieh = dgo.f6509b.mo4810a("videotrimming", ".webm", "video/webm", i, i2, i3);
        } else {
            ieh = dgo.f6509b.mo4810a("videotrimming", ".mp4", "video/mp4", i, i2, i3);
        }
        return gpc.m10581b(ieh).mo6896a((icf) new dgj(dgo, ccc), (Executor) dgo.f6511d);
    }
}
