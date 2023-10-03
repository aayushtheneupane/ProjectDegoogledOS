package com.android.settings.display;

import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import com.android.settings.core.TogglePreferenceController;
import com.havoc.config.center.C1715R;

public class AdaptiveSleepPreferenceController extends TogglePreferenceController {
    private static final int DEFAULT_VALUE = 0;
    public static final String PREF_NAME = "adaptive_sleep";
    private static final String SYSTEM_KEY = "adaptive_sleep";

    public AdaptiveSleepPreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean isChecked() {
        return hasSufficientPermission(this.mContext.getPackageManager()) && Settings.System.getInt(this.mContext.getContentResolver(), "adaptive_sleep", 0) != 0;
    }

    public boolean setChecked(boolean z) {
        Settings.System.putInt(this.mContext.getContentResolver(), "adaptive_sleep", z ? 1 : 0);
        return true;
    }

    public int getAvailabilityStatus() {
        return isControllerAvailable(this.mContext);
    }

    public CharSequence getSummary() {
        return this.mContext.getText(isChecked() ? C1715R.string.adaptive_sleep_summary_on : C1715R.string.adaptive_sleep_summary_off);
    }

    public static int isControllerAvailable(Context context) {
        return context.getResources().getBoolean(17891338) ? 1 : 3;
    }

    static boolean hasSufficientPermission(PackageManager packageManager) {
        String attentionServicePackageName = packageManager.getAttentionServicePackageName();
        return attentionServicePackageName != null && packageManager.checkPermission("android.permission.CAMERA", attentionServicePackageName) == 0;
    }
}
