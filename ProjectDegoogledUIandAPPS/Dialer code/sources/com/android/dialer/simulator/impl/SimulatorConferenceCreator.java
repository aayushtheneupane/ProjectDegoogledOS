package com.android.dialer.simulator.impl;

import android.content.Context;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.SimulatorComponent;
import com.android.dialer.simulator.SimulatorConnectionsBank;
import com.android.dialer.simulator.impl.SimulatorConference;
import com.android.dialer.simulator.impl.SimulatorConnection;
import com.android.dialer.simulator.impl.SimulatorConnectionService;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

final class SimulatorConferenceCreator implements SimulatorConnectionService.Listener, SimulatorConnection.Listener, SimulatorConference.Listener {
    private final int conferenceType;
    private final Context context;
    private boolean onNewIncomingConnectionEnabled = false;
    private final SimulatorConnectionsBank simulatorConnectionsBank;

    SimulatorConferenceCreator(Context context2, int i) {
        Assert.isNotNull(context2);
        this.context = context2;
        this.conferenceType = i;
        this.simulatorConnectionsBank = SimulatorComponent.get(context2).getSimulatorConnectionsBank();
    }

    private void addNextCall(int i, boolean z) {
        LogUtil.m9i("SimulatorConferenceCreator.addNextIncomingCall", GeneratedOutlineSupport.outline5("callCount: ", i), new Object[0]);
        if (i <= 0) {
            LogUtil.m9i("SimulatorConferenceCreator.addNextCall", "done adding calls", new Object[0]);
            if (z) {
                ((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).disconnectAllConnections();
                addNextCall(((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).getConnectionTags().size(), false);
                return;
            }
            ((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).mergeAllConnections(this.conferenceType, this.context);
            SimulatorConnectionService.removeListener(this);
            return;
        }
        String format = String.format(Locale.US, "+1-650-234%04d", new Object[]{Integer.valueOf(i)});
        Bundle bundle = new Bundle();
        bundle.putInt("call_count", i - 1);
        bundle.putBoolean("reconnect", z);
        if (this.conferenceType == 2) {
            bundle.putBoolean("ISVOLTE", true);
        }
        SimulatorSimCallManager.addNewIncomingCall(this.context, format, 1, bundle);
    }

    public /* synthetic */ void lambda$onNewIncomingConnection$0$SimulatorConferenceCreator(SimulatorConnection simulatorConnection) {
        simulatorConnection.setActive();
        addNextCall(simulatorConnection.getExtras().getInt("call_count"), simulatorConnection.getExtras().getBoolean("reconnect"));
    }

    public void onConference(SimulatorConnection simulatorConnection, SimulatorConnection simulatorConnection2) {
        LogUtil.enterBlock("SimulatorConferenceCreator.onConference");
        if (!((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).isSimulatorConnection(simulatorConnection) || !((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).isSimulatorConnection(simulatorConnection2)) {
            LogUtil.m9i("SimulatorConferenceCreator.onConference", "unknown connections, ignoring", new Object[0]);
        } else if (simulatorConnection.getConference() != null) {
            simulatorConnection.getConference().addConnection(simulatorConnection2);
        } else if (simulatorConnection2.getConference() != null) {
            simulatorConnection2.getConference().addConnection(simulatorConnection);
        } else {
            SimulatorConference newGsmConference = SimulatorConference.newGsmConference(SimulatorSimCallManager.getSystemPhoneAccountHandle(this.context));
            newGsmConference.addConnection(simulatorConnection);
            newGsmConference.addConnection(simulatorConnection2);
            newGsmConference.addListener(this);
            SimulatorConnectionService.getInstance().addConference(newGsmConference);
        }
    }

    public void onEvent(SimulatorConnection simulatorConnection, Simulator.Event event) {
        int i = event.type;
        if (i == -1) {
            throw new IllegalStateException();
        } else if (i == 3) {
            simulatorConnection.setOnHold();
        } else if (i == 4) {
            simulatorConnection.setActive();
        } else if (i != 5) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected conference event: ");
            outline13.append(event.type);
            LogUtil.m9i("SimulatorConferenceCreator.onEvent", outline13.toString(), new Object[0]);
        } else {
            simulatorConnection.setDisconnected(new DisconnectCause(2));
        }
    }

    public void onNewIncomingConnection(SimulatorConnection simulatorConnection) {
        if (this.onNewIncomingConnectionEnabled) {
            if (!((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).isSimulatorConnection(simulatorConnection)) {
                LogUtil.m9i("SimulatorConferenceCreator.onNewOutgoingConnection", "unknown connection", new Object[0]);
                return;
            }
            LogUtil.m9i("SimulatorConferenceCreator.onNewOutgoingConnection", "connection created", new Object[0]);
            simulatorConnection.addListener(this);
            DialerExecutorModule.postDelayedOnUiThread(new Runnable(simulatorConnection) {
                private final /* synthetic */ SimulatorConnection f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SimulatorConferenceCreator.this.lambda$onNewIncomingConnection$0$SimulatorConferenceCreator(this.f$1);
                }
            }, 1000);
        }
    }

    public void onNewOutgoingConnection(SimulatorConnection simulatorConnection) {
    }

    /* access modifiers changed from: package-private */
    public void start(int i) {
        this.onNewIncomingConnectionEnabled = true;
        SimulatorConnectionService.addListener(this);
        int i2 = this.conferenceType;
        if (i2 == 2) {
            addNextCall(i, true);
        } else if (i2 == 1) {
            addNextCall(i, false);
        }
    }

    public void onEvent(SimulatorConference simulatorConference, Simulator.Event event) {
        int i = event.type;
        if (i == 5) {
            Iterator it = new ArrayList(simulatorConference.getConnections()).iterator();
            while (it.hasNext()) {
                ((Connection) it.next()).setDisconnected(new DisconnectCause(2));
            }
            simulatorConference.setDisconnected(new DisconnectCause(2));
        } else if (i == 11) {
            simulatorConference.setConnectionCapabilities(simulatorConference.getConnectionCapabilities() | 8);
        } else if (i != 12) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected conference event: ");
            outline13.append(event.type);
            LogUtil.m9i("SimulatorConferenceCreator.onEvent", outline13.toString(), new Object[0]);
        } else {
            simulatorConference.removeConnection(SimulatorSimCallManager.findConnectionByTag(event.data1));
        }
    }
}
