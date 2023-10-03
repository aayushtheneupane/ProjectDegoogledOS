package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.Intent;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.settings.Utils;
import com.android.settings.applications.AppStoreUtil;
import com.android.settingslib.applications.AppUtils;
import com.havoc.config.center.C1715R;

public class AppInstallerInfoPreferenceController extends AppInfoPreferenceControllerBase {
    private CharSequence mInstallerLabel;
    private String mInstallerPackage;
    private String mPackageName;

    public AppInstallerInfoPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!UserManager.get(this.mContext).isManagedProfile() && this.mInstallerLabel != null) {
            return 0;
        }
        return 4;
    }

    public void updateState(Preference preference) {
        preference.setSummary((CharSequence) this.mContext.getString(AppUtils.isInstant(this.mParent.getPackageInfo().applicationInfo) ? C1715R.string.instant_app_details_summary : C1715R.string.app_install_details_summary, new Object[]{this.mInstallerLabel}));
        Intent appStoreLink = AppStoreUtil.getAppStoreLink(this.mContext, this.mInstallerPackage, this.mPackageName);
        if (appStoreLink != null) {
            preference.setIntent(appStoreLink);
        } else {
            preference.setEnabled(false);
        }
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
        this.mInstallerPackage = AppStoreUtil.getInstallerPackageName(this.mContext, this.mPackageName);
        this.mInstallerLabel = Utils.getApplicationLabel(this.mContext, this.mInstallerPackage);
    }
}
