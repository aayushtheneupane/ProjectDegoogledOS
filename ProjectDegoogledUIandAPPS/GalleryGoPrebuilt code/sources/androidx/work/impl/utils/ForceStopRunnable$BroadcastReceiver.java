package androidx.work.impl.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: PG */
public class ForceStopRunnable$BroadcastReceiver extends BroadcastReceiver {
    static {
        iol.m14236b("ForceStopRunnable$Rcvr");
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null && "ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
            iol.m14231a();
            ama.m757a(context);
        }
    }
}
