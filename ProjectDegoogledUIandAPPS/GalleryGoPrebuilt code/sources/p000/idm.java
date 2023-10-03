package p000;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: idm */
/* compiled from: PG */
final class idm implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ieh f13930a;

    /* renamed from: b */
    private final /* synthetic */ ieh f13931b;

    /* renamed from: c */
    private final /* synthetic */ AtomicReference f13932c;

    /* renamed from: d */
    private final /* synthetic */ iev f13933d;

    /* renamed from: e */
    private final /* synthetic */ ieh f13934e;

    public idm(ieh ieh, ieh ieh2, AtomicReference atomicReference, iev iev, ieh ieh3) {
        this.f13930a = ieh;
        this.f13931b = ieh2;
        this.f13932c = atomicReference;
        this.f13933d = iev;
        this.f13934e = ieh3;
    }

    public final void run() {
        if (this.f13930a.isDone() || (this.f13931b.isCancelled() && this.f13932c.compareAndSet(idn.NOT_RUN, idn.CANCELLED))) {
            this.f13933d.mo6946a(this.f13934e);
        }
    }
}
