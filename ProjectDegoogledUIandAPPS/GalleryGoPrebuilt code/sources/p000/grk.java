package p000;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: grk */
/* compiled from: PG */
public final class grk extends ibs {

    /* renamed from: a */
    private final /* synthetic */ Executor f11901a;

    /* renamed from: b */
    private final /* synthetic */ iel f11902b;

    public grk(Executor executor, iel iel) {
        this.f11901a = executor;
        this.f11902b = iel;
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.f11902b.awaitTermination(j, timeUnit);
    }

    public final void execute(Runnable runnable) {
        this.f11901a.execute(runnable);
    }

    public final boolean isShutdown() {
        return this.f11902b.isShutdown();
    }

    public final boolean isTerminated() {
        return this.f11902b.isTerminated();
    }

    public final void shutdown() {
        this.f11902b.shutdown();
    }

    public final List shutdownNow() {
        return this.f11902b.shutdownNow();
    }
}
