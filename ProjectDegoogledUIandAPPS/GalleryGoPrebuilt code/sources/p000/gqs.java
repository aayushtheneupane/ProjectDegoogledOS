package p000;

import java.util.concurrent.Executor;

/* renamed from: gqs */
/* compiled from: PG */
final /* synthetic */ class gqs implements Runnable {

    /* renamed from: a */
    private final gqw f11846a;

    /* renamed from: b */
    private final Executor f11847b;

    public gqs(gqw gqw, Executor executor) {
        this.f11846a = gqw;
        this.f11847b = executor;
    }

    public final void run() {
        this.f11847b.execute(new gqu(this.f11846a));
    }
}
