package com.android.dialer.metrics;

import dagger.internal.Factory;

public enum StubMetricsInitializer_Factory implements Factory<StubMetricsInitializer> {
    INSTANCE;

    public Object get() {
        return new StubMetricsInitializer();
    }
}
