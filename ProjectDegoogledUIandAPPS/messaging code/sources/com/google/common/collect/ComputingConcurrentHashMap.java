package com.google.common.collect;

import com.google.common.base.C1546u;
import com.google.common.base.C1547v;
import com.google.common.collect.MapMakerInternalMap;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentMap;

class ComputingConcurrentHashMap extends MapMakerInternalMap {
    private static final long serialVersionUID = 4;
    final C1547v computingFunction;

    final class ComputingSerializationProxy extends MapMakerInternalMap.AbstractSerializationProxy {
        private static final long serialVersionUID = 4;
        final C1547v computingFunction;

        ComputingSerializationProxy(MapMakerInternalMap.Strength strength, MapMakerInternalMap.Strength strength2, C1546u uVar, C1546u uVar2, long j, long j2, int i, int i2, C1670ka kaVar, ConcurrentMap concurrentMap, C1547v vVar) {
            super(strength, strength2, uVar, uVar2, j, j2, i, i2, kaVar, concurrentMap);
            this.computingFunction = vVar;
        }

        private void readObject(ObjectInputStream objectInputStream) {
            ConcurrentMap concurrentMap;
            objectInputStream.defaultReadObject();
            C1673la b = mo8871b(objectInputStream);
            C1547v vVar = this.computingFunction;
            if (b.f2539NN == null) {
                concurrentMap = new MapMaker$ComputingMapAdapter(b, vVar);
            } else {
                concurrentMap = new MapMaker$NullComputingConcurrentMap(b, vVar);
            }
            this.delegate = concurrentMap;
            mo8869a(objectInputStream);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.defaultWriteObject();
            mo8870a(objectOutputStream);
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.delegate;
        }
    }

