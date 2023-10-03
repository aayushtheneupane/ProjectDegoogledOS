package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: gpt */
/* compiled from: PG */
public final class gpt extends idv implements iel {

    /* renamed from: a */
    public final iel f11813a;

    /* renamed from: b */
    private final iek f11814b;

    private gpt(iek iek, iel iel) {
        this.f11814b = iek;
        this.f11813a = iel;
    }

    /* renamed from: a */
    public final iek mo6914a() {
        return this.f11814b;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ExecutorService mo6915b() {
        return this.f11814b;
    }

    /* renamed from: c */
    public final /* bridge */ /* synthetic */ Object mo6916c() {
        return this.f11814b;
    }

    /* renamed from: a */
    public static gpt m10595a(iek iek, iel iel) {
        return new gpt(iek, iel);
    }

    /* renamed from: a */
    public final iej schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        iei a = iei.m12760a(runnable);
        return new gps(a, this.f11813a.mo5935a((Runnable) new gpj(this, a), j, timeUnit));
    }

    /* renamed from: a */
    public final iej schedule(Callable callable, long j, TimeUnit timeUnit) {
        iei a = iei.m12761a(callable);
        return new gps(a, this.f11813a.mo5935a((Runnable) new gpk(this, a), j, timeUnit));
    }

    /* renamed from: a */
    public final iej scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Executor a = ife.m12837a((Executor) this);
        iev f = iev.m12774f();
        Runnable runnable2 = runnable;
        return new gps(f, this.f11813a.mo5934a(new gpl(a, runnable, f), j, j2, timeUnit));
    }

    /* renamed from: b */
    public final iej scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        iev f = iev.m12774f();
        gps gps = new gps(f, (iej) null);
        long j3 = j;
        gps.f11812a = this.f11813a.mo5935a((Runnable) new gpq(this, runnable, f, gps, j2, timeUnit), j, timeUnit);
        return gps;
    }
}
