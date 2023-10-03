package p000;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: aav */
/* compiled from: PG */
final class aav extends aaq {

    /* renamed from: a */
    public final AtomicReferenceFieldUpdater f50a;

    /* renamed from: b */
    public final AtomicReferenceFieldUpdater f51b;

    /* renamed from: c */
    public final AtomicReferenceFieldUpdater f52c;

    /* renamed from: d */
    public final AtomicReferenceFieldUpdater f53d;

    /* renamed from: e */
    public final AtomicReferenceFieldUpdater f54e;

    public aav(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f50a = atomicReferenceFieldUpdater;
        this.f51b = atomicReferenceFieldUpdater2;
        this.f52c = atomicReferenceFieldUpdater3;
        this.f53d = atomicReferenceFieldUpdater4;
        this.f54e = atomicReferenceFieldUpdater5;
    }

    /* renamed from: a */
    public final boolean mo46a(aaz aaz, aau aau, aau aau2) {
        return this.f53d.compareAndSet(aaz, aau, aau2);
    }

    /* renamed from: a */
    public final boolean mo48a(aaz aaz, Object obj, Object obj2) {
        return this.f54e.compareAndSet(aaz, obj, obj2);
    }

    /* renamed from: a */
    public final boolean mo47a(aaz aaz, aay aay, aay aay2) {
        return this.f52c.compareAndSet(aaz, aay, aay2);
    }

    /* renamed from: a */
    public final void mo44a(aay aay, aay aay2) {
        this.f51b.lazySet(aay, aay2);
    }

    /* renamed from: a */
    public final void mo45a(aay aay, Thread thread) {
        this.f50a.lazySet(aay, thread);
    }
}
