package com.android.settings.wifi;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.applications.AppInfoWithHeader;
import com.android.settings.applications.AppStateAppOpsBridge;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.wifi.AppStateChangeWifiStateBridge;
import com.android.settingslib.applications.ApplicationsState;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class ChangeWifiStateDetails extends AppInfoWithHeader implements Preference.OnPreferenceChangeListener {
    private AppStateChangeWifiStateBridge mAppBridge;
    private AppOpsManager mAppOpsManager;
    private SwitchPreference mSwitchPref;
    private AppStateChangeWifiStateBridge.WifiSettingsState mWifiSettingsState;

    /* access modifiers changed from: protected */
    public AlertDialog createDialog(int i, int i2) {
        return null;
    }

    public int getMetricsCategory() {
        return 338;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mAppBridge = new AppStateChangeWifiStateBridge(activity, this.mState, (AppStateBaseBridge.Callback) null);
        this.mAppOpsManager = (AppOpsManager) activity.getSystemService("appops");
        addPreferencesFromResource(C1715R.xml.change_wifi_state_details);
        this.mSwitchPref = (SwitchPreference) findPreference("app_ops_settings_switch");
        this.mSwitchPref.setTitle((int) C1715R.string.change_wifi_state_app_detail_switch);
        this.mSwitchPref.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean z = false;
        if (preference != this.mSwitchPref) {
            return false;
        }
        if (!(this.mWifiSettingsState == null || ((Boolean) obj).booleanValue() == this.mWifiSettingsState.isPermissible())) {
            if (!this.mWifiSettingsState.isPermissible()) {
                z = true;
            }
            setCanChangeWifiState(z);
            refreshUi();
        }
        return true;
    }

    private void setCanChangeWifiState(boolean z) {
        logSpecialPermissionChange(z, this.mPackageName);
        this.mAppOpsManager.setMode(71, this.mPackageInfo.applicationInfo.uid, this.mPackageName, z ^ true ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public void logSpecialPermissionChange(boolean z, String str) {
        FeatureFactory.getFactory(getContext()).getMetricsFeatureProvider().action(getContext(), z ? 774 : 775, str);
    }

    /* access modifiers changed from: protected */
    public boolean refreshUi() {
        ApplicationInfo applicationInfo;
        PackageInfo packageInfo = this.mPackageInfo;
        if (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null) {
            return false;
        }
        this.mWifiSettingsState = this.mAppBridge.getWifiSettingsInfo(this.mPackageName, applicationInfo.uid);
        this.mSwitchPref.setChecked(this.mWifiSettingsState.isPermissible());
        this.mSwitchPref.setEnabled(this.mWifiSettingsState.permissionDeclared);
        return true;
    }

    public static CharSequence getSummary(Context context, ApplicationsState.AppEntry appEntry) {
        AppStateChangeWifiStateBridge.WifiSettingsState wifiSettingsState;
        Object obj = appEntry.extraInfo;
        if (obj instanceof AppStateChangeWifiStateBridge.WifiSettingsState) {
            wifiSettingsState = (AppStateChangeWifiStateBridge.WifiSettingsState) obj;
        } else if (obj instanceof AppStateAppOpsBridge.PermissionState) {
            wifiSettingsState = new AppStateChangeWifiStateBridge.WifiSettingsState((AppStateAppOpsBridge.PermissionState) obj);
        } else {
            wifiSettingsState = new AppStateChangeWifiStateBridge(context, (ApplicationsState) null, (AppStateBaseBridge.Callback) null).getWifiSettingsInfo(appEntry.info.packageName, appEntry.info.uid);
        }
        return getSummary(context, wifiSettingsState);
    }

    public static CharSequence getSummary(Context context, AppStateChangeWifiStateBridge.WifiSettingsState wifiSettingsState) {
        return context.getString(wifiSettingsState.isPermissible() ? C1715R.string.app_permission_summary_allowed : C1715R.string.app_permission_summary_not_allowed);
    }
}
