package com.havoc.config.center;

import android.os.Bundle;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;

public class ConfigCenter extends SettingsPreferenceFragment {
    private Preference mCustomDoze;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center);
        this.mCustomDoze = findPreference("ambient_display_custom");
        if (!getResources().getBoolean(17891359)) {
            getPreferenceScreen().removePreference(this.mCustomDoze);
        }
    }
}
