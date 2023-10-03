package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import com.havoc.support.preferences.SystemSettingMasterSwitchPreference;

public class StatusBar extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SystemSettingMasterSwitchPreference mBatteryBar;
    private ListPreference mBatteryPercent;
    private ListPreference mBatteryStyle;
    private SystemSettingMasterSwitchPreference mCarrierLabel;
    private boolean mConfigUseOldMobileType;
    private Handler mHandler;
    /* access modifiers changed from: private */
    public boolean mIsBarSwitchingMode = false;
    private SystemSettingMasterSwitchPreference mNetworkTraffic;
    private SystemSettingMasterSwitchPreference mStatusBarClockShow;
    private SystemSettingMasterSwitchPreference mStatusBarLogo;
    private SwitchPreference mUseOldMobileType;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_statusbar);
        getPreferenceScreen();
        ContentResolver contentResolver = getActivity().getContentResolver();
        this.mHandler = new Handler();
        this.mConfigUseOldMobileType = getResources().getBoolean(17891594);
        boolean z = this.mConfigUseOldMobileType;
        this.mUseOldMobileType = (SwitchPreference) findPreference("use_old_mobiletype");
        boolean z2 = true;
        this.mUseOldMobileType.setChecked(Settings.System.getInt(contentResolver, "use_old_mobiletype", z ? 1 : 0) == 1);
        this.mUseOldMobileType.setOnPreferenceChangeListener(this);
        this.mBatteryStyle = (ListPreference) findPreference("status_bar_battery_style");
        int intForUser = Settings.System.getIntForUser(contentResolver, "status_bar_battery_style", 0, -2);
        this.mBatteryStyle.setValue(String.valueOf(intForUser));
        ListPreference listPreference = this.mBatteryStyle;
        listPreference.setSummary(listPreference.getEntry());
        this.mBatteryStyle.setOnPreferenceChangeListener(this);
        this.mBatteryPercent = (ListPreference) findPreference("status_bar_show_battery_percent");
        this.mBatteryPercent.setValue(String.valueOf(Settings.System.getIntForUser(contentResolver, "status_bar_show_battery_percent", 0, -2)));
        ListPreference listPreference2 = this.mBatteryPercent;
        listPreference2.setSummary(listPreference2.getEntry());
        this.mBatteryPercent.setOnPreferenceChangeListener(this);
        ListPreference listPreference3 = this.mBatteryPercent;
        if (intForUser == 4 || intForUser == 5) {
            z2 = false;
        }
        listPreference3.setEnabled(z2);
        updateMasterPrefs();
    }

    private void updateMasterPrefs() {
        this.mStatusBarClockShow = (SystemSettingMasterSwitchPreference) findPreference("status_bar_clock");
        boolean z = false;
        this.mStatusBarClockShow.setChecked(Settings.System.getInt(getActivity().getContentResolver(), "status_bar_clock", 1) == 1);
        this.mStatusBarClockShow.setOnPreferenceChangeListener(this);
        this.mStatusBarLogo = (SystemSettingMasterSwitchPreference) findPreference("status_bar_logo");
        this.mStatusBarLogo.setChecked(Settings.System.getInt(getActivity().getContentResolver(), "status_bar_logo", 0) == 1);
        this.mStatusBarLogo.setOnPreferenceChangeListener(this);
        this.mNetworkTraffic = (SystemSettingMasterSwitchPreference) findPreference("network_traffic_state");
        this.mNetworkTraffic.setChecked(Settings.System.getInt(getActivity().getContentResolver(), "network_traffic_state", 0) == 1);
        this.mNetworkTraffic.setOnPreferenceChangeListener(this);
        this.mBatteryBar = (SystemSettingMasterSwitchPreference) findPreference("statusbar_battery_bar");
        this.mBatteryBar.setChecked(Settings.System.getInt(getActivity().getContentResolver(), "statusbar_battery_bar", 0) == 1);
        this.mBatteryBar.setOnPreferenceChangeListener(this);
        this.mCarrierLabel = (SystemSettingMasterSwitchPreference) findPreference("carrier_label_enabled");
        SystemSettingMasterSwitchPreference systemSettingMasterSwitchPreference = this.mCarrierLabel;
        if (Settings.System.getInt(getActivity().getContentResolver(), "carrier_label_enabled", 1) == 1) {
            z = true;
        }
        systemSettingMasterSwitchPreference.setChecked(z);
        this.mCarrierLabel.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mStatusBarClockShow) {
            Settings.System.putInt(getActivity().getContentResolver(), "status_bar_clock", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mStatusBarLogo) {
            Settings.System.putInt(getActivity().getContentResolver(), "status_bar_logo", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mUseOldMobileType) {
            Settings.System.putInt(getActivity().getContentResolver(), "use_old_mobiletype", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else {
            boolean z = false;
            if (preference == this.mBatteryStyle) {
                String str = (String) obj;
                int parseInt = Integer.parseInt(str);
                Settings.System.putIntForUser(getActivity().getContentResolver(), "status_bar_battery_style", parseInt, -2);
                int findIndexOfValue = this.mBatteryStyle.findIndexOfValue(str);
                ListPreference listPreference = this.mBatteryStyle;
                listPreference.setSummary(listPreference.getEntries()[findIndexOfValue]);
                ListPreference listPreference2 = this.mBatteryPercent;
                if (!(parseInt == 4 || parseInt == 5)) {
                    z = true;
                }
                listPreference2.setEnabled(z);
                return true;
            } else if (preference == this.mBatteryPercent) {
                String str2 = (String) obj;
                Settings.System.putIntForUser(getActivity().getContentResolver(), "status_bar_show_battery_percent", Integer.parseInt(str2), -2);
                int findIndexOfValue2 = this.mBatteryPercent.findIndexOfValue(str2);
                ListPreference listPreference3 = this.mBatteryPercent;
                listPreference3.setSummary(listPreference3.getEntries()[findIndexOfValue2]);
                return true;
            } else if (preference == this.mNetworkTraffic) {
                Settings.System.putInt(getActivity().getContentResolver(), "network_traffic_state", ((Boolean) obj).booleanValue() ? 1 : 0);
                return true;
            } else if (preference == this.mBatteryBar) {
                if (this.mIsBarSwitchingMode) {
                    return false;
                }
                this.mIsBarSwitchingMode = true;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                Settings.System.putIntForUser(getActivity().getContentResolver(), "statusbar_battery_bar", booleanValue ? 1 : 0, -2);
                this.mBatteryBar.setChecked(booleanValue);
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        boolean unused = StatusBar.this.mIsBarSwitchingMode = false;
                    }
                }, 1500);
                return true;
            } else if (preference != this.mCarrierLabel) {
                return false;
            } else {
                Settings.System.putInt(getActivity().getContentResolver(), "carrier_label_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
                return true;
            }
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
