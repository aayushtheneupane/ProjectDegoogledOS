package com.havoc.config.center.fragments;

import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.internal.util.havoc.Utils;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.GlobalSettingMasterSwitchPreference;
import com.havoc.support.preferences.SystemSettingMasterSwitchPreference;

public class Notifications extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SystemSettingMasterSwitchPreference mBatteryLightEnabled;
    private SystemSettingMasterSwitchPreference mEdgeLightEnabled;
    private GlobalSettingMasterSwitchPreference mHeadsUpEnabled;
    private PreferenceCategory mLightsCategory;
    private SystemSettingMasterSwitchPreference mTickerEnabled;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_notifications);
        updateMasterPrefs();
    }

    private void updateMasterPrefs() {
        this.mBatteryLightEnabled = (SystemSettingMasterSwitchPreference) findPreference("battery_light_enabled");
        this.mBatteryLightEnabled.setOnPreferenceChangeListener(this);
        boolean z = true;
        this.mBatteryLightEnabled.setChecked(Settings.System.getInt(getContentResolver(), "battery_light_enabled", 1) != 0);
        this.mLightsCategory = (PreferenceCategory) findPreference("notification_lights");
        if (!getResources().getBoolean(17891481)) {
            this.mLightsCategory.setVisible(false);
        }
        this.mHeadsUpEnabled = (GlobalSettingMasterSwitchPreference) findPreference("heads_up_notifications_enabled");
        this.mHeadsUpEnabled.setOnPreferenceChangeListener(this);
        this.mHeadsUpEnabled.setChecked(Settings.Global.getInt(getContentResolver(), "heads_up_notifications_enabled", 1) != 0);
        this.mEdgeLightEnabled = (SystemSettingMasterSwitchPreference) findPreference("ambient_notification_light");
        this.mEdgeLightEnabled.setOnPreferenceChangeListener(this);
        this.mEdgeLightEnabled.setChecked(Settings.System.getInt(getContentResolver(), "ambient_notification_light", 0) != 0);
        this.mTickerEnabled = (SystemSettingMasterSwitchPreference) findPreference("status_bar_show_ticker");
        this.mTickerEnabled.setOnPreferenceChangeListener(this);
        int i = Settings.System.getInt(getContentResolver(), "status_bar_show_ticker", 0);
        SystemSettingMasterSwitchPreference systemSettingMasterSwitchPreference = this.mTickerEnabled;
        if (i == 0) {
            z = false;
        }
        systemSettingMasterSwitchPreference.setChecked(z);
        if (Utils.hasNotch(getActivity())) {
            this.mTickerEnabled.setVisible(false);
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

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mHeadsUpEnabled) {
            Settings.Global.putInt(getContentResolver(), "heads_up_notifications_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mBatteryLightEnabled) {
            Settings.System.putInt(getContentResolver(), "battery_light_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mEdgeLightEnabled) {
            Settings.System.putInt(getContentResolver(), "ambient_notification_light", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference != this.mTickerEnabled) {
            return false;
        } else {
            Settings.System.putInt(getContentResolver(), "status_bar_show_ticker", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        }
    }
}
