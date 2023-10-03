package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: ien */
/* compiled from: PG */
final class ien implements Executor {

    /* renamed from: a */
    public boolean f13960a = true;

    /* renamed from: b */
    private final /* synthetic */ Executor f13961b;

    /* renamed from: c */
    private final /* synthetic */ ibr f13962c;

    public ien(Executor executor, ibr ibr) {
        this.f13961b = executor;
        this.f13962c = ibr;
    }

    public final void execute(Runnable runnable) {
        try {
            this.f13961b.execute(new iem(this, runnable));
        } catch (RejectedExecutionException e) {
            if (this.f13960a) {
                this.f13962c.mo6952a((Throwable) e);
            }
        }
    }
}
