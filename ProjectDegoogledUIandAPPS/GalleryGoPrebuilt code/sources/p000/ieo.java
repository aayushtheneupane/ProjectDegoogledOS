package p000;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: ieo */
/* compiled from: PG */
class ieo extends ibs {

    /* renamed from: a */
    private final ExecutorService f13963a;

    public ieo(ExecutorService executorService) {
        this.f13963a = (ExecutorService) ife.m12898e((Object) executorService);
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.f13963a.awaitTermination(j, timeUnit);
    }

    public final void execute(Runnable runnable) {
        this.f13963a.execute(runnable);
    }

    public final boolean isShutdown() {
        return this.f13963a.isShutdown();
    }

    public final boolean isTerminated() {
        return this.f13963a.isTerminated();
    }

    public final void shutdown() {
        this.f13963a.shutdown();
    }

    public final List shutdownNow() {
        return this.f13963a.shutdownNow();
    }
}
