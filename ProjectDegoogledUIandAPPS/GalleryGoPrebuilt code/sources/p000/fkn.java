package p000;

import java.util.concurrent.ExecutorService;

/* renamed from: fkn */
/* compiled from: PG */
final class fkn implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ExecutorService f9897a;

    /* renamed from: b */
    private final /* synthetic */ Runnable f9898b;

    /* renamed from: c */
    private final /* synthetic */ boolean f9899c;

    /* renamed from: d */
    private final /* synthetic */ fks f9900d;

    public fkn(fks fks, ExecutorService executorService, Runnable runnable, boolean z) {
        this.f9900d = fks;
        this.f9897a = executorService;
        this.f9898b = runnable;
        this.f9899c = z;
    }

    public final void run() {
        ExecutorService executorService = this.f9897a;
        fks fks = this.f9900d;
        try {
            executorService.submit(this.f9898b);
        } catch (RuntimeException e) {
            flw.m9198b("Primes", "Primes failed to initialize", e, new Object[0]);
            fks.mo5835a();
        }
        if (this.f9899c) {
            this.f9897a.shutdown();
        }
    }
}
