package com.android.dialer.simulator;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class SimulatorComponent {

    public interface HasComponent {
    }

    public static SimulatorComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).simulatorComponent();
    }

    public abstract Simulator getSimulator();

    public abstract SimulatorConnectionsBank getSimulatorConnectionsBank();

    public abstract SimulatorEnrichedCall getSimulatorEnrichedCall();
}