    ComputingConcurrentHashMap(C1673la laVar, C1547v vVar) {
        super(laVar);
        if (vVar != null) {
            this.computingFunction = vVar;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: D */
    public Object mo8604D(Object obj) {
        if (obj != null) {
            int v = mo8867v(obj);
            return m4090gb(v).mo8608a(obj, v, this.computingFunction);
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: O */
    public MapMakerInternalMap.Segment mo8605O(int i, int i2) {
        return new ComputingSegment(this, i, i2);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new ComputingSerializationProxy(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this, this.computingFunction);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: gb */
    public ComputingSegment m4090gb(int i) {
        return (ComputingSegment) this.segments[this.f2456dQ & (i >>> this.f2457eQ)];
    }

    final class ComputingSegment extends MapMakerInternalMap.Segment {
        ComputingSegment(MapMakerInternalMap mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
            if (r0.mo8578j().mo8592W() == false) goto L_0x0021;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
            if (r7.mo8578j().mo8592W() == false) goto L_0x005e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x005c, code lost:
            r3 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x005e, code lost:
            r9 = r7.mo8578j().get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0066, code lost:
            if (r9 != null) goto L_0x006e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0068, code lost:
            mo8879a(r8, r13, r9, com.google.common.collect.MapMaker$RemovalCause.f2454lP);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0074, code lost:
            if (r11.map.mo8837Xl() == false) goto L_0x0090;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x007c, code lost:
            if (r11.map.mo8852g(r7) == false) goto L_0x0090;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x007e, code lost:
            mo8879a(r8, r13, r9, com.google.common.collect.MapMaker$RemovalCause.EXPIRED);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0083, code lost:
            r11.evictionQueue.remove(r7);
            r11.expirationQueue.remove(r7);
            r11.count = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0090, code lost:
            mo8910k(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            unlock();
            mo8918nm();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0099, code lost:
            mo8915mm();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x009c, code lost:
            return r9;
         */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00d4 A[Catch:{ all -> 0x00eb, all -> 0x00f3 }] */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x00c6 A[SYNTHETIC] */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object mo8608a(java.lang.Object r12, int r13, com.google.common.base.C1547v r14) {
            /*
                r11 = this;
            L_0x0000:
                com.google.common.collect.Aa r0 = r11.mo8896e((java.lang.Object) r12, (int) r13)     // Catch:{ all -> 0x00f3 }
                if (r0 == 0) goto L_0x0013
                java.lang.Object r1 = r11.mo8900f(r0)     // Catch:{ all -> 0x00f3 }
                if (r1 == 0) goto L_0x0013
                r11.mo8912l(r0)     // Catch:{ all -> 0x00f3 }
                r11.mo8915mm()
                return r1
            L_0x0013:
                r1 = 0
                r2 = 1
                if (r0 == 0) goto L_0x0021
                com.google.common.collect.Ia r3 = r0.mo8578j()     // Catch:{ all -> 0x00f3 }
                boolean r3 = r3.mo8592W()     // Catch:{ all -> 0x00f3 }
                if (r3 != 0) goto L_0x00ce
            L_0x0021:
                r0 = 0
                r11.lock()     // Catch:{ all -> 0x00f3 }
                r11.mo8919om()     // Catch:{ all -> 0x00eb }
                int r3 = r11.count     // Catch:{ all -> 0x00eb }
                int r3 = r3 - r2
                java.util.concurrent.atomic.AtomicReferenceArray r4 = r11.table     // Catch:{ all -> 0x00eb }
                int r5 = r4.length()     // Catch:{ all -> 0x00eb }
                int r5 = r5 - r2
                r5 = r5 & r13
                java.lang.Object r6 = r4.get(r5)     // Catch:{ all -> 0x00eb }
                com.google.common.collect.Aa r6 = (com.google.common.collect.C1553Aa) r6     // Catch:{ all -> 0x00eb }
                r7 = r6
            L_0x003a:
                if (r7 == 0) goto L_0x00a2
                java.lang.Object r8 = r7.getKey()     // Catch:{ all -> 0x00eb }
                int r9 = r7.getHash()     // Catch:{ all -> 0x00eb }
                if (r9 != r13) goto L_0x009d
                if (r8 == 0) goto L_0x009d
                com.google.common.collect.MapMakerInternalMap r9 = r11.map     // Catch:{ all -> 0x00eb }
                com.google.common.base.u r9 = r9.keyEquivalence     // Catch:{ all -> 0x00eb }
                boolean r9 = r9.mo8558d(r12, r8)     // Catch:{ all -> 0x00eb }
                if (r9 == 0) goto L_0x009d
                com.google.common.collect.Ia r9 = r7.mo8578j()     // Catch:{ all -> 0x00eb }
                boolean r9 = r9.mo8592W()     // Catch:{ all -> 0x00eb }
                if (r9 == 0) goto L_0x005e
                r3 = r1
                goto L_0x00a3
            L_0x005e:
                com.google.common.collect.Ia r9 = r7.mo8578j()     // Catch:{ all -> 0x00eb }
                java.lang.Object r9 = r9.get()     // Catch:{ all -> 0x00eb }
                if (r9 != 0) goto L_0x006e
                com.google.common.collect.MapMaker$RemovalCause r10 = com.google.common.collect.MapMaker$RemovalCause.COLLECTED     // Catch:{ all -> 0x00eb }
                r11.mo8879a((java.lang.Object) r8, (int) r13, (java.lang.Object) r9, (com.google.common.collect.MapMaker$RemovalCause) r10)     // Catch:{ all -> 0x00eb }
                goto L_0x0083
            L_0x006e:
                com.google.common.collect.MapMakerInternalMap r10 = r11.map     // Catch:{ all -> 0x00eb }
                boolean r10 = r10.mo8837Xl()     // Catch:{ all -> 0x00eb }
                if (r10 == 0) goto L_0x0090
                com.google.common.collect.MapMakerInternalMap r10 = r11.map     // Catch:{ all -> 0x00eb }
                boolean r10 = r10.mo8852g(r7)     // Catch:{ all -> 0x00eb }
                if (r10 == 0) goto L_0x0090
                com.google.common.collect.MapMaker$RemovalCause r10 = com.google.common.collect.MapMaker$RemovalCause.EXPIRED     // Catch:{ all -> 0x00eb }
                r11.mo8879a((java.lang.Object) r8, (int) r13, (java.lang.Object) r9, (com.google.common.collect.MapMaker$RemovalCause) r10)     // Catch:{ all -> 0x00eb }
            L_0x0083:
                java.util.Queue r8 = r11.evictionQueue     // Catch:{ all -> 0x00eb }
                r8.remove(r7)     // Catch:{ all -> 0x00eb }
                java.util.Queue r8 = r11.expirationQueue     // Catch:{ all -> 0x00eb }
                r8.remove(r7)     // Catch:{ all -> 0x00eb }
                r11.count = r3     // Catch:{ all -> 0x00eb }
                goto L_0x00a2
            L_0x0090:
                r11.mo8910k(r7)     // Catch:{ all -> 0x00eb }
                r11.unlock()     // Catch:{ all -> 0x00f3 }
                r11.mo8918nm()     // Catch:{ all -> 0x00f3 }
                r11.mo8915mm()
                return r9
            L_0x009d:
                com.google.common.collect.Aa r7 = r7.getNext()     // Catch:{ all -> 0x00eb }
                goto L_0x003a
            L_0x00a2:
                r3 = r2
            L_0x00a3:
                if (r3 == 0) goto L_0x00bc
                com.google.common.collect.G r0 = new com.google.common.collect.G     // Catch:{ all -> 0x00eb }
                r0.<init>(r14)     // Catch:{ all -> 0x00eb }
                if (r7 != 0) goto L_0x00b9
                com.google.common.collect.Aa r6 = r11.newEntry(r12, r13, r6)     // Catch:{ all -> 0x00eb }
                r6.mo8570a((com.google.common.collect.C1571Ia) r0)     // Catch:{ all -> 0x00eb }
                r4.set(r5, r6)     // Catch:{ all -> 0x00eb }
                r4 = r0
                r0 = r6
                goto L_0x00be
            L_0x00b9:
                r7.mo8570a((com.google.common.collect.C1571Ia) r0)     // Catch:{ all -> 0x00eb }
            L_0x00bc:
                r4 = r0
                r0 = r7
            L_0x00be:
                r11.unlock()     // Catch:{ all -> 0x00f3 }
                r11.mo8918nm()     // Catch:{ all -> 0x00f3 }
                if (r3 == 0) goto L_0x00ce
                java.lang.Object r12 = r11.mo8609a(r12, r13, r0, r4)     // Catch:{ all -> 0x00f3 }
                r11.mo8915mm()
                return r12
            L_0x00ce:
                boolean r3 = java.lang.Thread.holdsLock(r0)     // Catch:{ all -> 0x00f3 }
                if (r3 != 0) goto L_0x00d5
                r1 = r2
            L_0x00d5:
                java.lang.String r2 = "Recursive computation"
                com.google.common.base.C1508E.m3961a(r1, r2)     // Catch:{ all -> 0x00f3 }
                com.google.common.collect.Ia r1 = r0.mo8578j()     // Catch:{ all -> 0x00f3 }
                java.lang.Object r1 = r1.mo8596v()     // Catch:{ all -> 0x00f3 }
                if (r1 == 0) goto L_0x0000
                r11.mo8912l(r0)     // Catch:{ all -> 0x00f3 }
                r11.mo8915mm()
                return r1
            L_0x00eb:
                r12 = move-exception
                r11.unlock()     // Catch:{ all -> 0x00f3 }
                r11.mo8918nm()     // Catch:{ all -> 0x00f3 }
                throw r12     // Catch:{ all -> 0x00f3 }
            L_0x00f3:
                r12 = move-exception
                r11.mo8915mm()
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.mo8608a(java.lang.Object, int, com.google.common.base.v):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
            if (mo8876a(r7, r8, r2, true) == null) goto L_0x001e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
            mo8879a(r7, r8, r2, com.google.common.collect.MapMaker$RemovalCause.f2453kP);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
            if (r3 != 0) goto L_0x0025;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
            java.lang.System.nanoTime();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
            if (r2 != null) goto L_0x002a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
            mo8883a(r7, r8, (com.google.common.collect.C1571Ia) r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
            return r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x002f, code lost:
            r9 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
            if (r2 == null) goto L_0x001e;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object mo8609a(java.lang.Object r7, int r8, com.google.common.collect.C1553Aa r9, com.google.common.collect.C1566G r10) {
            /*
                r6 = this;
                java.lang.System.nanoTime()
                r0 = 0
                r2 = 0
                monitor-enter(r9)     // Catch:{ all -> 0x0033 }
                java.lang.Object r2 = r10.mo8672b(r7, r8)     // Catch:{ all -> 0x002b }
                long r3 = java.lang.System.nanoTime()     // Catch:{ all -> 0x002b }
                monitor-exit(r9)     // Catch:{ all -> 0x0031 }
                if (r2 == 0) goto L_0x001e
                r9 = 1
                java.lang.Object r9 = r6.mo8876a((java.lang.Object) r7, (int) r8, (java.lang.Object) r2, (boolean) r9)     // Catch:{ all -> 0x002f }
                if (r9 == 0) goto L_0x001e
                com.google.common.collect.MapMaker$RemovalCause r9 = com.google.common.collect.MapMaker$RemovalCause.REPLACED     // Catch:{ all -> 0x002f }
                r6.mo8879a((java.lang.Object) r7, (int) r8, (java.lang.Object) r2, (com.google.common.collect.MapMaker$RemovalCause) r9)     // Catch:{ all -> 0x002f }
            L_0x001e:
                int r9 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r9 != 0) goto L_0x0025
                java.lang.System.nanoTime()
            L_0x0025:
                if (r2 != 0) goto L_0x002a
                r6.mo8883a((java.lang.Object) r7, (int) r8, (com.google.common.collect.C1571Ia) r10)
            L_0x002a:
                return r2
            L_0x002b:
                r5 = move-exception
                r3 = r0
            L_0x002d:
                monitor-exit(r9)     // Catch:{ all -> 0x0031 }
                throw r5     // Catch:{ all -> 0x002f }
            L_0x002f:
                r9 = move-exception
                goto L_0x0035
            L_0x0031:
                r5 = move-exception
                goto L_0x002d
            L_0x0033:
                r9 = move-exception
                r3 = r0
            L_0x0035:
                int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x003c
                java.lang.System.nanoTime()
            L_0x003c:
                if (r2 != 0) goto L_0x0041
                r6.mo8883a((java.lang.Object) r7, (int) r8, (com.google.common.collect.C1571Ia) r10)
            L_0x0041:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.mo8609a(java.lang.Object, int, com.google.common.collect.Aa, com.google.common.collect.G):java.lang.Object");
        }
    }
}
