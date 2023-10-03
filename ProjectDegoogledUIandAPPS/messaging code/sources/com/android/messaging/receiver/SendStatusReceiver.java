package com.android.messaging.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsMessage;
import com.android.messaging.datamodel.action.ProcessDeliveryReportAction;
import com.android.messaging.datamodel.action.ProcessDownloadedMmsAction;
import com.android.messaging.datamodel.action.ProcessSentMessageAction;
import com.android.messaging.sms.C1004C;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1430e;

public class SendStatusReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int resultCode = getResultCode();
        if ("com.android.messaging.receiver.SendStatusReceiver.MESSAGE_SENT".equals(action)) {
            C1004C.m2332a(intent.getData(), resultCode, intent.getIntExtra("errorCode", -1), intent.getIntExtra("partId", -1), intent.getIntExtra("subId", -1));
        } else if ("com.android.messaging.receiver.SendStatusReceiver.MMS_SENT".equals(action)) {
            ProcessSentMessageAction.m1426a(resultCode, intent.getData(), intent.getExtras());
        } else if ("com.android.messaging.receiver.SendStatusReceiver.MMS_DOWNLOADED".equals(action)) {
            ProcessDownloadedMmsAction.m1408a(resultCode, intent.getExtras());
        } else if ("com.android.messaging.receiver.SendStatusReceiver.MESSAGE_DELIVERED".equals(action)) {
            SmsMessage a = C1029y.m2422a(intent);
            Uri data = intent.getData();
            if (a == null) {
                C1430e.m3622e("MessagingApp", "SendStatusReceiver: empty report message");
                return;
            }
            try {
                String stringExtra = intent.getStringExtra("format");
                int status = a.getStatus();
                if ("3gpp2".equals(stringExtra)) {
                    int i = (status >> 24) & 3;
                    int i2 = (status >> 16) & 63;
                    if (i != 0) {
                        if (i != 2 && i == 3) {
                            status = 64;
                        }
                    } else if (i2 == 2) {
                        status = 0;
                    }
                    status = 32;
                }
                ProcessDeliveryReportAction.m1405a(data, status);
            } catch (NullPointerException unused) {
                C1430e.m3622e("MessagingApp", "SendStatusReceiver: NPE inside SmsMessage");
            }
        }
    }
}
