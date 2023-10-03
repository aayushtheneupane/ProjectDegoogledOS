package p000;

/* renamed from: fls */
/* compiled from: PG */
final /* synthetic */ class fls implements Runnable {

    /* renamed from: a */
    private final flv f9997a;

    /* renamed from: b */
    private final Runnable f9998b;

    public fls(flv flv, Runnable runnable) {
        this.f9997a = flv;
        this.f9998b = runnable;
    }

    public final void run() {
        flv flv = this.f9997a;
        try {
            this.f9998b.run();
        } catch (Throwable th) {
            flv.f10001a.mo3868a(th);
            throw th;
        }
    }
}
