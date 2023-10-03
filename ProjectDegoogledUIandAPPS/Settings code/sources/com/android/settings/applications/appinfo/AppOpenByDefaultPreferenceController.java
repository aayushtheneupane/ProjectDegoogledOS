package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.IUsbManager;
import android.os.ServiceManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.applications.AppLaunchSettings;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;

public class AppOpenByDefaultPreferenceController extends AppInfoPreferenceControllerBase {
    private PackageManager mPackageManager;
    private IUsbManager mUsbManager = IUsbManager.Stub.asInterface(ServiceManager.getService("usb"));

    public AppOpenByDefaultPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        ApplicationInfo applicationInfo;
        super.displayPreference(preferenceScreen);
        ApplicationsState.AppEntry appEntry = this.mParent.getAppEntry();
        if (appEntry == null || (applicationInfo = appEntry.info) == null) {
            this.mPreference.setEnabled(false);
        } else if ((applicationInfo.flags & 8388608) == 0 || !applicationInfo.enabled) {
            this.mPreference.setEnabled(false);
        }
    }

    public void updateState(Preference preference) {
        PackageInfo packageInfo = this.mParent.getPackageInfo();
        if (packageInfo == null || AppUtils.isInstant(packageInfo.applicationInfo)) {
            preference.setVisible(false);
            return;
        }
        preference.setVisible(true);
        preference.setSummary(AppUtils.getLaunchByDefaultSummary(this.mParent.getAppEntry(), this.mUsbManager, this.mPackageManager, this.mContext));
    }

    /* access modifiers changed from: protected */
    public Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return AppLaunchSettings.class;
    }
}
