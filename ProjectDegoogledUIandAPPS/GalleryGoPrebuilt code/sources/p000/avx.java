package p000;

import android.os.Process;
import android.os.StrictMode;
import android.util.Log;

/* renamed from: avx */
/* compiled from: PG */
final class avx extends Thread {

    /* renamed from: a */
    private final /* synthetic */ avy f1783a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public avx(avy avy, Runnable runnable, String str) {
        super(runnable, str);
        this.f1783a = avy;
    }

    public final void run() {
        Process.setThreadPriority(9);
        if (this.f1783a.f1784a) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
        }
        try {
            super.run();
        } catch (Throwable th) {
            if (Log.isLoggable("GlideExecutor", 6)) {
                Log.e("GlideExecutor", "Request threw uncaught throwable", th);
            }
        }
    }
}
