package com.android.dialer.simulator.impl;

import android.content.Context;
import android.os.Bundle;
import android.telecom.DisconnectCause;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.simulator.impl.SimulatorConnectionService;

final class SimulatorMissedCallCreator implements SimulatorConnectionService.Listener {
    private final Context context;

    SimulatorMissedCallCreator(Context context2) {
        Assert.isNotNull(context2);
        this.context = context2;
    }

    private void addNextIncomingCall(int i) {
        if (i <= 0) {
            LogUtil.m9i("SimulatorMissedCallCreator.addNextIncomingCall", "done adding calls", new Object[0]);
            SimulatorConnectionService.removeListener(this);
            return;
        }
        String format = String.format("+%d", new Object[]{Integer.valueOf(i)});
        Bundle bundle = new Bundle();
        bundle.putInt("call_count", i - 1);
        bundle.putBoolean("is_missed_call_connection", true);
        SimulatorSimCallManager.addNewIncomingCall(this.context, format, 1, bundle);
    }

    public /* synthetic */ void lambda$onNewIncomingConnection$0$SimulatorMissedCallCreator(SimulatorConnection simulatorConnection) {
        simulatorConnection.setDisconnected(new DisconnectCause(5));
        addNextIncomingCall(simulatorConnection.getExtras().getInt("call_count"));
    }

    public void onConference(SimulatorConnection simulatorConnection, SimulatorConnection simulatorConnection2) {
    }

    public void onNewIncomingConnection(SimulatorConnection simulatorConnection) {
        if (simulatorConnection.getExtras().getBoolean("is_missed_call_connection")) {
            DialerExecutorModule.postDelayedOnUiThread(new Runnable(simulatorConnection) {
                private final /* synthetic */ SimulatorConnection f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SimulatorMissedCallCreator.this.lambda$onNewIncomingConnection$0$SimulatorMissedCallCreator(this.f$1);
                }
            }, 1000);
        }
    }

    public void onNewOutgoingConnection(SimulatorConnection simulatorConnection) {
    }

    public void start(int i) {
        SimulatorConnectionService.addListener(this);
        addNextIncomingCall(i);
    }
}
