package com.android.dialer.util;

import android.util.LruCache;
import java.util.concurrent.atomic.AtomicInteger;

public class ExpirableCache<K, V> {
    private LruCache<K, CachedValue<V>> cache;
    private final AtomicInteger generation = new AtomicInteger(0);

    public interface CachedValue<V> {
        V getValue();

        boolean isExpired();
    }

    private static class GenerationalCachedValue<V> implements CachedValue<V> {
        private final AtomicInteger cacheGeneration;
        private final int generation = this.cacheGeneration.get();
        public final V value;

        public GenerationalCachedValue(V v, AtomicInteger atomicInteger) {
            this.value = v;
            this.cacheGeneration = atomicInteger;
        }

        public V getValue() {
            return this.value;
        }

        public boolean isExpired() {
            return this.generation != this.cacheGeneration.get();
        }
    }

    private ExpirableCache(LruCache<K, CachedValue<V>> lruCache) {
        this.cache = lruCache;
    }

    public static <K, V> ExpirableCache<K, V> create(int i) {
        return new ExpirableCache<>(new LruCache(i));
    }

    public void expireAll() {
        this.generation.incrementAndGet();
    }

    public CachedValue<V> getCachedValue(K k) {
        return this.cache.get(k);
    }

    public V getPossiblyExpired(K k) {
        CachedValue cachedValue = getCachedValue(k);
        if (cachedValue == null) {
            return null;
        }
        return ((GenerationalCachedValue) cachedValue).getValue();
    }

    public void put(K k, V v) {
        this.cache.put(k, new GenerationalCachedValue(v, this.generation));
    }
}
