package p000;

import android.os.Bundle;
import android.os.StrictMode;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: gsc */
/* compiled from: PG */
public final class gsc extends grx implements fwt, fwg, fwr, fwp, fwq, fws, fvn {

    /* renamed from: a */
    public static final hvy f11939a = hvy.m12261a("com/google/apps/tiktok/concurrent/futuresmixin/FuturesMixinImpl");

    /* renamed from: b */
    public boolean f11940b = false;

    /* renamed from: c */
    private final Executor f11941c;

    /* renamed from: d */
    private final iqk f11942d;

    /* renamed from: e */
    private boolean f11943e = true;

    /* renamed from: f */
    private boolean f11944f = false;

    /* renamed from: g */
    private final gsb f11945g = new gsb((byte[]) null);

    /* renamed from: h */
    private final C0438q f11946h = new grz(this);

    /* renamed from: i */
    private boolean f11947i = false;

    /* renamed from: j */
    private final Set f11948j = new HashSet();

    public gsc(iqk iqk, fwc fwc, C0600w wVar, Executor executor) {
        this.f11942d = iqk;
        this.f11941c = executor;
        fwc.mo6246a((fwt) this);
        wVar.mo64a(this.f11946h);
    }

    /* renamed from: g */
    private final gsg m10707g() {
        gsg gsg = (gsg) ((C0171gd) this.f11942d.mo2097a()).mo6418a("FuturesMixinFragmentTag");
        if (gsg == null) {
            gsg = new gsg();
            C0182gn a = ((C0171gd) this.f11942d.mo2097a()).mo6419a();
            a.mo6851a((C0147fh) gsg, "FuturesMixinFragmentTag");
            a.mo5244a();
        }
        gsg.f11956a = this.f11941c;
        return gsg;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6989a(ieh ieh, Object obj, gry gry) {
        fxk.m9830b();
        boolean z = true;
        ife.m12879b(this.f11944f && !((C0171gd) this.f11942d.mo2097a()).mo6443c(), "Futures should not be triggered by lifecycle changes, and cannot be listened to while a Fragment is stopped. Consider using SubscriptionMixin instead. See go/tiktok/concurrent/futuresmixin.md. We observed that resumed state was %s and the fragmentmanager's state was %s.", Boolean.valueOf(this.f11944f), Boolean.valueOf(!((C0171gd) this.f11942d.mo2097a()).mo6443c()));
        if (!this.f11940b || hlo.m11708a()) {
            StrictMode.noteSlowCall("FuturesMixin called from Lifecycle");
        }
        gsg g = m10707g();
        fxk.m9830b();
        List list = hnb.f13076a;
        grs grs = g.f11957b;
        fxk.m9830b();
        Integer num = (Integer) grs.f11929e.get(gry.getClass());
        ife.m12878b(num != null, "The callback %s has not been registered", (Object) gry.getClass());
        if (grs.mo6983a(num.intValue()) != gry) {
            z = false;
        }
        ife.m12878b(z, "The callback class %s was registered using a different instance. The instance registered in onCreate() must be the same instance used to listen. You can use a final member variable to safely hold the callback reference for each lifecycle.", (Object) gry.getClass());
        gsk gsk = new gsk(num.intValue(), obj, ieh);
        g.f11958c.add(gsk);
        if (g.f11959d) {
            gsk.mo6998a(g);
            if (!ieh.isDone()) {
                gry.mo2649a(obj);
            }
        }
        if (m10707g().mo5653m().isFinishing()) {
            Throwable th = new Throwable();
            th.fillInStackTrace();
            ((hvv) ((hvv) ((hvv) f11939a.mo8180b()).mo8202a(th)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/FuturesMixinImpl", "listen", 212, "FuturesMixinImpl.java")).mo8203a("listen() called while finishing");
        }
        if (m10707g().mo5653m().isChangingConfigurations()) {
            Throwable th2 = new Throwable();
            th2.fillInStackTrace();
            ((hvv) ((hvv) ((hvv) f11939a.mo8180b()).mo8202a(th2)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/FuturesMixinImpl", "listen", 218, "FuturesMixinImpl.java")).mo8203a("listen() called while changing configurations");
        }
        if (!this.f11947i) {
            Throwable th3 = new Throwable();
            th3.fillInStackTrace();
            ((hvv) ((hvv) ((hvv) f11939a.mo8180b()).mo8202a(th3)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/FuturesMixinImpl", "listen", 223, "FuturesMixinImpl.java")).mo8203a("listen() called outside listening window");
            this.f11945g.f11937a.add(gry);
            this.f11945g.f11938b = hmq.m11748a((Runnable) new gsa());
            gsb gsb = this.f11945g;
            fxk.m9832b((Runnable) gsb);
            fxk.m9824a((Runnable) gsb);
        }
    }

    /* renamed from: a */
    public final void mo6072a(Bundle bundle) {
        boolean z = bundle != null;
        this.f11944f = z;
        this.f11940b = z;
    }

    /* renamed from: b */
    public final void mo6218b() {
        if (this.f11947i) {
            m10706f();
        }
    }

    /* renamed from: a */
    public final void mo6066a() {
        if (!this.f11947i) {
            m10705e();
        }
        this.f11944f = true;
    }

    /* renamed from: b */
    public final void mo6073b(Bundle bundle) {
        if (this.f11947i) {
            m10706f();
        } else if (!this.f11945g.f11937a.isEmpty()) {
            Throwable th = new Throwable();
            th.fillInStackTrace();
            ((hvv) ((hvv) ((hvv) f11939a.mo8180b()).mo8202a(th)).mo8201a("com/google/apps/tiktok/concurrent/futuresmixin/FuturesMixinImpl", "onSaveInstanceState", 269, "FuturesMixinImpl.java")).mo8203a("possible root cause for b/66999648 found");
        }
    }

    /* renamed from: c */
    public final void mo6270c() {
        ife.m12876b(!this.f11947i, (Object) "FuturesMixin.onStart() was manually invoked, and is now re-running.");
        this.f11943e = false;
        m10705e();
    }

    /* renamed from: d */
    public final void mo6271d() {
        if (this.f11947i) {
            m10706f();
        }
    }

    /* renamed from: a */
    public final void mo6988a(gry gry) {
        fxk.m9830b();
        ife.m12876b(this.f11943e, (Object) "FuturesMixin.registerCallback() must be called exactly once for each callback, in onCreate().");
        this.f11948j.add(gry);
    }

    /* renamed from: e */
    private final void m10705e() {
        gsg g = m10707g();
        Iterator it = this.f11948j.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            gry gry = (gry) it.next();
            grs grs = g.f11957b;
            fxk.m9830b();
            Class<?> cls = gry.getClass();
            if (grs.f11929e.containsKey(cls)) {
                if (grs.f11928d.put(Integer.valueOf(((Integer) grs.f11929e.get(cls)).intValue()), gry) != null) {
                    z = false;
                }
                ife.m12878b(z, "Attempted to register a callback class twice: %", (Object) cls);
            } else {
                int andIncrement = grs.f11926b.getAndIncrement();
                C0290kn knVar = grs.f11929e;
                Integer valueOf = Integer.valueOf(andIncrement);
                knVar.put(cls, valueOf);
                grs.f11928d.put(valueOf, gry);
            }
        }
        this.f11948j.clear();
        fxk.m9832b((Runnable) this.f11945g);
        this.f11945g.f11937a.clear();
        this.f11945g.f11938b = null;
        this.f11947i = true;
        ife.m12869b((Object) g.f11956a, (Object) "FuturesMixinRetainedFragment.setDependencies() must be called by the hosting Activity or Fragment before startCallbacks() is called.");
        g.f11959d = true;
        g.f11957b.mo6984a();
        for (gsk gsk : g.f11958c) {
            if (!gsk.f11963b) {
                gry gry2 = (gry) g.f11957b.mo6983a(gsk.f11962a);
                hlj a = hnb.m11767a("onPending FuturesMixin", hnf.f13084a, hlm.f12987a);
                try {
                    gry2.mo2649a(gsk.f11964c);
                    a.close();
                } catch (Throwable th) {
                    ifn.m12935a(th, th);
                }
            } else {
                try {
                    g.f11957b.mo6983a(gsk.f11962a);
                } catch (NullPointerException e) {
                    String valueOf2 = String.valueOf(gsk);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 7);
                    sb.append("future=");
                    sb.append(valueOf2);
                    throw new IllegalStateException(sb.toString(), e);
                }
            }
            gsk.mo6998a(g);
        }
        return;
        throw th;
    }

    /* renamed from: f */
    private final void m10706f() {
        gsg g = m10707g();
        g.f11959d = false;
        for (gsk a : g.f11958c) {
            a.mo6998a((gsj) null);
        }
        if (!this.f11940b) {
            this.f11944f = true;
            this.f11940b = true;
        }
        this.f11947i = false;
    }
}
