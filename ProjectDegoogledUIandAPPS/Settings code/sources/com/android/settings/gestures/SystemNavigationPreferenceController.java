package com.android.settings.gestures;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class SystemNavigationPreferenceController extends BasePreferenceController {
    private static final String ACTION_QUICKSTEP = "android.intent.action.QUICKSTEP_SERVICE";
    static final String PREF_KEY_SYSTEM_NAVIGATION = "gesture_system_navigation";

    public SystemNavigationPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return isGestureAvailable(this.mContext) ? 0 : 3;
    }

    public CharSequence getSummary() {
        if (isEdgeToEdgeEnabled(this.mContext)) {
            return this.mContext.getText(C1715R.string.edge_to_edge_navigation_title);
        }
        if (isSwipeUpEnabled(this.mContext)) {
            return this.mContext.getText(C1715R.string.swipe_up_to_switch_apps_title);
        }
        return this.mContext.getText(C1715R.string.legacy_navigation_title);
    }

    static boolean isGestureAvailable(Context context) {
        ComponentName unflattenFromString;
        if (!context.getResources().getBoolean(17891580) || (unflattenFromString = ComponentName.unflattenFromString(context.getString(17039814))) == null) {
            return false;
        }
        if (context.getPackageManager().resolveService(new Intent(ACTION_QUICKSTEP).setPackage(unflattenFromString.getPackageName()), 1048576) == null) {
            return false;
        }
        return true;
    }

    static boolean isOverlayPackageAvailable(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    static boolean isSwipeUpEnabled(Context context) {
        if (isEdgeToEdgeEnabled(context)) {
            return false;
        }
        return 1 == context.getResources().getInteger(17694869);
    }

    static boolean isEdgeToEdgeEnabled(Context context) {
        return 2 == context.getResources().getInteger(17694869);
    }
}
