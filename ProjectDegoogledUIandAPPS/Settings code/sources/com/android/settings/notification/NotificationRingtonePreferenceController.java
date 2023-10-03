package com.android.settings.notification;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.havoc.config.center.C1715R;

public class NotificationRingtonePreferenceController extends RingtonePreferenceControllerBase {
    public String getPreferenceKey() {
        return "notification_ringtone";
    }

    public int getRingtoneType() {
        return 2;
    }

    public NotificationRingtonePreferenceController(Context context) {
        super(context);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (findPreference != null) {
            updateState(findPreference);
        }
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_notification_ringtone);
    }
}
