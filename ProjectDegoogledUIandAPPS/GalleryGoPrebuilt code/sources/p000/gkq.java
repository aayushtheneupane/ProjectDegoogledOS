package p000;

/* renamed from: gkq */
/* compiled from: PG */
final /* synthetic */ class gkq implements Runnable {

    /* renamed from: a */
    private final gkt f11549a;

    public gkq(gkt gkt) {
        this.f11549a = gkt;
    }

    public final void run() {
        gkt gkt = this.f11549a;
        synchronized (gkt.f11552b) {
            for (gks a : gkt.f11552b) {
                a.mo6815a();
            }
        }
    }
}
