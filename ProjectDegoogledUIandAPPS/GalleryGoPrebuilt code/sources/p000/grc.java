package p000;

import java.util.concurrent.Executor;

/* renamed from: grc */
/* compiled from: PG */
final class grc implements Runnable {

    /* renamed from: a */
    public ice f11882a;

    /* renamed from: b */
    public Executor f11883b;

    public grc(ice ice, Executor executor) {
        this.f11882a = (ice) ife.m12898e((Object) ice);
        this.f11883b = (Executor) ife.m12898e((Object) executor);
    }

    public final void run() {
        this.f11882a = null;
        this.f11883b = null;
    }
}
