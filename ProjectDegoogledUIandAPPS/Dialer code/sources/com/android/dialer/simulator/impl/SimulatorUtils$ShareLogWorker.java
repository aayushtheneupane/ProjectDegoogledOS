package com.android.dialer.simulator.impl;

import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.persistentlog.PersistentLogger;

class SimulatorUtils$ShareLogWorker implements DialerExecutor.Worker<Void, String> {
    /* synthetic */ SimulatorUtils$ShareLogWorker(SimulatorUtils$1 simulatorUtils$1) {
    }

    public Object doInBackground(Object obj) throws Throwable {
        Void voidR = (Void) obj;
        return PersistentLogger.dumpLogToString();
    }
}
