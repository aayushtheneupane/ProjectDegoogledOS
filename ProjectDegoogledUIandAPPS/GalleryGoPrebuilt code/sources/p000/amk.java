package p000;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* renamed from: amk */
/* compiled from: PG */
final class amk implements ThreadFactory {

    /* renamed from: a */
    private int f771a = 0;

    public final Thread newThread(Runnable runnable) {
        Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
        newThread.setName("WorkManager-WorkTimer-thread-" + this.f771a);
        this.f771a = this.f771a + 1;
        return newThread;
    }
}
