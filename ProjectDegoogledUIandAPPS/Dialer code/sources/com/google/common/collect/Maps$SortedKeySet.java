package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

class Maps$SortedKeySet<K, V> extends Maps$KeySet<K, V> implements SortedSet<K> {
    Maps$SortedKeySet(SortedMap<K, V> sortedMap) {
        super(sortedMap);
    }

    public Comparator<? super K> comparator() {
        return map().comparator();
    }

    public K first() {
        return map().firstKey();
    }

    public K last() {
        return map().lastKey();
    }

    /* access modifiers changed from: package-private */
    public SortedMap<K, V> map() {
        throw null;
    }
}
