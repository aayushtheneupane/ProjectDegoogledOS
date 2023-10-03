package com.android.dialer.simulator.impl;

import android.content.Context;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.preferredsim.PreferredSimFallbackContract;

class SimulatorUtils$ClearPreferredSimWorker implements DialerExecutor.Worker<Context, Void> {
    /* synthetic */ SimulatorUtils$ClearPreferredSimWorker(SimulatorUtils$1 simulatorUtils$1) {
    }

    public Object doInBackground(Object obj) throws Throwable {
        ((Context) obj).getContentResolver().delete(PreferredSimFallbackContract.CONTENT_URI, (String) null, (String[]) null);
        return null;
    }
}
