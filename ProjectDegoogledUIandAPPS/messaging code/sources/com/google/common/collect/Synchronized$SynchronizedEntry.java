package com.google.common.collect;

import java.util.Map;

class Synchronized$SynchronizedEntry extends Synchronized$SynchronizedObject implements Map.Entry {
    private static final long serialVersionUID = 0;

    Synchronized$SynchronizedEntry(Map.Entry entry, Object obj) {
        super(entry, obj);
    }

    public boolean equals(Object obj) {
        boolean equals;
        synchronized (this.mutex) {
            equals = mo9012ml().equals(obj);
        }
        return equals;
    }

    public Object getKey() {
        Object key;
        synchronized (this.mutex) {
            key = mo9012ml().getKey();
        }
        return key;
    }

    public Object getValue() {
        Object value;
        synchronized (this.mutex) {
            value = mo9012ml().getValue();
        }
        return value;
    }

    public int hashCode() {
        int hashCode;
        synchronized (this.mutex) {
            hashCode = mo9012ml().hashCode();
        }
        return hashCode;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ml */
    public Map.Entry mo9012ml() {
        return (Map.Entry) this.delegate;
    }

    public Object setValue(Object obj) {
        Object value;
        synchronized (this.mutex) {
            value = mo9012ml().setValue(obj);
        }
        return value;
    }
}
