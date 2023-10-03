package com.android.dialer.simulator.impl;

import com.android.dialer.simulator.SimulatorComponent;

/* renamed from: com.android.dialer.simulator.impl.-$$Lambda$SimulatorConnectionService$Uj4vvZk62ppDg2Gwheey0y9DWJ4  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SimulatorConnectionService$Uj4vvZk62ppDg2Gwheey0y9DWJ4 implements Runnable {
    public static final /* synthetic */ $$Lambda$SimulatorConnectionService$Uj4vvZk62ppDg2Gwheey0y9DWJ4 INSTANCE = new $$Lambda$SimulatorConnectionService$Uj4vvZk62ppDg2Gwheey0y9DWJ4();

    private /* synthetic */ $$Lambda$SimulatorConnectionService$Uj4vvZk62ppDg2Gwheey0y9DWJ4() {
    }

    public final void run() {
        ((SimulatorConnectionsBankImpl) SimulatorComponent.get(SimulatorConnectionService.instance).getSimulatorConnectionsBank()).updateConferenceableConnections();
    }
}
