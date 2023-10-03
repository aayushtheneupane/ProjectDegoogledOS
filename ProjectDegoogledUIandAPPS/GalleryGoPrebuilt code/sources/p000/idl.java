package p000;

import java.util.concurrent.Executor;

/* renamed from: idl */
/* compiled from: PG */
final class idl implements Executor {

    /* renamed from: a */
    private final /* synthetic */ ieh f13928a;

    /* renamed from: b */
    private final /* synthetic */ Executor f13929b;

    public idl(ieh ieh, Executor executor) {
        this.f13928a = ieh;
        this.f13929b = executor;
    }

    public final void execute(Runnable runnable) {
        this.f13928a.mo53a(runnable, this.f13929b);
    }
}
