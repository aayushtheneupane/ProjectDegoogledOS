package com.android.dialer.app.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.android.dialer.R;

public class DisplayOptionsSettingsFragment extends PreferenceFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.display_options_settings);
    }
}
