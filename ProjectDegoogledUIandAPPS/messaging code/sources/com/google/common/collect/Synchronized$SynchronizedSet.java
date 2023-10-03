package com.google.common.collect;

import java.util.Set;

class Synchronized$SynchronizedSet extends Synchronized$SynchronizedCollection implements Set {
    private static final long serialVersionUID = 0;

    Synchronized$SynchronizedSet(Set set, Object obj) {
        super(set, obj, (C1680nb) null);
    }

    public boolean equals(Object obj) {
        boolean equals;
        if (obj == this) {
            return true;
        }
        synchronized (this.mutex) {
            equals = mo9001ml().equals(obj);
        }
        return equals;
    }

    public int hashCode() {
        int hashCode;
        synchronized (this.mutex) {
            hashCode = mo9001ml().hashCode();
        }
        return hashCode;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ml */
    public Set m4514ml() {
        return (Set) this.delegate;
    }
}
