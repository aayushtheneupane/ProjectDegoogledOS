package com.android.settings.notification;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.TogglePreferenceController;

public class SnoozeNotificationPreferenceController extends TogglePreferenceController {
    static final int OFF = 0;

    /* renamed from: ON */
    static final int f55ON = 1;
    private static final String TAG = "SnoozeNotifPrefContr";

    public int getAvailabilityStatus() {
        return 0;
    }

    public SnoozeNotificationPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (findPreference != null) {
            updateState(findPreference);
        }
    }

    public boolean isChecked() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "show_notification_snooze", 0) == 1;
    }

    public boolean setChecked(boolean z) {
        return Settings.Secure.putInt(this.mContext.getContentResolver(), "show_notification_snooze", z ? 1 : 0);
    }
}
