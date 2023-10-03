package p000;

import android.os.Process;

/* renamed from: era */
/* compiled from: PG */
final class era implements Runnable {

    /* renamed from: a */
    private final Runnable f8864a;

    public era(Runnable runnable) {
        this.f8864a = runnable;
    }

    public final void run() {
        Process.setThreadPriority(0);
        this.f8864a.run();
    }
}
