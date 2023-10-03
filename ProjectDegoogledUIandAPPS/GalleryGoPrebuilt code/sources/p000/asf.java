package p000;

import android.os.Process;

/* renamed from: asf */
/* compiled from: PG */
final class asf implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Runnable f1516a;

    public asf(Runnable runnable) {
        this.f1516a = runnable;
    }

    public final void run() {
        Process.setThreadPriority(10);
        this.f1516a.run();
    }
}
