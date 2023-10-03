package p000;

import java.util.concurrent.Future;

/* renamed from: idx */
/* compiled from: PG */
final class idx implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Future f13942a;

    public idx(Future future) {
        this.f13942a = future;
    }

    public final void run() {
        this.f13942a.cancel(false);
    }
}
