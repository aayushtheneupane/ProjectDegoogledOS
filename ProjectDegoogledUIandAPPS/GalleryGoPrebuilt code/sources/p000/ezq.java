package p000;

/* renamed from: ezq */
/* compiled from: PG */
public final /* synthetic */ class ezq implements Runnable {

    /* renamed from: a */
    private final iev f9222a;

    /* renamed from: b */
    private final eyn f9223b;

    public ezq(iev iev, eyn eyn) {
        this.f9222a = iev;
        this.f9223b = eyn;
    }

    public final void run() {
        iev iev = this.f9222a;
        eyn eyn = this.f9223b;
        if (iev.isCancelled()) {
            eyn.mo5403a();
        }
    }
}
