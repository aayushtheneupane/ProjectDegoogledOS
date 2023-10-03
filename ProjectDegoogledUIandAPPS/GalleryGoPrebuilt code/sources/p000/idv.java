package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* renamed from: idv */
/* compiled from: PG */
public abstract class idv extends idr implements iek {
    protected idv() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract iek mo6914a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public /* bridge */ /* synthetic */ ExecutorService mo6915b() {
        throw null;
    }

    /* renamed from: a */
    public final ieh submit(Runnable runnable) {
        return mo6914a().mo5931a(runnable);
    }

    /* renamed from: a */
    public final ieh submit(Runnable runnable, Object obj) {
        return mo6914a().mo5932a(runnable, obj);
    }

    /* renamed from: a */
    public final ieh submit(Callable callable) {
        return mo6914a().mo5933a(callable);
    }
}
