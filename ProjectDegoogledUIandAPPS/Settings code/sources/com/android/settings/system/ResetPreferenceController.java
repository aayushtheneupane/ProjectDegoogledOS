package com.android.settings.system;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class ResetPreferenceController extends BasePreferenceController {
    public ResetPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_reset_dashboard) ? 1 : 3;
    }
}
