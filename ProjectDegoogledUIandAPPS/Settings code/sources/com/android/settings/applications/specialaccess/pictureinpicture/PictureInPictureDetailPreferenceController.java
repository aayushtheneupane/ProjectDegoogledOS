package com.android.settings.applications.specialaccess.pictureinpicture;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.applications.appinfo.AppInfoPreferenceControllerBase;

public class PictureInPictureDetailPreferenceController extends AppInfoPreferenceControllerBase {
    private static final String TAG = "PicInPicDetailControl";
    private final PackageManager mPackageManager;
    private String mPackageName;

    public PictureInPictureDetailPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
    }

    public int getAvailabilityStatus() {
        return hasPictureInPictureActivites() ? 0 : 4;
    }

    public void updateState(Preference preference) {
        preference.setSummary(getPreferenceSummary());
    }

    /* access modifiers changed from: protected */
    public Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return PictureInPictureDetails.class;
    }

    /* access modifiers changed from: package-private */
    public boolean hasPictureInPictureActivites() {
        PackageInfo packageInfo;
        try {
            packageInfo = this.mPackageManager.getPackageInfoAsUser(this.mPackageName, 1, UserHandle.myUserId());
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Exception while retrieving the package info of " + this.mPackageName, e);
            packageInfo = null;
        }
        if (packageInfo == null || !PictureInPictureSettings.checkPackageHasPictureInPictureActivities(packageInfo.packageName, packageInfo.activities)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int getPreferenceSummary() {
        return PictureInPictureDetails.getPreferenceSummary(this.mContext, this.mParent.getPackageInfo().applicationInfo.uid, this.mPackageName);
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }
}
