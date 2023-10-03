package p000;

import java.util.concurrent.Executor;

/* renamed from: gvi */
/* compiled from: PG */
public final class gvi {

    /* renamed from: a */
    public final grm f12126a;

    /* renamed from: b */
    private final exm f12127b;

    /* renamed from: c */
    private final C0147fh f12128c;

    /* renamed from: d */
    private final Executor f12129d;

    /* renamed from: e */
    private final gus f12130e;

    public gvi(exm exm, C0147fh fhVar, gus gus, Executor executor) {
        this.f12128c = fhVar;
        this.f12127b = exm;
        this.f12130e = gus;
        grm a = grm.m10663a(gtf.f12011a);
        this.f12126a = a;
        a.mo6960a();
        this.f12129d = executor;
        fhVar.mo5ad().mo64a(new hnk(new gvh(this)));
    }

    /* renamed from: a */
    public final gvj mo7112a() {
        gvj gvj = (gvj) this.f12128c.mo5659r().mo6418a("SubscriptionMixinFragmentTag");
        if (gvj == null) {
            gvj = new gvj();
            C0182gn a = this.f12128c.mo5659r().mo6419a();
            a.mo6851a((C0147fh) gvj, "SubscriptionMixinFragmentTag");
            a.mo5244a();
        }
        exm exm = this.f12127b;
        gus gus = this.f12130e;
        Executor executor = this.f12129d;
        gvj.f12132a = (exm) ife.m12898e((Object) exm);
        gvj.f12133aa = (gus) ife.m12898e((Object) gus);
        gvj.f12131Z = (Executor) ife.m12898e((Object) executor);
        if (gvj.f12134b == null) {
            gvj.f12134b = grm.m10663a(gtf.f12011a);
            gvj.f12134b.mo6960a();
        }
        return gvj;
    }

    /* renamed from: a */
    public final void mo7113a(gud gud, guy guy, gvc gvc) {
        fxk.m9830b();
        ife.m12876b(!(gvc instanceof gua), (Object) "Callbacks that implement BackgroundCallbacks must be registered with subscribeWithBackground().");
        this.f12126a.execute(new gvg(this, gud, guy, gvc));
    }

    gvi() {
    }
}
