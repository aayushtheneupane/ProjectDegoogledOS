package com.google.common.collect;

import java.util.Map;
import java.util.function.BiConsumer;

final class SingletonImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    transient ImmutableBiMap<V, K> inverse;
    final transient K singleKey;
    final transient V singleValue;

    SingletonImmutableBiMap(K k, V v) {
        Collections2.checkEntryNotNull(k, v);
        this.singleKey = k;
        this.singleValue = v;
    }

    public boolean containsKey(Object obj) {
        return this.singleKey.equals(obj);
    }

    public boolean containsValue(Object obj) {
        return this.singleValue.equals(obj);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return ImmutableSet.m87of(new ImmutableEntry(this.singleKey, this.singleValue));
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> createKeySet() {
        return ImmutableSet.m87of(this.singleKey);
    }

    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        if (biConsumer != null) {
            biConsumer.accept(this.singleKey, this.singleValue);
            return;
        }
        throw new NullPointerException();
    }

    public V get(Object obj) {
        if (this.singleKey.equals(obj)) {
            return this.singleValue;
        }
        return null;
    }

    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> immutableBiMap = this.inverse;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        SingletonImmutableBiMap singletonImmutableBiMap = new SingletonImmutableBiMap(this.singleValue, this.singleKey, this);
        this.inverse = singletonImmutableBiMap;
        return singletonImmutableBiMap;
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return 1;
    }

    private SingletonImmutableBiMap(K k, V v, ImmutableBiMap<V, K> immutableBiMap) {
        this.singleKey = k;
        this.singleValue = v;
        this.inverse = immutableBiMap;
    }
}
