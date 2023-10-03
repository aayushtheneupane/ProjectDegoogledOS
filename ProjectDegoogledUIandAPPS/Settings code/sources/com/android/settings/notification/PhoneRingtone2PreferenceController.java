package com.android.settings.notification;

import android.content.Context;
import com.android.settings.Utils;

public class PhoneRingtone2PreferenceController extends PhoneRingtonePreferenceController {
    public int getIdForPhoneRingtonePreference() {
        return 1;
    }

    public String getPreferenceKey() {
        return "ringtone2";
    }

    public PhoneRingtone2PreferenceController(Context context) {
        super(context);
    }

    public boolean isAvailable() {
        return Utils.isVoiceCapable(this.mContext) && hasMultiPhoneAccountHandle();
    }
}
