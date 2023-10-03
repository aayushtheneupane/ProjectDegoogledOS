package p000;

import java.util.concurrent.Callable;

/* renamed from: idz */
/* compiled from: PG */
public final class idz implements Callable {

    /* renamed from: a */
    private final /* synthetic */ Runnable f13945a;

    public idz(Runnable runnable) {
        this.f13945a = runnable;
    }

    public final /* bridge */ /* synthetic */ Object call() {
        this.f13945a.run();
        return null;
    }
}
