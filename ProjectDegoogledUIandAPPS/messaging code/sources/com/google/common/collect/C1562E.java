package com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;

/* renamed from: com.google.common.collect.E */
final class C1562E implements C1571Ia {

    /* renamed from: t */
    final Throwable f2426t;

    C1562E(Throwable th) {
        this.f2426t = th;
    }

    /* renamed from: W */
    public boolean mo8592W() {
        return false;
    }

    /* renamed from: a */
    public C1571Ia mo8593a(ReferenceQueue referenceQueue, Object obj, C1553Aa aa) {
        return this;
    }

    /* renamed from: b */
    public void mo8594b(C1571Ia ia) {
    }

    public Object get() {
        return null;
    }

    public C1553Aa getEntry() {
        return null;
    }

    /* renamed from: v */
    public Object mo8596v() {
        throw new ExecutionException(this.f2426t);
    }
}
