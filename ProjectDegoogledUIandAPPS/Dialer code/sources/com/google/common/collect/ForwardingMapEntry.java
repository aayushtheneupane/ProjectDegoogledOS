package com.google.common.collect;

import java.util.Map;

public abstract class ForwardingMapEntry<K, V> extends ForwardingObject implements Map.Entry<K, V> {
    protected ForwardingMapEntry() {
    }

    public boolean equals(Object obj) {
        return r2.equals(obj);
    }

    public K getKey() {
        return r2.getKey();
    }

    public V getValue() {
        return r2.getValue();
    }

    public int hashCode() {
        return r2.hashCode();
    }
}
