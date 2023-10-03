package p000;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: gol */
/* compiled from: PG */
public final /* synthetic */ class gol implements Runnable {

    /* renamed from: a */
    private final Future f11741a;

    /* renamed from: b */
    private final ieh f11742b;

    public gol(Future future, ieh ieh) {
        this.f11741a = future;
        this.f11742b = ieh;
    }

    public final void run() {
        Future future = this.f11741a;
        ieh ieh = this.f11742b;
        future.cancel(true);
        try {
            ife.m12871b((Future) ieh);
        } catch (ExecutionException e) {
            hmw.m11760b(e.getCause());
        }
    }
}
