package p000;

import android.os.Build;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* renamed from: agz */
/* compiled from: PG */
public final class agz {

    /* renamed from: a */
    public final Executor f463a;

    /* renamed from: b */
    public final Executor f464b;

    /* renamed from: c */
    public final ahv f465c;

    public agz(agx agx) {
        Executor executor = agx.f460a;
        if (executor == null) {
            this.f463a = m470b();
        } else {
            this.f463a = executor;
        }
        Executor executor2 = agx.f462c;
        if (executor2 == null) {
            this.f464b = m470b();
        } else {
            this.f464b = executor2;
        }
        ahv ahv = agx.f461b;
        if (ahv == null) {
            this.f465c = ahv.m511a();
        } else {
            this.f465c = ahv;
        }
    }

    /* renamed from: b */
    private static final Executor m470b() {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)));
    }

    /* renamed from: a */
    public static final void m469a() {
        int i = Build.VERSION.SDK_INT;
    }
}
