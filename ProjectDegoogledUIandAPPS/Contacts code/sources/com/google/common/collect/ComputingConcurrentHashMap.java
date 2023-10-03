package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

class ComputingConcurrentHashMap<K, V> extends MapMakerInternalMap<K, V> {
    private static final long serialVersionUID = 4;
    final Function<? super K, ? extends V> computingFunction;

    ComputingConcurrentHashMap(MapMaker mapMaker, Function<? super K, ? extends V> function) {
        super(mapMaker);
        Preconditions.checkNotNull(function);
        this.computingFunction = function;
    }

    /* access modifiers changed from: package-private */
    public MapMakerInternalMap.Segment<K, V> createSegment(int i, int i2) {
        return new ComputingSegment(this, i, i2);
    }

    /* access modifiers changed from: package-private */
    public ComputingSegment<K, V> segmentFor(int i) {
        return (ComputingSegment) super.segmentFor(i);
    }

    /* access modifiers changed from: package-private */
    public V getOrCompute(K k) throws ExecutionException {
        Preconditions.checkNotNull(k);
        int hash = hash(k);
        return segmentFor(hash).getOrCompute(k, hash, this.computingFunction);
    }

    static final class ComputingSegment<K, V> extends MapMakerInternalMap.Segment<K, V> {
        ComputingSegment(MapMakerInternalMap<K, V> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
            if (r0.getValueReference().isComputingReference() == false) goto L_0x0021;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
            if (r7.getValueReference().isComputingReference() == false) goto L_0x005e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x005c, code lost:
            r3 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x005e, code lost:
            r9 = r7.getValueReference().get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0066, code lost:
            if (r9 != null) goto L_0x006e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0068, code lost:
            enqueueNotification(r8, r13, r9, com.google.common.collect.MapMaker.RemovalCause.COLLECTED);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0074, code lost:
            if (r11.map.expires() == false) goto L_0x0090;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x007c, code lost:
            if (r11.map.isExpired(r7) == false) goto L_0x0090;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x007e, code lost:
            enqueueNotification(r8, r13, r9, com.google.common.collect.MapMaker.RemovalCause.EXPIRED);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0083, code lost:
            r11.evictionQueue.remove(r7);
            r11.expirationQueue.remove(r7);
            r11.count = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0090, code lost:
            recordLockedRead(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            unlock();
            postWriteCleanup();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0099, code lost:
            postReadCleanup();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x009c, code lost:
            return r9;
         */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00d4 A[Catch:{ all -> 0x00eb, all -> 0x00f3 }] */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x00c6 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V getOrCompute(K r12, int r13, com.google.common.base.Function<? super K, ? extends V> r14) throws java.util.concurrent.ExecutionException {
            /*
                r11 = this;
            L_0x0000:
                com.google.common.collect.MapMakerInternalMap$ReferenceEntry r0 = r11.getEntry(r12, r13)     // Catch:{ all -> 0x00f3 }
                if (r0 == 0) goto L_0x0013
                java.lang.Object r1 = r11.getLiveValue(r0)     // Catch:{ all -> 0x00f3 }
                if (r1 == 0) goto L_0x0013
                r11.recordRead(r0)     // Catch:{ all -> 0x00f3 }
                r11.postReadCleanup()
                return r1
            L_0x0013:
                r1 = 0
                r2 = 1
                if (r0 == 0) goto L_0x0021
                com.google.common.collect.MapMakerInternalMap$ValueReference r3 = r0.getValueReference()     // Catch:{ all -> 0x00f3 }
                boolean r3 = r3.isComputingReference()     // Catch:{ all -> 0x00f3 }
                if (r3 != 0) goto L_0x00ce
            L_0x0021:
                r0 = 0
                r11.lock()     // Catch:{ all -> 0x00f3 }
                r11.preWriteCleanup()     // Catch:{ all -> 0x00eb }
                int r3 = r11.count     // Catch:{ all -> 0x00eb }
                int r3 = r3 - r2
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.collect.MapMakerInternalMap$ReferenceEntry<K, V>> r4 = r11.table     // Catch:{ all -> 0x00eb }
                int r5 = r4.length()     // Catch:{ all -> 0x00eb }
                int r5 = r5 - r2
                r5 = r5 & r13
                java.lang.Object r6 = r4.get(r5)     // Catch:{ all -> 0x00eb }
                com.google.common.collect.MapMakerInternalMap$ReferenceEntry r6 = (com.google.common.collect.MapMakerInternalMap.ReferenceEntry) r6     // Catch:{ all -> 0x00eb }
                r7 = r6
            L_0x003a:
                if (r7 == 0) goto L_0x00a2
                java.lang.Object r8 = r7.getKey()     // Catch:{ all -> 0x00eb }
                int r9 = r7.getHash()     // Catch:{ all -> 0x00eb }
                if (r9 != r13) goto L_0x009d
                if (r8 == 0) goto L_0x009d
                com.google.common.collect.MapMakerInternalMap<K, V> r9 = r11.map     // Catch:{ all -> 0x00eb }
                com.google.common.base.Equivalence<java.lang.Object> r9 = r9.keyEquivalence     // Catch:{ all -> 0x00eb }
                boolean r9 = r9.equivalent(r12, r8)     // Catch:{ all -> 0x00eb }
                if (r9 == 0) goto L_0x009d
                com.google.common.collect.MapMakerInternalMap$ValueReference r9 = r7.getValueReference()     // Catch:{ all -> 0x00eb }
                boolean r9 = r9.isComputingReference()     // Catch:{ all -> 0x00eb }
                if (r9 == 0) goto L_0x005e
                r3 = 0
                goto L_0x00a3
            L_0x005e:
                com.google.common.collect.MapMakerInternalMap$ValueReference r9 = r7.getValueReference()     // Catch:{ all -> 0x00eb }
                java.lang.Object r9 = r9.get()     // Catch:{ all -> 0x00eb }
                if (r9 != 0) goto L_0x006e
                com.google.common.collect.MapMaker$RemovalCause r10 = com.google.common.collect.MapMaker.RemovalCause.COLLECTED     // Catch:{ all -> 0x00eb }
                r11.enqueueNotification(r8, r13, r9, r10)     // Catch:{ all -> 0x00eb }
                goto L_0x0083
            L_0x006e:
                com.google.common.collect.MapMakerInternalMap<K, V> r10 = r11.map     // Catch:{ all -> 0x00eb }
                boolean r10 = r10.expires()     // Catch:{ all -> 0x00eb }
                if (r10 == 0) goto L_0x0090
                com.google.common.collect.MapMakerInternalMap<K, V> r10 = r11.map     // Catch:{ all -> 0x00eb }
                boolean r10 = r10.isExpired(r7)     // Catch:{ all -> 0x00eb }
                if (r10 == 0) goto L_0x0090
                com.google.common.collect.MapMaker$RemovalCause r10 = com.google.common.collect.MapMaker.RemovalCause.EXPIRED     // Catch:{ all -> 0x00eb }
                r11.enqueueNotification(r8, r13, r9, r10)     // Catch:{ all -> 0x00eb }
            L_0x0083:
                java.util.Queue<com.google.common.collect.MapMakerInternalMap$ReferenceEntry<K, V>> r8 = r11.evictionQueue     // Catch:{ all -> 0x00eb }
                r8.remove(r7)     // Catch:{ all -> 0x00eb }
                java.util.Queue<com.google.common.collect.MapMakerInternalMap$ReferenceEntry<K, V>> r8 = r11.expirationQueue     // Catch:{ all -> 0x00eb }
                r8.remove(r7)     // Catch:{ all -> 0x00eb }
                r11.count = r3     // Catch:{ all -> 0x00eb }
                goto L_0x00a2
            L_0x0090:
                r11.recordLockedRead(r7)     // Catch:{ all -> 0x00eb }
                r11.unlock()     // Catch:{ all -> 0x00f3 }
                r11.postWriteCleanup()     // Catch:{ all -> 0x00f3 }
                r11.postReadCleanup()
                return r9
            L_0x009d:
                com.google.common.collect.MapMakerInternalMap$ReferenceEntry r7 = r7.getNext()     // Catch:{ all -> 0x00eb }
                goto L_0x003a
            L_0x00a2:
                r3 = 1
            L_0x00a3:
                if (r3 == 0) goto L_0x00bc
                com.google.common.collect.ComputingConcurrentHashMap$ComputingValueReference r0 = new com.google.common.collect.ComputingConcurrentHashMap$ComputingValueReference     // Catch:{ all -> 0x00eb }
                r0.<init>(r14)     // Catch:{ all -> 0x00eb }
                if (r7 != 0) goto L_0x00b9
                com.google.common.collect.MapMakerInternalMap$ReferenceEntry r6 = r11.newEntry(r12, r13, r6)     // Catch:{ all -> 0x00eb }
                r6.setValueReference(r0)     // Catch:{ all -> 0x00eb }
                r4.set(r5, r6)     // Catch:{ all -> 0x00eb }
                r4 = r0
                r0 = r6
                goto L_0x00be
            L_0x00b9:
                r7.setValueReference(r0)     // Catch:{ all -> 0x00eb }
            L_0x00bc:
                r4 = r0
                r0 = r7
            L_0x00be:
                r11.unlock()     // Catch:{ all -> 0x00f3 }
                r11.postWriteCleanup()     // Catch:{ all -> 0x00f3 }
                if (r3 == 0) goto L_0x00ce
                java.lang.Object r12 = r11.compute(r12, r13, r0, r4)     // Catch:{ all -> 0x00f3 }
                r11.postReadCleanup()
                return r12
            L_0x00ce:
                boolean r3 = java.lang.Thread.holdsLock(r0)     // Catch:{ all -> 0x00f3 }
                if (r3 != 0) goto L_0x00d5
                r1 = 1
            L_0x00d5:
                java.lang.String r2 = "Recursive computation"
                com.google.common.base.Preconditions.checkState(r1, r2)     // Catch:{ all -> 0x00f3 }
                com.google.common.collect.MapMakerInternalMap$ValueReference r1 = r0.getValueReference()     // Catch:{ all -> 0x00f3 }
                java.lang.Object r1 = r1.waitForValue()     // Catch:{ all -> 0x00f3 }
                if (r1 == 0) goto L_0x0000
                r11.recordRead(r0)     // Catch:{ all -> 0x00f3 }
                r11.postReadCleanup()
                return r1
            L_0x00eb:
                r12 = move-exception
                r11.unlock()     // Catch:{ all -> 0x00f3 }
                r11.postWriteCleanup()     // Catch:{ all -> 0x00f3 }
                throw r12     // Catch:{ all -> 0x00f3 }
            L_0x00f3:
                r12 = move-exception
                r11.postReadCleanup()
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.getOrCompute(java.lang.Object, int, com.google.common.base.Function):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
            if (put(r7, r8, r2, true) == null) goto L_0x001e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
            enqueueNotification(r7, r8, r2, com.google.common.collect.MapMaker.RemovalCause.REPLACED);
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
            clearValue(r7, r8, r10);
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
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V compute(K r7, int r8, com.google.common.collect.MapMakerInternalMap.ReferenceEntry<K, V> r9, com.google.common.collect.ComputingConcurrentHashMap.ComputingValueReference<K, V> r10) throws java.util.concurrent.ExecutionException {
            /*
                r6 = this;
                java.lang.System.nanoTime()
                r0 = 0
                r2 = 0
                monitor-enter(r9)     // Catch:{ all -> 0x0033 }
                java.lang.Object r2 = r10.compute(r7, r8)     // Catch:{ all -> 0x002b }
                long r3 = java.lang.System.nanoTime()     // Catch:{ all -> 0x002b }
                monitor-exit(r9)     // Catch:{ all -> 0x0031 }
                if (r2 == 0) goto L_0x001e
                r9 = 1
                java.lang.Object r9 = r6.put(r7, r8, r2, r9)     // Catch:{ all -> 0x002f }
                if (r9 == 0) goto L_0x001e
                com.google.common.collect.MapMaker$RemovalCause r9 = com.google.common.collect.MapMaker.RemovalCause.REPLACED     // Catch:{ all -> 0x002f }
                r6.enqueueNotification(r7, r8, r2, r9)     // Catch:{ all -> 0x002f }
            L_0x001e:
                int r9 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r9 != 0) goto L_0x0025
                java.lang.System.nanoTime()
            L_0x0025:
                if (r2 != 0) goto L_0x002a
                r6.clearValue(r7, r8, r10)
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
                int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r5 != 0) goto L_0x003c
                java.lang.System.nanoTime()
            L_0x003c:
                if (r2 != 0) goto L_0x0041
                r6.clearValue(r7, r8, r10)
            L_0x0041:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.compute(java.lang.Object, int, com.google.common.collect.MapMakerInternalMap$ReferenceEntry, com.google.common.collect.ComputingConcurrentHashMap$ComputingValueReference):java.lang.Object");
        }
    }

    private static final class ComputationExceptionReference<K, V> implements MapMakerInternalMap.ValueReference<K, V> {

        /* renamed from: t */
        final Throwable f25t;

        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
        }

        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public V get() {
            return null;
        }

        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public boolean isComputingReference() {
            return false;
        }

        ComputationExceptionReference(Throwable th) {
            this.f25t = th;
        }

        public V waitForValue() throws ExecutionException {
            throw new ExecutionException(this.f25t);
        }
    }

    private static final class ComputedReference<K, V> implements MapMakerInternalMap.ValueReference<K, V> {
        final V value;

        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
        }

        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public boolean isComputingReference() {
            return false;
        }

        ComputedReference(V v) {
            this.value = v;
        }

        public V get() {
            return this.value;
        }

        public V waitForValue() {
            return get();
        }
    }

