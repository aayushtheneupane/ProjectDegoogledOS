package p000;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: inh */
/* compiled from: PG */
public final class inh extends ibs implements iel {

    /* renamed from: e */
    private static final ThreadLocal f14560e = new ThreadLocal();

    /* renamed from: a */
    private final iel f14561a;

    /* renamed from: b */
    private final imt f14562b;

    /* renamed from: c */
    private final imr f14563c;

    /* renamed from: d */
    private final inc f14564d;

    public inh(iel iel, imt imt, imr imr, inc inc) {
        this.f14561a = iel;
        this.f14562b = imt;
        this.f14563c = imr;
        this.f14564d = inc;
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.f14561a.awaitTermination(j, timeUnit);
    }

    public final void execute(Runnable runnable) {
        Class<?> cls = (Class) f14560e.get();
        f14560e.remove();
        if (cls == null) {
            cls = runnable.getClass();
        }
        imu a = iol.m14226a((Class) cls, this.f14562b, this.f14563c);
        this.f14564d.mo9013b(a);
        this.f14561a.execute(iol.m14230a(runnable, a, this.f14564d));
    }

    /* renamed from: a */
    private final imu m14182a(Class cls) {
        imu a = iol.m14226a(cls, this.f14562b, this.f14563c);
        this.f14564d.mo9013b(a);
        return a;
    }

    public final boolean isShutdown() {
        return this.f14561a.isShutdown();
    }

    public final boolean isTerminated() {
        return this.f14561a.isTerminated();
    }

    /* renamed from: a */
    public final iej schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f14561a.mo5935a(iol.m14230a(runnable, m14182a((Class) runnable.getClass()), this.f14564d), j, timeUnit);
    }

    /* renamed from: a */
    public final iej schedule(Callable callable, long j, TimeUnit timeUnit) {
        return this.f14561a.mo5936a((Callable) new ing(m14182a((Class) callable.getClass()), this.f14564d, callable), j, timeUnit);
    }

    /* renamed from: a */
    public final iej scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: b */
    public final iej scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final void shutdown() {
        this.f14561a.shutdown();
    }

    public final List shutdownNow() {
        return this.f14561a.shutdownNow();
    }

    /* renamed from: a */
    public final ieh mo5931a(Runnable runnable) {
        f14560e.set(runnable.getClass());
        try {
            return super.submit(runnable);
        } finally {
            f14560e.remove();
        }
    }

    public final /* bridge */ /* synthetic */ Future submit(Runnable runnable) {
        return submit(runnable);
    }

    /* renamed from: a */
    public final ieh mo5932a(Runnable runnable, Object obj) {
        f14560e.set(runnable.getClass());
        try {
            return super.submit(runnable, obj);
        } finally {
            f14560e.remove();
        }
    }

    public final /* bridge */ /* synthetic */ Future submit(Runnable runnable, Object obj) {
        return submit(runnable, obj);
    }

    /* renamed from: a */
    public final ieh mo5933a(Callable callable) {
        f14560e.set(callable.getClass());
        try {
            return super.submit(callable);
        } finally {
            f14560e.remove();
        }
    }

    public final /* bridge */ /* synthetic */ Future submit(Callable callable) {
        return submit(callable);
    }
}
