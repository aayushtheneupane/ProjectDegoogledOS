package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/* renamed from: grq */
/* compiled from: PG */
final class grq extends ibr implements Runnable, iej {

    /* renamed from: a */
    private Callable f11921a;

    /* renamed from: f */
    private final long f11922f;

    /* renamed from: g */
    private final /* synthetic */ grr f11923g;

    public /* synthetic */ grq(grr grr, Callable callable, long j) {
        this.f11923g = grr;
        this.f11921a = callable;
        this.f11922f = j;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return grr.m10670a((Delayed) this, (Delayed) obj);
    }

    public final long getDelay(TimeUnit timeUnit) {
        return Math.max(0, TimeUnit.MILLISECONDS.convert(this.f11922f - this.f11923g.f11924a.mo5372c(), timeUnit));
    }

    public final void run() {
        if (!isDone()) {
            try {
                Callable callable = this.f11921a;
                this.f11921a = null;
                mo8346b(callable.call());
            } catch (Throwable th) {
                mo6952a(th);
                throw new RuntimeException(th);
            }
        }
    }
}
