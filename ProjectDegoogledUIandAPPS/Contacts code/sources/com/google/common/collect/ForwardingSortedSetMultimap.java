package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedSet;

public abstract class ForwardingSortedSetMultimap<K, V> extends ForwardingSetMultimap<K, V> implements SortedSetMultimap<K, V> {
    /* access modifiers changed from: protected */
    public abstract SortedSetMultimap<K, V> delegate();

    protected ForwardingSortedSetMultimap() {
    }

    public SortedSet<V> get(K k) {
        return delegate().get(k);
    }

    public SortedSet<V> removeAll(Object obj) {
        return delegate().removeAll(obj);
    }

    public SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return delegate().replaceValues(k, iterable);
    }

    public Comparator<? super V> valueComparator() {
        return delegate().valueComparator();
    }
}
