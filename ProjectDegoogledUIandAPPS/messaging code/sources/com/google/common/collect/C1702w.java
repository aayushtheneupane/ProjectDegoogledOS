package com.google.common.collect;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

/* renamed from: com.google.common.collect.w */
final class C1702w extends C1570I implements NavigableMap {
    private transient Comparator comparator;

    /* renamed from: nN */
    private transient Set f2548nN;

    /* renamed from: oN */
    private transient NavigableSet f2549oN;
    final /* synthetic */ C1704x this$0;

    /* synthetic */ C1702w(C1704x xVar, C1700v vVar) {
        this.this$0 = xVar;
    }

    public Map.Entry ceilingEntry(Object obj) {
        return this.this$0.floorEntry(obj);
    }

    public Object ceilingKey(Object obj) {
        return this.this$0.floorKey(obj);
    }

    public Comparator comparator() {
        Comparator comparator2 = this.comparator;
        if (comparator2 != null) {
            return comparator2;
        }
        Comparator comparator3 = this.this$0.comparator();
        if (comparator3 == null) {
            comparator3 = C1644bb.m4563zl();
        }
        C1644bb reverse = C1644bb.m4562b(comparator3).reverse();
        this.comparator = reverse;
        return reverse;
    }

    public NavigableSet descendingKeySet() {
        return this.this$0.navigableKeySet();
    }

    public NavigableMap descendingMap() {
        return this.this$0;
    }

    public Set entrySet() {
        Set set = this.f2548nN;
        if (set != null) {
            return set;
        }
        C1610Ra ra = new C1610Ra(this);
        this.f2548nN = ra;
        return ra;
    }

    public Map.Entry firstEntry() {
        return this.this$0.lastEntry();
    }

    public Object firstKey() {
        return this.this$0.lastKey();
    }

    public Map.Entry floorEntry(Object obj) {
        return this.this$0.ceilingEntry(obj);
    }

    public Object floorKey(Object obj) {
        return this.this$0.ceilingKey(obj);
    }

    public NavigableMap headMap(Object obj, boolean z) {
        return this.this$0.tailMap(obj, z).descendingMap();
    }

    public Map.Entry higherEntry(Object obj) {
        return this.this$0.lowerEntry(obj);
    }

    public Object higherKey(Object obj) {
        return this.this$0.lowerKey(obj);
    }

    public Set keySet() {
        NavigableSet navigableSet = this.f2549oN;
        if (navigableSet != null) {
            return navigableSet;
        }
        C1627Ua ua = new C1627Ua(this);
        this.f2549oN = ua;
        return ua;
    }

    public Map.Entry lastEntry() {
        return this.this$0.firstEntry();
    }

    public Object lastKey() {
        return this.this$0.firstKey();
    }

    public Map.Entry lowerEntry(Object obj) {
        return this.this$0.higherEntry(obj);
    }

    public Object lowerKey(Object obj) {
        return this.this$0.higherKey(obj);
    }

    /* access modifiers changed from: protected */
    /* renamed from: ml */
    public Object mo8674ml() {
        return this.this$0;
    }

    public NavigableSet navigableKeySet() {
        NavigableSet navigableSet = this.f2549oN;
        if (navigableSet != null) {
            return navigableSet;
        }
        C1627Ua ua = new C1627Ua(this);
        this.f2549oN = ua;
        return ua;
    }

    public Map.Entry pollFirstEntry() {
        return this.this$0.pollLastEntry();
    }

    public Map.Entry pollLastEntry() {
        return this.this$0.pollFirstEntry();
    }

    public NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
        return this.this$0.subMap(obj2, z2, obj, z).descendingMap();
    }

    public NavigableMap tailMap(Object obj, boolean z) {
        return this.this$0.headMap(obj, z).descendingMap();
    }

    public String toString() {
        return mo8689nl();
    }

    public Collection values() {
        return new C1631Wa(this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: ml */
    public final Map m4635ml() {
        return this.this$0;
    }

    public SortedMap headMap(Object obj) {
        return this.this$0.tailMap(obj, false).descendingMap();
    }

    public SortedMap subMap(Object obj, Object obj2) {
        return this.this$0.subMap(obj2, false, obj, true).descendingMap();
    }

    public SortedMap tailMap(Object obj) {
        return this.this$0.headMap(obj, true).descendingMap();
    }
}
