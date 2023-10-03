package com.android.dialer.duo.stub;

import dagger.internal.Factory;

public enum DuoStub_Factory implements Factory<DuoStub> {
    INSTANCE;

    public Object get() {
        return new DuoStub();
    }
}
