package com.google.common.collect;

@FunctionalInterface
public interface Maps$EntryTransformer<K, V1, V2> {
    V2 transformEntry(K k, V1 v1);
}
