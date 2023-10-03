package com.google.common.collect;

public interface MapConstraint<K, V> {
    void checkKeyValue(K k, V v);

    String toString();
}
