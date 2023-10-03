package p000;

import java.util.concurrent.Executor;

/* renamed from: ana */
/* compiled from: PG */
final class ana implements Executor {

    /* renamed from: a */
    private final /* synthetic */ anb f808a;

    public ana(anb anb) {
        this.f808a = anb;
    }

    public final void execute(Runnable runnable) {
        this.f808a.f810b.post(runnable);
    }
}
