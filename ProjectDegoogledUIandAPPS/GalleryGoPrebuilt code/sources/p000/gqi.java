package p000;

/* renamed from: gqi */
/* compiled from: PG */
final /* synthetic */ class gqi implements Runnable {

    /* renamed from: a */
    private final iev f11837a;

    /* renamed from: b */
    private final eyn f11838b;

    public gqi(iev iev, eyn eyn) {
        this.f11837a = iev;
        this.f11838b = eyn;
    }

    public final void run() {
        iev iev = this.f11837a;
        eyn eyn = this.f11838b;
        if (iev.isCancelled()) {
            eyn.mo5403a();
        }
    }
}
