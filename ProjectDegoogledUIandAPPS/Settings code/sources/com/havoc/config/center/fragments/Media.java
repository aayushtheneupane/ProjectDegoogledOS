package com.havoc.config.center.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SecureSettingMasterSwitchPreference;

public class Media extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SecureSettingMasterSwitchPreference mPulse;
    private ListPreference mRingtoneFocusMode;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_media);
        Resources resources = getResources();
        this.mRingtoneFocusMode = (ListPreference) findPreference("ringtone_focus_mode");
        if (!resources.getBoolean(17891411)) {
            this.mRingtoneFocusMode.setVisible(false);
        }
        updateMasterPrefs();
    }

    private void updateMasterPrefs() {
        this.mPulse = (SecureSettingMasterSwitchPreference) findPreference("pulse_enabled");
        SecureSettingMasterSwitchPreference secureSettingMasterSwitchPreference = this.mPulse;
        boolean z = true;
        if (Settings.Secure.getInt(getActivity().getContentResolver(), "pulse_enabled", 0) != 1) {
            z = false;
        }
        secureSettingMasterSwitchPreference.setChecked(z);
        this.mPulse.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference != this.mPulse) {
            return false;
        }
        Settings.Secure.putInt(getActivity().getContentResolver(), "pulse_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
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
