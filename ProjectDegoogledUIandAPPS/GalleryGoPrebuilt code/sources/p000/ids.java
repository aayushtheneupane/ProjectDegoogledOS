package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: ids */
/* compiled from: PG */
final class ids extends idq {

    /* renamed from: a */
    private final ieh f13940a;

    public ids(ieh ieh) {
        this.f13940a = (ieh) ife.m12898e((Object) ieh);
    }

    /* renamed from: a */
    public final void mo53a(Runnable runnable, Executor executor) {
        this.f13940a.mo53a(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        return this.f13940a.cancel(z);
    }

    public final Object get() {
        return this.f13940a.get();
    }

    public final Object get(long j, TimeUnit timeUnit) {
        return this.f13940a.get(j, timeUnit);
    }

    public final boolean isCancelled() {
        return this.f13940a.isCancelled();
    }

    public final boolean isDone() {
        return this.f13940a.isDone();
    }

    public final String toString() {
        return this.f13940a.toString();
    }
}
