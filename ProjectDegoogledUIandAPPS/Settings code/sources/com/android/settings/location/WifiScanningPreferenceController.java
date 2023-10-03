package com.android.settings.location;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.support.preferences.SwitchPreference;

public class WifiScanningPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    public String getPreferenceKey() {
        return "wifi_always_scanning";
    }

    public boolean isAvailable() {
        return true;
    }

    public WifiScanningPreferenceController(Context context) {
        super(context);
    }

    public void updateState(Preference preference) {
        SwitchPreference switchPreference = (SwitchPreference) preference;
        boolean z = false;
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "wifi_scan_always_enabled", 0) == 1) {
            z = true;
        }
        switchPreference.setChecked(z);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        int i = 0;
        if (!"wifi_always_scanning".equals(preference.getKey())) {
            return false;
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        if (((SwitchPreference) preference).isChecked()) {
            i = 1;
        }
        Settings.Global.putInt(contentResolver, "wifi_scan_always_enabled", i);
        return true;
    }
}
