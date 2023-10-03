package com.android.voicemail.impl.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.telecom.PhoneAccountHandle;

public class OmtpCvvmMessageSender extends OmtpMessageSender {
    public OmtpCvvmMessageSender(Context context, PhoneAccountHandle phoneAccountHandle, short s, String str) {
        super(context, phoneAccountHandle, s, str);
    }

    private void sendCvvmMessage(String str, PendingIntent pendingIntent) {
        sendSms(str + ":" + "dt" + "=" + "6", pendingIntent);
    }

    public void requestVvmActivation(PendingIntent pendingIntent) {
        sendCvvmMessage("Activate", pendingIntent);
    }

    public void requestVvmDeactivation(PendingIntent pendingIntent) {
        sendCvvmMessage("Deactivate", pendingIntent);
    }

    public void requestVvmStatus(PendingIntent pendingIntent) {
        sendCvvmMessage("Status", pendingIntent);
    }
}