    private static final class ComputingValueReference<K, V> implements MapMakerInternalMap.ValueReference<K, V> {
        volatile MapMakerInternalMap.ValueReference<K, V> computedReference = MapMakerInternalMap.unset();
        final Function<? super K, ? extends V> computingFunction;

        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public V get() {
            return null;
        }

        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public boolean isComputingReference() {
            return true;
        }

        public ComputingValueReference(Function<? super K, ? extends V> function) {
            this.computingFunction = function;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0023, code lost:
            r0 = th;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V waitForValue() throws java.util.concurrent.ExecutionException {
            /*
                r3 = this;
                com.google.common.collect.MapMakerInternalMap$ValueReference<K, V> r0 = r3.computedReference
                com.google.common.collect.MapMakerInternalMap$ValueReference<java.lang.Object, java.lang.Object> r1 = com.google.common.collect.MapMakerInternalMap.UNSET
                if (r0 != r1) goto L_0x0032
                r0 = 0
                monitor-enter(r3)     // Catch:{ all -> 0x0025 }
                r1 = 0
            L_0x0009:
                com.google.common.collect.MapMakerInternalMap$ValueReference<K, V> r0 = r3.computedReference     // Catch:{ all -> 0x0020 }
                com.google.common.collect.MapMakerInternalMap$ValueReference<java.lang.Object, java.lang.Object> r2 = com.google.common.collect.MapMakerInternalMap.UNSET     // Catch:{ all -> 0x0020 }
                if (r0 != r2) goto L_0x0015
                r3.wait()     // Catch:{ InterruptedException -> 0x0013 }
                goto L_0x0009
            L_0x0013:
                r1 = 1
                goto L_0x0009
            L_0x0015:
                monitor-exit(r3)     // Catch:{ all -> 0x0020 }
                if (r1 == 0) goto L_0x0032
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
                goto L_0x0032
            L_0x0020:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0020 }
                throw r0     // Catch:{ all -> 0x0023 }
            L_0x0023:
                r0 = move-exception
                goto L_0x0028
            L_0x0025:
                r1 = move-exception
                r0 = r1
                r1 = 0
            L_0x0028:
                if (r1 == 0) goto L_0x0031
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L_0x0031:
                throw r0
            L_0x0032:
                com.google.common.collect.MapMakerInternalMap$ValueReference<K, V> r0 = r3.computedReference
                java.lang.Object r0 = r0.waitForValue()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingValueReference.waitForValue():java.lang.Object");
        }

        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
            setValueReference(valueReference);
        }

        /* access modifiers changed from: package-private */
        public V compute(K k, int i) throws ExecutionException {
            try {
                V apply = this.computingFunction.apply(k);
                setValueReference(new ComputedReference(apply));
                return apply;
            } catch (Throwable th) {
                setValueReference(new ComputationExceptionReference(th));
                throw new ExecutionException(th);
            }
        }

        /* access modifiers changed from: package-private */
        public void setValueReference(MapMakerInternalMap.ValueReference<K, V> valueReference) {
            synchronized (this) {
                if (this.computedReference == MapMakerInternalMap.UNSET) {
                    this.computedReference = valueReference;
                    notifyAll();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new ComputingSerializationProxy(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this, this.computingFunction);
    }

    static final class ComputingSerializationProxy<K, V> extends MapMakerInternalMap.AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 4;
        final Function<? super K, ? extends V> computingFunction;

        ComputingSerializationProxy(MapMakerInternalMap.Strength strength, MapMakerInternalMap.Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j, long j2, int i, int i2, MapMaker.RemovalListener<? super K, ? super V> removalListener, ConcurrentMap<K, V> concurrentMap, Function<? super K, ? extends V> function) {
            super(strength, strength2, equivalence, equivalence2, j, j2, i, i2, removalListener, concurrentMap);
            this.computingFunction = function;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            writeMapTo(objectOutputStream);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = readMapMaker(objectInputStream).makeComputingMap(this.computingFunction);
            readEntries(objectInputStream);
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.delegate;
        }
    }
}
