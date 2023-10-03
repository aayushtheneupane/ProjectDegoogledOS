package com.android.settings.notification;

import android.content.Context;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class DockingSoundPreferenceController extends SettingPrefController {
    public DockingSoundPreferenceController(Context context, SettingsPreferenceFragment settingsPreferenceFragment, Lifecycle lifecycle) {
        super(context, settingsPreferenceFragment, lifecycle);
        this.mPreference = new SettingPref(1, "docking_sounds", "dock_sounds_enabled", 1, new int[0]) {
            public boolean isApplicable(Context context) {
                return context.getResources().getBoolean(C1715R.bool.has_dock_settings);
            }
        };
    }
}
