package com.android.messaging.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class SmsDeliverReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        SmsReceiver.m2321a(context, intent);
    }
}
