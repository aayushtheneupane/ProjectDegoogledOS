package p000;

/* renamed from: hfg */
/* compiled from: PG */
final /* synthetic */ class hfg implements Runnable {

    /* renamed from: a */
    private final ieh f12649a;

    public hfg(ieh ieh) {
        this.f12649a = ieh;
    }

    public final void run() {
        ieh ieh = this.f12649a;
        if (!ieh.isCancelled()) {
            fxk.m9824a(hmq.m11748a((Runnable) new hfj(ieh)));
        }
    }
}
