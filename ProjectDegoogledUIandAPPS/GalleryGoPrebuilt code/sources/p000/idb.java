package p000;

import java.io.Closeable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: idb */
/* compiled from: PG */
public final class idb {

    /* renamed from: a */
    public static final Logger f13904a = Logger.getLogger(idb.class.getName());

    /* renamed from: b */
    public final AtomicReference f13905b;

    /* renamed from: c */
    public final icp f13906c;

    /* renamed from: d */
    public final idq f13907d;

    private idb(icq icq, Executor executor) {
        this.f13905b = new AtomicReference(icy.OPEN);
        this.f13906c = new icp((byte[]) null);
        ife.m12898e((Object) icq);
        ifd a = ifd.m12799a((Callable) new ici(this, icq));
        executor.execute(a);
        this.f13907d = a;
    }

    private idb(ieh ieh) {
        this.f13905b = new AtomicReference(icy.OPEN);
        this.f13906c = new icp((byte[]) null);
        this.f13907d = idq.m12731c(ieh);
    }

    public /* synthetic */ idb(ieh ieh, byte[] bArr) {
        this(ieh);
    }

    /* renamed from: a */
    public final void mo8399a(icp icp) {
        mo8400a(icy.OPEN, icy.SUBSUMED);
        icp.mo8381a(this.f13906c, idh.f13918a);
    }

    /* renamed from: a */
    public final void mo8400a(icy icy, icy icy2) {
        ife.m12879b(mo8402b(icy, icy2), "Expected state to be %s, but it was %s", icy, icy2);
    }

    /* renamed from: c */
    public final void mo8403c() {
        f13904a.logp(Level.FINER, "com.google.common.util.concurrent.ClosingFuture", "close", "closing {0}", this);
        this.f13906c.close();
    }

    /* renamed from: a */
    public static void m12701a(Closeable closeable, Executor executor) {
        if (closeable != null) {
            try {
                executor.execute(new icn(closeable));
            } catch (RejectedExecutionException e) {
                RejectedExecutionException rejectedExecutionException = e;
                if (f13904a.isLoggable(Level.WARNING)) {
                    f13904a.logp(Level.WARNING, "com.google.common.util.concurrent.ClosingFuture", "closeQuietly", String.format("while submitting close to %s; will close inline", new Object[]{executor}), rejectedExecutionException);
                }
                m12701a(closeable, (Executor) idh.f13918a);
            }
        }
    }

    /* renamed from: b */
    public final idq mo8401b() {
        if (mo8402b(icy.OPEN, icy.WILL_CLOSE)) {
            f13904a.logp(Level.FINER, "com.google.common.util.concurrent.ClosingFuture", "closing", "will close {0}", this);
            this.f13907d.mo53a((Runnable) new icl(this), (Executor) idh.f13918a);
        } else {
            int ordinal = ((icy) this.f13905b.get()).ordinal();
            if (ordinal == 0) {
                throw new AssertionError();
            } else if (ordinal == 1) {
                throw new IllegalStateException("Cannot call closing() after deriving another step");
            } else if (ordinal == 5) {
                throw new IllegalStateException("Cannot call closing() after calling getValueAndCloser()");
            }
        }
        return this.f13907d;
    }

    /* renamed from: b */
    public final boolean mo8402b(icy icy, icy icy2) {
        return this.f13905b.compareAndSet(icy, icy2);
    }

    /* renamed from: a */
    private final idb m12698a(idq idq) {
        idb idb = new idb(idq);
        mo8399a(idb.f13906c);
        return idb;
    }

    @Deprecated
    /* renamed from: a */
    public static idb m12700a(ieh ieh, Executor executor) {
        ife.m12898e((Object) executor);
        idb idb = new idb(ife.m12817a(ieh));
        ife.m12841a(ieh, (idw) new ich(idb, executor), (Executor) idh.f13918a);
        return idb;
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
        if (((icy) this.f13905b.get()).equals(icy.OPEN)) {
            f13904a.logp(Level.SEVERE, "com.google.common.util.concurrent.ClosingFuture", "finalize", "Uh oh! An open ClosingFuture has leaked and will close: {0}", this);
            mo8401b();
        }
    }

    /* renamed from: a */
    public static idb m12699a(ieh ieh) {
        return new idb(ieh);
    }

    /* renamed from: a */
    public final ieh mo8398a() {
        return ife.m12817a(ibv.m12657a((ieh) this.f13907d, ife.m12906g((Object) null), (Executor) idh.f13918a));
    }

    /* renamed from: a */
    public static idb m12697a(icq icq, Executor executor) {
        return new idb(icq, executor);
    }

    public final String toString() {
        hpx f = ife.m12901f((Object) this);
        Object obj = this.f13905b.get();
        hpw a = f.mo7671a();
        a.f13238b = obj;
        a.f13237a = (String) ife.m12898e((Object) "state");
        f.mo7672a(this.f13907d);
        return f.toString();
    }

    /* renamed from: a */
    public final idb mo8397a(icr icr, Executor executor) {
        ife.m12898e((Object) icr);
        return m12698a((idq) ibv.m12658a((ieh) this.f13907d, (icf) new icj(this, icr), executor));
    }

    /* renamed from: a */
    public final idb mo8396a(ico ico, Executor executor) {
        ife.m12898e((Object) ico);
        return m12698a((idq) ibv.m12658a((ieh) this.f13907d, (icf) new ick(this, ico), executor));
    }

    /* renamed from: a */
    public static icv m12696a(Iterable iterable) {
        return new icv(iterable);
    }
}
