package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: ier */
/* compiled from: PG */
final class ier extends ieo implements iel {

    /* renamed from: a */
    private final ScheduledExecutorService f13966a;

    public ier(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        this.f13966a = (ScheduledExecutorService) ife.m12898e((Object) scheduledExecutorService);
    }

    /* renamed from: a */
    public final iej schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ifd a = ifd.m12798a(runnable, (Object) null);
        return new iep(a, this.f13966a.schedule(a, j, timeUnit));
    }

    /* renamed from: a */
    public final iej schedule(Callable callable, long j, TimeUnit timeUnit) {
        ifd a = ifd.m12799a(callable);
        return new iep(a, this.f13966a.schedule(a, j, timeUnit));
    }

    /* renamed from: a */
    public final iej scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        ieq ieq = new ieq(runnable);
        return new iep(ieq, this.f13966a.scheduleAtFixedRate(ieq, j, j2, timeUnit));
    }

    /* renamed from: b */
    public final iej scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        ieq ieq = new ieq(runnable);
        return new iep(ieq, this.f13966a.scheduleWithFixedDelay(ieq, j, j2, timeUnit));
    }
}
