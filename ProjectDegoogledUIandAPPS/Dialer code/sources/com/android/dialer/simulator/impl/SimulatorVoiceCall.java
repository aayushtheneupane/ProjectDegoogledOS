package com.android.dialer.simulator.impl;

import android.content.Context;
import android.os.Bundle;
import android.telecom.DisconnectCause;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.SimulatorComponent;
import com.android.dialer.simulator.SimulatorEnrichedCall;
import com.android.dialer.simulator.impl.SimulatorConnection;
import com.android.dialer.simulator.impl.SimulatorConnectionService;
import com.android.dialer.simulator.stub.SimulatorEnrichedCallStub;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class SimulatorVoiceCall implements SimulatorConnectionService.Listener, SimulatorConnection.Listener {
    private String connectionTag;
    private final Context context;
    private final SimulatorEnrichedCall simulatorEnrichedCall;

    SimulatorVoiceCall(Context context2) {
        Assert.isNotNull(context2);
        this.context = context2;
        this.simulatorEnrichedCall = SimulatorComponent.get(context2).getSimulatorEnrichedCall();
        SimulatorConnectionService.addListener(this);
        SimulatorConnectionService.addListener(new SimulatorConferenceCreator(context2, 1));
    }

    /* access modifiers changed from: package-private */
    public void addCustomizedIncomingCall(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("PRESENTATIONCHOICE", i);
        this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, str, 1, bundle);
    }

    /* access modifiers changed from: package-private */
    public void addCustomizedOutgoingCall(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("PRESENTATIONCHOICE", i);
        this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, str, 1, bundle);
    }

    /* access modifiers changed from: package-private */
    public void addNewEmergencyCallBack() {
        this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, "911", 1);
    }

    /* access modifiers changed from: package-private */
    public void addNewIncomingCall() {
        this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, "+44 (0) 20 7031 3000", 1);
    }

    /* access modifiers changed from: package-private */
    public void addNewOutgoingCall() {
        this.connectionTag = SimulatorSimCallManager.addNewOutgoingCall(this.context, "+55-31-2128-6800", 1);
    }

    /* access modifiers changed from: package-private */
    public void addSpamIncomingCall() {
        this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, "+1-661-778-3020", 1);
    }

    /* access modifiers changed from: package-private */
    public void incomingEnrichedCall() {
        ((SimulatorEnrichedCallStub) this.simulatorEnrichedCall).setupIncomingEnrichedCall("+44 (0) 20 7031 3000").addListener(new Runnable() {
            public final void run() {
                SimulatorVoiceCall.this.lambda$incomingEnrichedCall$0$SimulatorVoiceCall();
            }
        }, DialerExecutorComponent.get(this.context).uiExecutor());
    }

    public /* synthetic */ void lambda$addCustomizedIncomingCallWithDialog$2$SimulatorVoiceCall(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("PRESENTATIONCHOICE", i);
        this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, str, 1, bundle);
    }

    public /* synthetic */ void lambda$addCustomizedOutgoingCallWithDialog$3$SimulatorVoiceCall(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("PRESENTATIONCHOICE", i);
        this.connectionTag = SimulatorSimCallManager.addNewOutgoingCall(this.context, str, 1, bundle);
    }

    public /* synthetic */ void lambda$incomingEnrichedCall$0$SimulatorVoiceCall() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("ISENRICHEDCALL", true);
        this.connectionTag = SimulatorSimCallManager.addNewIncomingCall(this.context, "+44 (0) 20 7031 3000", 1, bundle);
    }

    public /* synthetic */ void lambda$outgoingEnrichedCall$1$SimulatorVoiceCall() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("ISENRICHEDCALL", true);
        this.connectionTag = SimulatorSimCallManager.addNewOutgoingCall(this.context, "+55-31-2128-6800", 1, bundle);
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
        } else if (i != 14) {
            if (i != 1) {
                if (i == 2) {
                    simulatorConnection.setDisconnected(new DisconnectCause(6));
                    return;
                } else if (i == 3) {
                    simulatorConnection.setOnHold();
                    return;
                } else if (i != 4) {
                    if (i != 5) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected event: ");
                        outline13.append(event.type);
                        LogUtil.m9i("SimulatorVoiceCall.onEvent", outline13.toString(), new Object[0]);
                        return;
                    }
                    simulatorConnection.setDisconnected(new DisconnectCause(2));
                    if (simulatorConnection.getExtras().getBoolean("ISENRICHEDCALL")) {
                        ((EnrichedCallManagerStub) EnrichedCallComponent.get(this.context).getEnrichedCallManager()).unregisterStateChangedListener(this.simulatorEnrichedCall);
                        return;
                    }
                    return;
                }
            }
            simulatorConnection.setActive();
        } else {
            simulatorConnection.sendRttInitiationSuccess();
        }
    }

    public void onNewIncomingConnection(SimulatorConnection simulatorConnection) {
        if (simulatorConnection.getExtras().getBoolean(this.connectionTag)) {
            LogUtil.m9i("SimulatorVoiceCall.onNewIncomingConnection", "connection created", new Object[0]);
            simulatorConnection.addListener(this);
            simulatorConnection.setConnectionCapabilities(simulatorConnection.getConnectionCapabilities() | 768 | 3072);
        }
    }

    public void onNewOutgoingConnection(SimulatorConnection simulatorConnection) {
        if (simulatorConnection.getExtras().getBoolean(this.connectionTag)) {
            LogUtil.m9i("SimulatorVoiceCall.onNewOutgoingConnection", "connection created", new Object[0]);
            simulatorConnection.addListener(this);
            simulatorConnection.setConnectionCapabilities(simulatorConnection.getConnectionCapabilities() | 768 | 3072);
            DialerExecutorModule.postOnUiThread(new Runnable() {
                public final void run() {
                    SimulatorConnection.this.setActive();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void outgoingEnrichedCall() {
        ((EnrichedCallManagerStub) EnrichedCallComponent.get(this.context).getEnrichedCallManager()).registerStateChangedListener(this.simulatorEnrichedCall);
        ((SimulatorEnrichedCallStub) this.simulatorEnrichedCall).setupOutgoingEnrichedCall("+55-31-2128-6800").addListener(new Runnable() {
            public final void run() {
                SimulatorVoiceCall.this.lambda$outgoingEnrichedCall$1$SimulatorVoiceCall();
            }
        }, DialerExecutorComponent.get(this.context).uiExecutor());
    }
}
