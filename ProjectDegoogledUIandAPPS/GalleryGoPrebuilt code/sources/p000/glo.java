package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: glo */
/* compiled from: PG */
public final class glo implements gud {

    /* renamed from: a */
    private final glp f11581a;

    /* renamed from: b */
    private final glx f11582b;

    public glo(glp glp, glx glx) {
        this.f11581a = glp;
        this.f11582b = glx;
    }

    /* renamed from: c */
    public final Object mo2735c() {
        return "com.google.apps.tiktok.account.data.AllAccounts";
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        return this.f11582b.mo6837b();
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        ieh a = this.f11582b.f11595b.mo6359a();
        glp glp = this.f11581a;
        ieh a2 = ibv.m12657a(glp.f11583a.mo6359a(), gmj.f11624a, (Executor) glp.f11584b);
        return gpc.m10577a(ife.m12884c(a, a2).mo8443a((Callable) new gln(a, a2), (Executor) idh.f13918a));
    }
}
