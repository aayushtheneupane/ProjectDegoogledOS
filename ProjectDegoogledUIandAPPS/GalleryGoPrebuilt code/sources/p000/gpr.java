package p000;

/* renamed from: gpr */
/* compiled from: PG */
final /* synthetic */ class gpr implements Runnable {

    /* renamed from: a */
    private final gps f11811a;

    public gpr(gps gps) {
        this.f11811a = gps;
    }

    public final void run() {
        this.f11811a.f11812a.cancel(false);
    }
}
