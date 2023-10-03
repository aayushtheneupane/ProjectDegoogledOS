package com.android.messaging.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.messaging.sms.C1025u;
import com.android.messaging.util.C1474sa;

public class StorageStatusReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.DEVICE_STORAGE_LOW".equals(intent.getAction())) {
            C1025u.m2392Ei();
        } else if ("android.intent.action.DEVICE_STORAGE_OK".equals(intent.getAction()) && C1474sa.getDefault().mo8230lk()) {
            C1025u.m2391Di();
        }
    }
}
