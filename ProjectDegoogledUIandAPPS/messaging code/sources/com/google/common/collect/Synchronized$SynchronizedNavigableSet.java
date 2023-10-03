package com.google.common.collect;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

class Synchronized$SynchronizedNavigableSet extends Synchronized$SynchronizedSortedSet implements NavigableSet {
    private static final long serialVersionUID = 0;

    /* renamed from: bO */
    transient NavigableSet f2503bO;

    Synchronized$SynchronizedNavigableSet(NavigableSet navigableSet, Object obj) {
        super(navigableSet, obj);
    }

    public Object ceiling(Object obj) {
        Object ceiling;
        synchronized (this.mutex) {
            ceiling = m4512ml().ceiling(obj);
        }
        return ceiling;
    }

    public Iterator descendingIterator() {
        return m4512ml().descendingIterator();
    }

    public NavigableSet descendingSet() {
        synchronized (this.mutex) {
            if (this.f2503bO == null) {
                Synchronized$SynchronizedNavigableSet synchronized$SynchronizedNavigableSet = new Synchronized$SynchronizedNavigableSet(m4512ml().descendingSet(), this.mutex);
                this.f2503bO = synchronized$SynchronizedNavigableSet;
                return synchronized$SynchronizedNavigableSet;
            }
            NavigableSet navigableSet = this.f2503bO;
            return navigableSet;
        }
    }

    public Object floor(Object obj) {
        Object floor;
        synchronized (this.mutex) {
            floor = m4512ml().floor(obj);
        }
        return floor;
    }

    public NavigableSet headSet(Object obj, boolean z) {
        Synchronized$SynchronizedNavigableSet synchronized$SynchronizedNavigableSet;
        synchronized (this.mutex) {
            synchronized$SynchronizedNavigableSet = new Synchronized$SynchronizedNavigableSet(m4512ml().headSet(obj, z), this.mutex);
        }
        return synchronized$SynchronizedNavigableSet;
    }

    public Object higher(Object obj) {
        Object higher;
        synchronized (this.mutex) {
            higher = m4512ml().higher(obj);
        }
        return higher;
    }

    public Object lower(Object obj) {
        Object lower;
        synchronized (this.mutex) {
            lower = m4512ml().lower(obj);
        }
        return lower;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ml */
    public NavigableSet m4510ml() {
        return (NavigableSet) this.delegate;
    }

    public Object pollFirst() {
        Object pollFirst;
        synchronized (this.mutex) {
            pollFirst = m4512ml().pollFirst();
        }
        return pollFirst;
    }

    public Object pollLast() {
        Object pollLast;
        synchronized (this.mutex) {
            pollLast = m4512ml().pollLast();
        }
        return pollLast;
    }

    public NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        Synchronized$SynchronizedNavigableSet synchronized$SynchronizedNavigableSet;
        synchronized (this.mutex) {
            synchronized$SynchronizedNavigableSet = new Synchronized$SynchronizedNavigableSet(m4512ml().subSet(obj, z, obj2, z2), this.mutex);
        }
        return synchronized$SynchronizedNavigableSet;
    }

    public NavigableSet tailSet(Object obj, boolean z) {
        Synchronized$SynchronizedNavigableSet synchronized$SynchronizedNavigableSet;
        synchronized (this.mutex) {
            synchronized$SynchronizedNavigableSet = new Synchronized$SynchronizedNavigableSet(m4512ml().tailSet(obj, z), this.mutex);
        }
        return synchronized$SynchronizedNavigableSet;
    }

    public SortedSet headSet(Object obj) {
        return headSet(obj, false);
    }

    public SortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    public SortedSet tailSet(Object obj) {
        return tailSet(obj, true);
    }
}
