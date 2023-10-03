package com.android.settings.notification;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.RestrictedSwitchPreference;

public class LightsPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    public String getPreferenceKey() {
        return "lights";
    }

    public LightsPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    public boolean isAvailable() {
        if (this.mContext.getResources().getBoolean(17891481) && super.isAvailable() && this.mChannel != null && checkCanBeVisible(3) && canPulseLight()) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        if (this.mChannel != null) {
            RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
            restrictedSwitchPreference.setDisabledByAdmin(this.mAdmin);
            restrictedSwitchPreference.setEnabled(!restrictedSwitchPreference.isDisabledByAdmin());
            restrictedSwitchPreference.setChecked(this.mChannel.shouldShowLights());
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mChannel == null) {
            return true;
        }
        this.mChannel.enableLights(((Boolean) obj).booleanValue());
        saveChannel();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean canPulseLight() {
        if (!this.mContext.getResources().getBoolean(17891494)) {
            return false;
        }
        return Settings.System.getInt(this.mContext.getContentResolver(), "notification_light_pulse", 1) == 1;
    }
}
