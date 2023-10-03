package com.android.settings.applications.appinfo;

import android.app.AppLockManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.UserManager;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.applications.ApplicationsState;
import com.havoc.config.center.C1715R;

public class DrawOverlayDetailPreferenceController extends AppInfoPreferenceControllerBase {
    private final AppLockManager mAppLockManager;

    public DrawOverlayDetailPreferenceController(Context context, String str) {
        super(context, str);
        this.mAppLockManager = (AppLockManager) context.getSystemService("applock");
    }

    public int getAvailabilityStatus() {
        PackageInfo packageInfo;
        if (!UserManager.get(this.mContext).isManagedProfile() && (packageInfo = this.mParent.getPackageInfo()) != null && packageInfo.requestedPermissions != null) {
            if (!this.mAppLockManager.isAppLocked(packageInfo.packageName)) {
                int i = 0;
                while (true) {
                    String[] strArr = packageInfo.requestedPermissions;
                    if (i >= strArr.length) {
                        break;
                    } else if (strArr[i].equals("android.permission.SYSTEM_ALERT_WINDOW")) {
                        return 0;
                    } else {
                        i++;
                    }
                }
            } else {
                return 5;
            }
        }
        return 4;
    }

    /* access modifiers changed from: protected */
    public Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return DrawOverlayDetails.class;
    }

    public CharSequence getSummary() {
        ApplicationsState.AppEntry appEntry = this.mParent.getAppEntry();
        if (this.mAppLockManager.isAppLocked(appEntry.info.packageName)) {
            return this.mContext.getString(C1715R.string.applock_overlay_summary);
        }
        return DrawOverlayDetails.getSummary(this.mContext, appEntry);
    }
}
