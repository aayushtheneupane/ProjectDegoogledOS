package com.havoc.config.center.fragments;

import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SystemSettingMasterSwitchPreference;

public class Misc extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SystemSettingMasterSwitchPreference mGamingMode;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_misc);
        updateMasterPrefs();
    }

    private void updateMasterPrefs() {
        this.mGamingMode = (SystemSettingMasterSwitchPreference) findPreference("gaming_mode_enabled");
        SystemSettingMasterSwitchPreference systemSettingMasterSwitchPreference = this.mGamingMode;
        boolean z = true;
        if (Settings.System.getInt(getActivity().getContentResolver(), "gaming_mode_enabled", 0) != 1) {
            z = false;
        }
        systemSettingMasterSwitchPreference.setChecked(z);
        this.mGamingMode.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference != this.mGamingMode) {
            return false;
        }
        Settings.System.putInt(getActivity().getContentResolver(), "gaming_mode_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }

    public void onResume() {
        super.onResume();
        updateMasterPrefs();
    }

    public void onPause() {
        super.onPause();
        updateMasterPrefs();
    }
}
