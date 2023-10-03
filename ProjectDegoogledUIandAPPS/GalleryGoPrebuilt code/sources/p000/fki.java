package p000;

import java.lang.Thread;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: fki */
/* compiled from: PG */
final class fki implements Thread.UncaughtExceptionHandler, fkj {

    /* renamed from: a */
    private final Thread.UncaughtExceptionHandler f9883a;

    /* renamed from: b */
    private final AtomicReference f9884b;

    /* renamed from: c */
    private final AtomicReference f9885c;

    /* renamed from: d */
    private volatile fkm f9886d;

    public /* synthetic */ fki(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, AtomicReference atomicReference, AtomicReference atomicReference2) {
        this.f9883a = uncaughtExceptionHandler;
        this.f9884b = atomicReference;
        this.f9885c = atomicReference2;
    }

    /* renamed from: a */
    public final void mo5896a(fit fit) {
        this.f9886d = fit;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        if (this.f9886d == null) {
            Runnable runnable = (Runnable) this.f9884b.getAndSet((Object) null);
            CountDownLatch countDownLatch = (CountDownLatch) this.f9885c.getAndSet((Object) null);
            if (runnable == null || countDownLatch == null) {
                Thread.sleep(100);
            } else {
                try {
                    Executors.newSingleThreadExecutor(fkh.f9882a).execute(runnable);
                    countDownLatch.await(1000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    flw.m9202d("Primes", "Wait for initialization is interrupted", new Object[0]);
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (this.f9886d != null) {
            fkm fkm = this.f9886d;
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f9883a;
            fit fit = (fit) fkm;
            if (fit.f9756a.mo5872a()) {
                uncaughtExceptionHandler = fit.f9756a.mo5873b().mo5845a(uncaughtExceptionHandler);
            }
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.f9883a;
        if (uncaughtExceptionHandler2 != null) {
            uncaughtExceptionHandler2.uncaughtException(thread, th);
        }
    }
}
