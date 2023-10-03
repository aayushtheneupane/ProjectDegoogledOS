package p000;

import android.util.Log;
import java.util.logging.Level;

/* renamed from: gpv */
/* compiled from: PG */
final class gpv implements Runnable {

    /* renamed from: a */
    private final Runnable f11816a;

    public gpv(Runnable runnable) {
        this.f11816a = runnable;
    }

    public final void run() {
        try {
            this.f11816a.run();
        } catch (Throwable th) {
            gpw.f11817a.logp(Level.SEVERE, "com.google.apps.tiktok.concurrent.ErrorLoggingExecutorService$LoggingRunnable", "run", "Uncaught exception from runnable", th);
            Log.e("ErrorLoggingExecutor", "Uncaught exception from runnable", th);
        }
    }

    public final String toString() {
        return this.f11816a.toString();
    }
}
