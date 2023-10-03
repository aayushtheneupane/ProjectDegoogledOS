package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.CustomSeekBarPreference;
import com.havoc.support.preferences.SystemSettingMasterSwitchPreference;

public class QuickSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SystemSettingMasterSwitchPreference mCustomHeader;
    private SystemSettingMasterSwitchPreference mQsBlur;
    private CustomSeekBarPreference mQsColumnsLandscape;
    private CustomSeekBarPreference mQsColumnsPortrait;
    private CustomSeekBarPreference mQsColumnsQuickbar;
    private CustomSeekBarPreference mQsRowsLandscape;
    private CustomSeekBarPreference mQsRowsPortrait;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_quicksettings);
        ContentResolver contentResolver = getActivity().getContentResolver();
        this.mQsColumnsPortrait = (CustomSeekBarPreference) findPreference("qs_columns_portrait");
        this.mQsColumnsPortrait.setValue(Settings.System.getIntForUser(contentResolver, "qs_layout_columns", 4, -2));
        this.mQsColumnsPortrait.setOnPreferenceChangeListener(this);
        this.mQsColumnsLandscape = (CustomSeekBarPreference) findPreference("qs_columns_landscape");
        this.mQsColumnsLandscape.setValue(Settings.System.getIntForUser(contentResolver, "qs_layout_columns_landscape", 4, -2));
        this.mQsColumnsLandscape.setOnPreferenceChangeListener(this);
        this.mQsRowsPortrait = (CustomSeekBarPreference) findPreference("qs_rows_portrait");
        this.mQsRowsPortrait.setValue(Settings.System.getIntForUser(contentResolver, "qs_layout_rows", 3, -2));
        this.mQsRowsPortrait.setOnPreferenceChangeListener(this);
        this.mQsRowsLandscape = (CustomSeekBarPreference) findPreference("qs_rows_landscape");
        this.mQsRowsLandscape.setValue(Settings.System.getIntForUser(contentResolver, "qs_layout_rows_landscape", 2, -2));
        this.mQsRowsLandscape.setOnPreferenceChangeListener(this);
        this.mQsColumnsQuickbar = (CustomSeekBarPreference) findPreference("qs_columns_quickbar");
        this.mQsColumnsQuickbar.setValue(Settings.System.getInt(contentResolver, "qs_quickbar_columns", 6));
        this.mQsColumnsQuickbar.setOnPreferenceChangeListener(this);
        updateMasterPrefs();
    }

    private void updateMasterPrefs() {
        this.mCustomHeader = (SystemSettingMasterSwitchPreference) findPreference("status_bar_custom_header");
        boolean z = false;
        this.mCustomHeader.setChecked(Settings.System.getInt(getActivity().getContentResolver(), "status_bar_custom_header", 0) != 0);
        this.mCustomHeader.setOnPreferenceChangeListener(this);
        this.mQsBlur = (SystemSettingMasterSwitchPreference) findPreference("qs_background_blur");
        int i = Settings.System.getInt(getActivity().getContentResolver(), "qs_background_blur", 0);
        SystemSettingMasterSwitchPreference systemSettingMasterSwitchPreference = this.mQsBlur;
        if (i != 0) {
            z = true;
        }
        systemSettingMasterSwitchPreference.setChecked(z);
        this.mQsBlur.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        if (preference == this.mQsColumnsQuickbar) {
            Settings.System.putIntForUser(getContentResolver(), "qs_quickbar_columns", ((Integer) obj).intValue(), -2);
            return true;
        } else if (preference == this.mQsColumnsPortrait) {
            Settings.System.putIntForUser(contentResolver, "qs_layout_columns", ((Integer) obj).intValue(), -2);
            return true;
        } else if (preference == this.mQsColumnsLandscape) {
            Settings.System.putIntForUser(contentResolver, "qs_layout_columns_landscape", ((Integer) obj).intValue(), -2);
            return true;
        } else if (preference == this.mQsRowsPortrait) {
            Settings.System.putIntForUser(contentResolver, "qs_layout_rows", ((Integer) obj).intValue(), -2);
            return true;
        } else if (preference == this.mQsRowsLandscape) {
            Settings.System.putIntForUser(contentResolver, "qs_layout_rows_landscape", ((Integer) obj).intValue(), -2);
            return true;
        } else if (preference == this.mCustomHeader) {
            Settings.System.putInt(contentResolver, "status_bar_custom_header", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference != this.mQsBlur) {
            return false;
        } else {
            Settings.System.putInt(getContentResolver(), "qs_background_blur", ((Boolean) obj).booleanValue() ? 1 : 0);
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
