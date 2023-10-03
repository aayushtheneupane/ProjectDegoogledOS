package com.android.voicemail.impl.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.telecom.PhoneAccountHandle;

public class Vvm3MessageSender extends OmtpMessageSender {
    public Vvm3MessageSender(Context context, PhoneAccountHandle phoneAccountHandle, short s, String str) {
        super(context, phoneAccountHandle, s, str);
    }

    public void requestVvmActivation(PendingIntent pendingIntent) {
        sendSms("STATUS", pendingIntent);
    }

    public void requestVvmDeactivation(PendingIntent pendingIntent) {
    }

    public void requestVvmStatus(PendingIntent pendingIntent) {
        sendSms("STATUS", pendingIntent);
    }
}
