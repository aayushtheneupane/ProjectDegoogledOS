package com.android.systemui.statusbar.phone;

import android.content.pm.ActivityInfo;
import android.provider.Settings;

public class RightBackSwipeCustomApp extends LeftBackSwipeCustomApp {
    /* access modifiers changed from: protected */
    public void setPackage(String str, String str2) {
        Settings.System.putStringForUser(getContentResolver(), "right_long_back_swipe_app_action", str, -2);
        Settings.System.putStringForUser(getContentResolver(), "right_long_back_swipe_app_fr_action", str2, -2);
    }

    /* access modifiers changed from: protected */
    public void setPackageActivity(ActivityInfo activityInfo) {
        Settings.System.putStringForUser(getContentResolver(), "right_long_back_swipe_app_activity_action", activityInfo != null ? activityInfo.name : "NONE", -2);
    }
}
