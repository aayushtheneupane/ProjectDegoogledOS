package com.android.voicemail.impl.protocol;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.impl.sms.OmtpCvvmMessageSender;
import com.android.voicemail.impl.sms.OmtpMessageSender;

public class CvvmProtocol extends VisualVoicemailProtocol {
    public OmtpMessageSender createMessageSender(Context context, PhoneAccountHandle phoneAccountHandle, short s, String str) {
        return new OmtpCvvmMessageSender(context, phoneAccountHandle, s, str);
    }

    public String getCommand(String str) {
        return str == "XCHANGE_TUI_PWD PWD=%1$s OLD_PWD=%2$s" ? "CHANGE_TUI_PWD PWD=%1$s OLD_PWD=%2$s" : str == "XCLOSE_NUT" ? "CLOSE_NUT" : str == "XCHANGE_VM_LANG LANG=%1$s" ? "CHANGE_VM_LANG Lang=%1$s" : str;
    }
}
