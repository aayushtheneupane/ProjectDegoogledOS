package p000;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* renamed from: ce */
/* compiled from: PG */
public final class C0061ce implements Executor {

    /* renamed from: a */
    private final Executor f4147a;

    /* renamed from: b */
    private final ArrayDeque f4148b = new ArrayDeque();

    /* renamed from: c */
    private Runnable f4149c;

    public C0061ce(Executor executor) {
        this.f4147a = executor;
    }

    public final synchronized void execute(Runnable runnable) {
        this.f4148b.offer(new C0060cd(this, runnable));
        if (this.f4149c == null) {
            mo3052a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo3052a() {
        Runnable runnable = (Runnable) this.f4148b.poll();
        this.f4149c = runnable;
        if (runnable != null) {
            this.f4147a.execute(runnable);
        }
    }
}
