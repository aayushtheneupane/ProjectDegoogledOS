package com.android.settings.applications.managedomainurls;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.core.BasePreferenceController;

public class WebActionCategoryController extends BasePreferenceController {
    public WebActionCategoryController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return isDisableWebActions(this.mContext) ? 3 : 0;
    }

    public static boolean isDisableWebActions(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "enable_ephemeral_feature", 1) == 0;
    }
}
