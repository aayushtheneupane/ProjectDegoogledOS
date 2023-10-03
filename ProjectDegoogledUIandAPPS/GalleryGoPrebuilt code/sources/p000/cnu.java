package p000;

/* renamed from: cnu */
/* compiled from: PG */
final /* synthetic */ class cnu implements Runnable {

    /* renamed from: a */
    private final iev f4744a;

    /* renamed from: b */
    private final bdz f4745b;

    public cnu(iev iev, bdz bdz) {
        this.f4744a = iev;
        this.f4745b = bdz;
    }

    public final void run() {
        iev iev = this.f4744a;
        bdz bdz = this.f4745b;
        if (iev.isCancelled()) {
            bdz.cancel(true);
        }
    }
}
