package p000;

/* renamed from: inf */
/* compiled from: PG */
public final /* synthetic */ class inf implements Runnable {

    /* renamed from: a */
    private final imu f14554a;

    /* renamed from: b */
    private final inc f14555b;

    /* renamed from: c */
    private final Runnable f14556c;

    public inf(imu imu, inc inc, Runnable runnable) {
        this.f14554a = imu;
        this.f14555b = inc;
        this.f14556c = runnable;
    }

    public final void run() {
        imu imu = this.f14554a;
        inc inc = this.f14555b;
        Runnable runnable = this.f14556c;
        ine.m14181a(imu);
        inc.mo9014c(imu);
        try {
            runnable.run();
        } finally {
            inc.mo9015d(imu);
            ine.m14180a();
        }
    }
}
