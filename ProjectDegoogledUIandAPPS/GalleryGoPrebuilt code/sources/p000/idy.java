package p000;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: idy */
/* compiled from: PG */
final class idy implements Runnable {

    /* renamed from: a */
    private final Future f13943a;

    /* renamed from: b */
    private final idw f13944b;

    public idy(Future future, idw idw) {
        this.f13943a = future;
        this.f13944b = idw;
    }

    public final void run() {
        Throwable e;
        Future future = this.f13943a;
        if (!(future instanceof iff) || (e = ((iff) future).mo8349e()) == null) {
            try {
                this.f13944b.mo3867a(ife.m12871b(this.f13943a));
            } catch (ExecutionException e2) {
                this.f13944b.mo3868a(e2.getCause());
            } catch (Error | RuntimeException e3) {
                this.f13944b.mo3868a(e3);
            }
        } else {
            this.f13944b.mo3868a(e);
        }
    }

    public final String toString() {
        hpx f = ife.m12901f((Object) this);
        f.mo7672a(this.f13944b);
        return f.toString();
    }
}
