package com.android.settings.applications.specialaccess;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class HighPowerAppsController extends BasePreferenceController {
    public HighPowerAppsController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_high_power_apps) ? 0 : 3;
    }
}
