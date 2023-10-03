package com.google.common.collect;

import com.google.common.base.C1508E;
import com.google.common.collect.MapMakerInternalMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: com.google.common.collect.xa */
abstract class C1705xa implements Iterator {

    /* renamed from: HN */
    C1553Aa f2551HN;

    /* renamed from: TN */
    int f2552TN;

    /* renamed from: UN */
    int f2553UN = -1;

    /* renamed from: VN */
    MapMakerInternalMap.Segment f2554VN;

    /* renamed from: WN */
    AtomicReferenceArray f2555WN;

    /* renamed from: XN */
    C1607Pa f2556XN;

    /* renamed from: YN */
    C1607Pa f2557YN;
    final /* synthetic */ MapMakerInternalMap this$0;

    C1705xa(MapMakerInternalMap mapMakerInternalMap) {
        this.this$0 = mapMakerInternalMap;
        this.f2552TN = mapMakerInternalMap.segments.length - 1;
        advance();
    }

    /* access modifiers changed from: package-private */
    public final void advance() {
        this.f2556XN = null;
        if (!mo9335vl() && !mo9336wl()) {
            while (true) {
                int i = this.f2552TN;
                if (i >= 0) {
                    MapMakerInternalMap.Segment[] segmentArr = this.this$0.segments;
                    this.f2552TN = i - 1;
                    this.f2554VN = segmentArr[i];
                    if (this.f2554VN.count != 0) {
                        this.f2555WN = this.f2554VN.table;
                        this.f2553UN = this.f2555WN.length() - 1;
                        if (mo9336wl()) {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public boolean mo9331e(C1553Aa aa) {
        boolean z;
        try {
            Object key = aa.getKey();
            Object f = this.this$0.mo8850f(aa);
            if (f != null) {
                this.f2556XN = new C1607Pa(this.this$0, key, f);
                z = true;
            } else {
                z = false;
            }
            return z;
        } finally {
            this.f2554VN.mo8915mm();
        }
    }

    public boolean hasNext() {
        return this.f2556XN != null;
    }

    public void remove() {
        C1508E.m3961a(this.f2557YN != null, "no calls to next() since the last call to remove()");
        this.this$0.remove(this.f2557YN.key);
        this.f2557YN = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ul */
    public C1607Pa mo9334ul() {
        C1607Pa pa = this.f2556XN;
        if (pa != null) {
            this.f2557YN = pa;
            advance();
            return this.f2557YN;
        }
        throw new NoSuchElementException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: vl */
    public boolean mo9335vl() {
        C1553Aa aa = this.f2551HN;
        if (aa == null) {
            return false;
        }
        while (true) {
            this.f2551HN = aa.getNext();
            C1553Aa aa2 = this.f2551HN;
            if (aa2 == null) {
                return false;
            }
            if (mo9331e(aa2)) {
                return true;
            }
            aa = this.f2551HN;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: wl */
    public boolean mo9336wl() {
        while (true) {
            int i = this.f2553UN;
            if (i < 0) {
                return false;
            }
            AtomicReferenceArray atomicReferenceArray = this.f2555WN;
            this.f2553UN = i - 1;
            C1553Aa aa = (C1553Aa) atomicReferenceArray.get(i);
            this.f2551HN = aa;
            if (aa != null && (mo9331e(this.f2551HN) || mo9335vl())) {
                return true;
            }
        }
    }
}
