package com.android.settings.system;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class AdditionalSystemUpdatePreferenceController extends BasePreferenceController {
    private static final String KEY_UPDATE_SETTING = "additional_system_update_settings";

    public AdditionalSystemUpdatePreferenceController(Context context) {
        super(context, KEY_UPDATE_SETTING);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_additional_system_update_setting_enable) ? 0 : 3;
    }
}
