package com.android.dialer.metrics;

import dagger.internal.Factory;

public enum StubMetrics_Factory implements Factory<StubMetrics> {
    INSTANCE;

    public Object get() {
        return new StubMetrics();
    }
}
