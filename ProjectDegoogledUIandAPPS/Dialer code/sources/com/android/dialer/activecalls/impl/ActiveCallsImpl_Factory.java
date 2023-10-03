package com.android.dialer.activecalls.impl;

import dagger.internal.Factory;

public enum ActiveCallsImpl_Factory implements Factory<ActiveCallsImpl> {
    INSTANCE;

    public Object get() {
        return new ActiveCallsImpl();
    }
}
