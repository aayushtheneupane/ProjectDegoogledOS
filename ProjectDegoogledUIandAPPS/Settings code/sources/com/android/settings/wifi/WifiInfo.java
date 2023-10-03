package com.android.settings.wifi;

import android.os.Bundle;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;

public class WifiInfo extends SettingsPreferenceFragment {
    public int getMetricsCategory() {
        return 89;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.testing_wifi_settings);
    }
}
