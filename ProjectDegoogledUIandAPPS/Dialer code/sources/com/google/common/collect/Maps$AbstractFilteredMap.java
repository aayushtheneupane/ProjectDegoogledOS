package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Map;

abstract class Maps$AbstractFilteredMap<K, V> extends Maps$ViewCachingAbstractMap<K, V> {
    final Predicate<? super Map.Entry<K, V>> predicate;
    final Map<K, V> unfiltered;

    Maps$AbstractFilteredMap(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate2) {
        this.unfiltered = map;
        this.predicate = predicate2;
    }

    /* access modifiers changed from: package-private */
    public boolean apply(Object obj, V v) {
        return this.predicate.apply(new ImmutableEntry(obj, v));
    }

    public boolean containsKey(Object obj) {
        if (this.unfiltered.containsKey(obj)) {
            if (this.predicate.apply(new ImmutableEntry(obj, this.unfiltered.get(obj)))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createValues() {
        return new Maps$FilteredMapValues(this, this.unfiltered, this.predicate);
    }

    public V get(Object obj) {
        V v = this.unfiltered.get(obj);
        if (v == null || !this.predicate.apply(new ImmutableEntry(obj, v))) {
            return null;
        }
        return v;
    }

    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    public V put(K k, V v) {
        MoreObjects.checkArgument(this.predicate.apply(new ImmutableEntry(k, v)));
        return this.unfiltered.put(k, v);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            MoreObjects.checkArgument(apply(next.getKey(), next.getValue()));
        }
        this.unfiltered.putAll(map);
    }

    public V remove(Object obj) {
        if (containsKey(obj)) {
            return this.unfiltered.remove(obj);
        }
        return null;
    }
}
