package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap;
import java.util.Map;

class Ordering$ArbitraryOrdering extends C1644bb {

    /* renamed from: aO */
    private Map f2475aO;

    Ordering$ArbitraryOrdering() {
        Map map;
        C1673la laVar = new C1673la();
        laVar.mo9213a(MapMakerInternalMap.Strength.WEAK);
        C1641ab abVar = new C1641ab(this);
        if (laVar.f2539NN == null) {
            map = new MapMaker$ComputingMapAdapter(laVar, abVar);
        } else {
            map = new MapMaker$NullComputingConcurrentMap(laVar, abVar);
        }
        this.f2475aO = map;
    }

    public int compare(Object obj, Object obj2) {
        if (obj == obj2) {
            return 0;
        }
        if (obj == null) {
            return -1;
        }
        if (obj2 == null) {
            return 1;
        }
        int identityHashCode = identityHashCode(obj);
        int identityHashCode2 = identityHashCode(obj2);
        if (identityHashCode == identityHashCode2) {
            int compareTo = ((Integer) this.f2475aO.get(obj)).compareTo((Integer) this.f2475aO.get(obj2));
            if (compareTo != 0) {
                return compareTo;
            }
            throw new AssertionError();
        } else if (identityHashCode < identityHashCode2) {
            return -1;
        } else {
            return 1;
        }
    }

    /* access modifiers changed from: package-private */
    public int identityHashCode(Object obj) {
        return System.identityHashCode(obj);
    }

    public String toString() {
        return "Ordering.arbitrary()";
    }
}
