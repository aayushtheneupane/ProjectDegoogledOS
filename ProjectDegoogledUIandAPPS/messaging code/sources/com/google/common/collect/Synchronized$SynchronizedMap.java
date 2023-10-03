package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

class Synchronized$SynchronizedMap extends Synchronized$SynchronizedObject implements Map {
    private static final long serialVersionUID = 0;
    transient Set keySet;

    /* renamed from: nN */
    transient Set f2499nN;
    transient Collection values;

    Synchronized$SynchronizedMap(Map map, Object obj) {
        super(map, obj);
    }

    public void clear() {
        synchronized (this.mutex) {
            mo8993ml().clear();
        }
    }

    public boolean containsKey(Object obj) {
        boolean containsKey;
        synchronized (this.mutex) {
            containsKey = mo8993ml().containsKey(obj);
        }
        return containsKey;
    }

    public boolean containsValue(Object obj) {
        boolean containsValue;
        synchronized (this.mutex) {
            containsValue = mo8993ml().containsValue(obj);
        }
        return containsValue;
    }

    public Set entrySet() {
        Set set;
        synchronized (this.mutex) {
            if (this.f2499nN == null) {
                this.f2499nN = new Synchronized$SynchronizedSet(mo8993ml().entrySet(), this.mutex);
            }
            set = this.f2499nN;
        }
        return set;
    }

    public boolean equals(Object obj) {
        boolean equals;
        if (obj == this) {
            return true;
        }
        synchronized (this.mutex) {
            equals = mo8993ml().equals(obj);
        }
        return equals;
    }

    public Object get(Object obj) {
        Object obj2;
        synchronized (this.mutex) {
            obj2 = mo8993ml().get(obj);
        }
        return obj2;
    }

    public int hashCode() {
        int hashCode;
        synchronized (this.mutex) {
            hashCode = mo8993ml().hashCode();
        }
        return hashCode;
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.mutex) {
            isEmpty = mo8993ml().isEmpty();
        }
        return isEmpty;
    }

    public Set keySet() {
        Set set;
        synchronized (this.mutex) {
            if (this.keySet == null) {
                this.keySet = new Synchronized$SynchronizedSet(mo8993ml().keySet(), this.mutex);
            }
            set = this.keySet;
        }
        return set;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ml */
    public Map mo8993ml() {
        return (Map) this.delegate;
    }

    public Object put(Object obj, Object obj2) {
        Object put;
        synchronized (this.mutex) {
            put = mo8993ml().put(obj, obj2);
        }
        return put;
    }

    public void putAll(Map map) {
        synchronized (this.mutex) {
            mo8993ml().putAll(map);
        }
    }

    public Object remove(Object obj) {
        Object remove;
        synchronized (this.mutex) {
            remove = mo8993ml().remove(obj);
        }
        return remove;
    }

    public int size() {
        int size;
        synchronized (this.mutex) {
            size = mo8993ml().size();
        }
        return size;
    }

    public Collection values() {
        Collection collection;
        synchronized (this.mutex) {
            if (this.values == null) {
                this.values = new Synchronized$SynchronizedCollection(mo8993ml().values(), this.mutex, (C1680nb) null);
            }
            collection = this.values;
        }
        return collection;
    }
}
