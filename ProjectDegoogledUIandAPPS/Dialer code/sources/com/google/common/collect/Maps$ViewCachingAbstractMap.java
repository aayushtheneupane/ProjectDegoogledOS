package com.google.common.collect;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

abstract class Maps$ViewCachingAbstractMap<K, V> extends AbstractMap<K, V> {
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient Set<K> keySet;
    private transient Collection<V> values;

    Maps$ViewCachingAbstractMap() {
    }

    /* access modifiers changed from: package-private */
    public abstract Set<Map.Entry<K, V>> createEntrySet();

    /* access modifiers changed from: package-private */
    public Set<K> createKeySet() {
        return new Maps$KeySet(this);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createValues() {
        return new Maps$Values(this);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        Set<K> createKeySet = createKeySet();
        this.keySet = createKeySet;
        return createKeySet;
    }

    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Collection<V> createValues = createValues();
        this.values = createValues;
        return createValues;
    }
}
