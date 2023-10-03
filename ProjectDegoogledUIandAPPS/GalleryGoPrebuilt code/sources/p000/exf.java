package p000;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* renamed from: exf */
/* compiled from: PG */
final class exf implements Executor {

    /* renamed from: a */
    private final Handler f9168a = new eui(Looper.getMainLooper());

    public final void execute(Runnable runnable) {
        this.f9168a.post(runnable);
    }
}
