package p000;

import java.util.concurrent.Executor;

/* renamed from: gpy */
/* compiled from: PG */
final /* synthetic */ class gpy implements Executor {

    /* renamed from: a */
    private final ieh f11818a;

    /* renamed from: b */
    private final Executor f11819b;

    public gpy(ieh ieh, Executor executor) {
        this.f11818a = ieh;
        this.f11819b = executor;
    }

    public final void execute(Runnable runnable) {
        this.f11818a.mo53a(runnable, this.f11819b);
    }
}
