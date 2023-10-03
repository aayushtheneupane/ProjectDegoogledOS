package com.android.settings.notification;

import android.content.Context;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class ChargingSoundPreferenceController extends SettingPrefController {
    public ChargingSoundPreferenceController(Context context, SettingsPreferenceFragment settingsPreferenceFragment, Lifecycle lifecycle) {
        super(context, settingsPreferenceFragment, lifecycle);
        this.mPreference = new SettingPref(3, "charging_sounds", "charging_sounds_enabled", 1, new int[0]);
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_charging_sounds);
    }
}
