package p000;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: fko */
/* compiled from: PG */
final class fko implements Runnable {

    /* renamed from: a */
    private final AtomicReference f9901a = new AtomicReference(this.f9902b);

    /* renamed from: b */
    private final /* synthetic */ Runnable f9902b;

    public fko(Runnable runnable) {
        this.f9902b = runnable;
    }

    public final void run() {
        Runnable runnable = (Runnable) this.f9901a.getAndSet((Object) null);
        if (runnable != null) {
            runnable.run();
        }
    }
}
