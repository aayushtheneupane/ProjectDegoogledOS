package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface SetMultimap<K, V> extends Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    Set<Map.Entry<K, V>> entries();

    boolean equals(Object obj);

    Set<V> get(K k);

    Set<V> removeAll(Object obj);

    Set<V> replaceValues(K k, Iterable<? extends V> iterable);
}
