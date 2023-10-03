package com.android.voicemail.impl.sms;

import android.os.Bundle;
import com.android.dialer.common.PerAccountSharedPreferences;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.VvmLog;

public class StatusMessage {
    private final String clientSmsDestinationNumber;
    private final String imapPassword;
    private final String imapPort;
    private final String imapUserName;
    private final String provisioningStatus;
    private final String serverAddress;
    private final String smtpPassword;
    private final String smtpPort;
    private final String smtpUserName;
    private final String statusReturnCode;
    private final String subscriptionUrl;
    private final String tuiAccessNumber;
    private final String tuiPasswordLength;

    public StatusMessage(Bundle bundle) {
        String string = bundle.getString("st");
        string = string == null ? "" : string;
        if (string.length() >= 2 && string.startsWith("\"") && string.endsWith("\"")) {
            string = string.substring(1, string.length() - 1);
        }
        this.provisioningStatus = string;
        String string2 = bundle.getString("rc");
        this.statusReturnCode = string2 == null ? "" : string2;
        String string3 = bundle.getString("rs");
        this.subscriptionUrl = string3 == null ? "" : string3;
        String string4 = bundle.getString("srv");
        this.serverAddress = string4 == null ? "" : string4;
        String string5 = bundle.getString("tui");
        this.tuiAccessNumber = string5 == null ? "" : string5;
        String string6 = bundle.getString("dn");
        this.clientSmsDestinationNumber = string6 == null ? "" : string6;
        String string7 = bundle.getString("ipt");
        this.imapPort = string7 == null ? "" : string7;
        String string8 = bundle.getString("u");
        this.imapUserName = string8 == null ? "" : string8;
        String string9 = bundle.getString("pw");
        this.imapPassword = string9 == null ? "" : string9;
        String string10 = bundle.getString("spt");
        this.smtpPort = string10 == null ? "" : string10;
        String string11 = bundle.getString("smtp_u");
        this.smtpUserName = string11 == null ? "" : string11;
        String string12 = bundle.getString("smtp_pw");
        this.smtpPassword = string12 == null ? "" : string12;
        String string13 = bundle.getString("pw_len");
        this.tuiPasswordLength = string13 == null ? "" : string13;
    }

    public String getImapUserName() {
        return this.imapUserName;
    }

    public String getProvisioningStatus() {
        return this.provisioningStatus;
    }

    public String getReturnCode() {
        return this.statusReturnCode;
    }

    public PerAccountSharedPreferences.Editor putStatus(PerAccountSharedPreferences.Editor editor) {
        editor.putString("ipt", this.imapPort);
        editor.putString("srv", this.serverAddress);
        editor.putString("u", getImapUserName());
        editor.putString("pw", this.imapPassword);
        editor.putString("pw_len", this.tuiPasswordLength);
        return editor;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("StatusMessage [mProvisioningStatus=");
        outline13.append(this.provisioningStatus);
        outline13.append(", mStatusReturnCode=");
        outline13.append(this.statusReturnCode);
        outline13.append(", mSubscriptionUrl=");
        outline13.append(this.subscriptionUrl);
        outline13.append(", mServerAddress=");
        outline13.append(this.serverAddress);
        outline13.append(", mTuiAccessNumber=");
        outline13.append(this.tuiAccessNumber);
        outline13.append(", mClientSmsDestinationNumber=");
        outline13.append(this.clientSmsDestinationNumber);
        outline13.append(", mImapPort=");
        outline13.append(this.imapPort);
        outline13.append(", mImapUserName=");
        outline13.append(this.imapUserName);
        outline13.append(", mImapPassword=");
        outline13.append(VvmLog.pii(this.imapPassword));
        outline13.append(", mSmtpPort=");
        outline13.append(this.smtpPort);
        outline13.append(", mSmtpUserName=");
        outline13.append(this.smtpUserName);
        outline13.append(", mSmtpPassword=");
        outline13.append(VvmLog.pii(this.smtpPassword));
        outline13.append(", mTuiPasswordLength=");
        return GeneratedOutlineSupport.outline12(outline13, this.tuiPasswordLength, "]");
    }
}
