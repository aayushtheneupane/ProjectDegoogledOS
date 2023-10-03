package com.android.messaging.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.messaging.datamodel.action.ReceiveMmsMessageAction;
import com.android.messaging.util.C1474sa;

public class MmsWapPushReceiver extends BroadcastReceiver {
    /* renamed from: a */
    static void m2316a(int i, byte[] bArr) {
        if (C1474sa.getDefault().mo8230lk()) {
            new ReceiveMmsMessageAction(i, bArr).start();
        }
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.provider.Telephony.WAP_PUSH_RECEIVED".equals(intent.getAction()) && "application/vnd.wap.mms-message".equals(intent.getType()) && C1474sa.getDefault().mo8230lk()) {
            m2316a(C1474sa.getDefault().mo8202a(intent, "subscription"), intent.getByteArrayExtra("data"));
        }
    }
}
