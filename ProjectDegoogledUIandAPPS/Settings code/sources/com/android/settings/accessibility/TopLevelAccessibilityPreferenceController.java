package com.android.settings.accessibility;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class TopLevelAccessibilityPreferenceController extends BasePreferenceController {
    public TopLevelAccessibilityPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_top_level_accessibility) ? 1 : 3;
    }
}
