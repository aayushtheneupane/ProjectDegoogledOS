package androidx.loader.content;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: androidx.loader.content.j */
abstract class C0479j {
    private static Handler sHandler;

    /* renamed from: lq */
    final AtomicBoolean f454lq = new AtomicBoolean();
    final AtomicBoolean mCancelled = new AtomicBoolean();
    private final FutureTask mFuture = new C0477h(this, new C0476g(this));
    private volatile ModernAsyncTask$Status mStatus = ModernAsyncTask$Status.PENDING;

    C0479j() {
    }

    private static Handler getHandler() {
        Handler handler;
        synchronized (C0479j.class) {
            if (sHandler == null) {
                sHandler = new Handler(Looper.getMainLooper());
            }
            handler = sHandler;
        }
        return handler;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Kc */
    public abstract Object mo4483Kc();

    /* renamed from: a */
    public final void mo4526a(Executor executor) {
        if (this.mStatus != ModernAsyncTask$Status.PENDING) {
            int ordinal = this.mStatus.ordinal();
            if (ordinal == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            } else if (ordinal != 2) {
                throw new IllegalStateException("We should never reach this state");
            } else {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        } else {
            this.mStatus = ModernAsyncTask$Status.RUNNING;
            executor.execute(this.mFuture);
        }
    }

    public final boolean cancel(boolean z) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: m */
    public void mo4528m(Object obj) {
        if (this.mCancelled.get()) {
            C0470a aVar = (C0470a) this;
            aVar.this$0.mo4485a(aVar, obj);
        } else {
            C0470a aVar2 = (C0470a) this;
            aVar2.this$0.mo4486b(aVar2, obj);
        }
        this.mStatus = ModernAsyncTask$Status.FINISHED;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: n */
    public void mo4529n(Object obj) {
        getHandler().post(new C0478i(this, obj));
    }
}
