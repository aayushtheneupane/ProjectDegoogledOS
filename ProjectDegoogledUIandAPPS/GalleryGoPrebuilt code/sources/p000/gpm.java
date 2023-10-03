package p000;

/* renamed from: gpm */
/* compiled from: PG */
final /* synthetic */ class gpm implements Runnable {

    /* renamed from: a */
    private final Runnable f11794a;

    /* renamed from: b */
    private final iev f11795b;

    public gpm(Runnable runnable, iev iev) {
        this.f11794a = runnable;
        this.f11795b = iev;
    }

    public final void run() {
        Runnable runnable = this.f11794a;
        iev iev = this.f11795b;
        try {
            runnable.run();
        } catch (Throwable th) {
            iev.mo6952a(th);
        }
    }
}
