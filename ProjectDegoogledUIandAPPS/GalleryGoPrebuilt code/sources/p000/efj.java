package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: efj */
/* compiled from: PG */
public final class efj implements gud {

    /* renamed from: a */
    public final Object f8147a = new Object();

    /* renamed from: b */
    public final int f8148b;

    /* renamed from: c */
    public final boolean f8149c;

    /* renamed from: d */
    public final boolean f8150d;

    /* renamed from: e */
    public efd f8151e = null;

    /* renamed from: f */
    private final /* synthetic */ efk f8152f;

    public efj(efk efk, int i, boolean z, boolean z2) {
        this.f8152f = efk;
        this.f8148b = i;
        this.f8149c = z;
        this.f8150d = z2;
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        ieh a = this.f8152f.f8153a.mo4755a();
        ieh a2 = this.f8152f.f8154b.f8041b.mo6359a();
        cwn.m5509a(a, "SharingFragmentDataService: Failed to fetch apps.", new Object[0]);
        cwn.m5509a(a2, "SharingFragmentDataService: Failed to fetch preferences.", new Object[0]);
        return gte.m10769a(a, a2).mo7613a((Callable) new efe(this, a, a2), (Executor) this.f8152f.f8155c);
    }

    /* renamed from: c */
    public final Object mo2735c() {
        return guj.m10828a("SHARING_FRAGMENT_DATA_SERVICE", "SHARING_DATA_SERVICE");
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        synchronized (this.f8147a) {
            efd efd = this.f8151e;
            if (efd != null) {
                gpc a = gpc.m10579a((Object) guc.m10815a(efd, this.f8152f.f8156d.mo5370a()));
                return a;
            }
            gpc a2 = gpc.m10579a((Object) guc.f12067a);
            return a2;
        }
    }
}
