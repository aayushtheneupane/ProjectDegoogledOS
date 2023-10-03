package p000;

import java.util.concurrent.Executor;

/* renamed from: gpl */
/* compiled from: PG */
final /* synthetic */ class gpl implements Runnable {

    /* renamed from: a */
    private final Executor f11791a;

    /* renamed from: b */
    private final Runnable f11792b;

    /* renamed from: c */
    private final iev f11793c;

    public gpl(Executor executor, Runnable runnable, iev iev) {
        this.f11791a = executor;
        this.f11792b = runnable;
        this.f11793c = iev;
    }

    public final void run() {
        this.f11791a.execute(new gpm(this.f11792b, this.f11793c));
    }
}
