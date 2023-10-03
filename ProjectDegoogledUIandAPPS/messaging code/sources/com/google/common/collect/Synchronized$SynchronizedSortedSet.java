package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedSet;

class Synchronized$SynchronizedSortedSet extends Synchronized$SynchronizedSet implements SortedSet {
    private static final long serialVersionUID = 0;

    Synchronized$SynchronizedSortedSet(SortedSet sortedSet, Object obj) {
        super(sortedSet, obj);
    }

    public Comparator comparator() {
        Comparator comparator;
        synchronized (this.mutex) {
            comparator = m4518ml().comparator();
        }
        return comparator;
    }

    public Object first() {
        Object first;
        synchronized (this.mutex) {
            first = m4518ml().first();
        }
        return first;
    }

    public SortedSet headSet(Object obj) {
        Synchronized$SynchronizedSortedSet synchronized$SynchronizedSortedSet;
        synchronized (this.mutex) {
            synchronized$SynchronizedSortedSet = new Synchronized$SynchronizedSortedSet(m4518ml().headSet(obj), this.mutex);
        }
        return synchronized$SynchronizedSortedSet;
    }

    public Object last() {
        Object last;
        synchronized (this.mutex) {
            last = m4518ml().last();
        }
        return last;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ml */
    public SortedSet m4519ml() {
        return (SortedSet) this.delegate;
    }

    public SortedSet subSet(Object obj, Object obj2) {
        Synchronized$SynchronizedSortedSet synchronized$SynchronizedSortedSet;
        synchronized (this.mutex) {
            synchronized$SynchronizedSortedSet = new Synchronized$SynchronizedSortedSet(m4518ml().subSet(obj, obj2), this.mutex);
        }
        return synchronized$SynchronizedSortedSet;
    }

    public SortedSet tailSet(Object obj) {
        Synchronized$SynchronizedSortedSet synchronized$SynchronizedSortedSet;
        synchronized (this.mutex) {
            synchronized$SynchronizedSortedSet = new Synchronized$SynchronizedSortedSet(m4518ml().tailSet(obj), this.mutex);
        }
        return synchronized$SynchronizedSortedSet;
    }
}
