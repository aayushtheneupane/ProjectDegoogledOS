package p000;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* renamed from: bfh */
/* compiled from: PG */
final class bfh implements Executor {

    /* renamed from: a */
    private final Handler f2207a = new Handler(Looper.getMainLooper());

    public final void execute(Runnable runnable) {
        this.f2207a.post(runnable);
    }
}
