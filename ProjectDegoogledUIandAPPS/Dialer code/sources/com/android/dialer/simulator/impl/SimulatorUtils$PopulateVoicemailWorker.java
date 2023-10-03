package com.android.dialer.simulator.impl;

import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.databasepopulator.VoicemailPopulator;

class SimulatorUtils$PopulateVoicemailWorker implements DialerExecutor.Worker<SimulatorUtils$PopulateDatabaseWorkerInput, Void> {
    /* synthetic */ SimulatorUtils$PopulateVoicemailWorker(SimulatorUtils$1 simulatorUtils$1) {
    }

    public Object doInBackground(Object obj) throws Throwable {
        SimulatorUtils$PopulateDatabaseWorkerInput simulatorUtils$PopulateDatabaseWorkerInput = (SimulatorUtils$PopulateDatabaseWorkerInput) obj;
        VoicemailPopulator.populateVoicemail(simulatorUtils$PopulateDatabaseWorkerInput.context, simulatorUtils$PopulateDatabaseWorkerInput.fastMode);
        return null;
    }
}
