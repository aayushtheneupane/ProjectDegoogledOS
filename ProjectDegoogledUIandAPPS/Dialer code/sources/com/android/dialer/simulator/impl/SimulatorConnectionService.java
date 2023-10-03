package com.android.dialer.simulator.impl;

import android.net.Uri;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.ConnectionService;
import android.telecom.PhoneAccountHandle;
import android.widget.Toast;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.simulator.SimulatorComponent;
import com.android.dialer.simulator.SimulatorConnectionsBank;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;

public class SimulatorConnectionService extends ConnectionService {
    private static SimulatorConnectionService instance;
    private static final List<Listener> listeners = new ArrayList();
    private SimulatorConnectionsBank simulatorConnectionsBank;

    public interface Listener {
        void onConference(SimulatorConnection simulatorConnection, SimulatorConnection simulatorConnection2);

        void onNewIncomingConnection(SimulatorConnection simulatorConnection);

        void onNewOutgoingConnection(SimulatorConnection simulatorConnection);
    }

    public static void addListener(Listener listener) {
        List<Listener> list = listeners;
        Assert.isNotNull(listener);
        list.add(listener);
    }

    public static SimulatorConnectionService getInstance() {
        return instance;
    }

    public static void removeListener(Listener listener) {
        List<Listener> list = listeners;
        Assert.isNotNull(listener);
        list.remove(listener);
    }

    public void onConference(Connection connection, Connection connection2) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("connection1: ");
        outline13.append(SimulatorSimCallManager.getConnectionTag(connection));
        outline13.append(", connection2: ");
        outline13.append(SimulatorSimCallManager.getConnectionTag(connection2));
        LogUtil.m9i("SimulatorConnectionService.onConference", outline13.toString(), new Object[0]);
        for (Listener onConference : listeners) {
            onConference.onConference((SimulatorConnection) connection, (SimulatorConnection) connection2);
        }
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        this.simulatorConnectionsBank = SimulatorComponent.get(this).getSimulatorConnectionsBank();
    }

    public Connection onCreateIncomingConnection(PhoneAccountHandle phoneAccountHandle, ConnectionRequest connectionRequest) {
        LogUtil.enterBlock("SimulatorConnectionService.onCreateIncomingConnection");
        if (!SimulatorSimCallManager.isSimulatorConnectionRequest(connectionRequest)) {
            LogUtil.m9i("SimulatorConnectionService.onCreateIncomingConnection", "incoming call not from simulator, unregistering", new Object[0]);
            Toast.makeText(this, "Unregistering simulator, got a real incoming call", 1).show();
            SimulatorSimCallManager.unregister(this);
            return null;
        }
        SimulatorConnection simulatorConnection = new SimulatorConnection(this, connectionRequest);
        simulatorConnection.setAddress(Uri.fromParts("tel", connectionRequest.getExtras().getString("incoming_number"), (String) null), connectionRequest.getExtras().getInt("PRESENTATIONCHOICE", 1));
        simulatorConnection.setRinging();
        ((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).add(simulatorConnection);
        DialerExecutorModule.postOnUiThread($$Lambda$SimulatorConnectionService$Uj4vvZk62ppDg2Gwheey0y9DWJ4.INSTANCE);
        for (Listener onNewIncomingConnection : listeners) {
            onNewIncomingConnection.onNewIncomingConnection(simulatorConnection);
        }
        return simulatorConnection;
    }

    public Connection onCreateOutgoingConnection(PhoneAccountHandle phoneAccountHandle, ConnectionRequest connectionRequest) {
        LogUtil.enterBlock("SimulatorConnectionService.onCreateOutgoingConnection");
        if (((SimulatorImpl) SimulatorComponent.get(this).getSimulator()).isSimulatorMode() || SimulatorSimCallManager.isSimulatorConnectionRequest(connectionRequest)) {
            SimulatorConnection simulatorConnection = new SimulatorConnection(this, connectionRequest);
            if (SimulatorSimCallManager.isSimulatorConnectionRequest(connectionRequest)) {
                ((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).add(simulatorConnection);
                simulatorConnection.setAddress(connectionRequest.getAddress(), connectionRequest.getExtras().getInt("PRESENTATIONCHOICE", 1));
                simulatorConnection.setDialing();
                DialerExecutorModule.postOnUiThread($$Lambda$SimulatorConnectionService$wkvTEFE_270GHDA44YRUWufKGQ.INSTANCE);
                for (Listener onNewOutgoingConnection : listeners) {
                    onNewOutgoingConnection.onNewOutgoingConnection(simulatorConnection);
                }
            } else {
                simulatorConnection.setAddress(connectionRequest.getAddress(), 1);
                Bundle extras = simulatorConnection.getExtras();
                extras.putString("connection_tag", "SimulatorMode");
                simulatorConnection.putExtras(extras);
                ((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).add(simulatorConnection);
                simulatorConnection.addListener(new NonSimulatorConnectionListener());
                simulatorConnection.setDialing();
                DialerExecutorModule.postOnUiThread(new Runnable() {
                    public final void run() {
                        SimulatorConnection.this.setActive();
                    }
                });
            }
            return simulatorConnection;
        }
        LogUtil.m9i("SimulatorConnectionService.onCreateOutgoingConnection", "outgoing call not from simulator, unregistering", new Object[0]);
        Toast.makeText(this, "Unregistering simulator, making a real phone call", 1).show();
        SimulatorSimCallManager.unregister(this);
        return null;
    }

    public void onDestroy() {
        LogUtil.enterBlock("SimulatorConnectionService.onDestroy");
        instance = null;
        this.simulatorConnectionsBank = null;
        super.onDestroy();
    }
}
