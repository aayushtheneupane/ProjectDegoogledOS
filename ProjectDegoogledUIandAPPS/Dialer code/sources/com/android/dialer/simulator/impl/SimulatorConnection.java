package com.android.dialer.simulator.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.VideoProfile;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.SimulatorComponent;
import com.android.dialer.simulator.SimulatorConnectionsBank;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(28)
public final class SimulatorConnection extends Connection {
    private int currentState = 1;
    private final List<Simulator.Event> events = new ArrayList();
    private final List<Listener> listeners = new ArrayList();
    private RttChatBot rttChatBot;
    private Connection.RttTextStream rttTextStream;
    private final SimulatorConnectionsBank simulatorConnectionsBank;

    public interface Listener {
        void onEvent(SimulatorConnection simulatorConnection, Simulator.Event event);
    }

    SimulatorConnection(Context context, ConnectionRequest connectionRequest) {
        Assert.isNotNull(context);
        Assert.isNotNull(connectionRequest);
        putExtras(connectionRequest.getExtras());
        setConnectionCapabilities(532547);
        if (connectionRequest.getExtras() != null && !connectionRequest.getExtras().getBoolean("ISVOLTE")) {
            setConnectionCapabilities(getConnectionCapabilities() | 4096);
        }
        int i = Build.VERSION.SDK_INT;
        this.rttTextStream = connectionRequest.getRttTextStream();
        setVideoProvider(new SimulatorVideoProvider(context, this));
        this.simulatorConnectionsBank = SimulatorComponent.get(context).getSimulatorConnectionsBank();
    }

    public void addListener(Listener listener) {
        List<Listener> list = this.listeners;
        Assert.isNotNull(listener);
        list.add(listener);
    }

    /* access modifiers changed from: package-private */
    public Connection.RttTextStream getRttTextStream() {
        return this.rttTextStream;
    }

    public void handleRttUpgradeResponse(Connection.RttTextStream rttTextStream2) {
        LogUtil.enterBlock("SimulatorConnection.handleRttUpgradeResponse");
        onEvent(new Simulator.Event(16));
    }

    /* access modifiers changed from: package-private */
    public void handleSessionModifyRequest(Simulator.Event event) {
        VideoProfile videoProfile = new VideoProfile(Integer.parseInt(event.data1));
        VideoProfile videoProfile2 = new VideoProfile(Integer.parseInt(event.data2));
        setVideoState(videoProfile2.getVideoState());
        getVideoProvider().receiveSessionModifyResponse(1, videoProfile, videoProfile2);
    }

    public void onAnswer(int i) {
        LogUtil.enterBlock("SimulatorConnection.onAnswer");
        onEvent(new Simulator.Event(1, Integer.toString(i), (String) null));
    }

    public void onDisconnect() {
        LogUtil.enterBlock("SimulatorConnection.onDisconnect");
        ((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).remove(this);
        onEvent(new Simulator.Event(5));
        this.rttTextStream = null;
        RttChatBot rttChatBot2 = this.rttChatBot;
        if (rttChatBot2 != null) {
            rttChatBot2.stop();
            this.rttChatBot = null;
        }
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

    public void onHold() {
        LogUtil.enterBlock("SimulatorConnection.onHold");
        onEvent(new Simulator.Event(3));
    }

    public void onPlayDtmfTone(char c) {
        LogUtil.enterBlock("SimulatorConnection.onPlayDtmfTone");
        onEvent(new Simulator.Event(7, Character.toString(c), (String) null));
    }

    public void onReject() {
        LogUtil.enterBlock("SimulatorConnection.onReject");
        ((SimulatorConnectionsBankImpl) this.simulatorConnectionsBank).remove(this);
        onEvent(new Simulator.Event(2));
    }

    public void onStartRtt(Connection.RttTextStream rttTextStream2) {
        LogUtil.enterBlock("SimulatorConnection.onStartRtt");
        if (!(this.rttTextStream == null && this.rttChatBot == null)) {
            LogUtil.m8e("SimulatorConnection.onStartRtt", "rttTextStream or rttChatBot is not null!", new Object[0]);
        }
        this.rttTextStream = rttTextStream2;
        this.rttChatBot = new RttChatBot(rttTextStream2);
        this.rttChatBot.start();
        onEvent(new Simulator.Event(14));
    }

    public void onStateChanged(int i) {
        LogUtil.m9i("SimulatorConnection.onStateChanged", "%s -> %s", Connection.stateToString(this.currentState), Connection.stateToString(i));
        int i2 = this.currentState;
        this.currentState = i;
        onEvent(new Simulator.Event(6, Connection.stateToString(i2), Connection.stateToString(i)));
    }

    public void onStopRtt() {
        LogUtil.enterBlock("SimulatorConnection.onStopRtt");
        this.rttChatBot.stop();
        this.rttChatBot = null;
        this.rttTextStream = null;
        onEvent(new Simulator.Event(15));
    }

    public void onUnhold() {
        LogUtil.enterBlock("SimulatorConnection.onUnhold");
        onEvent(new Simulator.Event(4));
    }

    public void removeListener(Listener listener) {
        List<Listener> list = this.listeners;
        Assert.isNotNull(listener);
        list.remove(listener);
    }
}
