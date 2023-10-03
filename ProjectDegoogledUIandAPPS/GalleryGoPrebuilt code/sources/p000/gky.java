package p000;

import java.util.concurrent.Executor;

/* renamed from: gky */
/* compiled from: PG */
public final /* synthetic */ class gky implements hbg {

    /* renamed from: a */
    private final glm f11559a;

    public gky(glm glm) {
        this.f11559a = glm;
    }

    /* renamed from: a */
    public final void mo6819a() {
        glm glm = this.f11559a;
        ife.m12898e((Object) gtf.f12011a);
        ieh a = ibd.m12604a(glm.f11578a.mo6837b(), Throwable.class, hmq.m11744a((icf) new gll(glm)), (Executor) idh.f13918a);
        glx glx = glm.f11578a;
        ife.m12841a(a, hmq.m11746a((idw) new glw(glx)), glx.f11598e);
        goo.m10562a(a, "Failed account invalidation.", new Object[0]);
    }
}
