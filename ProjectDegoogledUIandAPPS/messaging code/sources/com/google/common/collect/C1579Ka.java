package com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* renamed from: com.google.common.collect.Ka */
class C1579Ka extends WeakReference implements C1553Aa {

    /* renamed from: ZN */
    volatile C1571Ia f2445ZN = MapMakerInternalMap.UNSET;
    final int hash;
    final C1553Aa next;

    C1579Ka(ReferenceQueue referenceQueue, Object obj, int i, C1553Aa aa) {
        super(obj, referenceQueue);
        this.hash = i;
        this.next = aa;
    }

    /* renamed from: F */
    public C1553Aa mo8566F() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: P */
    public C1553Aa mo8567P() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public void mo8568a(long j) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: b */
    public void mo8571b(C1553Aa aa) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: c */
    public void mo8572c(C1553Aa aa) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: d */
    public void mo8573d(C1553Aa aa) {
        throw new UnsupportedOperationException();
    }

    public long getExpirationTime() {
        throw new UnsupportedOperationException();
    }

    public int getHash() {
        return this.hash;
    }

    public Object getKey() {
        return get();
    }

    public C1553Aa getNext() {
        return this.next;
    }

    /* renamed from: j */
    public C1571Ia mo8578j() {
        return this.f2445ZN;
    }

    /* renamed from: s */
    public C1553Aa mo8579s() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: t */
    public C1553Aa mo8580t() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public void mo8569a(C1553Aa aa) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public void mo8570a(C1571Ia ia) {
        C1571Ia ia2 = this.f2445ZN;
        this.f2445ZN = ia;
        ia2.mo8594b(ia);
    }
}
