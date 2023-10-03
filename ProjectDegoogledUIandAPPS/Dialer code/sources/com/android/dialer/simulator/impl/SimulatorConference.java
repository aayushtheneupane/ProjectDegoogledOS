package com.android.dialer.simulator.impl;

import android.telecom.CallAudioState;
import android.telecom.Conference;
import android.telecom.Connection;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.impl.SimulatorConnection;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class SimulatorConference extends Conference implements SimulatorConnection.Listener {
    private final int conferenceType;
    private final List<Simulator.Event> events = new ArrayList();
    private final List<Listener> listeners = new ArrayList();

    public interface Listener {
        void onEvent(SimulatorConference simulatorConference, Simulator.Event event);
    }

    private SimulatorConference(PhoneAccountHandle phoneAccountHandle, int i) {
        super(phoneAccountHandle);
        this.conferenceType = i;
        setActive();
    }

    static SimulatorConference newGsmConference(PhoneAccountHandle phoneAccountHandle) {
        SimulatorConference simulatorConference = new SimulatorConference(phoneAccountHandle, 1);
        simulatorConference.setConnectionCapabilities(195);
        return simulatorConference;
    }

    static SimulatorConference newVoLteConference(PhoneAccountHandle phoneAccountHandle) {
        SimulatorConference simulatorConference = new SimulatorConference(phoneAccountHandle, 2);
        simulatorConference.setConnectionCapabilities(195);
        return simulatorConference;
    }

    public void addListener(Listener listener) {
        List<Listener> list = this.listeners;
        Assert.isNotNull(listener);
        list.add(listener);
    }

    public void onCallAudioStateChanged(CallAudioState callAudioState) {
        LogUtil.enterBlock("SimulatorConference.onCallAudioStateChanged");
        onEvent(new Simulator.Event(9));
    }

    public void onConnectionAdded(Connection connection) {
        LogUtil.enterBlock("SimulatorConference.onConnectionAdded");
        onEvent(new Simulator.Event(10, SimulatorSimCallManager.getConnectionTag(connection), (String) null));
        ((SimulatorConnection) connection).addListener(this);
    }

    public void onDisconnect() {
        LogUtil.enterBlock("SimulatorConference.onDisconnect");
        onEvent(new Simulator.Event(5));
    }

    public void onEvent(SimulatorConnection simulatorConnection, Simulator.Event event) {
        if (this.conferenceType == 1 && event.type == 6 && Connection.stateToString(6).equals(event.data2)) {
            removeConnection(simulatorConnection);
            simulatorConnection.removeListener(this);
            if (getConnections().size() <= 1) {
                setDisconnected(simulatorConnection.getDisconnectCause());
                destroy();
            }
        }
    }

    public void onHold() {
        LogUtil.enterBlock("SimulatorConference.onHold");
        onEvent(new Simulator.Event(3));
    }

    public void onMerge(Connection connection) {
        LogUtil.m9i("SimulatorConference.onMerge", GeneratedOutlineSupport.outline6("connection: ", connection), new Object[0]);
        onEvent(new Simulator.Event(11, SimulatorSimCallManager.getConnectionTag(connection), (String) null));
    }

    public void onPlayDtmfTone(char c) {
        LogUtil.enterBlock("SimulatorConference.onPlayDtmfTone");
        onEvent(new Simulator.Event(7, Character.toString(c), (String) null));
    }

    public void onSeparate(Connection connection) {
        LogUtil.m9i("SimulatorConference.onSeparate", GeneratedOutlineSupport.outline6("connection: ", connection), new Object[0]);
        onEvent(new Simulator.Event(12, SimulatorSimCallManager.getConnectionTag(connection), (String) null));
        if (this.conferenceType == 1 && getConnections().size() == 1) {
            removeConnection(getConnections().get(0));
            destroy();
        }
    }

    public void onSwap() {
        LogUtil.enterBlock("SimulatorConference.onSwap");
        onEvent(new Simulator.Event(13));
    }

    public void onUnhold() {
        LogUtil.enterBlock("SimulatorConference.onUnhold");
        onEvent(new Simulator.Event(4));
    }

    public void onMerge() {
        LogUtil.enterBlock("SimulatorConference.onMerge");
        onEvent(new Simulator.Event(11));
    }

    /* access modifiers changed from: package-private */
    public void onEvent(Simulator.Event event) {
        List<Simulator.Event> list = this.events;
        Assert.isNotNull(event);
        list.add(event);
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).onEvent(this, event);
        }
    }
}
