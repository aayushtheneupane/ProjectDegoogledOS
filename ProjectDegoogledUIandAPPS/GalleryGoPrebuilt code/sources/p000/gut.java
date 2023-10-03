package p000;

import java.util.Set;

/* renamed from: gut */
/* compiled from: PG */
final class gut implements idw {

    /* renamed from: a */
    private final /* synthetic */ Object f12092a;

    /* renamed from: b */
    private final /* synthetic */ guw f12093b;

    /* renamed from: c */
    private final /* synthetic */ hpy f12094c;

    /* renamed from: d */
    private final /* synthetic */ gus f12095d;

    public gut(gus gus, Object obj, guw guw, hpy hpy) {
        this.f12095d = gus;
        this.f12092a = obj;
        this.f12093b = guw;
        this.f12094c = hpy;
    }

    /* renamed from: a */
    public final void mo3868a(Throwable th) {
    }

    /* renamed from: a */
    public final void mo3867a(Object obj) {
        Set<gux> set;
        gus gus = this.f12095d;
        Object obj2 = this.f12092a;
        guw guw = this.f12093b;
        hpy hpy = this.f12094c;
        ife.m12869b(obj2, (Object) "Cannot notify change for a null key");
        synchronized (gus.f12089a) {
            huo huo = (huo) gus.f12090b.get(obj2);
            if (huo == null) {
                set = hvf.f13465a;
            } else {
                set = huo.mo7794e();
            }
        }
        for (gux gux : set) {
            if (hpy.mo7648c() != gux) {
                gux.mo7104a(guw);
            }
        }
    }
}
