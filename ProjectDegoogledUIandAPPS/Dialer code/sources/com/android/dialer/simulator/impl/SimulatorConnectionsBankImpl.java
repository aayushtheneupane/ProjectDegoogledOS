package com.android.dialer.simulator.impl;

import android.content.Context;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import com.android.dialer.common.LogUtil;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.SimulatorConnectionsBank;
import com.android.dialer.simulator.impl.SimulatorConference;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimulatorConnectionsBankImpl implements SimulatorConnectionsBank, SimulatorConference.Listener {
    private final List<String> connectionTags = new ArrayList();

    public void add(Connection connection) {
        this.connectionTags.add(SimulatorSimCallManager.getConnectionTag(connection));
    }

    public void disconnectAllConnections() {
        for (Connection disconnected : SimulatorConnectionService.getInstance().getAllConnections()) {
            disconnected.setDisconnected(new DisconnectCause(2));
        }
    }

    public List<String> getConnectionTags() {
        return this.connectionTags;
    }

    public boolean isSimulatorConnection(Connection connection) {
        for (String str : this.connectionTags) {
            if (connection.getExtras().getBoolean(str)) {
                return true;
            }
        }
        return false;
    }

    public void mergeAllConnections(int i, Context context) {
        SimulatorConference simulatorConference;
        if (i == 1) {
            simulatorConference = SimulatorConference.newGsmConference(SimulatorSimCallManager.getSystemPhoneAccountHandle(context));
        } else {
            simulatorConference = i == 2 ? SimulatorConference.newVoLteConference(SimulatorSimCallManager.getSystemPhoneAccountHandle(context)) : null;
        }
        for (Connection addConnection : SimulatorConnectionService.getInstance().getAllConnections()) {
            simulatorConference.addConnection(addConnection);
        }
        simulatorConference.addListener(this);
        SimulatorConnectionService.getInstance().addConference(simulatorConference);
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

    public void remove(Connection connection) {
        this.connectionTags.remove(SimulatorSimCallManager.getConnectionTag(connection));
    }

    public void updateConferenceableConnections() {
        LogUtil.enterBlock("SimulatorConferenceCreator.updateConferenceableConnections");
        for (String findConnectionByTag : this.connectionTags) {
            SimulatorConnection findConnectionByTag2 = SimulatorSimCallManager.findConnectionByTag(findConnectionByTag);
            ArrayList arrayList = new ArrayList();
            for (String findConnectionByTag3 : this.connectionTags) {
                SimulatorConnection findConnectionByTag4 = SimulatorSimCallManager.findConnectionByTag(findConnectionByTag3);
                arrayList.add(findConnectionByTag4);
                if (findConnectionByTag4.getConference() != null && !arrayList.contains(findConnectionByTag4.getConference())) {
                    arrayList.add(findConnectionByTag4.getConference());
                }
            }
            arrayList.remove(findConnectionByTag2);
            arrayList.remove(findConnectionByTag2.getConference());
            findConnectionByTag2.setConferenceables(arrayList);
        }
    }
}
