package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: gvu */
/* compiled from: PG */
final class gvu {

    /* renamed from: a */
    public final exm f12155a;

    /* renamed from: b */
    public final grm f12156b;

    /* renamed from: c */
    public final Executor f12157c;

    /* renamed from: d */
    public gux f12158d;

    /* renamed from: e */
    public final AtomicReference f12159e = new AtomicReference((Object) null);

    /* renamed from: f */
    public gva f12160f;

    /* renamed from: g */
    public guz f12161g;

    /* renamed from: h */
    public gve f12162h = gve.m10877f();

    /* renamed from: i */
    public final gur f12163i = new gur(gvk.f12137a, 2);

    /* renamed from: j */
    public final gur f12164j = new gur(gvl.f12138a, 1);

    /* renamed from: k */
    public final gus f12165k;

    public gvu(gud gud, exm exm, gus gus, grm grm, Executor executor) {
        this.f12155a = exm;
        this.f12165k = gus;
        this.f12156b = grm;
        gud gud2 = gud;
        gtx gtx = new gtx(gud2, guf.f12070a, 0, 1, new gty(gud, Long.MIN_VALUE, gul.m10830b(), gup.m10836b(), 0, Long.MIN_VALUE));
        this.f12161g = gtx;
        this.f12160f = gtx.f12051d;
        this.f12157c = executor;
    }

