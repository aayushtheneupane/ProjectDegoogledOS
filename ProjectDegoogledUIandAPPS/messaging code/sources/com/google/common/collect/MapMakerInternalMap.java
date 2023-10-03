package com.google.common.collect;

import com.google.common.base.C1508E;
import com.google.common.base.C1525S;
import com.google.common.base.C1546u;
import com.google.common.primitives.C1722a;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

class MapMakerInternalMap extends AbstractMap implements ConcurrentMap, Serializable {
    static final C1571Ia UNSET = new C1676ma();

    /* renamed from: gQ */
    static final Queue f2455gQ = new C1679na();
    private static final Logger logger = Logger.getLogger(MapMakerInternalMap.class.getName());
    private static final long serialVersionUID = 5;
    final int concurrencyLevel;

    /* renamed from: dQ */
    final transient int f2456dQ;

    /* renamed from: eQ */
    final transient int f2457eQ;
    final long expireAfterAccessNanos;
    final long expireAfterWriteNanos;

    /* renamed from: fQ */
    final transient EntryFactory f2458fQ;
    final C1546u keyEquivalence;
    transient Set keySet;
    final Strength keyStrength;
    final int maximumSize;

    /* renamed from: nN */
    transient Set f2459nN;
    final C1670ka removalListener;
    final Queue removalNotificationQueue;
    final transient Segment[] segments;
    final C1525S ticker;
    final C1546u valueEquivalence;
    final Strength valueStrength;
    transient Collection values;

