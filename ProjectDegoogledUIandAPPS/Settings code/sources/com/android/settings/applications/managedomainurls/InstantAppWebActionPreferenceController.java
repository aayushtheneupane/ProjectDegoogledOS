package com.android.settings.applications.managedomainurls;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.core.TogglePreferenceController;

public class InstantAppWebActionPreferenceController extends TogglePreferenceController {
    public InstantAppWebActionPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return WebActionCategoryController.isDisableWebActions(this.mContext) ? 3 : 0;
    }

    public boolean isChecked() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "instant_apps_enabled", 1) == 1;
    }

    public boolean setChecked(boolean z) {
        return Settings.Secure.putInt(this.mContext.getContentResolver(), "instant_apps_enabled", z ? 1 : 0);
    }
}
