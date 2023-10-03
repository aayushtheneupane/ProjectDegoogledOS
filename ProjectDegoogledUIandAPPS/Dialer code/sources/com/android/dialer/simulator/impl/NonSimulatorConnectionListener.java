package com.android.dialer.simulator.impl;

import android.telecom.DisconnectCause;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.impl.SimulatorConnection;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class NonSimulatorConnectionListener implements SimulatorConnection.Listener {
    NonSimulatorConnectionListener() {
    }

    public void onEvent(SimulatorConnection simulatorConnection, Simulator.Event event) {
        int i = event.type;
        if (i == 8) {
            DialerExecutorModule.postDelayedOnUiThread(new Runnable(event) {
                private final /* synthetic */ Simulator.Event f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SimulatorConnection.this.handleSessionModifyRequest(this.f$1);
                }
            }, 2000);
        } else if (i != 14) {
            switch (i) {
                case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                case 4:
                    simulatorConnection.setActive();
                    return;
                case 2:
                    simulatorConnection.setDisconnected(new DisconnectCause(6));
                    return;
                case 3:
                    simulatorConnection.setOnHold();
                    return;
                case 5:
                    simulatorConnection.setDisconnected(new DisconnectCause(2));
                    return;
                case 6:
                    LogUtil.m9i("SimulatorVoiceCall.onEvent", String.format("state changed from %s to %s ", new Object[]{event.data1, event.data2}), new Object[0]);
                    return;
                default:
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected event: ");
                    outline13.append(event.type);
                    LogUtil.m9i("SimulatorVoiceCall.onEvent", outline13.toString(), new Object[0]);
                    throw new IllegalStateException();
            }
        } else {
            simulatorConnection.sendRttInitiationSuccess();
        }
    }
}
