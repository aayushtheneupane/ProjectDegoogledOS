package com.android.dialer.simulator.impl;

import android.content.Context;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.impl.SimulatorConnection;
import com.android.dialer.simulator.impl.SimulatorConnectionService;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class SimulatorRttCall implements SimulatorConnectionService.Listener, SimulatorConnection.Listener {
    private String connectionTag;
    private final Context context;
    private RttChatBot rttChatBot;

    SimulatorRttCall(Context context2) {
        Assert.isNotNull(context2);
        this.context = context2;
        SimulatorConnectionService.addListener(this);
        SimulatorConnectionService.addListener(new SimulatorConferenceCreator(context2, 1));
    }

    /* access modifiers changed from: package-private */
    public void addNewEmergencyCall() {
        this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, "911", 3);
    }

    /* access modifiers changed from: package-private */
    public void addNewIncomingCall(boolean z) {
        this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, z ? "+1-661-778-3020" : "+44 (0) 20 7031 3000", 3);
    }

    /* access modifiers changed from: package-private */
    public void addNewOutgoingCall() {
        this.connectionTag = SimulatorSimCallManager.addNewOutgoingCall(this.context, "+55-31-2128-6800", 3);
    }

    public void onConference(SimulatorConnection simulatorConnection, SimulatorConnection simulatorConnection2) {
    }

    public void onEvent(SimulatorConnection simulatorConnection, Simulator.Event event) {
        switch (event.type) {
            case -1:
                throw new IllegalStateException();
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
                this.rttChatBot.stop();
                simulatorConnection.setDisconnected(new DisconnectCause(2));
                return;
            case 6:
                if (Connection.stateToString(4).equals(event.data2)) {
                    this.rttChatBot = new RttChatBot(simulatorConnection.getRttTextStream());
                    this.rttChatBot.start();
                    return;
                }
                return;
            case 8:
                DialerExecutorModule.postDelayedOnUiThread(new Runnable(event) {
                    private final /* synthetic */ Simulator.Event f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        SimulatorConnection.this.handleSessionModifyRequest(this.f$1);
                    }
                }, 2000);
                return;
            default:
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected event: ");
                outline13.append(event.type);
                LogUtil.m9i("SimulatorRttCall.onEvent", outline13.toString(), new Object[0]);
                return;
        }
    }

    public void onNewIncomingConnection(SimulatorConnection simulatorConnection) {
        if (simulatorConnection.getExtras().getBoolean(this.connectionTag)) {
            LogUtil.m9i("SimulatorRttCall.onNewIncomingConnection", "connection created", new Object[0]);
            simulatorConnection.addListener(this);
            simulatorConnection.setConnectionProperties(simulatorConnection.getConnectionProperties() | 256);
        }
    }

    public void onNewOutgoingConnection(SimulatorConnection simulatorConnection) {
        if (simulatorConnection.getExtras().getBoolean(this.connectionTag)) {
            LogUtil.m9i("SimulatorRttCall.onNewOutgoingConnection", "connection created", new Object[0]);
            simulatorConnection.addListener(this);
            simulatorConnection.setConnectionProperties(simulatorConnection.getConnectionProperties() | 256);
            DialerExecutorModule.postOnUiThread(new Runnable() {
                public final void run() {
                    SimulatorConnection.this.setActive();
                }
            });
        }
    }
}
