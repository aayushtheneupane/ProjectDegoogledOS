package p000;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.logging.Level;

/* renamed from: guo */
/* compiled from: PG */
public final class guo {

    /* renamed from: a */
    public final Object f12079a = new Object();

    /* renamed from: b */
    public icz f12080b;

    /* renamed from: c */
    private gpc f12081c;

    public guo(gpc gpc, gup gup) {
        fxk.m9830b();
        this.f12081c = (gpc) ife.m12898e((Object) gpc);
        gup gup2 = (gup) ife.m12898e((Object) gup);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo7088c() {
        synchronized (this.f12079a) {
            gpc gpc = this.f12081c;
            if (gpc != null) {
                idb idb = gpc.f11782a;
                idb.f13904a.logp(Level.FINER, "com.google.common.util.concurrent.ClosingFuture", "cancel", "cancelling {0}", idb);
                if (idb.f13907d.cancel(true)) {
                    idb.mo8403c();
                }
                m10832d();
            }
            this.f12080b.f13902a.mo8403c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final guc mo7086a() {
        guc guc;
        synchronized (this.f12079a) {
            if (this.f12081c != null) {
                m10832d();
            }
            try {
                guc = (guc) ife.m12871b((Future) this.f12080b.f13902a.f13907d);
            } catch (ExecutionException e) {
                throw new gvf(e.getCause());
            }
        }
        return guc;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final ieh mo7087b() {
        synchronized (this.f12079a) {
            gpc gpc = this.f12081c;
            if (gpc != null) {
                ieh c = gpc.mo6900c();
                return c;
            }
            ieh a = ife.m12820a((Object) null);
            return a;
        }
    }

    /* renamed from: d */
    private final void m10832d() {
        synchronized (this.f12079a) {
            ife.m12896d(this.f12081c.mo6900c().isDone());
            idb idb = this.f12081c.f11782a;
            gun gun = new gun(this);
            idh idh = idh.f13918a;
            ife.m12898e((Object) gun);
            if (!idb.mo8402b(icy.OPEN, icy.WILL_CREATE_VALUE_AND_CLOSER)) {
                int ordinal = ((icy) idb.f13905b.get()).ordinal();
                if (ordinal == 1) {
                    throw new IllegalStateException("Cannot call getValueAndCloser() after deriving another step");
                } else if (ordinal == 2 || ordinal == 3 || ordinal == 4) {
                    throw new IllegalStateException("Cannot call getValueAndCloser() after calling closing()");
                } else if (ordinal != 5) {
                    throw new AssertionError(idb.f13905b);
                } else {
                    throw new IllegalStateException("Cannot call getValueAndCloser() twice");
                }
            } else {
                idb.f13907d.mo53a((Runnable) new icm(idb, gun), (Executor) idh);
                this.f12081c = null;
            }
        }
    }
}
