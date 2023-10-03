package p000;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: grr */
/* compiled from: PG */
public final class grr extends ibs implements iel {

    /* renamed from: a */
    public final exm f11924a;

    public grr(exm exm) {
        this.f11924a = exm;
    }

    public final boolean isShutdown() {
        return false;
    }

    public final boolean isTerminated() {
        return false;
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public static int m10670a(Delayed delayed, Delayed delayed2) {
        long delay = delayed2.getDelay(TimeUnit.MILLISECONDS);
        long delay2 = delayed.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= delay2) {
            return delay == delay2 ? 0 : 1;
        }
        return -1;
    }

    public final void execute(Runnable runnable) {
        fxk.m9824a(runnable);
    }

    /* renamed from: a */
    private static final void m10672a(ieh ieh, Runnable runnable) {
        ieh.mo53a(new gro(runnable), idh.f13918a);
    }

    /* renamed from: a */
    public final iej schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return schedule((Callable) Executors.callable(runnable), j, timeUnit);
    }

    /* renamed from: a */
    public final iej schedule(Callable callable, long j, TimeUnit timeUnit) {
        long millis = timeUnit.toMillis(j);
        grq grq = new grq(this, callable, this.f11924a.mo5372c() + millis);
        fxk.m9825a((Runnable) grq, millis);
        m10672a((ieh) grq, (Runnable) grq);
        return grq;
    }

    /* renamed from: a */
    public final iej scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return m10671a(runnable, j, j2, timeUnit, true);
    }

    /* renamed from: a */
    private final iej m10671a(Runnable runnable, long j, long j2, TimeUnit timeUnit, boolean z) {
        TimeUnit timeUnit2 = timeUnit;
        long j3 = j;
        long millis = timeUnit2.toMillis(j);
        long c = this.f11924a.mo5372c();
        Runnable runnable2 = runnable;
        grp grp = new grp(this, runnable2, millis + c, timeUnit2.toMillis(j2), z);
        fxk.m9825a((Runnable) grp, millis);
        m10672a((ieh) grp, (Runnable) grp);
        return grp;
    }

    /* renamed from: b */
    public final iej scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return m10671a(runnable, j, j2, timeUnit, false);
    }

    public final void shutdown() {
        throw new UnsupportedOperationException();
    }

    public final List shutdownNow() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public final ieh mo5933a(Callable callable) {
        iev f = iev.m12774f();
        fxk.m9824a((Runnable) new grn(f, callable));
        return f;
    }

    public final /* bridge */ /* synthetic */ Future submit(Callable callable) {
        return submit(callable);
    }
}
