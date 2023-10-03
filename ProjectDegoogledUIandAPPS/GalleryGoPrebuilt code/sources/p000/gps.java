package p000;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/* renamed from: gps */
/* compiled from: PG */
final class gps extends idu implements iej {

    /* renamed from: a */
    public volatile iej f11812a;

    public gps(ieh ieh, iej iej) {
        super(ieh);
        this.f11812a = iej;
        ieh.mo53a(new gpr(this), idh.f13918a);
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.f11812a.compareTo((Delayed) obj);
    }

    public final long getDelay(TimeUnit timeUnit) {
        return this.f11812a.getDelay(timeUnit);
    }
}
