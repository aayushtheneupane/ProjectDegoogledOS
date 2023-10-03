package com.android.dialer.simulator.stub;

import dagger.internal.Factory;

public enum SimulatorEnrichedCallStub_Factory implements Factory<SimulatorEnrichedCallStub> {
    INSTANCE;

    public Object get() {
        return new SimulatorEnrichedCallStub();
    }
}
