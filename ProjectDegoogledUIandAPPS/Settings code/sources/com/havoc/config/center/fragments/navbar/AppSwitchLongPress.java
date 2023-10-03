package com.havoc.config.center.fragments.navbar;

import android.content.pm.ActivityInfo;
import android.provider.Settings;

public class AppSwitchLongPress extends BackLongPress {
    /* access modifiers changed from: protected */
    public void setPackage(String str, String str2) {
        Settings.System.putString(getContentResolver(), "key_app_switch_long_press_custom_app", str);
        Settings.System.putString(getContentResolver(), "key_app_switch_long_press_custom_app_fr_name", str2);
    }

    /* access modifiers changed from: protected */
    public void setPackageActivity(ActivityInfo activityInfo) {
        Settings.System.putString(getContentResolver(), "key_app_switch_long_press_custom_activity", activityInfo != null ? activityInfo.name : "NONE");
    }
}
