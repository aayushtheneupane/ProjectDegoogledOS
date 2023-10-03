package com.android.dialer.simulator.stub;

import com.android.dialer.simulator.SimulatorEnrichedCall;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class SimulatorEnrichedCallStub implements SimulatorEnrichedCall {
    public ListenableFuture<Void> setupIncomingEnrichedCall(String str) {
        return Futures.immediateFuture(null);
    }

    public ListenableFuture<Void> setupOutgoingEnrichedCall(String str) {
        return Futures.immediateFuture(null);
    }
}
