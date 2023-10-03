package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;

abstract class AbstractNavigableMap<K, V> extends Maps$IteratorBasedAbstractMap<K, V> implements NavigableMap<K, V> {

    private final class DescendingMap extends Maps$DescendingMap<K, V> {
        /* synthetic */ DescendingMap(C08581 r2) {
        }
    }

    AbstractNavigableMap() {
    }

    public Map.Entry<K, V> ceilingEntry(K k) {
        return tailMap(k, true).firstEntry();
    }

    public K ceilingKey(K k) {
        return Collections2.keyOrNull(ceilingEntry(k));
    }

    /* access modifiers changed from: package-private */
    public abstract Iterator<Map.Entry<K, V>> descendingEntryIterator();

    public NavigableSet<K> descendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    public NavigableMap<K, V> descendingMap() {
        return new DescendingMap((C08581) null);
    }

    public Map.Entry<K, V> firstEntry() {
        return (Map.Entry) Collections2.getNext(entryIterator(), null);
    }

    public K firstKey() {
        Map.Entry firstEntry = firstEntry();
        if (firstEntry != null) {
            return firstEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    public Map.Entry<K, V> floorEntry(K k) {
        return headMap(k, true).lastEntry();
    }

    public K floorKey(K k) {
        return Collections2.keyOrNull(floorEntry(k));
    }

    public SortedMap<K, V> headMap(K k) {
        return headMap(k, false);
    }

    public Map.Entry<K, V> higherEntry(K k) {
        return tailMap(k, false).firstEntry();
    }

    public K higherKey(K k) {
        return Collections2.keyOrNull(higherEntry(k));
    }

    public Set<K> keySet() {
        return navigableKeySet();
    }

    public Map.Entry<K, V> lastEntry() {
        return (Map.Entry) Collections2.getNext(descendingEntryIterator(), null);
    }

    public K lastKey() {
        Map.Entry lastEntry = lastEntry();
        if (lastEntry != null) {
            return lastEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    public Map.Entry<K, V> lowerEntry(K k) {
        return headMap(k, false).lastEntry();
    }

    public K lowerKey(K k) {
        return Collections2.keyOrNull(lowerEntry(k));
    }

    public NavigableSet<K> navigableKeySet() {
        return new Maps$NavigableKeySet(this);
    }

    public Map.Entry<K, V> pollFirstEntry() {
        return (Map.Entry) Collections2.pollNext(entryIterator());
    }

    public Map.Entry<K, V> pollLastEntry() {
        return (Map.Entry) Collections2.pollNext(descendingEntryIterator());
    }

    public SortedMap<K, V> subMap(K k, K k2) {
        return subMap(k, true, k2, false);
    }

    public SortedMap<K, V> tailMap(K k) {
        return tailMap(k, true);
    }
}
