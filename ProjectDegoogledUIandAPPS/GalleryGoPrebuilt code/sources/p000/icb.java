package p000;

import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: icb */
/* compiled from: PG */
final class icb extends ica {

    /* renamed from: a */
    private final AtomicReferenceFieldUpdater f13871a;

    /* renamed from: b */
    private final AtomicIntegerFieldUpdater f13872b;

    public icb(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
        this.f13871a = atomicReferenceFieldUpdater;
        this.f13872b = atomicIntegerFieldUpdater;
    }

    /* renamed from: a */
    public final void mo8372a(icd icd, Set set) {
        this.f13871a.compareAndSet(icd, (Object) null, set);
    }

    /* renamed from: a */
    public final int mo8371a(icd icd) {
        return this.f13872b.decrementAndGet(icd);
    }
}
