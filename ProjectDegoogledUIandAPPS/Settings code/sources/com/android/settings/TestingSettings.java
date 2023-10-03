package com.android.settings;

import android.os.Bundle;
import android.os.UserManager;
import androidx.preference.PreferenceScreen;
import com.havoc.config.center.C1715R;

public class TestingSettings extends SettingsPreferenceFragment {
    public int getMetricsCategory() {
        return 89;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.testing_settings);
        if (!UserManager.get(getContext()).isAdminUser()) {
            getPreferenceScreen().removePreference((PreferenceScreen) findPreference("radio_info_settings"));
        }
    }
}
