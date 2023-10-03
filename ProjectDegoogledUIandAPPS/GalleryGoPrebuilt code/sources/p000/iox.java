package p000;

import java.util.concurrent.Executor;

/* renamed from: iox */
/* compiled from: PG */
public abstract class iox extends iow implements Executor, icf {

    /* renamed from: b */
    private final iqk f14613b;

    /* renamed from: c */
    private final iph f14614c;

    /* renamed from: d */
    private final iqk f14615d;

    /* renamed from: e */
    private volatile ipg f14616e = null;

    protected iox(iqk iqk, iph iph, iqk iqk2) {
        this.f14613b = (iqk) iol.m14228a((Object) iqk);
        this.f14614c = iph;
        this.f14615d = (iqk) iol.m14228a((Object) iqk2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract ieh mo3131a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract ieh mo3132b(Object obj);

    @Deprecated
    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        this.f14616e.mo7600b();
        try {
            return mo3132b(obj);
        } finally {
            this.f14616e.mo7601c();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final ieh mo9052c() {
        this.f14616e = ((ipl) this.f14613b.mo2097a()).mo7602a(this.f14614c);
        this.f14616e.mo7599a();
        ieh a = ibv.m12658a(mo3131a(), (icf) this, (Executor) this);
        this.f14616e.mo9054a(a);
        return a;
    }

    @Deprecated
    public final void execute(Runnable runnable) {
        this.f14616e.mo9018d();
        ((Executor) this.f14615d.mo2097a()).execute(runnable);
    }
}
