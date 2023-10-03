package com.android.dialer.simulator.impl;

import dagger.internal.Factory;

public enum SimulatorImpl_Factory implements Factory<SimulatorImpl> {
    INSTANCE;

    public Object get() {
        return new SimulatorImpl();
    }
}
