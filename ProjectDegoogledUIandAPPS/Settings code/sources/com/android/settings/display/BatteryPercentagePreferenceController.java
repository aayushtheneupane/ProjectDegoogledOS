package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.PreferenceControllerMixin;
import com.havoc.support.preferences.SwitchPreference;

public class BatteryPercentagePreferenceController extends BasePreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    public int getAvailabilityStatus() {
        return 3;
    }

    public BatteryPercentagePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateState(Preference preference) {
        boolean z = false;
        SwitchPreference switchPreference = (SwitchPreference) preference;
        if (Settings.System.getInt(this.mContext.getContentResolver(), "dummy_show_battery_percent", 0) == 1) {
            z = true;
        }
        switchPreference.setChecked(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        Settings.System.putInt(this.mContext.getContentResolver(), "dummy_show_battery_percent", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }
}
