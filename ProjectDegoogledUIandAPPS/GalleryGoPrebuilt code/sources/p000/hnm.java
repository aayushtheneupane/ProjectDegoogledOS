package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: hnm */
/* compiled from: PG */
public final class hnm {

    /* renamed from: a */
    private final iea f13110a;

    public /* synthetic */ hnm(iea iea) {
        this.f13110a = iea;
    }

    /* renamed from: a */
    public final ieh mo7613a(Callable callable, Executor executor) {
        return this.f13110a.mo8443a(hmq.m11749a(callable), executor);
    }

    /* renamed from: a */
    public final ieh mo7611a(ice ice, Executor executor) {
        return this.f13110a.mo8442a(hmq.m11743a(ice), executor);
    }

    /* renamed from: a */
    public final ieh mo7612a(Runnable runnable, Executor executor) {
        return this.f13110a.mo8443a((Callable) new idz(hmq.m11748a(runnable)), executor);
    }
}
