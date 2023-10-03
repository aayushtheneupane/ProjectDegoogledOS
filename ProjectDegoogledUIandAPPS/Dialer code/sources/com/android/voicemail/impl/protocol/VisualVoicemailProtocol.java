package com.android.voicemail.impl.protocol;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.impl.ActivationTask;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.OmtpEvents;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.sms.OmtpMessageSender;
import com.android.voicemail.impl.sms.StatusMessage;

public abstract class VisualVoicemailProtocol {
    public abstract OmtpMessageSender createMessageSender(Context context, PhoneAccountHandle phoneAccountHandle, short s, String str);

    public String getCommand(String str) {
        return str;
    }

    public void handleEvent(Context context, OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, VoicemailStatus$Editor voicemailStatus$Editor, OmtpEvents omtpEvents) {
        Assert.handleEvent(context, omtpVvmCarrierConfigHelper, voicemailStatus$Editor, omtpEvents);
    }

    public void requestStatus(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, PendingIntent pendingIntent) {
        OmtpMessageSender messageSender = R$style.getMessageSender(this, omtpVvmCarrierConfigHelper);
        if (messageSender != null) {
            messageSender.requestVvmStatus(pendingIntent);
        }
    }

    public void startActivation(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, PendingIntent pendingIntent) {
        OmtpMessageSender messageSender = R$style.getMessageSender(this, omtpVvmCarrierConfigHelper);
        if (messageSender != null) {
            messageSender.requestVvmActivation(pendingIntent);
        }
    }

    public void startDeactivation(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper) {
        OmtpMessageSender messageSender = R$style.getMessageSender(this, omtpVvmCarrierConfigHelper);
        if (messageSender != null) {
            messageSender.requestVvmDeactivation((PendingIntent) null);
        }
    }

    public void startProvisioning(ActivationTask activationTask, PhoneAccountHandle phoneAccountHandle, OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, VoicemailStatus$Editor voicemailStatus$Editor, StatusMessage statusMessage, Bundle bundle, boolean z) {
    }

    public boolean supportsProvisioning() {
        return false;
    }

    public Bundle translateStatusSmsBundle(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, String str, Bundle bundle) {
        return null;
    }
}
