package p000;

import android.os.Process;
import android.os.StrictMode;

/* renamed from: goi */
/* compiled from: PG */
final /* synthetic */ class goi implements Runnable {

    /* renamed from: a */
    private final hpy f11735a;

    /* renamed from: b */
    private final int f11736b;

    /* renamed from: c */
    private final Runnable f11737c;

    public goi(hpy hpy, int i, Runnable runnable) {
        this.f11735a = hpy;
        this.f11736b = i;
        this.f11737c = runnable;
    }

    public final void run() {
        hpy hpy = this.f11735a;
        int i = this.f11736b;
        Runnable runnable = this.f11737c;
        if (hpy.mo7646a()) {
            StrictMode.setThreadPolicy((StrictMode.ThreadPolicy) hpy.mo7647b());
        }
        Process.setThreadPriority(i);
        runnable.run();
    }
}
