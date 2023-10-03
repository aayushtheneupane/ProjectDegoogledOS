package p000;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/* renamed from: ibs */
/* compiled from: PG */
public abstract class ibs extends AbstractExecutorService implements iek {
    /* access modifiers changed from: protected */
    public final RunnableFuture newTaskFor(Runnable runnable, Object obj) {
        return ifd.m12798a(runnable, obj);
    }

    /* access modifiers changed from: protected */
    public final RunnableFuture newTaskFor(Callable callable) {
        return ifd.m12799a(callable);
    }

    /* renamed from: a */
    public ieh submit(Runnable runnable) {
        return (ieh) super.submit(runnable);
    }

    /* renamed from: a */
    public ieh submit(Runnable runnable, Object obj) {
        return (ieh) super.submit(runnable, obj);
    }

    /* renamed from: a */
    public ieh submit(Callable callable) {
        return (ieh) super.submit(callable);
    }
}
