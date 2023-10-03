package com.android.voicemail.stub;

import android.content.Context;
import android.os.PersistableBundle;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.PinChanger;
import com.android.voicemail.VoicemailClient;
import java.util.List;

public final class StubVoicemailClient implements VoicemailClient {
    public void addActivationStateListener(VoicemailClient.ActivationStateListener activationStateListener) {
    }

    public void appendOmtpVoicemailSelectionClause(Context context, StringBuilder sb, List<String> list) {
    }

    public void appendOmtpVoicemailStatusSelectionClause(Context context, StringBuilder sb, List<String> list) {
    }

    public PinChanger createPinChanger(Context context, PhoneAccountHandle phoneAccountHandle) {
        throw new AssertionError("should never be called on stub.");
    }

    public String getCarrierConfigString(Context context, PhoneAccountHandle phoneAccountHandle, String str) {
        return null;
    }

    public PersistableBundle getConfig(Context context, PhoneAccountHandle phoneAccountHandle) {
        return new PersistableBundle();
    }

    public boolean hasAcceptedTos(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean hasCarrierSupport(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean isActivated(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean isVoicemailArchiveAvailable(Context context) {
        return false;
    }

    public boolean isVoicemailArchiveEnabled(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean isVoicemailDonationAvailable(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean isVoicemailDonationEnabled(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean isVoicemailEnabled(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean isVoicemailModuleEnabled() {
        return false;
    }

    public boolean isVoicemailTranscriptionAvailable(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean isVoicemailTranscriptionEnabled(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public void onBoot(Context context) {
    }

    public void onShutdown(Context context) {
    }

    public void onTosAccepted(Context context, PhoneAccountHandle phoneAccountHandle) {
    }

    public void removeActivationStateListener(VoicemailClient.ActivationStateListener activationStateListener) {
    }

    public void setVoicemailArchiveEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
    }

    public void setVoicemailDonationEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
    }

    public void setVoicemailEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
    }

    public void setVoicemailTranscriptionEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
    }

    public void showConfigUi(Context context) {
    }
}
