package com.android.dialer.simulator.impl;

import com.android.dialer.simulator.SimulatorComponent;

/* renamed from: com.android.dialer.simulator.impl.-$$Lambda$SimulatorConnectionService$wkvTE-FE_270GHDA44YRUWufKGQ  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SimulatorConnectionService$wkvTEFE_270GHDA44YRUWufKGQ implements Runnable {
    public static final /* synthetic */ $$Lambda$SimulatorConnectionService$wkvTEFE_270GHDA44YRUWufKGQ INSTANCE = new $$Lambda$SimulatorConnectionService$wkvTEFE_270GHDA44YRUWufKGQ();

    private /* synthetic */ $$Lambda$SimulatorConnectionService$wkvTEFE_270GHDA44YRUWufKGQ() {
    }

    public final void run() {
        ((SimulatorConnectionsBankImpl) SimulatorComponent.get(SimulatorConnectionService.instance).getSimulatorConnectionsBank()).updateConferenceableConnections();
    }
}
