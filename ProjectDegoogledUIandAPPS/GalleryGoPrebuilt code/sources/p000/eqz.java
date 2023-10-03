package p000;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: eqz */
/* compiled from: PG */
public final class eqz implements ThreadFactory {

    /* renamed from: a */
    private final String f8859a;

    /* renamed from: b */
    private final AtomicInteger f8860b = new AtomicInteger();

    /* renamed from: c */
    private final ThreadFactory f8861c = Executors.defaultThreadFactory();

    public eqz(String str) {
        this.f8859a = (String) abj.m86a((Object) str, (Object) "Name must not be null");
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.f8861c.newThread(new era(runnable));
        String str = this.f8859a;
        int andIncrement = this.f8860b.getAndIncrement();
        StringBuilder sb = new StringBuilder(str.length() + 13);
        sb.append(str);
        sb.append("[");
        sb.append(andIncrement);
        sb.append("]");
        newThread.setName(sb.toString());
        return newThread;
    }
}
