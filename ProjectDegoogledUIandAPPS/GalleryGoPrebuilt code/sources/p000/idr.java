package p000;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: idr */
/* compiled from: PG */
public abstract class idr extends hsb implements ExecutorService {
    protected idr() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract ExecutorService mo6915b();

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public /* bridge */ /* synthetic */ Object mo6916c() {
        throw null;
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        return mo6915b().awaitTermination(j, timeUnit);
    }

    public void execute(Runnable runnable) {
        mo6915b().execute(runnable);
    }

    public final List invokeAll(Collection collection) {
        return mo6915b().invokeAll(collection);
    }

    public final List invokeAll(Collection collection, long j, TimeUnit timeUnit) {
        return mo6915b().invokeAll(collection, j, timeUnit);
    }

    public final Object invokeAny(Collection collection) {
        return mo6915b().invokeAny(collection);
    }

    public final Object invokeAny(Collection collection, long j, TimeUnit timeUnit) {
        return mo6915b().invokeAny(collection, j, timeUnit);
    }

    public final boolean isShutdown() {
        return mo6915b().isShutdown();
    }

    public final boolean isTerminated() {
        return mo6915b().isTerminated();
    }

    public final void shutdown() {
        mo6915b().shutdown();
    }

    public final List shutdownNow() {
        return mo6915b().shutdownNow();
    }

    public Future submit(Runnable runnable) {
        return mo6915b().submit(runnable);
    }

    public Future submit(Runnable runnable, Object obj) {
        return mo6915b().submit(runnable, obj);
    }

    public Future submit(Callable callable) {
        return mo6915b().submit(callable);
    }
}
