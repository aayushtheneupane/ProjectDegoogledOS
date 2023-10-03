package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: com.google.common.collect.Ua */
class C1627Ua extends C1629Va implements NavigableSet {
    C1627Ua(NavigableMap navigableMap) {
        super(navigableMap);
    }

    public Object ceiling(Object obj) {
        return ((NavigableMap) this.map).ceilingKey(obj);
    }

    public Iterator descendingIterator() {
        return ((NavigableMap) this.map).descendingKeySet().iterator();
    }

    public NavigableSet descendingSet() {
        return ((NavigableMap) this.map).descendingKeySet();
    }

    public Object floor(Object obj) {
        return ((NavigableMap) this.map).floorKey(obj);
    }

    public NavigableSet headSet(Object obj, boolean z) {
        return ((NavigableMap) this.map).headMap(obj, z).navigableKeySet();
    }

    public Object higher(Object obj) {
        return ((NavigableMap) this.map).higherKey(obj);
    }

    public Object lower(Object obj) {
        return ((NavigableMap) this.map).lowerKey(obj);
    }

    /* access modifiers changed from: package-private */
    public Map map() {
        return (NavigableMap) this.map;
    }

    public Object pollFirst() {
        return C1633Xa.m4539a(((NavigableMap) this.map).pollFirstEntry());
    }

    public Object pollLast() {
        return C1633Xa.m4539a(((NavigableMap) this.map).pollLastEntry());
    }

    public NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        return ((NavigableMap) this.map).subMap(obj, z, obj2, z2).navigableKeySet();
    }

    public NavigableSet tailSet(Object obj, boolean z) {
        return ((NavigableMap) this.map).tailMap(obj, z).navigableKeySet();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: map  reason: collision with other method in class */
    public SortedMap m4707map() {
        return (NavigableMap) this.map;
    }

    public SortedSet headSet(Object obj) {
        return ((NavigableMap) this.map).headMap(obj, false).navigableKeySet();
    }

    public SortedSet subSet(Object obj, Object obj2) {
        return ((NavigableMap) this.map).subMap(obj, true, obj2, false).navigableKeySet();
    }

    public SortedSet tailSet(Object obj) {
        return ((NavigableMap) this.map).tailMap(obj, true).navigableKeySet();
    }
}
