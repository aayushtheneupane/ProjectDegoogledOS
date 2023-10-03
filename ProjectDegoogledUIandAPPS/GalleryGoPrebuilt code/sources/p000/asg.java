package p000;

import java.util.concurrent.ThreadFactory;

/* renamed from: asg */
/* compiled from: PG */
final class asg implements ThreadFactory {
    public final Thread newThread(Runnable runnable) {
        return new Thread(new asf(runnable), "glide-active-resources");
    }
}
