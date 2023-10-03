package com.android.settings.applications.specialaccess;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;

public class MoreSpecialAccessPreferenceController extends BasePreferenceController {
    private final Intent mIntent;

    public MoreSpecialAccessPreferenceController(Context context, String str) {
        super(context, str);
        PackageManager packageManager = context.getPackageManager();
        String permissionControllerPackageName = packageManager.getPermissionControllerPackageName();
        if (permissionControllerPackageName != null) {
            Intent intent = new Intent("android.intent.action.MANAGE_SPECIAL_APP_ACCESSES").setPackage(permissionControllerPackageName);
            this.mIntent = packageManager.resolveActivity(intent, 65536) == null ? null : intent;
            return;
        }
        this.mIntent = null;
    }

    public int getAvailabilityStatus() {
        return this.mIntent != null ? 1 : 3;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), this.mPreferenceKey)) {
            return false;
        }
        Intent intent = this.mIntent;
        if (intent == null) {
            return true;
        }
        this.mContext.startActivity(intent);
        return true;
    }
}
