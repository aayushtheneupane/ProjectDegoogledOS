package p000;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* renamed from: anb */
/* compiled from: PG */
public final class anb implements amz {

    /* renamed from: a */
    public final amf f809a;

    /* renamed from: b */
    public final Handler f810b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    public final Executor f811c = new ana(this);

    public anb(Executor executor) {
        this.f809a = new amf(executor);
    }

    /* renamed from: a */
    public final void mo668a(Runnable runnable) {
        this.f809a.execute(runnable);
    }
}
