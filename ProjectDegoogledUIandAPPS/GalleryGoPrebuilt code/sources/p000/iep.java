package p000;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: iep */
/* compiled from: PG */
final class iep extends idu implements iej {

    /* renamed from: a */
    private final ScheduledFuture f13964a;

    public iep(ieh ieh, ScheduledFuture scheduledFuture) {
        super(ieh);
        this.f13964a = scheduledFuture;
    }

    public final boolean cancel(boolean z) {
        boolean cancel = super.cancel(z);
        if (cancel) {
            this.f13964a.cancel(z);
        }
        return cancel;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.f13964a.compareTo((Delayed) obj);
    }

    public final long getDelay(TimeUnit timeUnit) {
        return this.f13964a.getDelay(timeUnit);
    }
}
