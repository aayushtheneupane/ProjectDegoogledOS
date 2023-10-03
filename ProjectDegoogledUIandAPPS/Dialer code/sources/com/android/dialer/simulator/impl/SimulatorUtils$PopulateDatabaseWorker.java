package com.android.dialer.simulator.impl;

import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.databasepopulator.CallLogPopulator;
import com.android.dialer.databasepopulator.ContactsPopulator;
import com.android.dialer.databasepopulator.VoicemailPopulator;

class SimulatorUtils$PopulateDatabaseWorker implements DialerExecutor.Worker<SimulatorUtils$PopulateDatabaseWorkerInput, Void> {
    /* synthetic */ SimulatorUtils$PopulateDatabaseWorker(SimulatorUtils$1 simulatorUtils$1) {
    }

    public Object doInBackground(Object obj) throws Throwable {
        SimulatorUtils$PopulateDatabaseWorkerInput simulatorUtils$PopulateDatabaseWorkerInput = (SimulatorUtils$PopulateDatabaseWorkerInput) obj;
        ContactsPopulator.populateContacts(simulatorUtils$PopulateDatabaseWorkerInput.context, simulatorUtils$PopulateDatabaseWorkerInput.fastMode);
        CallLogPopulator.populateCallLog(simulatorUtils$PopulateDatabaseWorkerInput.context, false, simulatorUtils$PopulateDatabaseWorkerInput.fastMode);
        VoicemailPopulator.populateVoicemail(simulatorUtils$PopulateDatabaseWorkerInput.context, simulatorUtils$PopulateDatabaseWorkerInput.fastMode);
        return null;
    }
}
