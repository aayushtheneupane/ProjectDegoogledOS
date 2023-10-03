package p000;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: ibj */
/* compiled from: PG */
final class ibj extends ibe {

    /* renamed from: a */
    public final AtomicReferenceFieldUpdater f13838a;

    /* renamed from: b */
    public final AtomicReferenceFieldUpdater f13839b;

    /* renamed from: c */
    public final AtomicReferenceFieldUpdater f13840c;

    /* renamed from: d */
    public final AtomicReferenceFieldUpdater f13841d;

    /* renamed from: e */
    public final AtomicReferenceFieldUpdater f13842e;

    public ibj(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f13838a = atomicReferenceFieldUpdater;
        this.f13839b = atomicReferenceFieldUpdater2;
        this.f13840c = atomicReferenceFieldUpdater3;
        this.f13841d = atomicReferenceFieldUpdater4;
        this.f13842e = atomicReferenceFieldUpdater5;
    }

    /* renamed from: a */
    public final boolean mo8338a(ibr ibr, ibi ibi, ibi ibi2) {
        return this.f13841d.compareAndSet(ibr, ibi, ibi2);
    }

    /* renamed from: a */
    public final boolean mo8340a(ibr ibr, Object obj, Object obj2) {
        return this.f13842e.compareAndSet(ibr, obj, obj2);
    }

    /* renamed from: a */
    public final boolean mo8339a(ibr ibr, ibq ibq, ibq ibq2) {
        return this.f13840c.compareAndSet(ibr, ibq, ibq2);
    }

    /* renamed from: a */
    public final void mo8336a(ibq ibq, ibq ibq2) {
        this.f13839b.lazySet(ibq, ibq2);
    }

    /* renamed from: a */
    public final void mo8337a(ibq ibq, Thread thread) {
        this.f13838a.lazySet(ibq, thread);
    }
}
