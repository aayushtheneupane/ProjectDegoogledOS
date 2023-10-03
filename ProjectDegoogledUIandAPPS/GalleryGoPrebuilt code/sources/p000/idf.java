package p000;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: idf */
/* compiled from: PG */
abstract class idf extends ieg {

    /* renamed from: a */
    public boolean f13913a = true;

    /* renamed from: b */
    private final Executor f13914b;

    /* renamed from: c */
    private final /* synthetic */ idg f13915c;

    public idf(idg idg, Executor executor) {
        this.f13915c = idg;
        this.f13914b = (Executor) ife.m12898e((Object) executor);
    }

    /* renamed from: a */
    public abstract void mo8407a(Object obj);

    /* renamed from: a */
    public final void mo8409a(Object obj, Throwable th) {
        idg idg = this.f13915c;
        int i = idg.f13916h;
        idg.f13917g = null;
        if (th == null) {
            mo8407a(obj);
        } else if (th instanceof ExecutionException) {
            idg.mo6952a(th.getCause());
        } else if (th instanceof CancellationException) {
            idg.cancel(false);
        } else {
            idg.mo6952a(th);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo8411d() {
        try {
            this.f13914b.execute(this);
        } catch (RejectedExecutionException e) {
            if (this.f13913a) {
                this.f13915c.mo6952a((Throwable) e);
            }
        }
    }

    /* renamed from: c */
    public final boolean mo8410c() {
        return this.f13915c.isDone();
    }
}
