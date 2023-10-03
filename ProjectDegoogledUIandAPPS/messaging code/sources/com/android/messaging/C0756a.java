package com.android.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.messaging.sms.C1024t;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.a */
class C0756a extends BroadcastReceiver {
    C0756a() {
    }

    public void onReceive(Context context, Intent intent) {
        C1430e.m3625i("MessagingApp", "Carrier config changed. Reloading MMS config.");
        C1024t.m2367Ci();
    }
}
