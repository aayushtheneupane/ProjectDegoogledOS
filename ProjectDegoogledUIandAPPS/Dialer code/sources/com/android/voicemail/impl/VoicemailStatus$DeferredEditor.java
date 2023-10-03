package com.android.voicemail.impl;

import android.content.Context;
import android.telecom.PhoneAccountHandle;

public class VoicemailStatus$DeferredEditor extends VoicemailStatus$Editor {
    /* synthetic */ VoicemailStatus$DeferredEditor(Context context, PhoneAccountHandle phoneAccountHandle, VoicemailStatus$1 voicemailStatus$1) {
        super(context, phoneAccountHandle, (VoicemailStatus$1) null);
    }

    public boolean apply() {
        return true;
    }

    public void deferredApply() {
        super.apply();
    }
}
