package com.android.voicemail.impl.sms;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;

@TargetApi(26)
public abstract class OmtpMessageSender {
    protected final short applicationPort;
    protected final Context context;
    protected final String destinationNumber;
    protected final PhoneAccountHandle phoneAccountHandle;

    public OmtpMessageSender(Context context2, PhoneAccountHandle phoneAccountHandle2, short s, String str) {
        this.context = context2;
        this.phoneAccountHandle = phoneAccountHandle2;
        this.applicationPort = s;
        this.destinationNumber = str;
    }

    public abstract void requestVvmActivation(PendingIntent pendingIntent);

    public abstract void requestVvmDeactivation(PendingIntent pendingIntent);

    public abstract void requestVvmStatus(PendingIntent pendingIntent);

    /* access modifiers changed from: protected */
    public void sendSms(String str, PendingIntent pendingIntent) {
        String.format("Sending sms '%s' to %s:%d", new Object[]{str, this.destinationNumber, Short.valueOf(this.applicationPort)});
        ((TelephonyManager) this.context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(this.phoneAccountHandle).sendVisualVoicemailSms(this.destinationNumber, this.applicationPort, str, pendingIntent);
    }
}
