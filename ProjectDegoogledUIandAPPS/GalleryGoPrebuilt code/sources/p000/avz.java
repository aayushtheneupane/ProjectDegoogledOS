package p000;

import android.os.Build;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: avz */
/* compiled from: PG */
public final class avz implements ExecutorService {

    /* renamed from: a */
    public static final long f1787a = TimeUnit.SECONDS.toMillis(10);

    /* renamed from: b */
    private static volatile int f1788b;

    /* renamed from: c */
    private final ExecutorService f1789c;

    public avz(ExecutorService executorService) {
        this.f1789c = executorService;
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.f1789c.awaitTermination(j, timeUnit);
    }

    /* renamed from: a */
    public static int m1778a() {
        if (f1788b == 0) {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            int i = Build.VERSION.SDK_INT;
            f1788b = Math.min(4, availableProcessors);
        }
        return f1788b;
    }

    public final void execute(Runnable runnable) {
        this.f1789c.execute(runnable);
    }

    public final List invokeAll(Collection collection) {
        return this.f1789c.invokeAll(collection);
    }

    public final List invokeAll(Collection collection, long j, TimeUnit timeUnit) {
        return this.f1789c.invokeAll(collection, j, timeUnit);
    }

    public final Object invokeAny(Collection collection) {
        return this.f1789c.invokeAny(collection);
    }

    public final Object invokeAny(Collection collection, long j, TimeUnit timeUnit) {
        return this.f1789c.invokeAny(collection, j, timeUnit);
    }

    public final boolean isShutdown() {
        return this.f1789c.isShutdown();
    }

    public final boolean isTerminated() {
        return this.f1789c.isTerminated();
    }

    public final void shutdown() {
        this.f1789c.shutdown();
    }

    public final List shutdownNow() {
        return this.f1789c.shutdownNow();
    }

    public final Future submit(Runnable runnable) {
        return this.f1789c.submit(runnable);
    }

    public final Future submit(Runnable runnable, Object obj) {
        return this.f1789c.submit(runnable, obj);
    }

    public final Future submit(Callable callable) {
        return this.f1789c.submit(callable);
    }

    public final String toString() {
        return this.f1789c.toString();
    }
}
