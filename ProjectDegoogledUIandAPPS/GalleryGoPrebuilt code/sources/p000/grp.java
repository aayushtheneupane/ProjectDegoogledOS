package p000;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: grp */
/* compiled from: PG */
final class grp extends ibr implements Runnable, iej {

    /* renamed from: a */
    private final long f11915a;

    /* renamed from: f */
    private final long f11916f;

    /* renamed from: g */
    private final boolean f11917g;

    /* renamed from: h */
    private final AtomicLong f11918h = new AtomicLong(0);

    /* renamed from: i */
    private Runnable f11919i;

    /* renamed from: j */
    private final /* synthetic */ grr f11920j;

    public /* synthetic */ grp(grr grr, Runnable runnable, long j, long j2, boolean z) {
        this.f11920j = grr;
        this.f11919i = runnable;
        this.f11915a = j;
        this.f11916f = j2;
        this.f11917g = z;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return grr.m10670a((Delayed) this, (Delayed) obj);
    }

    public final long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(m10669f(), TimeUnit.MILLISECONDS);
    }

    /* renamed from: f */
    private final long m10669f() {
        return Math.max(0, ((this.f11918h.get() * this.f11916f) + this.f11915a) - this.f11920j.f11924a.mo5372c());
    }

    public final void run() {
        if (!isDone()) {
            this.f11918h.incrementAndGet();
            try {
                this.f11919i.run();
                if (!this.f11917g) {
                    fxk.m9825a((Runnable) this, this.f11916f);
                } else {
                    fxk.m9825a((Runnable) this, m10669f());
                }
            } catch (Throwable th) {
                this.f11919i = null;
                mo6952a(th);
            }
        }
    }
}
