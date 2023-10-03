package p000;

/* renamed from: inj */
/* compiled from: PG */
final class inj implements Runnable {

    /* renamed from: a */
    private final imu f14567a;

    /* renamed from: b */
    private final Runnable f14568b;

    public inj(imu imu, Runnable runnable) {
        this.f14567a = imu;
        this.f14568b = runnable;
    }

    public final void run() {
        try {
            ine.m14181a(this.f14567a);
            this.f14568b.run();
        } finally {
            ine.m14180a();
        }
    }
}
