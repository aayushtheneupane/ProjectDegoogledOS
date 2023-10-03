package p000;

import java.util.concurrent.ThreadFactory;

/* renamed from: fkh */
/* compiled from: PG */
final /* synthetic */ class fkh implements ThreadFactory {

    /* renamed from: a */
    public static final ThreadFactory f9882a = new fkh();

    private fkh() {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "Primes-preInit");
    }
}
