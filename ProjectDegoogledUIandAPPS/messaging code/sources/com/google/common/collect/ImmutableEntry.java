package com.google.common.collect;

import java.io.Serializable;

class ImmutableEntry extends C1687q implements Serializable {
    private static final long serialVersionUID = 0;
    final Object key;
    final Object value;

    ImmutableEntry(Object obj, Object obj2) {
        this.key = obj;
        this.value = obj2;
    }

    public final Object getKey() {
        return this.key;
    }

    public final Object getValue() {
        return this.value;
    }

    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
