package com.android.voicemail.impl;

import android.content.Context;
import android.preference.PreferenceManager;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.PerAccountSharedPreferences;

public class VisualVoicemailPreferences extends PerAccountSharedPreferences {
    public VisualVoicemailPreferences(Context context, PhoneAccountHandle phoneAccountHandle) {
        super(phoneAccountHandle, PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()), "visual_voicemail_");
    }
}