    /* renamed from: a */
    public static gve m10899a(gve gve, guo guo) {
        gve gve2;
        hpy hpy;
        hpy hpy2;
        if (gve.mo7073e().mo7646a() && gve.mo7073e().mo7647b() == guo) {
            hpy2 = hph.f13219a;
            hpy = hph.f13219a;
            gve2 = gve.mo7109a(guo);
        } else if (!gve.mo7073e().mo7646a() && gve.mo7072d().mo7646a() && gve.mo7072d().mo7647b() == guo) {
            hpy2 = hpy.m11893b(((guo) gve.mo7072d().mo7647b()).mo7086a().mo7080a());
            hpy = hph.f13219a;
            gve2 = gve.mo7109a((guo) gve.mo7072d().mo7647b());
        } else if (!gve.mo7073e().mo7646a() || !((guo) gve.mo7073e().mo7647b()).mo7086a().mo7080a().equals(guo.mo7086a().mo7080a())) {
            hpy2 = hpy.m11893b(guo.mo7086a().mo7080a());
            hpy = gve.mo7072d();
            gve2 = gve.mo7109a(guo);
        } else {
            hpy2 = hph.f13219a;
            hpy = hpy.m11893b(guo);
            gve2 = gve;
        }
        if (hpy2.mo7646a()) {
            hlj a = hnb.m11765a("SubscriptionCallbacks.onNewData");
            try {
                gve.mo7070b().mo2680a(guo.mo7086a().mo7080a());
                if (a != null) {
                    a.close();
                }
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        }
        if (hpy.mo7646a()) {
            ((guo) hpy.mo7647b()).mo7088c();
        }
        return gve2;
        throw th;
    }

    /* renamed from: a */
    public final void mo7122a(gva gva, guo guo) {
        ife.m12896d(guo.mo7086a().mo7081b());
        this.f12162h = m10899a(this.f12162h, guo);
        this.f12160f = gva;
    }

    /* renamed from: a */
    public final void mo7121a(gva gva) {
        fxk.m9830b();
        boolean z = true;
        if (!this.f12162h.mo7072d().mo7646a()) {
            gvc b = this.f12162h.mo7070b();
            hlj a = hnb.m11765a("SubscriptionCallbacks.onPending");
            try {
                b.mo2679a();
                if (a != null) {
                    a.close();
                }
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else if ((this.f12162h.mo7070b() instanceof gua) && this.f12163i.mo7095b() && !this.f12162h.mo7071c()) {
            gve a2 = this.f12162h.mo7110a(true);
            this.f12162h = a2;
            m10900a((gua) a2.mo7070b());
        }
        gty gty = (gty) gva;
        gud gud = gty.f12053a;
        long j = gty.f12054b;
        gul gul = gty.f12055c;
        if (gul.mo7044a() == RecyclerView.FOREVER_NS) {
            z = false;
        }
        ife.m12876b(z, (Object) "You've just overflowed a long. Consider upgrading to a BigDecimal, if this happens more than once.");
        gty gty2 = new gty(gud, j, new gtv(gul.mo7044a() + 1), gty.f12056d, gty.f12057e, gty.f12058f);
        gul gul2 = gty2.f12055c;
        gud gud2 = gty2.f12053a;
        hlj a3 = hnb.m11765a("DataSource fetchAndStoreData()");
        try {
            guk guk = new guk(a3.mo7548a(gud2.mo2733a()), gul2);
            if (a3 != null) {
                a3.close();
            }
            this.f12163i.mo7094b(guk);
            guk.f12077a.mo53a(hmq.m11748a((Runnable) new gvm(this, gty2, guk)), idh.f13918a);
            return;
        } catch (Throwable th2) {
            ifn.m12935a(th, th2);
        }
        throw th;
        throw th;
    }

    /* renamed from: a */
    public final boolean mo7124a() {
        if (!(this.f12162h.mo7070b() instanceof gua) || !this.f12163i.mo7095b() || !this.f12164j.mo7095b()) {
            return false;
        }
        ife.m12896d(this.f12162h.mo7071c());
        return true;
    }

    /* renamed from: b */
    public final void mo7125b(gva gva) {
        boolean z;
        fxk.m9830b();
        gty gty = (gty) gva;
        gud gud = gty.f12053a;
        long j = gty.f12054b;
        gul gul = gty.f12055c;
        gup gup = gty.f12056d;
        if (gup.mo7048a() != RecyclerView.FOREVER_NS) {
            z = true;
        } else {
            z = false;
        }
        ife.m12876b(z, (Object) "You've just overflowed a long. Consider upgrading to a BigDecimal, if this happens more than once.");
        gty gty2 = new gty(gud, j, gul, new gtw(gup.mo7048a() + 1), gty.f12057e + 1, gty.f12058f);
        gup gup2 = gty2.f12056d;
        gud gud2 = gty2.f12053a;
        hlj a = hnb.m11765a("DataSource loadData()");
        try {
            gpc b = gud2.mo2734b();
            a.mo7548a(b.mo6900c());
            guo guo = new guo(b, gup2);
            if (a != null) {
                a.close();
            }
            this.f12164j.mo7094b(guo);
            guo.mo7087b().mo53a(hmq.m11748a((Runnable) new gvn(this, gty2, guo)), idh.f13918a);
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public static void m10900a(gua gua) {
        hlj a = hnb.m11765a("BackgroundCallbacks.onBackgroundFetch");
        try {
            gua.mo7077a();
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: b */
    public static void m10901b(gua gua) {
        hlj a = hnb.m11765a("BackgroundCallbacks.onBackgroundFetchSucceeded");
        try {
            gua.mo7078b();
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo7123a(Throwable th) {
        if (!this.f12162h.mo7072d().mo7646a()) {
            gvc b = this.f12162h.mo7070b();
            hlj a = hnb.m11765a("SubscriptionCallbacks.onError");
            try {
                b.mo2681a(th);
                if (a != null) {
                    a.close();
                }
            } catch (Throwable th2) {
                ifn.m12935a(th, th2);
            }
        } else if (this.f12162h.mo7070b() instanceof gvb) {
            gvb gvb = (gvb) this.f12162h.mo7070b();
            hlj a2 = hnb.m11765a("RefreshCallbacks.onRefreshError");
            try {
                gvb.mo7108a();
                if (a2 != null) {
                    a2.close();
                }
            } catch (Throwable th3) {
                ifn.m12935a(th, th3);
            }
        }
        if (this.f12162h.mo7071c() && mo7124a()) {
            gua gua = (gua) this.f12162h.mo7070b();
            hlj a3 = hnb.m11765a("BackgroundCallbacks.onBackgroundFetchError");
            try {
                gua.mo7079c();
                if (a3 != null) {
                    a3.close();
                }
                this.f12162h = this.f12162h.mo7110a(false);
                return;
            } catch (Throwable th4) {
                ifn.m12935a(th, th4);
            }
        } else {
            return;
        }
        throw th;
        throw th;
        throw th;
    }
}
