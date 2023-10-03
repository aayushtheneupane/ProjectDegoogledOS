package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedMap;

class Synchronized$SynchronizedSortedMap extends Synchronized$SynchronizedMap implements SortedMap {
    private static final long serialVersionUID = 0;

    Synchronized$SynchronizedSortedMap(SortedMap sortedMap, Object obj) {
        super(sortedMap, obj);
    }

    public Comparator comparator() {
        Comparator comparator;
        synchronized (this.mutex) {
            comparator = mo8993ml().comparator();
        }
        return comparator;
    }

    public Object firstKey() {
        Object firstKey;
        synchronized (this.mutex) {
            firstKey = mo8993ml().firstKey();
        }
        return firstKey;
    }

    public SortedMap headMap(Object obj) {
        Synchronized$SynchronizedSortedMap synchronized$SynchronizedSortedMap;
        synchronized (this.mutex) {
            synchronized$SynchronizedSortedMap = new Synchronized$SynchronizedSortedMap(mo8993ml().headMap(obj), this.mutex);
        }
        return synchronized$SynchronizedSortedMap;
    }

    public Object lastKey() {
        Object lastKey;
        synchronized (this.mutex) {
            lastKey = mo8993ml().lastKey();
        }
        return lastKey;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ml */
    public SortedMap m4516ml() {
        return (SortedMap) this.delegate;
    }

    public SortedMap subMap(Object obj, Object obj2) {
        Synchronized$SynchronizedSortedMap synchronized$SynchronizedSortedMap;
        synchronized (this.mutex) {
            synchronized$SynchronizedSortedMap = new Synchronized$SynchronizedSortedMap(mo8993ml().subMap(obj, obj2), this.mutex);
        }
        return synchronized$SynchronizedSortedMap;
    }

    public SortedMap tailMap(Object obj) {
        Synchronized$SynchronizedSortedMap synchronized$SynchronizedSortedMap;
        synchronized (this.mutex) {
            synchronized$SynchronizedSortedMap = new Synchronized$SynchronizedSortedMap(mo8993ml().tailMap(obj), this.mutex);
        }
        return synchronized$SynchronizedSortedMap;
    }
}
