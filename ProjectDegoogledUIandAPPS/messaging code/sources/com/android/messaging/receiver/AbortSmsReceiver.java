package com.android.messaging.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;

public final class AbortSmsReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (!C1464na.m3757Xj() && C1474sa.getDefault().mo8230lk()) {
            if (!(SmsReceiver.getMessagesFromIntent(intent) == null)) {
                abortBroadcast();
            }
        }
    }
}
