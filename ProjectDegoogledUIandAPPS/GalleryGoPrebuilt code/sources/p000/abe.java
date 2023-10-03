package p000;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: abe */
/* compiled from: PG */
public final class abe implements ieh {

    /* renamed from: a */
    public final WeakReference f74a;

    /* renamed from: b */
    public final aaz f75b = new abd(this);

    public abe(aba aba) {
        this.f74a = new WeakReference(aba);
    }

    /* renamed from: a */
    public final void mo53a(Runnable runnable, Executor executor) {
        this.f75b.mo53a(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        aba aba = (aba) this.f74a.get();
        boolean cancel = this.f75b.cancel(z);
        if (!cancel || aba == null) {
            return cancel;
        }
        aba.f69a = null;
        aba.f70b = null;
        aba.f71c.mo54a((Object) null);
        return true;
    }

    public final Object get() {
        return this.f75b.get();
    }

    public final Object get(long j, TimeUnit timeUnit) {
        return this.f75b.get(j, timeUnit);
    }

    public final boolean isCancelled() {
        return this.f75b.isCancelled();
    }

    public final boolean isDone() {
        return this.f75b.isDone();
    }

    /* renamed from: a */
    public final boolean mo72a(Throwable th) {
        aaz aaz = this.f75b;
        if (!aaz.f60c.mo48a(aaz, (Object) null, (Object) new aat((Throwable) aaz.m52b(th)))) {
            return false;
        }
        aaz.m50a(aaz);
        return true;
    }

    public final String toString() {
        return this.f75b.toString();
    }
}
