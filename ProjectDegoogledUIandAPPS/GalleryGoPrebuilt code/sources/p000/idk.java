package p000;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: idk */
/* compiled from: PG */
final class idk implements ice {

    /* renamed from: a */
    private final /* synthetic */ AtomicReference f13926a;

    /* renamed from: b */
    private final /* synthetic */ ice f13927b;

    public idk(AtomicReference atomicReference, ice ice) {
        this.f13926a = atomicReference;
        this.f13927b = ice;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        if (this.f13926a.compareAndSet(idn.NOT_RUN, idn.STARTED)) {
            return this.f13927b.mo2539a();
        }
        return ife.m12868b();
    }

    public final String toString() {
        return this.f13927b.toString();
    }
}
