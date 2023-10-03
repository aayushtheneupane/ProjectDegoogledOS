package p000;

import java.util.concurrent.Executor;

/* renamed from: gqr */
/* compiled from: PG */
public final class gqr {

    /* renamed from: a */
    public static final hvy f11845a = hvy.m12261a("com/google/apps/tiktok/concurrent/GcoreFutures");

    /* renamed from: a */
    public static ieh m10643a(eym eym) {
        hlj a = hnb.m11765a("Connecting GoogleApiClient");
        try {
            iev f = iev.m12774f();
            eym.mo5398a((eyk) new gqn(f));
            eym.mo5399a((eyl) new gqh(f));
            eym.mo5397a();
            ieh a2 = a.mo7548a(f);
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public static gpc m10642a(eyn eyn) {
        iev f = iev.m12774f();
        f.mo53a((Runnable) new gqi(f, eyn), (Executor) idh.f13918a);
        eyn.mo5404a(new gqj(f));
        return gpc.m10578a((ieh) f, new gqk(f));
    }

    /* renamed from: a */
    public static gpc m10641a(eym eym, ieh ieh, hpr hpr, Executor executor) {
        gpc a = gpc.m10577a(ieh).mo6893a((gpe) new gqf(hpr, eym), executor).mo6893a((gpe) new gqg(eym), (Executor) idh.f13918a);
        ife.m12841a(a.mo6900c(), (idw) new gqm(eym), (Executor) idh.f13918a);
        return a;
    }

    /* renamed from: a */
    public static gpc m10639a(eyi eyi, eyh eyh, gqo gqo, Executor executor) {
        return gpc.m10575a((gpd) new gqc(gqo, eyi, eyh, executor), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public static gpc m10640a(eyi eyi, eyh eyh, hpr hpr, Executor executor) {
        eyi.mo5391a(eyh);
        eym a = eyi.mo5392a();
        return m10641a(a, m10643a(a), hpr, executor);
    }

    /* renamed from: a */
    public static void m10644a(Object obj) {
        if (obj instanceof eyo) {
            ((eyo) obj).mo5405a();
        }
    }
}
