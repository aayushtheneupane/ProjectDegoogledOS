package p000a.p001a.p002a.p003a;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: a.a.a.a.d */
class C0003d implements ThreadFactory {
    private final AtomicInteger mThreadId = new AtomicInteger(0);

    C0003d(C0004e eVar) {
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(String.format("arch_disk_io_%d", new Object[]{Integer.valueOf(this.mThreadId.getAndIncrement())}));
        return thread;
    }
}
