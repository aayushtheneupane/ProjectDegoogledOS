package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settings.applications.AppStateInstallAppsBridge;
import com.android.settingslib.applications.ApplicationsState;

public class ExternalSourceDetailPreferenceController extends AppInfoPreferenceControllerBase {
    private String mPackageName;

    public ExternalSourceDetailPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!UserManager.get(this.mContext).isManagedProfile() && isPotentialAppSource()) {
            return 0;
        }
        return 4;
    }

    public void updateState(Preference preference) {
        preference.setSummary(getPreferenceSummary());
    }

    /* access modifiers changed from: protected */
    public Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return ExternalSourcesDetails.class;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getPreferenceSummary() {
        return ExternalSourcesDetails.getPreferenceSummary(this.mContext, this.mParent.getAppEntry());
    }

    /* access modifiers changed from: package-private */
    public boolean isPotentialAppSource() {
        PackageInfo packageInfo = this.mParent.getPackageInfo();
        if (packageInfo == null) {
            return false;
        }
        return new AppStateInstallAppsBridge(this.mContext, (ApplicationsState) null, (AppStateBaseBridge.Callback) null).createInstallAppsStateFor(this.mPackageName, packageInfo.applicationInfo.uid).isPotentialAppSource();
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }
}
