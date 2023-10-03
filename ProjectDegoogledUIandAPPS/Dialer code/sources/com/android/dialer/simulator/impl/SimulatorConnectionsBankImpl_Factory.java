package com.android.dialer.simulator.impl;

import dagger.internal.Factory;

public enum SimulatorConnectionsBankImpl_Factory implements Factory<SimulatorConnectionsBankImpl> {
    INSTANCE;

    public Object get() {
        return new SimulatorConnectionsBankImpl();
    }
}
