package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.UserManager;
import com.android.settings.SettingsPreferenceFragment;

public class WriteSystemSettingsPreferenceController extends AppInfoPreferenceControllerBase {
    public WriteSystemSettingsPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        PackageInfo packageInfo;
        if (!UserManager.get(this.mContext).isManagedProfile() && (packageInfo = this.mParent.getPackageInfo()) != null && packageInfo.requestedPermissions != null) {
            int i = 0;
            while (true) {
                String[] strArr = packageInfo.requestedPermissions;
                if (i >= strArr.length) {
                    break;
                } else if (strArr[i].equals("android.permission.WRITE_SETTINGS")) {
                    return 0;
                } else {
                    i++;
                }
            }
        }
        return 4;
    }

    /* access modifiers changed from: protected */
    public Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return WriteSettingsDetails.class;
    }

    public CharSequence getSummary() {
        return WriteSettingsDetails.getSummary(this.mContext, this.mParent.getAppEntry());
    }
}