    enum EntryFactory {
        STRONG {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8873a(Segment segment, Object obj, int i, C1553Aa aa) {
                return new C1559Ca(obj, i, aa);
            }
        },
        STRONG_EXPIRABLE {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8873a(Segment segment, Object obj, int i, C1553Aa aa) {
                return new C1563Ea(obj, i, aa);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8872a(Segment segment, C1553Aa aa, C1553Aa aa2) {
                C1553Aa a = mo8873a(segment, aa.getKey(), aa.getHash(), aa2);
                mo8875b(aa, a);
                return a;
            }
        },
        STRONG_EVICTABLE {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8873a(Segment segment, Object obj, int i, C1553Aa aa) {
                return new C1561Da(obj, i, aa);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8872a(Segment segment, C1553Aa aa, C1553Aa aa2) {
                C1553Aa a = mo8873a(segment, aa.getKey(), aa.getHash(), aa2);
                mo8874a(aa, a);
                return a;
            }
        },
        STRONG_EXPIRABLE_EVICTABLE {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8873a(Segment segment, Object obj, int i, C1553Aa aa) {
                return new C1565Fa(obj, i, aa);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8872a(Segment segment, C1553Aa aa, C1553Aa aa2) {
                C1553Aa a = mo8873a(segment, aa.getKey(), aa.getHash(), aa2);
                mo8875b(aa, a);
                mo8874a(aa, a);
                return a;
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8873a(Segment segment, Object obj, int i, C1553Aa aa) {
                return new C1579Ka(segment.keyReferenceQueue, obj, i, aa);
            }
        },
        WEAK_EXPIRABLE {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8873a(Segment segment, Object obj, int i, C1553Aa aa) {
                return new C1583Ma(segment.keyReferenceQueue, obj, i, aa);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8872a(Segment segment, C1553Aa aa, C1553Aa aa2) {
                C1553Aa a = mo8873a(segment, aa.getKey(), aa.getHash(), aa2);
                mo8875b(aa, a);
                return a;
            }
        },
        WEAK_EVICTABLE {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8873a(Segment segment, Object obj, int i, C1553Aa aa) {
                return new C1581La(segment.keyReferenceQueue, obj, i, aa);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8872a(Segment segment, C1553Aa aa, C1553Aa aa2) {
                C1553Aa a = mo8873a(segment, aa.getKey(), aa.getHash(), aa2);
                mo8874a(aa, a);
                return a;
            }
        },
        WEAK_EXPIRABLE_EVICTABLE {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8873a(Segment segment, Object obj, int i, C1553Aa aa) {
                return new C1603Na(segment.keyReferenceQueue, obj, i, aa);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1553Aa mo8872a(Segment segment, C1553Aa aa, C1553Aa aa2) {
                C1553Aa a = mo8873a(segment, aa.getKey(), aa.getHash(), aa2);
                mo8875b(aa, a);
                mo8874a(aa, a);
                return a;
            }
        };
        

        /* renamed from: uP */
        static final EntryFactory[][] f2467uP = null;

        static {
            EntryFactory entryFactory;
            EntryFactory entryFactory2;
            EntryFactory entryFactory3;
            EntryFactory entryFactory4;
            EntryFactory entryFactory5;
            EntryFactory entryFactory6;
            EntryFactory entryFactory7;
            EntryFactory entryFactory8;
            f2467uP = new EntryFactory[][]{new EntryFactory[]{entryFactory, entryFactory2, entryFactory3, entryFactory4}, new EntryFactory[0], new EntryFactory[]{entryFactory5, entryFactory6, entryFactory7, entryFactory8}};
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1553Aa mo8872a(Segment segment, C1553Aa aa, C1553Aa aa2) {
            return mo8873a(segment, aa.getKey(), aa.getHash(), aa2);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public abstract C1553Aa mo8873a(Segment segment, Object obj, int i, C1553Aa aa);

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo8875b(C1553Aa aa, C1553Aa aa2) {
            aa2.mo8568a(aa.getExpirationTime());
            MapMakerInternalMap.m4305d(aa.mo8567P(), aa2);
            C1553Aa t = aa.mo8580t();
            aa2.mo8572c(t);
            t.mo8573d(aa2);
            MapMakerInternalMap.m4307i(aa);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8874a(C1553Aa aa, C1553Aa aa2) {
            MapMakerInternalMap.m4304c(aa.mo8566F(), aa2);
            C1553Aa s = aa.mo8579s();
            aa2.mo8571b(s);
            s.mo8569a(aa2);
            MapMakerInternalMap.m4306h(aa);
        }
    }

    enum NullEntry implements C1553Aa {
        INSTANCE;

        /* renamed from: F */
        public C1553Aa mo8566F() {
            return this;
        }

        /* renamed from: P */
        public C1553Aa mo8567P() {
            return this;
        }

        /* renamed from: a */
        public void mo8568a(long j) {
        }

        /* renamed from: a */
        public void mo8569a(C1553Aa aa) {
        }

        /* renamed from: a */
        public void mo8570a(C1571Ia ia) {
        }

        /* renamed from: b */
        public void mo8571b(C1553Aa aa) {
        }

        /* renamed from: c */
        public void mo8572c(C1553Aa aa) {
        }

        /* renamed from: d */
        public void mo8573d(C1553Aa aa) {
        }

        public long getExpirationTime() {
            return 0;
        }

        public int getHash() {
            return 0;
        }

        public Object getKey() {
            return null;
        }

        public C1553Aa getNext() {
            return null;
        }

        /* renamed from: j */
        public C1571Ia mo8578j() {
            return null;
        }

        /* renamed from: s */
        public C1553Aa mo8579s() {
            return this;
        }

        /* renamed from: t */
        public C1553Aa mo8580t() {
            return this;
        }
    }

    class Segment extends ReentrantLock {
        volatile int count;
        final Queue evictionQueue;
        final Queue expirationQueue;
        final ReferenceQueue keyReferenceQueue;
        final MapMakerInternalMap map;
        final int maxSegmentSize;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        final Queue recencyQueue;
        volatile AtomicReferenceArray table;
        int threshold;
        final ReferenceQueue valueReferenceQueue;

        Segment(MapMakerInternalMap mapMakerInternalMap, int i, int i2) {
            Queue queue;
            Queue queue2;
            Queue queue3;
            this.map = mapMakerInternalMap;
            this.maxSegmentSize = i2;
            mo8880a(mo8907ib(i));
            ReferenceQueue referenceQueue = null;
            this.keyReferenceQueue = mapMakerInternalMap.mo8842bm() ? new ReferenceQueue() : null;
            this.valueReferenceQueue = mapMakerInternalMap.mo8845cm() ? new ReferenceQueue() : referenceQueue;
            if (mapMakerInternalMap.mo8836Wl() || mapMakerInternalMap.mo8838Yl()) {
                queue = new ConcurrentLinkedQueue();
            } else {
                queue = MapMakerInternalMap.f2455gQ;
            }
            this.recencyQueue = queue;
            if (mapMakerInternalMap.mo8836Wl()) {
                queue2 = new C1697ta();
            } else {
                queue2 = MapMakerInternalMap.f2455gQ;
            }
            this.evictionQueue = queue2;
            if (mapMakerInternalMap.mo8837Xl()) {
                queue3 = new C1703wa();
            } else {
                queue3 = MapMakerInternalMap.f2455gQ;
            }
            this.expirationQueue = queue3;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8880a(AtomicReferenceArray atomicReferenceArray) {
            this.threshold = (atomicReferenceArray.length() * 3) / 4;
            int i = this.threshold;
            if (i == this.maxSegmentSize) {
                this.threshold = i + 1;
            }
            this.table = atomicReferenceArray;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo8887b(C1553Aa aa, long j) {
            aa.mo8568a(this.map.ticker.read() + j);
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            if (this.count != 0) {
                lock();
                try {
                    AtomicReferenceArray atomicReferenceArray = this.table;
                    if (this.map.removalNotificationQueue != MapMakerInternalMap.f2455gQ) {
                        for (int i = 0; i < atomicReferenceArray.length(); i++) {
                            for (C1553Aa aa = (C1553Aa) atomicReferenceArray.get(i); aa != null; aa = aa.getNext()) {
                                if (!aa.mo8578j().mo8592W()) {
                                    mo8877a(aa, MapMaker$RemovalCause.EXPLICIT);
                                }
                            }
                        }
                    }
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        atomicReferenceArray.set(i2, (Object) null);
                    }
                    mo8897em();
                    this.evictionQueue.clear();
                    this.expirationQueue.clear();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                    mo8918nm();
                }
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public boolean containsValue(Object obj) {
            try {
                if (this.count != 0) {
                    AtomicReferenceArray atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i = 0; i < length; i++) {
                        for (C1553Aa aa = (C1553Aa) atomicReferenceArray.get(i); aa != null; aa = aa.getNext()) {
                            Object f = mo8900f(aa);
                            if (f != null) {
                                if (this.map.valueEquivalence.mo8558d(obj, f)) {
                                    mo8915mm();
                                    return true;
                                }
                            }
                        }
                    }
                }
                mo8915mm();
                return false;
            } catch (Throwable th) {
                mo8915mm();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        public C1553Aa copyEntry(C1553Aa aa, C1553Aa aa2) {
            if (aa.getKey() == null) {
                return null;
            }
            C1571Ia j = aa.mo8578j();
            Object obj = j.get();
            if (obj == null && !j.mo8592W()) {
                return null;
            }
            C1553Aa a = this.map.f2458fQ.mo8872a(this, aa, aa2);
            a.mo8570a(j.mo8593a(this.valueReferenceQueue, obj, a));
            return a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public boolean mo8893d(Object obj, int i) {
            try {
                boolean z = false;
                if (this.count != 0) {
                    C1553Aa f = mo8899f(obj, i);
                    if (f == null) {
                        return false;
                    }
                    if (f.mo8578j().get() != null) {
                        z = true;
                    }
                    mo8915mm();
                    return z;
                }
                mo8915mm();
                return false;
            } finally {
                mo8915mm();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: dm */
        public void mo8894dm() {
            do {
            } while (this.keyReferenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: e */
        public C1553Aa mo8896e(Object obj, int i) {
            if (this.count == 0) {
                return null;
            }
            for (C1553Aa hb = mo8905hb(i); hb != null; hb = hb.getNext()) {
                if (hb.getHash() == i) {
                    Object key = hb.getKey();
                    if (key == null) {
                        mo8923sm();
                    } else if (this.map.keyEquivalence.mo8558d(obj, key)) {
                        return hb;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: em */
        public void mo8897em() {
            if (this.map.mo8842bm()) {
                mo8894dm();
            }
            if (this.map.mo8845cm()) {
                mo8901fm();
            }
        }

        /* access modifiers changed from: package-private */
        public void expand() {
            AtomicReferenceArray atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length < 1073741824) {
                int i = this.count;
                AtomicReferenceArray ib = mo8907ib(length << 1);
                this.threshold = (ib.length() * 3) / 4;
                int length2 = ib.length() - 1;
                for (int i2 = 0; i2 < length; i2++) {
                    C1553Aa aa = (C1553Aa) atomicReferenceArray.get(i2);
                    if (aa != null) {
                        C1553Aa next = aa.getNext();
                        int hash = aa.getHash() & length2;
                        if (next == null) {
                            ib.set(hash, aa);
                        } else {
                            C1553Aa aa2 = aa;
                            while (next != null) {
                                int hash2 = next.getHash() & length2;
                                if (hash2 != hash) {
                                    aa2 = next;
                                    hash = hash2;
                                }
                                next = next.getNext();
                            }
                            ib.set(hash, aa2);
                            while (aa != aa2) {
                                int hash3 = aa.getHash() & length2;
                                C1553Aa copyEntry = copyEntry(aa, (C1553Aa) ib.get(hash3));
                                if (copyEntry != null) {
                                    ib.set(hash3, copyEntry);
                                } else {
                                    mo8916n(aa);
                                    i--;
                                }
                                aa = aa.getNext();
                            }
                        }
                    }
                }
                this.table = ib;
                this.count = i;
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: f */
        public C1553Aa mo8899f(Object obj, int i) {
            C1553Aa e = mo8896e(obj, i);
            if (e == null) {
                return null;
            }
            if (!this.map.mo8837Xl() || !this.map.mo8852g(e)) {
                return e;
            }
            mo8924tm();
            return null;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: fm */
        public void mo8901fm() {
            do {
            } while (this.valueReferenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: g */
        public Object mo8902g(Object obj, int i) {
            MapMaker$RemovalCause mapMaker$RemovalCause;
            lock();
            try {
                mo8919om();
                AtomicReferenceArray atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                C1553Aa aa = (C1553Aa) atomicReferenceArray.get(length);
                C1553Aa aa2 = aa;
                while (true) {
                    if (aa2 == null) {
                        break;
                    }
                    Object key = aa2.getKey();
                    if (aa2.getHash() != i || key == null || !this.map.keyEquivalence.mo8558d(obj, key)) {
                        aa2 = aa2.getNext();
                    } else {
                        C1571Ia j = aa2.mo8578j();
                        Object obj2 = j.get();
                        if (obj2 != null) {
                            mapMaker$RemovalCause = MapMaker$RemovalCause.EXPLICIT;
                        } else if (mo8892d(j)) {
                            mapMaker$RemovalCause = MapMaker$RemovalCause.COLLECTED;
                        }
                        this.modCount++;
                        mo8879a(key, i, obj2, mapMaker$RemovalCause);
                        atomicReferenceArray.set(length, mo8895e(aa, aa2));
                        this.count--;
                        return obj2;
                    }
                }
                unlock();
                mo8918nm();
                return null;
            } finally {
                unlock();
                mo8918nm();
            }
        }

        /* access modifiers changed from: package-private */
        public Object get(Object obj, int i) {
            try {
                C1553Aa f = mo8899f(obj, i);
                if (f == null) {
                    return null;
                }
                Object obj2 = f.mo8578j().get();
                if (obj2 != null) {
                    mo8912l(f);
                } else {
                    mo8923sm();
                }
                mo8915mm();
                return obj2;
            } finally {
                mo8915mm();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: gm */
        public void mo8904gm() {
            int i = 0;
            do {
                Reference poll = this.keyReferenceQueue.poll();
                if (poll != null) {
                    this.map.mo8855j((C1553Aa) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: hb */
        public C1553Aa mo8905hb(int i) {
            AtomicReferenceArray atomicReferenceArray = this.table;
            return (C1553Aa) atomicReferenceArray.get(i & (atomicReferenceArray.length() - 1));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: hm */
        public void mo8906hm() {
            while (true) {
                C1553Aa aa = (C1553Aa) this.recencyQueue.poll();
                if (aa != null) {
                    if (this.evictionQueue.contains(aa)) {
                        this.evictionQueue.add(aa);
                    }
                    if (this.map.mo8838Yl() && this.expirationQueue.contains(aa)) {
                        this.expirationQueue.add(aa);
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: ib */
        public AtomicReferenceArray mo8907ib(int i) {
            return new AtomicReferenceArray(i);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: im */
        public void mo8908im() {
            if (this.map.mo8842bm()) {
                mo8904gm();
            }
            if (this.map.mo8845cm()) {
                mo8909jm();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: jm */
        public void mo8909jm() {
            int i = 0;
            do {
                Reference poll = this.valueReferenceQueue.poll();
                if (poll != null) {
                    this.map.mo8843c((C1571Ia) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: k */
        public void mo8910k(C1553Aa aa) {
            this.evictionQueue.add(aa);
            if (this.map.mo8838Yl()) {
                mo8887b(aa, this.map.expireAfterAccessNanos);
                this.expirationQueue.add(aa);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: km */
        public boolean mo8911km() {
            if (!this.map.mo8836Wl() || this.count < this.maxSegmentSize) {
                return false;
            }
            mo8906hm();
            C1553Aa aa = (C1553Aa) this.evictionQueue.remove();
            if (mo8882a(aa, aa.getHash(), MapMaker$RemovalCause.SIZE)) {
                return true;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: l */
        public void mo8912l(C1553Aa aa) {
            if (this.map.mo8838Yl()) {
                mo8887b(aa, this.map.expireAfterAccessNanos);
            }
            this.recencyQueue.add(aa);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lm */
        public void mo8913lm() {
            C1553Aa aa;
            mo8906hm();
            if (!this.expirationQueue.isEmpty()) {
                long read = this.map.ticker.read();
                do {
                    aa = (C1553Aa) this.expirationQueue.peek();
                    if (aa == null || !this.map.mo8840a(aa, read)) {
                        return;
                    }
                } while (mo8882a(aa, aa.getHash(), MapMaker$RemovalCause.EXPIRED));
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: m */
        public void mo8914m(C1553Aa aa) {
            long j;
            mo8906hm();
            this.evictionQueue.add(aa);
            if (this.map.mo8837Xl()) {
                if (this.map.mo8838Yl()) {
                    j = this.map.expireAfterAccessNanos;
                } else {
                    j = this.map.expireAfterWriteNanos;
                }
                mo8887b(aa, j);
                this.expirationQueue.add(aa);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: mm */
        public void mo8915mm() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                mo8920pm();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: n */
        public void mo8916n(C1553Aa aa) {
            mo8877a(aa, MapMaker$RemovalCause.COLLECTED);
            this.evictionQueue.remove(aa);
            this.expirationQueue.remove(aa);
        }

        /* access modifiers changed from: package-private */
        public C1553Aa newEntry(Object obj, int i, C1553Aa aa) {
            return this.map.f2458fQ.mo8873a(this, obj, i, aa);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: nm */
        public void mo8918nm() {
            mo8922rm();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: om */
        public void mo8919om() {
            mo8921qm();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: pm */
        public void mo8920pm() {
            mo8921qm();
            mo8922rm();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: qm */
        public void mo8921qm() {
            if (tryLock()) {
                try {
                    mo8908im();
                    mo8913lm();
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: rm */
        public void mo8922rm() {
            if (!isHeldByCurrentThread()) {
                this.map.mo8841am();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: sm */
        public void mo8923sm() {
            if (tryLock()) {
                try {
                    mo8908im();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: tm */
        public void mo8924tm() {
            if (tryLock()) {
                try {
                    mo8913lm();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
            r6 = r3.mo8578j();
            r7 = r6.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
            if (r7 != null) goto L_0x005e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
            if (mo8892d(r6) == false) goto L_0x0057;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
            r8.modCount++;
            mo8879a(r5, r10, r7, com.google.common.collect.MapMaker$RemovalCause.COLLECTED);
            r0.set(r1, mo8895e(r2, r3));
            r8.count--;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            r8.modCount++;
            mo8879a(r9, r10, r7, com.google.common.collect.MapMaker$RemovalCause.REPLACED);
            mo8878a(r3, r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x006c, code lost:
            unlock();
            mo8918nm();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
            return r7;
         */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object mo8886b(java.lang.Object r9, int r10, java.lang.Object r11) {
            /*
                r8 = this;
                r8.lock()
                r8.mo8919om()     // Catch:{ all -> 0x0078 }
                java.util.concurrent.atomic.AtomicReferenceArray r0 = r8.table     // Catch:{ all -> 0x0078 }
                int r1 = r0.length()     // Catch:{ all -> 0x0078 }
                int r1 = r1 + -1
                r1 = r1 & r10
                java.lang.Object r2 = r0.get(r1)     // Catch:{ all -> 0x0078 }
                com.google.common.collect.Aa r2 = (com.google.common.collect.C1553Aa) r2     // Catch:{ all -> 0x0078 }
                r3 = r2
            L_0x0016:
                r4 = 0
                if (r3 == 0) goto L_0x0057
                java.lang.Object r5 = r3.getKey()     // Catch:{ all -> 0x0078 }
                int r6 = r3.getHash()     // Catch:{ all -> 0x0078 }
                if (r6 != r10) goto L_0x0073
                if (r5 == 0) goto L_0x0073
                com.google.common.collect.MapMakerInternalMap r6 = r8.map     // Catch:{ all -> 0x0078 }
                com.google.common.base.u r6 = r6.keyEquivalence     // Catch:{ all -> 0x0078 }
                boolean r6 = r6.mo8558d(r9, r5)     // Catch:{ all -> 0x0078 }
                if (r6 == 0) goto L_0x0073
                com.google.common.collect.Ia r6 = r3.mo8578j()     // Catch:{ all -> 0x0078 }
                java.lang.Object r7 = r6.get()     // Catch:{ all -> 0x0078 }
                if (r7 != 0) goto L_0x005e
                boolean r9 = r8.mo8892d(r6)     // Catch:{ all -> 0x0078 }
                if (r9 == 0) goto L_0x0057
                int r9 = r8.modCount     // Catch:{ all -> 0x0078 }
                int r9 = r9 + 1
                r8.modCount = r9     // Catch:{ all -> 0x0078 }
                com.google.common.collect.MapMaker$RemovalCause r9 = com.google.common.collect.MapMaker$RemovalCause.COLLECTED     // Catch:{ all -> 0x0078 }
                r8.mo8879a((java.lang.Object) r5, (int) r10, (java.lang.Object) r7, (com.google.common.collect.MapMaker$RemovalCause) r9)     // Catch:{ all -> 0x0078 }
                com.google.common.collect.Aa r9 = r8.mo8895e((com.google.common.collect.C1553Aa) r2, (com.google.common.collect.C1553Aa) r3)     // Catch:{ all -> 0x0078 }
                int r10 = r8.count     // Catch:{ all -> 0x0078 }
                int r10 = r10 + -1
                r0.set(r1, r9)     // Catch:{ all -> 0x0078 }
                r8.count = r10     // Catch:{ all -> 0x0078 }
            L_0x0057:
                r8.unlock()
                r8.mo8918nm()
                return r4
            L_0x005e:
                int r0 = r8.modCount     // Catch:{ all -> 0x0078 }
                int r0 = r0 + 1
                r8.modCount = r0     // Catch:{ all -> 0x0078 }
                com.google.common.collect.MapMaker$RemovalCause r0 = com.google.common.collect.MapMaker$RemovalCause.REPLACED     // Catch:{ all -> 0x0078 }
                r8.mo8879a((java.lang.Object) r9, (int) r10, (java.lang.Object) r7, (com.google.common.collect.MapMaker$RemovalCause) r0)     // Catch:{ all -> 0x0078 }
                r8.mo8878a((com.google.common.collect.C1553Aa) r3, (java.lang.Object) r11)     // Catch:{ all -> 0x0078 }
                r8.unlock()
                r8.mo8918nm()
                return r7
            L_0x0073:
                com.google.common.collect.Aa r3 = r3.getNext()     // Catch:{ all -> 0x0078 }
                goto L_0x0016
            L_0x0078:
                r9 = move-exception
                r8.unlock()
                r8.mo8918nm()
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.mo8886b(java.lang.Object, int, java.lang.Object):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: f */
        public Object mo8900f(C1553Aa aa) {
            if (aa.getKey() == null) {
                mo8923sm();
                return null;
            }
            Object obj = aa.mo8578j().get();
            if (obj == null) {
                mo8923sm();
                return null;
            } else if (!this.map.mo8837Xl() || !this.map.mo8852g(aa)) {
                return obj;
            } else {
                mo8924tm();
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8878a(C1553Aa aa, Object obj) {
            aa.mo8570a(this.map.valueStrength.mo8926a(this, aa, obj));
            mo8914m(aa);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public boolean mo8892d(C1571Ia ia) {
            if (!ia.mo8592W() && ia.get() == null) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8877a(C1553Aa aa, MapMaker$RemovalCause mapMaker$RemovalCause) {
            mo8879a(aa.getKey(), aa.getHash(), aa.mo8578j().get(), mapMaker$RemovalCause);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: e */
        public C1553Aa mo8895e(C1553Aa aa, C1553Aa aa2) {
            this.evictionQueue.remove(aa2);
            this.expirationQueue.remove(aa2);
            int i = this.count;
            C1553Aa next = aa2.getNext();
            while (aa != aa2) {
                C1553Aa copyEntry = copyEntry(aa, next);
                if (copyEntry != null) {
                    next = copyEntry;
                } else {
                    mo8916n(aa);
                    i--;
                }
                aa = aa.getNext();
            }
            this.count = i;
            return next;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8879a(Object obj, int i, Object obj2, MapMaker$RemovalCause mapMaker$RemovalCause) {
            if (this.map.removalNotificationQueue != MapMakerInternalMap.f2455gQ) {
                this.map.removalNotificationQueue.offer(new MapMaker$RemovalNotification(obj, obj2, mapMaker$RemovalCause));
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
            r1 = r4.mo8578j();
            r2 = r1.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
            if (r2 != null) goto L_0x0072;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
            r8.modCount++;
            mo8878a(r4, r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0055, code lost:
            if (r1.mo8592W() != false) goto L_0x005f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0057, code lost:
            mo8879a(r9, r10, r2, com.google.common.collect.MapMaker$RemovalCause.COLLECTED);
            r0 = r8.count;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0063, code lost:
            if (mo8911km() == false) goto L_0x0069;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0065, code lost:
            r0 = r8.count + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0069, code lost:
            r8.count = r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0072, code lost:
            if (r12 == false) goto L_0x007e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            mo8910k(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0077, code lost:
            unlock();
            mo8918nm();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x007d, code lost:
            return r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            r8.modCount++;
            mo8879a(r9, r10, r2, com.google.common.collect.MapMaker$RemovalCause.REPLACED);
            mo8878a(r4, r11);
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object mo8876a(java.lang.Object r9, int r10, java.lang.Object r11, boolean r12) {
            /*
                r8 = this;
                r8.lock()
                r8.mo8919om()     // Catch:{ all -> 0x00af }
                int r0 = r8.count     // Catch:{ all -> 0x00af }
                int r0 = r0 + 1
                int r1 = r8.threshold     // Catch:{ all -> 0x00af }
                if (r0 <= r1) goto L_0x0015
                r8.expand()     // Catch:{ all -> 0x00af }
                int r0 = r8.count     // Catch:{ all -> 0x00af }
                int r0 = r0 + 1
            L_0x0015:
                java.util.concurrent.atomic.AtomicReferenceArray r1 = r8.table     // Catch:{ all -> 0x00af }
                int r2 = r1.length()     // Catch:{ all -> 0x00af }
                int r2 = r2 + -1
                r2 = r2 & r10
                java.lang.Object r3 = r1.get(r2)     // Catch:{ all -> 0x00af }
                com.google.common.collect.Aa r3 = (com.google.common.collect.C1553Aa) r3     // Catch:{ all -> 0x00af }
                r4 = r3
            L_0x0025:
                r5 = 0
                if (r4 == 0) goto L_0x0092
                java.lang.Object r6 = r4.getKey()     // Catch:{ all -> 0x00af }
                int r7 = r4.getHash()     // Catch:{ all -> 0x00af }
                if (r7 != r10) goto L_0x008d
                if (r6 == 0) goto L_0x008d
                com.google.common.collect.MapMakerInternalMap r7 = r8.map     // Catch:{ all -> 0x00af }
                com.google.common.base.u r7 = r7.keyEquivalence     // Catch:{ all -> 0x00af }
                boolean r6 = r7.mo8558d(r9, r6)     // Catch:{ all -> 0x00af }
                if (r6 == 0) goto L_0x008d
                com.google.common.collect.Ia r1 = r4.mo8578j()     // Catch:{ all -> 0x00af }
                java.lang.Object r2 = r1.get()     // Catch:{ all -> 0x00af }
                if (r2 != 0) goto L_0x0072
                int r12 = r8.modCount     // Catch:{ all -> 0x00af }
                int r12 = r12 + 1
                r8.modCount = r12     // Catch:{ all -> 0x00af }
                r8.mo8878a((com.google.common.collect.C1553Aa) r4, (java.lang.Object) r11)     // Catch:{ all -> 0x00af }
                boolean r11 = r1.mo8592W()     // Catch:{ all -> 0x00af }
                if (r11 != 0) goto L_0x005f
                com.google.common.collect.MapMaker$RemovalCause r11 = com.google.common.collect.MapMaker$RemovalCause.COLLECTED     // Catch:{ all -> 0x00af }
                r8.mo8879a((java.lang.Object) r9, (int) r10, (java.lang.Object) r2, (com.google.common.collect.MapMaker$RemovalCause) r11)     // Catch:{ all -> 0x00af }
                int r0 = r8.count     // Catch:{ all -> 0x00af }
                goto L_0x0069
            L_0x005f:
                boolean r9 = r8.mo8911km()     // Catch:{ all -> 0x00af }
                if (r9 == 0) goto L_0x0069
                int r9 = r8.count     // Catch:{ all -> 0x00af }
                int r0 = r9 + 1
            L_0x0069:
                r8.count = r0     // Catch:{ all -> 0x00af }
            L_0x006b:
                r8.unlock()
                r8.mo8918nm()
                return r5
            L_0x0072:
                if (r12 == 0) goto L_0x007e
                r8.mo8910k(r4)     // Catch:{ all -> 0x00af }
            L_0x0077:
                r8.unlock()
                r8.mo8918nm()
                return r2
            L_0x007e:
                int r12 = r8.modCount     // Catch:{ all -> 0x00af }
                int r12 = r12 + 1
                r8.modCount = r12     // Catch:{ all -> 0x00af }
                com.google.common.collect.MapMaker$RemovalCause r12 = com.google.common.collect.MapMaker$RemovalCause.REPLACED     // Catch:{ all -> 0x00af }
                r8.mo8879a((java.lang.Object) r9, (int) r10, (java.lang.Object) r2, (com.google.common.collect.MapMaker$RemovalCause) r12)     // Catch:{ all -> 0x00af }
                r8.mo8878a((com.google.common.collect.C1553Aa) r4, (java.lang.Object) r11)     // Catch:{ all -> 0x00af }
                goto L_0x0077
            L_0x008d:
                com.google.common.collect.Aa r4 = r4.getNext()     // Catch:{ all -> 0x00af }
                goto L_0x0025
            L_0x0092:
                int r12 = r8.modCount     // Catch:{ all -> 0x00af }
                int r12 = r12 + 1
                r8.modCount = r12     // Catch:{ all -> 0x00af }
                com.google.common.collect.Aa r9 = r8.newEntry(r9, r10, r3)     // Catch:{ all -> 0x00af }
                r8.mo8878a((com.google.common.collect.C1553Aa) r9, (java.lang.Object) r11)     // Catch:{ all -> 0x00af }
                r1.set(r2, r9)     // Catch:{ all -> 0x00af }
                boolean r9 = r8.mo8911km()     // Catch:{ all -> 0x00af }
                if (r9 == 0) goto L_0x00ac
                int r9 = r8.count     // Catch:{ all -> 0x00af }
                int r0 = r9 + 1
            L_0x00ac:
                r8.count = r0     // Catch:{ all -> 0x00af }
                goto L_0x006b
            L_0x00af:
                r9 = move-exception
                r8.unlock()
                r8.mo8918nm()
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.mo8876a(java.lang.Object, int, java.lang.Object, boolean):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public boolean mo8888b(Object obj, int i, C1571Ia ia) {
            lock();
            try {
                AtomicReferenceArray atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                C1553Aa aa = (C1553Aa) atomicReferenceArray.get(length);
                C1553Aa aa2 = aa;
                while (aa2 != null) {
                    Object key = aa2.getKey();
                    if (aa2.getHash() != i || key == null || !this.map.keyEquivalence.mo8558d(obj, key)) {
                        aa2 = aa2.getNext();
                    } else if (aa2.mo8578j() == ia) {
                        this.modCount++;
                        mo8879a(obj, i, ia.get(), MapMaker$RemovalCause.COLLECTED);
                        atomicReferenceArray.set(length, mo8895e(aa, aa2));
                        this.count--;
                        return true;
                    } else {
                        unlock();
                        if (!isHeldByCurrentThread()) {
                            mo8918nm();
                        }
                        return false;
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    mo8918nm();
                }
                return false;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    mo8918nm();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
            r7 = r4.mo8578j();
            r8 = r7.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
            if (r8 != null) goto L_0x005c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
            if (mo8892d(r7) == false) goto L_0x0055;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
            r9.modCount++;
            mo8879a(r6, r11, r8, com.google.common.collect.MapMaker$RemovalCause.COLLECTED);
            r0.set(r1, mo8895e(r3, r4));
            r9.count--;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0064, code lost:
            if (r9.map.valueEquivalence.mo8558d(r12, r8) == false) goto L_0x007a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0066, code lost:
            r9.modCount++;
            mo8879a(r10, r11, r8, com.google.common.collect.MapMaker$RemovalCause.REPLACED);
            mo8878a(r4, r13);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0073, code lost:
            unlock();
            mo8918nm();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0079, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            mo8910k(r4);
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean mo8885a(java.lang.Object r10, int r11, java.lang.Object r12, java.lang.Object r13) {
            /*
                r9 = this;
                r9.lock()
                r9.mo8919om()     // Catch:{ all -> 0x0083 }
                java.util.concurrent.atomic.AtomicReferenceArray r0 = r9.table     // Catch:{ all -> 0x0083 }
                int r1 = r0.length()     // Catch:{ all -> 0x0083 }
                r2 = 1
                int r1 = r1 - r2
                r1 = r1 & r11
                java.lang.Object r3 = r0.get(r1)     // Catch:{ all -> 0x0083 }
                com.google.common.collect.Aa r3 = (com.google.common.collect.C1553Aa) r3     // Catch:{ all -> 0x0083 }
                r4 = r3
            L_0x0016:
                r5 = 0
                if (r4 == 0) goto L_0x0055
                java.lang.Object r6 = r4.getKey()     // Catch:{ all -> 0x0083 }
                int r7 = r4.getHash()     // Catch:{ all -> 0x0083 }
                if (r7 != r11) goto L_0x007e
                if (r6 == 0) goto L_0x007e
                com.google.common.collect.MapMakerInternalMap r7 = r9.map     // Catch:{ all -> 0x0083 }
                com.google.common.base.u r7 = r7.keyEquivalence     // Catch:{ all -> 0x0083 }
                boolean r7 = r7.mo8558d(r10, r6)     // Catch:{ all -> 0x0083 }
                if (r7 == 0) goto L_0x007e
                com.google.common.collect.Ia r7 = r4.mo8578j()     // Catch:{ all -> 0x0083 }
                java.lang.Object r8 = r7.get()     // Catch:{ all -> 0x0083 }
                if (r8 != 0) goto L_0x005c
                boolean r10 = r9.mo8892d(r7)     // Catch:{ all -> 0x0083 }
                if (r10 == 0) goto L_0x0055
                int r10 = r9.modCount     // Catch:{ all -> 0x0083 }
                int r10 = r10 + r2
                r9.modCount = r10     // Catch:{ all -> 0x0083 }
                com.google.common.collect.MapMaker$RemovalCause r10 = com.google.common.collect.MapMaker$RemovalCause.COLLECTED     // Catch:{ all -> 0x0083 }
                r9.mo8879a((java.lang.Object) r6, (int) r11, (java.lang.Object) r8, (com.google.common.collect.MapMaker$RemovalCause) r10)     // Catch:{ all -> 0x0083 }
                com.google.common.collect.Aa r10 = r9.mo8895e((com.google.common.collect.C1553Aa) r3, (com.google.common.collect.C1553Aa) r4)     // Catch:{ all -> 0x0083 }
                int r11 = r9.count     // Catch:{ all -> 0x0083 }
                int r11 = r11 - r2
                r0.set(r1, r10)     // Catch:{ all -> 0x0083 }
                r9.count = r11     // Catch:{ all -> 0x0083 }
            L_0x0055:
                r9.unlock()
                r9.mo8918nm()
                return r5
            L_0x005c:
                com.google.common.collect.MapMakerInternalMap r0 = r9.map     // Catch:{ all -> 0x0083 }
                com.google.common.base.u r0 = r0.valueEquivalence     // Catch:{ all -> 0x0083 }
                boolean r12 = r0.mo8558d(r12, r8)     // Catch:{ all -> 0x0083 }
                if (r12 == 0) goto L_0x007a
                int r12 = r9.modCount     // Catch:{ all -> 0x0083 }
                int r12 = r12 + r2
                r9.modCount = r12     // Catch:{ all -> 0x0083 }
                com.google.common.collect.MapMaker$RemovalCause r12 = com.google.common.collect.MapMaker$RemovalCause.REPLACED     // Catch:{ all -> 0x0083 }
                r9.mo8879a((java.lang.Object) r10, (int) r11, (java.lang.Object) r8, (com.google.common.collect.MapMaker$RemovalCause) r12)     // Catch:{ all -> 0x0083 }
                r9.mo8878a((com.google.common.collect.C1553Aa) r4, (java.lang.Object) r13)     // Catch:{ all -> 0x0083 }
                r9.unlock()
                r9.mo8918nm()
                return r2
            L_0x007a:
                r9.mo8910k(r4)     // Catch:{ all -> 0x0083 }
                goto L_0x0055
            L_0x007e:
                com.google.common.collect.Aa r4 = r4.getNext()     // Catch:{ all -> 0x0083 }
                goto L_0x0016
            L_0x0083:
                r10 = move-exception
                r9.unlock()
                r9.mo8918nm()
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.mo8885a(java.lang.Object, int, java.lang.Object, java.lang.Object):boolean");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo8884a(Object obj, int i, Object obj2) {
            MapMaker$RemovalCause mapMaker$RemovalCause;
            lock();
            try {
                mo8919om();
                AtomicReferenceArray atomicReferenceArray = this.table;
                boolean z = true;
                int length = (atomicReferenceArray.length() - 1) & i;
                C1553Aa aa = (C1553Aa) atomicReferenceArray.get(length);
                C1553Aa aa2 = aa;
                while (true) {
                    if (aa2 == null) {
                        break;
                    }
                    Object key = aa2.getKey();
                    if (aa2.getHash() != i || key == null || !this.map.keyEquivalence.mo8558d(obj, key)) {
                        aa2 = aa2.getNext();
                    } else {
                        C1571Ia j = aa2.mo8578j();
                        Object obj3 = j.get();
                        if (this.map.valueEquivalence.mo8558d(obj2, obj3)) {
                            mapMaker$RemovalCause = MapMaker$RemovalCause.EXPLICIT;
                        } else if (mo8892d(j)) {
                            mapMaker$RemovalCause = MapMaker$RemovalCause.COLLECTED;
                        }
                        this.modCount++;
                        mo8879a(key, i, obj3, mapMaker$RemovalCause);
                        atomicReferenceArray.set(length, mo8895e(aa, aa2));
                        this.count--;
                        if (mapMaker$RemovalCause != MapMaker$RemovalCause.EXPLICIT) {
                            z = false;
                        }
                        return z;
                    }
                }
                unlock();
                mo8918nm();
                return false;
            } finally {
                unlock();
                mo8918nm();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo8881a(C1553Aa aa, int i) {
            lock();
            try {
                AtomicReferenceArray atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                C1553Aa aa2 = (C1553Aa) atomicReferenceArray.get(length);
                for (C1553Aa aa3 = aa2; aa3 != null; aa3 = aa3.getNext()) {
                    if (aa3 == aa) {
                        this.modCount++;
                        mo8879a(aa3.getKey(), i, aa3.mo8578j().get(), MapMaker$RemovalCause.COLLECTED);
                        atomicReferenceArray.set(length, mo8895e(aa2, aa3));
                        this.count--;
                        return true;
                    }
                }
                unlock();
                mo8918nm();
                return false;
            } finally {
                unlock();
                mo8918nm();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo8883a(Object obj, int i, C1571Ia ia) {
            lock();
            try {
                AtomicReferenceArray atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                C1553Aa aa = (C1553Aa) atomicReferenceArray.get(length);
                C1553Aa aa2 = aa;
                while (true) {
                    if (aa2 == null) {
                        break;
                    }
                    Object key = aa2.getKey();
                    if (aa2.getHash() != i || key == null || !this.map.keyEquivalence.mo8558d(obj, key)) {
                        aa2 = aa2.getNext();
                    } else if (aa2.mo8578j() == ia) {
                        atomicReferenceArray.set(length, mo8895e(aa, aa2));
                        return true;
                    }
                }
                unlock();
                mo8918nm();
                return false;
            } finally {
                unlock();
                mo8918nm();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo8882a(C1553Aa aa, int i, MapMaker$RemovalCause mapMaker$RemovalCause) {
            AtomicReferenceArray atomicReferenceArray = this.table;
            int length = (atomicReferenceArray.length() - 1) & i;
            C1553Aa aa2 = (C1553Aa) atomicReferenceArray.get(length);
            for (C1553Aa aa3 = aa2; aa3 != null; aa3 = aa3.getNext()) {
                if (aa3 == aa) {
                    this.modCount++;
                    mo8879a(aa3.getKey(), i, aa3.mo8578j().get(), mapMaker$RemovalCause);
                    atomicReferenceArray.set(length, mo8895e(aa2, aa3));
                    this.count--;
                    return true;
                }
            }
            return false;
        }
    }

    final class SerializationProxy extends AbstractSerializationProxy {
        private static final long serialVersionUID = 3;

        SerializationProxy(Strength strength, Strength strength2, C1546u uVar, C1546u uVar2, long j, long j2, int i, int i2, C1670ka kaVar, ConcurrentMap concurrentMap) {
            super(strength, strength2, uVar, uVar2, j, j2, i, i2, kaVar, concurrentMap);
        }

        private void readObject(ObjectInputStream objectInputStream) {
            ConcurrentMap concurrentMap;
            objectInputStream.defaultReadObject();
            C1673la b = mo8871b(objectInputStream);
            if (!b.f2537LN) {
                concurrentMap = new ConcurrentHashMap(b.mo9216tl(), 0.75f, b.mo9215sl());
            } else if (b.f2539NN == null) {
                concurrentMap = new MapMakerInternalMap(b);
            } else {
                concurrentMap = new MapMaker$NullConcurrentMap(b);
            }
            this.delegate = concurrentMap;
            mo8869a(objectInputStream);
        }

        private Object readResolve() {
            return this.delegate;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.defaultWriteObject();
            mo8870a(objectOutputStream);
        }
    }

    enum Strength {
        STRONG {
            /* access modifiers changed from: package-private */
            /* renamed from: Kl */
            public C1546u mo8925Kl() {
                return C1546u.equals();
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1571Ia mo8926a(Segment segment, C1553Aa aa, Object obj) {
                return new C1567Ga(obj);
            }
        },
        SOFT {
            /* access modifiers changed from: package-private */
            /* renamed from: Kl */
            public C1546u mo8925Kl() {
                return C1546u.identity();
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1571Ia mo8926a(Segment segment, C1553Aa aa, Object obj) {
                return new C1555Ba(segment.valueReferenceQueue, obj, aa);
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            /* renamed from: Kl */
            public C1546u mo8925Kl() {
                return C1546u.identity();
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public C1571Ia mo8926a(Segment segment, C1553Aa aa, Object obj) {
                return new C1605Oa(segment.valueReferenceQueue, obj, aa);
            }
        };

        /* access modifiers changed from: package-private */
        /* renamed from: Kl */
        public abstract C1546u mo8925Kl();

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public abstract C1571Ia mo8926a(Segment segment, C1553Aa aa, Object obj);
    }

    MapMakerInternalMap(C1673la laVar) {
        Queue queue;
        int i = laVar.concurrencyLevel;
        this.concurrencyLevel = Math.min(i == -1 ? 4 : i, 65536);
        this.keyStrength = (Strength) C1508E.m3964e(laVar.keyStrength, Strength.STRONG);
        this.valueStrength = (Strength) C1508E.m3964e(laVar.valueStrength, Strength.STRONG);
        this.keyEquivalence = (C1546u) C1508E.m3964e(laVar.keyEquivalence, ((Strength) C1508E.m3964e(laVar.keyStrength, Strength.STRONG)).mo8925Kl());
        this.valueEquivalence = this.valueStrength.mo8925Kl();
        this.maximumSize = laVar.maximumSize;
        long j = laVar.expireAfterAccessNanos;
        this.expireAfterAccessNanos = j == -1 ? 0 : j;
        long j2 = laVar.expireAfterWriteNanos;
        this.expireAfterWriteNanos = j2 == -1 ? 0 : j2;
        int i2 = 0;
        this.f2458fQ = EntryFactory.f2467uP[this.keyStrength.ordinal()][mo8837Xl() | (mo8836Wl() ? (char) 2 : 0)];
        this.ticker = (C1525S) C1508E.m3964e(laVar.ticker, C1525S.m3984Yk());
        this.removalListener = (C1670ka) C1508E.m3964e(laVar.removalListener, GenericMapMaker$NullListener.INSTANCE);
        if (this.removalListener == GenericMapMaker$NullListener.INSTANCE) {
            queue = f2455gQ;
        } else {
            queue = new ConcurrentLinkedQueue();
        }
        this.removalNotificationQueue = queue;
        int i3 = laVar.f2538MN;
        int min = Math.min(i3 == -1 ? 16 : i3, 1073741824);
        min = mo8836Wl() ? Math.min(min, this.maximumSize) : min;
        int i4 = 1;
        int i5 = 0;
        while (i4 < this.concurrencyLevel && (!mo8836Wl() || i4 * 2 <= this.maximumSize)) {
            i5++;
            i4 <<= 1;
        }
        this.f2457eQ = 32 - i5;
        this.f2456dQ = i4 - 1;
        this.segments = mo8851fb(i4);
        int i6 = min / i4;
        i6 = i6 * i4 < min ? i6 + 1 : i6;
        int i7 = 1;
        while (i7 < i6) {
            i7 <<= 1;
        }
        if (mo8836Wl()) {
            int i8 = this.maximumSize;
            int i9 = (i8 / i4) + 1;
            int i10 = i8 % i4;
            while (i2 < this.segments.length) {
                if (i2 == i10) {
                    i9--;
                }
                this.segments[i2] = mo8605O(i7, i9);
                i2++;
            }
            return;
        }
        while (true) {
            Segment[] segmentArr = this.segments;
            if (i2 < segmentArr.length) {
                segmentArr[i2] = mo8605O(i7, -1);
                i2++;
            } else {
                return;
            }
        }
    }

    /* renamed from: _l */
    static C1553Aa m4303_l() {
        return NullEntry.INSTANCE;
    }

    /* renamed from: d */
    static void m4305d(C1553Aa aa, C1553Aa aa2) {
        aa.mo8572c(aa2);
        aa2.mo8573d(aa);
    }

    /* renamed from: h */
    static void m4306h(C1553Aa aa) {
        NullEntry nullEntry = NullEntry.INSTANCE;
        aa.mo8571b(nullEntry);
        aa.mo8569a((C1553Aa) nullEntry);
    }

    /* renamed from: i */
    static void m4307i(C1553Aa aa) {
        NullEntry nullEntry = NullEntry.INSTANCE;
        aa.mo8572c(nullEntry);
        aa.mo8573d(nullEntry);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: O */
    public Segment mo8605O(int i, int i2) {
        return new Segment(this, i, i2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Wl */
    public boolean mo8836Wl() {
        return this.maximumSize != -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Xl */
    public boolean mo8837Xl() {
        return mo8839Zl() || mo8838Yl();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Yl */
    public boolean mo8838Yl() {
        return this.expireAfterAccessNanos > 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Zl */
    public boolean mo8839Zl() {
        return this.expireAfterWriteNanos > 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo8840a(C1553Aa aa, long j) {
        return j - aa.getExpirationTime() > 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: am */
    public void mo8841am() {
        while (true) {
            MapMaker$RemovalNotification mapMaker$RemovalNotification = (MapMaker$RemovalNotification) this.removalNotificationQueue.poll();
            if (mapMaker$RemovalNotification != null) {
                try {
                    ((GenericMapMaker$NullListener) this.removalListener).mo8673a(mapMaker$RemovalNotification);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Exception thrown by removal listener", e);
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: bm */
    public boolean mo8842bm() {
        return this.keyStrength != Strength.STRONG;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo8843c(C1571Ia ia) {
        C1553Aa entry = ia.getEntry();
        int hash = entry.getHash();
        mo8606gb(hash).mo8888b(entry.getKey(), hash, ia);
    }

    public void clear() {
        for (Segment clear : this.segments) {
            clear.clear();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cm */
    public boolean mo8845cm() {
        return this.valueStrength != Strength.STRONG;
    }

    public boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        int v = mo8867v(obj);
        return mo8606gb(v).mo8893d(obj, v);
    }

    public boolean containsValue(Object obj) {
        Object obj2 = obj;
        boolean z = false;
        if (obj2 == null) {
            return false;
        }
        Segment[] segmentArr = this.segments;
        long j = -1;
        int i = 0;
        while (i < 3) {
            int length = segmentArr.length;
            long j2 = 0;
            int i2 = z;
            while (i2 < length) {
                Segment segment = segmentArr[i2];
                int i3 = segment.count;
                AtomicReferenceArray atomicReferenceArray = segment.table;
                for (int i4 = z; i4 < atomicReferenceArray.length(); i4++) {
                    for (C1553Aa aa = (C1553Aa) atomicReferenceArray.get(i4); aa != null; aa = aa.getNext()) {
                        Object f = segment.mo8900f(aa);
                        if (f != null && this.valueEquivalence.mo8558d(obj2, f)) {
                            return true;
                        }
                    }
                }
                j2 += (long) segment.modCount;
                i2++;
                z = false;
            }
            if (j2 == j) {
                return false;
            }
            i++;
            j = j2;
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public C1553Aa copyEntry(C1553Aa aa, C1553Aa aa2) {
        return mo8606gb(aa.getHash()).copyEntry(aa, aa2);
    }

    public Set entrySet() {
        Set set = this.f2459nN;
        if (set != null) {
            return set;
        }
        C1688qa qaVar = new C1688qa(this);
        this.f2459nN = qaVar;
        return qaVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public Object mo8850f(C1553Aa aa) {
        Object obj;
        if (aa.getKey() == null || (obj = aa.mo8578j().get()) == null) {
            return null;
        }
        if (!mo8837Xl() || !mo8852g(aa)) {
            return obj;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: fb */
    public final Segment[] mo8851fb(int i) {
        return new Segment[i];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean mo8852g(C1553Aa aa) {
        return mo8840a(aa, this.ticker.read());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: gb */
    public Segment mo8606gb(int i) {
        return this.segments[this.f2456dQ & (i >>> this.f2457eQ)];
    }

    public Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        int v = mo8867v(obj);
        return mo8606gb(v).get(obj, v);
    }

    public boolean isEmpty() {
        Segment[] segmentArr = this.segments;
        long j = 0;
        for (int i = 0; i < segmentArr.length; i++) {
            if (segmentArr[i].count != 0) {
                return false;
            }
            j += (long) segmentArr[i].modCount;
        }
        if (j == 0) {
            return true;
        }
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            if (segmentArr[i2].count != 0) {
                return false;
            }
            j -= (long) segmentArr[i2].modCount;
        }
        if (j != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isLive(C1553Aa aa) {
        return mo8606gb(aa.getHash()).mo8900f(aa) != null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo8855j(C1553Aa aa) {
        int hash = aa.getHash();
        mo8606gb(hash).mo8881a(aa, hash);
    }

    public Set keySet() {
        Set set = this.keySet;
        if (set != null) {
            return set;
        }
        C1709za zaVar = new C1709za(this);
        this.keySet = zaVar;
        return zaVar;
    }

    /* access modifiers changed from: package-private */
    public C1553Aa newEntry(Object obj, int i, C1553Aa aa) {
        return mo8606gb(i).newEntry(obj, i, aa);
    }

    /* access modifiers changed from: package-private */
    public C1571Ia newValueReference(C1553Aa aa, Object obj) {
        return this.valueStrength.mo8926a(mo8606gb(aa.getHash()), aa, obj);
    }

    public Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj2 != null) {
            int v = mo8867v(obj);
            return mo8606gb(v).mo8876a(obj, v, obj2, false);
        } else {
            throw new NullPointerException();
        }
    }

    public void putAll(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj2 != null) {
            int v = mo8867v(obj);
            return mo8606gb(v).mo8876a(obj, v, obj2, true);
        } else {
            throw new NullPointerException();
        }
    }

    public Object remove(Object obj) {
        if (obj == null) {
            return null;
        }
        int v = mo8867v(obj);
        return mo8606gb(v).mo8902g(obj, v);
    }

    public boolean replace(Object obj, Object obj2, Object obj3) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj3 == null) {
            throw new NullPointerException();
        } else if (obj2 == null) {
            return false;
        } else {
            int v = mo8867v(obj);
            return mo8606gb(v).mo8885a(obj, v, obj2, obj3);
        }
    }

    public int size() {
        Segment[] segmentArr = this.segments;
        long j = 0;
        for (Segment segment : segmentArr) {
            j += (long) segment.count;
        }
        return C1722a.m4652G(j);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: v */
    public int mo8867v(Object obj) {
        int v = this.keyEquivalence.mo8559v(obj);
        int i = v + ((v << 15) ^ -12931);
        int i2 = i ^ (i >>> 10);
        int i3 = i2 + (i2 << 3);
        int i4 = i3 ^ (i3 >>> 6);
        int i5 = (i4 << 2) + (i4 << 14) + i4;
        return (i5 >>> 16) ^ i5;
    }

    public Collection values() {
        Collection collection = this.values;
        if (collection != null) {
            return collection;
        }
        C1577Ja ja = new C1577Ja(this);
        this.values = ja;
        return ja;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializationProxy(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this);
    }

    abstract class AbstractSerializationProxy extends C1568H implements Serializable {
        private static final long serialVersionUID = 3;
        final int concurrencyLevel;
        transient ConcurrentMap delegate;
        final long expireAfterAccessNanos;
        final long expireAfterWriteNanos;
        final C1546u keyEquivalence;
        final Strength keyStrength;
        final int maximumSize;
        final C1670ka removalListener;
        final C1546u valueEquivalence;
        final Strength valueStrength;

        AbstractSerializationProxy(Strength strength, Strength strength2, C1546u uVar, C1546u uVar2, long j, long j2, int i, int i2, C1670ka kaVar, ConcurrentMap concurrentMap) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = uVar;
            this.valueEquivalence = uVar2;
            this.expireAfterWriteNanos = j;
            this.expireAfterAccessNanos = j2;
            this.maximumSize = i;
            this.concurrencyLevel = i2;
            this.removalListener = kaVar;
            this.delegate = concurrentMap;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8870a(ObjectOutputStream objectOutputStream) {
            objectOutputStream.writeInt(this.delegate.size());
            for (Map.Entry entry : this.delegate.entrySet()) {
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            objectOutputStream.writeObject((Object) null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public C1673la mo8871b(ObjectInputStream objectInputStream) {
            int readInt = objectInputStream.readInt();
            C1673la laVar = new C1673la();
            boolean z = false;
            C1508E.m3962a(laVar.f2538MN == -1, "initial capacity was already set to %s", Integer.valueOf(laVar.f2538MN));
            C1508E.checkArgument(readInt >= 0);
            laVar.f2538MN = readInt;
            laVar.mo9213a(this.keyStrength);
            Strength strength = this.valueStrength;
            C1508E.m3962a(laVar.valueStrength == null, "Value strength was already set to %s", laVar.valueStrength);
            if (strength != null) {
                laVar.valueStrength = strength;
                if (strength != Strength.STRONG) {
                    laVar.f2537LN = true;
                }
                C1546u uVar = this.keyEquivalence;
                C1508E.m3962a(laVar.keyEquivalence == null, "key equivalence was already set to %s", laVar.keyEquivalence);
                if (uVar != null) {
                    laVar.keyEquivalence = uVar;
                    laVar.f2537LN = true;
                    int i = this.concurrencyLevel;
                    C1508E.m3962a(laVar.concurrencyLevel == -1, "concurrency level was already set to %s", Integer.valueOf(laVar.concurrencyLevel));
                    C1508E.checkArgument(i > 0);
                    laVar.concurrencyLevel = i;
                    C1670ka kaVar = this.removalListener;
                    C1508E.checkState(laVar.removalListener == null);
                    if (kaVar != null) {
                        laVar.removalListener = kaVar;
                        laVar.f2537LN = true;
                        long j = this.expireAfterWriteNanos;
                        if (j > 0) {
                            laVar.mo9214b(j, TimeUnit.NANOSECONDS);
                        }
                        long j2 = this.expireAfterAccessNanos;
                        if (j2 > 0) {
                            laVar.mo9212a(j2, TimeUnit.NANOSECONDS);
                        }
                        int i2 = this.maximumSize;
                        if (i2 != -1) {
                            C1508E.m3962a(laVar.maximumSize == -1, "maximum size was already set to %s", Integer.valueOf(laVar.maximumSize));
                            if (i2 >= 0) {
                                z = true;
                            }
                            C1508E.checkArgument(z, "maximum size must not be negative");
                            laVar.maximumSize = i2;
                            laVar.f2537LN = true;
                            if (laVar.maximumSize == 0) {
                                laVar.f2539NN = MapMaker$RemovalCause.SIZE;
                            }
                        }
                        return laVar;
                    }
                    throw new NullPointerException();
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        /* renamed from: ml */
        public ConcurrentMap m4329ml() {
            return this.delegate;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8869a(ObjectInputStream objectInputStream) {
            while (true) {
                Object readObject = objectInputStream.readObject();
                if (readObject != null) {
                    this.delegate.put(readObject, objectInputStream.readObject());
                } else {
                    return;
                }
            }
        }
    }

    public boolean remove(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int v = mo8867v(obj);
        return mo8606gb(v).mo8884a(obj, v, obj2);
    }

    /* renamed from: c */
    static void m4304c(C1553Aa aa, C1553Aa aa2) {
        aa.mo8571b(aa2);
        aa2.mo8569a(aa);
    }

    public Object replace(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj2 != null) {
            int v = mo8867v(obj);
            return mo8606gb(v).mo8886b(obj, v, obj2);
        } else {
            throw new NullPointerException();
        }
    }
}
