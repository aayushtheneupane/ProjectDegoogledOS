package com.havoc.config.center.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SystemSettingMasterSwitchPreference;

public class Battery extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SystemSettingMasterSwitchPreference mEnableScreenStateToggles;
    private SystemSettingMasterSwitchPreference mSensorBlockEnabled;
    private SystemSettingMasterSwitchPreference mSmartPixelsEnabled;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_battery);
        updateMasterPrefs();
    }

    private void updateMasterPrefs() {
        this.mSmartPixelsEnabled = (SystemSettingMasterSwitchPreference) findPreference("smart_pixels_enable");
        this.mSmartPixelsEnabled.setOnPreferenceChangeListener(this);
        boolean z = false;
        this.mSmartPixelsEnabled.setChecked(Settings.System.getInt(getContentResolver(), "smart_pixels_enable", 0) != 0);
        if (!getResources().getBoolean(17891460)) {
            this.mSmartPixelsEnabled.setVisible(false);
        }
        this.mEnableScreenStateToggles = (SystemSettingMasterSwitchPreference) findPreference("screen_state_toggles_enable_key");
        this.mEnableScreenStateToggles.setChecked(Settings.System.getIntForUser(getContentResolver(), "start_screen_state_service", 0, -2) != 0);
        this.mEnableScreenStateToggles.setOnPreferenceChangeListener(this);
        this.mSensorBlockEnabled = (SystemSettingMasterSwitchPreference) findPreference("sensor_block");
        int intForUser = Settings.System.getIntForUser(getContentResolver(), "sensor_block", 0, -2);
        SystemSettingMasterSwitchPreference systemSettingMasterSwitchPreference = this.mSensorBlockEnabled;
        if (intForUser != 0) {
            z = true;
        }
        systemSettingMasterSwitchPreference.setChecked(z);
        this.mSensorBlockEnabled.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mSmartPixelsEnabled) {
            Settings.System.putInt(getContentResolver(), "smart_pixels_enable", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mEnableScreenStateToggles) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            Settings.System.putIntForUser(getContentResolver(), "start_screen_state_service", booleanValue ? 1 : 0, -2);
            Intent className = new Intent().setClassName("com.android.systemui", "com.android.systemui.havoc.screenstate.ScreenStateService");
            if (booleanValue) {
                getActivity().stopService(className);
                getActivity().startService(className);
            } else {
                getActivity().stopService(className);
            }
            return true;
        } else if (preference != this.mSensorBlockEnabled) {
            return false;
        } else {
            Settings.System.putIntForUser(getContentResolver(), "sensor_block", ((Boolean) obj).booleanValue() ? 1 : 0, -2);
            return true;
        }
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
