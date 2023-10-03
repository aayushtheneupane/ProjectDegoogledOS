package p000;

import android.os.Process;

/* renamed from: fll */
/* compiled from: PG */
final /* synthetic */ class fll implements Runnable {

    /* renamed from: a */
    private final flm f9984a;

    /* renamed from: b */
    private final Runnable f9985b;

    public fll(flm flm, Runnable runnable) {
        this.f9984a = flm;
        this.f9985b = runnable;
    }

    public final void run() {
        flm flm = this.f9984a;
        Runnable runnable = this.f9985b;
        int i = flm.f9986a;
        if (i != 0) {
            Process.setThreadPriority(i);
        }
        runnable.run();
    }
}
