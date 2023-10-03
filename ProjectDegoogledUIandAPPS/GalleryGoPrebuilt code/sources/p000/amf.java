package p000;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* renamed from: amf */
/* compiled from: PG */
public final class amf implements Executor {

    /* renamed from: a */
    public final ArrayDeque f758a = new ArrayDeque();

    /* renamed from: b */
    public final Object f759b = new Object();

    /* renamed from: c */
    private final Executor f760c;

    /* renamed from: d */
    private volatile Runnable f761d;

    public amf(Executor executor) {
        this.f760c = executor;
    }

    public final void execute(Runnable runnable) {
        synchronized (this.f759b) {
            this.f758a.add(new ame(this, runnable));
            if (this.f761d == null) {
                mo642a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo642a() {
        synchronized (this.f759b) {
            Runnable runnable = (Runnable) this.f758a.poll();
            this.f761d = runnable;
            if (runnable != null) {
                this.f760c.execute(this.f761d);
            }
        }
    }
}
