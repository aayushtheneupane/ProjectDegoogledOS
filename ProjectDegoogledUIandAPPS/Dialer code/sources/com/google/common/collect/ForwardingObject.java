package com.google.common.collect;

public abstract class ForwardingObject {
    protected ForwardingObject() {
    }

    /* access modifiers changed from: protected */
    public abstract Object delegate();

    public String toString() {
        return delegate().toString();
    }
}
