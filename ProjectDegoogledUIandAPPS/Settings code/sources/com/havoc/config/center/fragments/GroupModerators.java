package com.havoc.config.center.fragments;

import android.os.Bundle;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;

public class GroupModerators extends SettingsPreferenceFragment {
    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.group_moderators);
    }
}
