package com.android.dialer.simulator.impl;

import android.content.Context;
import android.content.Intent;
import android.telecom.DisconnectCause;
import android.telecom.TelecomManager;
import android.widget.Toast;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.impl.SimulatorConnection;
import com.android.dialer.simulator.impl.SimulatorConnectionService;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class SimulatorVideoCall implements SimulatorConnectionService.Listener, SimulatorConnection.Listener {
    private String connectionTag;
    private final Context context;
    private final int initialVideoCapability = 3840;
    private final int initialVideoState;

    SimulatorVideoCall(Context context2, int i) {
        Assert.isNotNull(context2);
        this.context = context2;
        this.initialVideoState = i;
        SimulatorConnectionService.addListener(this);
    }

    private boolean isVideoAccountEnabled() {
        SimulatorSimCallManager.register(this.context);
        return ((TelecomManager) this.context.getSystemService(TelecomManager.class)).getPhoneAccount(SimulatorSimCallManager.getVideoProviderHandle(this.context)).isEnabled();
    }

    private void showVideoAccountSettings() {
        this.context.startActivity(new Intent("android.telecom.action.CHANGE_PHONE_ACCOUNTS"));
        Toast.makeText(this.context, "Please enable simulator video provider", 1).show();
    }

    /* access modifiers changed from: package-private */
    public void addNewIncomingCall() {
        if (!isVideoAccountEnabled()) {
            showVideoAccountSettings();
        } else {
            this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, "+44 (0) 20 7031 3000", 2);
        }
    }

    /* access modifiers changed from: package-private */
    public void addNewOutgoingCall() {
        if (!isVideoAccountEnabled()) {
            showVideoAccountSettings();
        } else {
            this.connectionTag = SimulatorSimCallManager.addNewOutgoingCall(this.context, "+44 (0) 20 7031 3000", 2);
        }
    }

    public void onConference(SimulatorConnection simulatorConnection, SimulatorConnection simulatorConnection2) {
    }

    public void onEvent(SimulatorConnection simulatorConnection, Simulator.Event event) {
        int i = event.type;
        if (i == -1) {
            throw new IllegalStateException();
        } else if (i == 8) {
            DialerExecutorModule.postDelayedOnUiThread(new Runnable(event) {
                private final /* synthetic */ Simulator.Event f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SimulatorConnection.this.handleSessionModifyRequest(this.f$1);
                }
            }, 2000);
        } else if (i == 1) {
            simulatorConnection.setVideoState(Integer.parseInt(event.data1));
            simulatorConnection.setActive();
        } else if (i == 2) {
            simulatorConnection.setDisconnected(new DisconnectCause(6));
        } else if (i == 3) {
            simulatorConnection.setOnHold();
        } else if (i == 4) {
            simulatorConnection.setActive();
        } else if (i != 5) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected event: ");
            outline13.append(event.type);
            LogUtil.m9i("SimulatorVideoCall.onEvent", outline13.toString(), new Object[0]);
        } else {
            simulatorConnection.setDisconnected(new DisconnectCause(2));
        }
    }

    public void onNewIncomingConnection(SimulatorConnection simulatorConnection) {
        if (simulatorConnection.getExtras().getBoolean(this.connectionTag)) {
            LogUtil.m9i("SimulatorVideoCall.onNewIncomingConnection", "connection created", new Object[0]);
            simulatorConnection.addListener(this);
            simulatorConnection.setConnectionCapabilities(simulatorConnection.getConnectionCapabilities() | this.initialVideoCapability);
            simulatorConnection.setVideoState(this.initialVideoState);
        }
    }

    public void onNewOutgoingConnection(SimulatorConnection simulatorConnection) {
        if (simulatorConnection.getExtras().getBoolean(this.connectionTag)) {
            LogUtil.m9i("SimulatorVideoCall.onNewOutgoingConnection", "connection created", new Object[0]);
            simulatorConnection.addListener(this);
            simulatorConnection.setConnectionCapabilities(simulatorConnection.getConnectionCapabilities() | this.initialVideoCapability);
            simulatorConnection.setVideoState(this.initialVideoState);
            DialerExecutorModule.postOnUiThread(new Runnable() {
                public final void run() {
                    SimulatorConnection.this.setActive();
                }
            });
        }
    }
}
