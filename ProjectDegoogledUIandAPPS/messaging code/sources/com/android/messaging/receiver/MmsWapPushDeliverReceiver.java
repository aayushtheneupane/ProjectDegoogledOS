package com.android.messaging.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.messaging.util.C1474sa;

public class MmsWapPushDeliverReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.provider.Telephony.WAP_PUSH_DELIVER".equals(intent.getAction()) && "application/vnd.wap.mms-message".equals(intent.getType())) {
            MmsWapPushReceiver.m2316a(C1474sa.getDefault().mo8202a(intent, "subscription"), intent.getByteArrayExtra("data"));
        }
    }
}
