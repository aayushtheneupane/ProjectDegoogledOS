package p000;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/* renamed from: hpf */
/* compiled from: PG */
public final class hpf extends idu implements iej {

    /* renamed from: a */
    public volatile iej f13211a = null;

    public hpf(ieh ieh) {
        super(ieh);
        ieh.mo53a(new hpe(this), idh.f13918a);
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.f13211a.compareTo((Delayed) obj);
    }

    public final long getDelay(TimeUnit timeUnit) {
        return this.f13211a.getDelay(timeUnit);
    }

    /* renamed from: a */
    public final void mo7641a(iej iej) {
        this.f13211a = iej;
        if (isDone()) {
            iej.cancel(false);
        }
    }
}
