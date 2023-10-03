package com.google.common.collect;

import java.io.ObjectOutputStream;
import java.io.Serializable;

class Synchronized$SynchronizedObject implements Serializable {
    private static final long serialVersionUID = 0;
    final Object delegate;
    final Object mutex;

    Synchronized$SynchronizedObject(Object obj, Object obj2) {
        if (obj != null) {
            this.delegate = obj;
            this.mutex = obj2 == null ? this : obj2;
            return;
        }
        throw new NullPointerException();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        synchronized (this.mutex) {
            objectOutputStream.defaultWriteObject();
        }
    }

    public String toString() {
        String obj;
        synchronized (this.mutex) {
            obj = this.delegate.toString();
        }
        return obj;
    }
}
