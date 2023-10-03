package com.android.dialer.simulator.impl;

import android.content.Context;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.databasepopulator.BlockedBumberPopulator;
import com.android.dialer.databasepopulator.CallLogPopulator;
import com.android.dialer.databasepopulator.ContactsPopulator;
import com.android.dialer.databasepopulator.VoicemailPopulator;

class SimulatorUtils$CleanDatabaseWorker implements DialerExecutor.Worker<Context, Void> {
    /* synthetic */ SimulatorUtils$CleanDatabaseWorker(SimulatorUtils$1 simulatorUtils$1) {
    }

    public Object doInBackground(Object obj) throws Throwable {
        Context context = (Context) obj;
        ContactsPopulator.deleteAllContacts(context);
        CallLogPopulator.deleteAllCallLog(context);
        VoicemailPopulator.deleteAllVoicemail(context);
        BlockedBumberPopulator.deleteBlockedNumbers(context);
        return null;
    }
}
