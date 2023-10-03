package p000;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: flm */
/* compiled from: PG */
final class flm implements ThreadFactory {

    /* renamed from: a */
    public final int f9986a;

    /* renamed from: b */
    private final AtomicInteger f9987b;

    /* renamed from: c */
    private final String f9988c;

    public flm(int i) {
        this("Primes", i);
    }

    public flm(String str, int i) {
        this.f9987b = new AtomicInteger(1);
        this.f9986a = i;
        this.f9988c = str;
    }

    public final Thread newThread(Runnable runnable) {
        fll fll = new fll(this, runnable);
        String str = this.f9988c;
        int andIncrement = this.f9987b.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
        sb.append(str);
        sb.append("-");
        sb.append(andIncrement);
        Thread thread = new Thread(fll, sb.toString());
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        return thread;
    }
}
