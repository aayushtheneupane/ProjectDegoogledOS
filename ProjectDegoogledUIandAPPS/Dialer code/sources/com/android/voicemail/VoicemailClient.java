package com.android.voicemail;

import android.content.Context;
import android.os.PersistableBundle;
import android.telecom.PhoneAccountHandle;
import java.util.List;

public interface VoicemailClient {

    public interface ActivationStateListener {
    }

    void addActivationStateListener(ActivationStateListener activationStateListener);

    void appendOmtpVoicemailSelectionClause(Context context, StringBuilder sb, List<String> list);

    void appendOmtpVoicemailStatusSelectionClause(Context context, StringBuilder sb, List<String> list);

    PinChanger createPinChanger(Context context, PhoneAccountHandle phoneAccountHandle);

    String getCarrierConfigString(Context context, PhoneAccountHandle phoneAccountHandle, String str);

    PersistableBundle getConfig(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean hasAcceptedTos(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean hasCarrierSupport(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean isActivated(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean isVoicemailArchiveAvailable(Context context);

    boolean isVoicemailArchiveEnabled(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean isVoicemailDonationAvailable(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean isVoicemailDonationEnabled(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean isVoicemailEnabled(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean isVoicemailModuleEnabled();

    boolean isVoicemailTranscriptionAvailable(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean isVoicemailTranscriptionEnabled(Context context, PhoneAccountHandle phoneAccountHandle);

    void onBoot(Context context);

    void onShutdown(Context context);

    void onTosAccepted(Context context, PhoneAccountHandle phoneAccountHandle);

    void removeActivationStateListener(ActivationStateListener activationStateListener);

    void setVoicemailArchiveEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z);

    void setVoicemailDonationEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z);

    void setVoicemailEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z);

    void setVoicemailTranscriptionEnabled(Context context, PhoneAccountHandle phoneAccountHandle, boolean z);

    void showConfigUi(Context context);
}
