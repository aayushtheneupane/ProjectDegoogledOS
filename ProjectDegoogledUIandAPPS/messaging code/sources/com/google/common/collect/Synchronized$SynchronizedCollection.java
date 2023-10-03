package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;

class Synchronized$SynchronizedCollection extends Synchronized$SynchronizedObject implements Collection {
    private static final long serialVersionUID = 0;

    /* synthetic */ Synchronized$SynchronizedCollection(Collection collection, Object obj, C1680nb nbVar) {
        super(collection, obj);
    }

    public boolean add(Object obj) {
        boolean add;
        synchronized (this.mutex) {
            add = mo9001ml().add(obj);
        }
        return add;
    }

    public boolean addAll(Collection collection) {
        boolean addAll;
        synchronized (this.mutex) {
            addAll = mo9001ml().addAll(collection);
        }
        return addAll;
    }

    public void clear() {
        synchronized (this.mutex) {
            mo9001ml().clear();
        }
    }

    public boolean contains(Object obj) {
        boolean contains;
        synchronized (this.mutex) {
            contains = mo9001ml().contains(obj);
        }
        return contains;
    }

    public boolean containsAll(Collection collection) {
        boolean containsAll;
        synchronized (this.mutex) {
            containsAll = mo9001ml().containsAll(collection);
        }
        return containsAll;
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.mutex) {
            isEmpty = mo9001ml().isEmpty();
        }
        return isEmpty;
    }

    public Iterator iterator() {
        return mo9001ml().iterator();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ml */
    public Collection mo9001ml() {
        return (Collection) this.delegate;
    }

    public boolean remove(Object obj) {
        boolean remove;
        synchronized (this.mutex) {
            remove = mo9001ml().remove(obj);
        }
        return remove;
    }

    public boolean removeAll(Collection collection) {
        boolean removeAll;
        synchronized (this.mutex) {
            removeAll = mo9001ml().removeAll(collection);
        }
        return removeAll;
    }

    public boolean retainAll(Collection collection) {
        boolean retainAll;
        synchronized (this.mutex) {
            retainAll = mo9001ml().retainAll(collection);
        }
        return retainAll;
    }

    public int size() {
        int size;
        synchronized (this.mutex) {
            size = mo9001ml().size();
        }
        return size;
    }

    public Object[] toArray() {
        Object[] array;
        synchronized (this.mutex) {
            array = mo9001ml().toArray();
        }
        return array;
    }

    public Object[] toArray(Object[] objArr) {
        Object[] array;
        synchronized (this.mutex) {
            array = mo9001ml().toArray(objArr);
        }
        return array;
    }
}
