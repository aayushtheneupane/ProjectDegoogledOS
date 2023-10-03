package p000;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: amt */
/* compiled from: PG */
final class amt extends amo {

    /* renamed from: a */
    private final AtomicReferenceFieldUpdater f789a;

    /* renamed from: b */
    private final AtomicReferenceFieldUpdater f790b;

    /* renamed from: c */
    private final AtomicReferenceFieldUpdater f791c;

    /* renamed from: d */
    private final AtomicReferenceFieldUpdater f792d;

    /* renamed from: e */
    private final AtomicReferenceFieldUpdater f793e;

    public amt(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f789a = atomicReferenceFieldUpdater;
        this.f790b = atomicReferenceFieldUpdater2;
        this.f791c = atomicReferenceFieldUpdater3;
        this.f792d = atomicReferenceFieldUpdater4;
        this.f793e = atomicReferenceFieldUpdater5;
    }

    /* renamed from: a */
    public final boolean mo651a(amx amx, ams ams, ams ams2) {
        return this.f792d.compareAndSet(amx, ams, ams2);
    }

    /* renamed from: a */
    public final boolean mo653a(amx amx, Object obj, Object obj2) {
        return this.f793e.compareAndSet(amx, obj, obj2);
    }

    /* renamed from: a */
    public final boolean mo652a(amx amx, amw amw, amw amw2) {
        return this.f791c.compareAndSet(amx, amw, amw2);
    }

    /* renamed from: a */
    public final void mo649a(amw amw, amw amw2) {
        this.f790b.lazySet(amw, amw2);
    }

    /* renamed from: a */
    public final void mo650a(amw amw, Thread thread) {
        this.f789a.lazySet(amw, thread);
    }
}
