package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: idt */
/* compiled from: PG */
public abstract class idt extends hsb implements Future, ieh {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract ieh mo8430a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public /* bridge */ /* synthetic */ Future mo8431b() {
        throw null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public /* bridge */ /* synthetic */ Object mo6916c() {
        throw null;
    }

    public boolean cancel(boolean z) {
        return mo8431b().cancel(z);
    }

    public final Object get() {
        return mo8431b().get();
    }

    public final Object get(long j, TimeUnit timeUnit) {
        return mo8431b().get(j, timeUnit);
    }

    public final boolean isCancelled() {
        return mo8431b().isCancelled();
    }

    public final boolean isDone() {
        return mo8431b().isDone();
    }

    protected idt() {
    }

    /* renamed from: a */
    public final void mo53a(Runnable runnable, Executor executor) {
        mo8430a().mo53a(runnable, executor);
    }
}
