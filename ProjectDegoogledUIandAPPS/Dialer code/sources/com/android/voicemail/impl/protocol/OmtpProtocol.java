package com.android.voicemail.impl.protocol;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.impl.OmtpConstants;
import com.android.voicemail.impl.sms.OmtpMessageSender;
import com.android.voicemail.impl.sms.OmtpStandardMessageSender;

public class OmtpProtocol extends VisualVoicemailProtocol {
    public OmtpMessageSender createMessageSender(Context context, PhoneAccountHandle phoneAccountHandle, short s, String str) {
        return new OmtpStandardMessageSender(context, phoneAccountHandle, s, str, OmtpConstants.getClientType(), "11", (String) null);
    }
}
