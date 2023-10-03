package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: iaf */
/* compiled from: PG */
final class iaf extends hsb implements ieh {

    /* renamed from: a */
    private final ieh f13715a;

    public /* synthetic */ iaf(ieh ieh) {
        this.f13715a = ieh;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final Object mo6916c() {
        return this.f13715a;
    }

    public final boolean isCancelled() {
        return false;
    }

    /* renamed from: a */
    public final void mo53a(Runnable runnable, Executor executor) {
        this.f13715a.mo53a(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        this.f13715a.cancel(z);
        return false;
    }

    public final /* bridge */ /* synthetic */ Object get() {
        return iag.m12574a((Future) this.f13715a);
    }

    public final /* bridge */ /* synthetic */ Object get(long j, TimeUnit timeUnit) {
        return iag.m12575a(this.f13715a, j, timeUnit);
    }

    public final boolean isDone() {
        return this.f13715a.isDone();
    }
}
