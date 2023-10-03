package p000;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: gvj */
/* compiled from: PG */
public final class gvj extends C0147fh {

    /* renamed from: Z */
    public Executor f12131Z;

    /* renamed from: a */
    public exm f12132a;

    /* renamed from: aa */
    public gus f12133aa;

    /* renamed from: b */
    public grm f12134b;

    /* renamed from: c */
    public final Map f12135c = new HashMap();

    /* renamed from: d */
    public final grs f12136d = new grs("SubscriptionMixinRF");

    /* renamed from: a */
    public final void mo165a(Bundle bundle) {
        super.mo165a(bundle);
        mo5630K();
    }

    /* renamed from: x */
    public final void mo1836x() {
        super.mo1836x();
        for (gvu gvu : this.f12135c.values()) {
            if (gvu.f12158d != null) {
                gvu.f12165k.mo7100b(((gtx) gvu.f12161g).f12048a.mo2735c(), gvu.f12158d);
                gvu.f12158d = null;
            }
            gvu.f12163i.mo7092a();
            gvu.f12164j.mo7092a();
            if (gvu.f12162h.mo7072d().mo7646a()) {
                ((guo) gvu.f12162h.mo7072d().mo7647b()).mo7088c();
            }
            if (gvu.f12162h.mo7073e().mo7646a() && !gvu.f12162h.mo7073e().equals(gvu.f12162h.mo7072d())) {
                ((guo) gvu.f12162h.mo7073e().mo7647b()).mo7088c();
            }
        }
        grm grm = this.f12134b;
        if (grm != null) {
            grm.mo6961b().clear();
            this.f12134b = null;
        }
    }

    /* renamed from: c */
    public final void mo1834c() {
        super.mo1834c();
        for (gvu gvu : this.f12135c.values()) {
            gve gve = gvu.f12162h;
            gvc gvc = gve.f12120a;
            hpy d = gve.mo7072d();
            gvu.f12162h = new gtz(1 + gve.mo7069a(), gvc, false, d, hph.f13219a);
        }
        this.f12136d.mo6985b();
    }
}
