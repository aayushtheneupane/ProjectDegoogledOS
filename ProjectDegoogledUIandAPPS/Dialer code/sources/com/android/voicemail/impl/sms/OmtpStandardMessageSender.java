package com.android.voicemail.impl.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.telecom.PhoneAccountHandle;
import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class OmtpStandardMessageSender extends OmtpMessageSender {
    private final String clientPrefix;
    private final String clientType;
    private final String protocolVersion;

    public OmtpStandardMessageSender(Context context, PhoneAccountHandle phoneAccountHandle, short s, String str, String str2, String str3, String str4) {
        super(context, phoneAccountHandle, s, str);
        this.clientType = str2;
        this.protocolVersion = str3;
        this.clientPrefix = str4;
    }

    private void appendApplicationPort(StringBuilder sb) {
        sb.append(";");
        Short valueOf = Short.valueOf(this.applicationPort);
        sb.append("pt");
        sb.append("=");
        sb.append(valueOf);
    }

    private void appendProtocolVersionAndClientType(StringBuilder sb) {
        sb.append(":");
        String str = this.protocolVersion;
        sb.append("pv");
        sb.append("=");
        sb.append(str);
        sb.append(";");
        String str2 = this.clientType;
        sb.append("ct");
        sb.append("=");
        sb.append(str2);
    }

    public void requestVvmActivation(PendingIntent pendingIntent) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Activate");
        appendProtocolVersionAndClientType(outline13);
        if (TextUtils.equals(this.protocolVersion, "12") || TextUtils.equals(this.protocolVersion, "13")) {
            appendApplicationPort(outline13);
            outline13.append(";");
            outline13.append(this.clientPrefix);
        }
        sendSms(outline13.toString(), pendingIntent);
    }

    public void requestVvmDeactivation(PendingIntent pendingIntent) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Deactivate");
        appendProtocolVersionAndClientType(outline13);
        sendSms(outline13.toString(), pendingIntent);
    }

    public void requestVvmStatus(PendingIntent pendingIntent) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Status");
        if (TextUtils.equals(this.protocolVersion, "13")) {
            appendProtocolVersionAndClientType(outline13);
            appendApplicationPort(outline13);
            outline13.append(";");
            outline13.append(this.clientPrefix);
        }
        sendSms(outline13.toString(), pendingIntent);
    }
}
