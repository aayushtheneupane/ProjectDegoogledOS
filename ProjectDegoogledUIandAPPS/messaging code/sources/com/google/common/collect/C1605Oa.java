package com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* renamed from: com.google.common.collect.Oa */
final class C1605Oa extends WeakReference implements C1571Ia {
    final C1553Aa entry;

    C1605Oa(ReferenceQueue referenceQueue, Object obj, C1553Aa aa) {
        super(obj, referenceQueue);
        this.entry = aa;
    }

    /* renamed from: W */
    public boolean mo8592W() {
        return false;
    }

    /* renamed from: a */
    public C1571Ia mo8593a(ReferenceQueue referenceQueue, Object obj, C1553Aa aa) {
        return new C1605Oa(referenceQueue, obj, aa);
    }

    /* renamed from: b */
    public void mo8594b(C1571Ia ia) {
        clear();
    }

    public C1553Aa getEntry() {
        return this.entry;
    }

    /* renamed from: v */
    public Object mo8596v() {
        return get();
    }
}
