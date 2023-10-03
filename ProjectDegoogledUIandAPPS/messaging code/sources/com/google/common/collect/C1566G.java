package com.google.common.collect;

import com.google.common.base.C1547v;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;

/* renamed from: com.google.common.collect.G */
final class C1566G implements C1571Ia {
    final C1547v computingFunction;

    /* renamed from: mN */
    volatile C1571Ia f2433mN = MapMakerInternalMap.UNSET;

    public C1566G(C1547v vVar) {
        this.computingFunction = vVar;
    }

    /* renamed from: W */
    public boolean mo8592W() {
        return true;
    }

    /* renamed from: a */
    public C1571Ia mo8593a(ReferenceQueue referenceQueue, Object obj, C1553Aa aa) {
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8671a(C1571Ia ia) {
        synchronized (this) {
            if (this.f2433mN == MapMakerInternalMap.UNSET) {
                this.f2433mN = ia;
                notifyAll();
            }
        }
    }

    /* renamed from: b */
    public void mo8594b(C1571Ia ia) {
        mo8671a(ia);
    }

    public Object get() {
        return null;
    }

    public C1553Aa getEntry() {
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0023, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0024, code lost:
        r0 = r1;
     */
    /* renamed from: v */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object mo8596v() {
        /*
            r3 = this;
            com.google.common.collect.Ia r0 = r3.f2433mN
            com.google.common.collect.Ia r1 = com.google.common.collect.MapMakerInternalMap.UNSET
            if (r0 != r1) goto L_0x0031
            r0 = 0
            monitor-enter(r3)     // Catch:{ all -> 0x0026 }
            r1 = r0
        L_0x0009:
            com.google.common.collect.Ia r0 = r3.f2433mN     // Catch:{ all -> 0x0020 }
            com.google.common.collect.Ia r2 = com.google.common.collect.MapMakerInternalMap.UNSET     // Catch:{ all -> 0x0020 }
            if (r0 != r2) goto L_0x0015
            r3.wait()     // Catch:{ InterruptedException -> 0x0013 }
            goto L_0x0009
        L_0x0013:
            r1 = 1
            goto L_0x0009
        L_0x0015:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0031
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
            goto L_0x0031
        L_0x0020:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            throw r0     // Catch:{ all -> 0x0023 }
        L_0x0023:
            r3 = move-exception
            r0 = r1
            goto L_0x0027
        L_0x0026:
            r3 = move-exception
        L_0x0027:
            if (r0 == 0) goto L_0x0030
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
        L_0x0030:
            throw r3
        L_0x0031:
            com.google.common.collect.Ia r3 = r3.f2433mN
            java.lang.Object r3 = r3.mo8596v()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.C1566G.mo8596v():java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public Object mo8672b(Object obj, int i) {
        try {
            Object apply = this.computingFunction.apply(obj);
            mo8671a(new C1564F(apply));
            return apply;
        } catch (Throwable th) {
            mo8671a(new C1562E(th));
            throw new ExecutionException(th);
        }
    }
}
