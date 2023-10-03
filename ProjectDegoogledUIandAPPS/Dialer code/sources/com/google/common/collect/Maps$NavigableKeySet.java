package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.SortedSet;

class Maps$NavigableKeySet<K, V> extends Maps$SortedKeySet<K, V> implements NavigableSet<K> {
    Maps$NavigableKeySet(NavigableMap<K, V> navigableMap) {
        super(navigableMap);
    }

    public K ceiling(K k) {
        return ((NavigableMap) this.map).ceilingKey(k);
    }

    public Iterator<K> descendingIterator() {
        return ((NavigableMap) this.map).descendingKeySet().iterator();
    }

    public NavigableSet<K> descendingSet() {
        return ((NavigableMap) this.map).descendingKeySet();
    }

    public K floor(K k) {
        return ((NavigableMap) this.map).floorKey(k);
    }

    public NavigableSet<K> headSet(K k, boolean z) {
        return ((NavigableMap) this.map).headMap(k, z).navigableKeySet();
    }

    public K higher(K k) {
        return ((NavigableMap) this.map).higherKey(k);
    }

    public K lower(K k) {
        return ((NavigableMap) this.map).lowerKey(k);
    }

    /* access modifiers changed from: package-private */
    public Map map() {
        return (NavigableMap) this.map;
    }

    public K pollFirst() {
        return Collections2.keyOrNull(((NavigableMap) this.map).pollFirstEntry());
    }

    public K pollLast() {
        return Collections2.keyOrNull(((NavigableMap) this.map).pollLastEntry());
    }

    public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
        return ((NavigableMap) this.map).subMap(k, z, k2, z2).navigableKeySet();
    }

    public NavigableSet<K> tailSet(K k, boolean z) {
        return ((NavigableMap) this.map).tailMap(k, z).navigableKeySet();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: map  reason: collision with other method in class */
    public SortedMap m136map() {
        return (NavigableMap) this.map;
    }

    public SortedSet<K> headSet(K k) {
        return ((NavigableMap) this.map).headMap(k, false).navigableKeySet();
    }

    public SortedSet<K> subSet(K k, K k2) {
        return ((NavigableMap) this.map).subMap(k, true, k2, false).navigableKeySet();
    }

    public SortedSet<K> tailSet(K k) {
        return ((NavigableMap) this.map).tailMap(k, true).navigableKeySet();
    }
}
