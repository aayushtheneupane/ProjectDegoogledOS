package com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/* renamed from: com.google.common.collect.Ba */
final class C1555Ba extends SoftReference implements C1571Ia {
    final C1553Aa entry;

    C1555Ba(ReferenceQueue referenceQueue, Object obj, C1553Aa aa) {
        super(obj, referenceQueue);
        this.entry = aa;
    }

    /* renamed from: W */
    public boolean mo8592W() {
        return false;
    }

    /* renamed from: a */
    public C1571Ia mo8593a(ReferenceQueue referenceQueue, Object obj, C1553Aa aa) {
        return new C1555Ba(referenceQueue, obj, aa);
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
