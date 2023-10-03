package p000;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: c */
/* compiled from: PG */
final class C0056c implements ThreadFactory {

    /* renamed from: a */
    private final AtomicInteger f3948a = new AtomicInteger(0);

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(String.format("arch_disk_io_%d", new Object[]{Integer.valueOf(this.f3948a.getAndIncrement())}));
        return thread;
    }
}
