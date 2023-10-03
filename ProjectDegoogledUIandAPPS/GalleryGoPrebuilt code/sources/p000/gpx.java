package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* renamed from: gpx */
/* compiled from: PG */
abstract class gpx extends idv implements iel {
    protected gpx() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public /* bridge */ /* synthetic */ iek mo6914a() {
        throw null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract iel mo6921d();

    /* renamed from: a */
    public final iej schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return mo6921d().mo5935a(runnable, j, timeUnit);
    }

    /* renamed from: a */
    public final iej schedule(Callable callable, long j, TimeUnit timeUnit) {
        return mo6921d().mo5936a(callable, j, timeUnit);
    }

    /* renamed from: a */
    public final iej scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return mo6921d().mo5934a(runnable, j, j2, timeUnit);
    }

    /* renamed from: b */
    public final iej scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return mo6921d().mo5938b(runnable, j, j2, timeUnit);
    }
}
