package p000;

import java.util.concurrent.ThreadFactory;

/* renamed from: apq */
/* compiled from: PG */
final class apq implements ThreadFactory {
    private apq() {
    }

    public /* synthetic */ apq(byte[] bArr) {
    }

    public final synchronized Thread newThread(Runnable runnable) {
        Thread thread;
        thread = new Thread(runnable, "glide-disk-lru-cache-thread");
        thread.setPriority(1);
        return thread;
    }
}
