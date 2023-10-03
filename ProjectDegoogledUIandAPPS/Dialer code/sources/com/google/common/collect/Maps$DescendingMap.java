package com.google.common.collect;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

abstract class Maps$DescendingMap<K, V> extends ForwardingMap<K, V> implements NavigableMap<K, V> {
    private transient Comparator<? super K> comparator;
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient NavigableSet<K> navigableKeySet;

    Maps$DescendingMap() {
    }

    public Map.Entry<K, V> ceilingEntry(K k) {
        return AbstractNavigableMap.this.floorEntry(k);
    }

    public K ceilingKey(K k) {
        return AbstractNavigableMap.this.floorKey(k);
    }

    public Comparator<? super K> comparator() {
        Comparator<? super K> comparator2 = this.comparator;
        if (comparator2 != null) {
            return comparator2;
        }
        Comparator comparator3 = AbstractNavigableMap.this.comparator();
        if (comparator3 == null) {
            comparator3 = Ordering.natural();
        }
        Ordering reverse = Ordering.from(comparator3).reverse();
        this.comparator = reverse;
        return reverse;
    }

    /* access modifiers changed from: protected */
    public Object delegate() {
        return AbstractNavigableMap.this;
    }

    public NavigableSet<K> descendingKeySet() {
        return AbstractNavigableMap.this.navigableKeySet();
    }

    public NavigableMap<K, V> descendingMap() {
        return AbstractNavigableMap.this;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        AnonymousClass1EntrySetImpl r0 = new Maps$EntrySet<K, V>() {
            public Iterator<Map.Entry<K, V>> iterator() {
                return AbstractNavigableMap.this.descendingEntryIterator();
            }

            /* access modifiers changed from: package-private */
            public Map<K, V> map() {
                return Maps$DescendingMap.this;
            }
        };
        this.entrySet = r0;
        return r0;
    }

    public Map.Entry<K, V> firstEntry() {
        return AbstractNavigableMap.this.lastEntry();
    }

    public K firstKey() {
        return AbstractNavigableMap.this.lastKey();
    }

    public Map.Entry<K, V> floorEntry(K k) {
        return AbstractNavigableMap.this.ceilingEntry(k);
    }

    public K floorKey(K k) {
        return AbstractNavigableMap.this.ceilingKey(k);
    }

    public NavigableMap<K, V> headMap(K k, boolean z) {
        return AbstractNavigableMap.this.tailMap(k, z).descendingMap();
    }

    public Map.Entry<K, V> higherEntry(K k) {
        return AbstractNavigableMap.this.lowerEntry(k);
    }

    public K higherKey(K k) {
        return AbstractNavigableMap.this.lowerKey(k);
    }

    public Set<K> keySet() {
        NavigableSet<K> navigableSet = this.navigableKeySet;
        if (navigableSet != null) {
            return navigableSet;
        }
        Maps$NavigableKeySet maps$NavigableKeySet = new Maps$NavigableKeySet(this);
        this.navigableKeySet = maps$NavigableKeySet;
        return maps$NavigableKeySet;
    }

    public Map.Entry<K, V> lastEntry() {
        return AbstractNavigableMap.this.firstEntry();
    }

    public K lastKey() {
        return AbstractNavigableMap.this.firstKey();
    }

    public Map.Entry<K, V> lowerEntry(K k) {
        return AbstractNavigableMap.this.higherEntry(k);
    }

    public K lowerKey(K k) {
        return AbstractNavigableMap.this.higherKey(k);
    }

    public NavigableSet<K> navigableKeySet() {
        NavigableSet<K> navigableSet = this.navigableKeySet;
        if (navigableSet != null) {
            return navigableSet;
        }
        Maps$NavigableKeySet maps$NavigableKeySet = new Maps$NavigableKeySet(this);
        this.navigableKeySet = maps$NavigableKeySet;
        return maps$NavigableKeySet;
    }

    public Map.Entry<K, V> pollFirstEntry() {
        return AbstractNavigableMap.this.pollLastEntry();
    }

    public Map.Entry<K, V> pollLastEntry() {
        return AbstractNavigableMap.this.pollFirstEntry();
    }

    public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
        return AbstractNavigableMap.this.subMap(k2, z2, k, z).descendingMap();
    }

    public NavigableMap<K, V> tailMap(K k, boolean z) {
        return AbstractNavigableMap.this.headMap(k, z).descendingMap();
    }

    public String toString() {
        return standardToString();
    }

    public Collection<V> values() {
        return new Maps$Values(this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: delegate  reason: collision with other method in class */
    public final Map<K, V> m133delegate() {
        return AbstractNavigableMap.this;
    }

    public SortedMap<K, V> headMap(K k) {
        return AbstractNavigableMap.this.tailMap(k, false).descendingMap();
    }

    public SortedMap<K, V> subMap(K k, K k2) {
        return AbstractNavigableMap.this.subMap(k2, false, k, true).descendingMap();
    }

    public SortedMap<K, V> tailMap(K k) {
        return AbstractNavigableMap.this.headMap(k, true).descendingMap();
    }
}
