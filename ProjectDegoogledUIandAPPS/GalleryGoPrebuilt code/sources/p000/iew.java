package p000;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: iew */
/* compiled from: PG */
final class iew implements ThreadFactory {

    /* renamed from: a */
    private final /* synthetic */ ThreadFactory f13975a;

    /* renamed from: b */
    private final /* synthetic */ String f13976b;

    /* renamed from: c */
    private final /* synthetic */ AtomicLong f13977c;

    /* renamed from: d */
    private final /* synthetic */ Boolean f13978d;

    public iew(ThreadFactory threadFactory, String str, AtomicLong atomicLong, Boolean bool) {
        this.f13975a = threadFactory;
        this.f13976b = str;
        this.f13977c = atomicLong;
        this.f13978d = bool;
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.f13975a.newThread(runnable);
        String str = this.f13976b;
        if (str != null) {
            newThread.setName(iex.m12777a(str, Long.valueOf(this.f13977c.getAndIncrement())));
        }
        Boolean bool = this.f13978d;
        if (bool != null) {
            newThread.setDaemon(bool.booleanValue());
        }
        return newThread;
    }
}
