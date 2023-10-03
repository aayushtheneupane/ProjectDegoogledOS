package com.android.dialer.simulator.impl;

import android.support.p002v7.app.AppCompatActivity;
import android.view.ActionProvider;
import com.android.dialer.buildtype.BuildType;
import com.android.dialer.common.LogUtil;
import com.android.dialer.simulator.Simulator;

final class SimulatorImpl implements Simulator {
    private boolean simulatorMode = false;

    public void disableSimulatorMode() {
        this.simulatorMode = false;
    }

    public void enableSimulatorMode() {
        this.simulatorMode = true;
    }

    public ActionProvider getActionProvider(AppCompatActivity appCompatActivity) {
        return new SimulatorMainPortal(appCompatActivity).getActionProvider();
    }

    public boolean isSimulatorMode() {
        return this.simulatorMode;
    }

    public boolean shouldShow() {
        return BuildType.get() == 1 || LogUtil.isDebugEnabled();
    }
}
