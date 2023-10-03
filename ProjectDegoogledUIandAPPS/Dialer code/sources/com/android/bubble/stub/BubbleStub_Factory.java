package com.android.bubble.stub;

import dagger.internal.Factory;

public enum BubbleStub_Factory implements Factory<BubbleStub> {
    INSTANCE;

    public Object get() {
        return new BubbleStub();
    }
}
