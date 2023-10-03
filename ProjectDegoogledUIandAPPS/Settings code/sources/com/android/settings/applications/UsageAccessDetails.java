package com.android.settings.applications;

import android.app.AppOpsManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settings.applications.AppStateUsageBridge;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class UsageAccessDetails extends AppInfoWithHeader implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private AppOpsManager mAppOpsManager;
    private DevicePolicyManager mDpm;
    private Intent mSettingsIntent;
    private SwitchPreference mSwitchPref;
    private AppStateUsageBridge mUsageBridge;
    private Preference mUsageDesc;
    private AppStateUsageBridge.UsageState mUsageState;

    /* access modifiers changed from: protected */
    public AlertDialog createDialog(int i, int i2) {
        return null;
    }

    public int getMetricsCategory() {
        return 183;
    }

    public boolean onPreferenceClick(Preference preference) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mUsageBridge = new AppStateUsageBridge(activity, this.mState, (AppStateBaseBridge.Callback) null);
        this.mAppOpsManager = (AppOpsManager) activity.getSystemService("appops");
        this.mDpm = (DevicePolicyManager) activity.getSystemService(DevicePolicyManager.class);
        addPreferencesFromResource(C1715R.xml.app_ops_permissions_details);
        this.mSwitchPref = (SwitchPreference) findPreference("app_ops_settings_switch");
        this.mUsageDesc = findPreference("app_ops_settings_description");
        getPreferenceScreen().setTitle((int) C1715R.string.usage_access);
        this.mSwitchPref.setTitle((int) C1715R.string.permit_usage_access);
        this.mUsageDesc.setSummary((int) C1715R.string.usage_access_description);
        this.mSwitchPref.setOnPreferenceChangeListener(this);
        this.mSettingsIntent = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.USAGE_ACCESS_CONFIG").setPackage(this.mPackageName);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mUsageBridge.release();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean z = false;
        if (preference != this.mSwitchPref) {
            return false;
        }
        if (!(this.mUsageState == null || ((Boolean) obj).booleanValue() == this.mUsageState.isPermissible())) {
            if (this.mUsageState.isPermissible() && this.mDpm.isProfileOwnerApp(this.mPackageName)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(17302376);
                builder.setTitle(17039380);
                builder.setMessage((int) C1715R.string.work_profile_usage_access_warning);
                builder.setPositiveButton((int) C1715R.string.okay, (DialogInterface.OnClickListener) null);
                builder.show();
            }
            if (!this.mUsageState.isPermissible()) {
                z = true;
            }
            setHasAccess(z);
            refreshUi();
        }
        return true;
    }

    private void setHasAccess(boolean z) {
        logSpecialPermissionChange(z, this.mPackageName);
        this.mAppOpsManager.setMode(43, this.mPackageInfo.applicationInfo.uid, this.mPackageName, z ^ true ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public void logSpecialPermissionChange(boolean z, String str) {
        int i = z ? 783 : 784;
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(getContext()).getMetricsFeatureProvider();
        metricsFeatureProvider.action(metricsFeatureProvider.getAttribution(getActivity()), i, getMetricsCategory(), str, 0);
    }

    /* access modifiers changed from: protected */
    public boolean refreshUi() {
        PackageInfo packageInfo;
        retrieveAppEntry();
        if (this.mAppEntry == null || (packageInfo = this.mPackageInfo) == null) {
            return false;
        }
        this.mUsageState = this.mUsageBridge.getUsageInfo(this.mPackageName, packageInfo.applicationInfo.uid);
        this.mSwitchPref.setChecked(this.mUsageState.isPermissible());
        this.mSwitchPref.setEnabled(this.mUsageState.permissionDeclared);
        ResolveInfo resolveActivityAsUser = this.mPm.resolveActivityAsUser(this.mSettingsIntent, 128, this.mUserId);
        if (resolveActivityAsUser == null) {
            return true;
        }
        Bundle bundle = resolveActivityAsUser.activityInfo.metaData;
        this.mSettingsIntent.setComponent(new ComponentName(resolveActivityAsUser.activityInfo.packageName, resolveActivityAsUser.activityInfo.name));
        if (bundle == null || !bundle.containsKey("android.settings.metadata.USAGE_ACCESS_REASON")) {
            return true;
        }
        this.mSwitchPref.setSummary((CharSequence) bundle.getString("android.settings.metadata.USAGE_ACCESS_REASON"));
        return true;
    }
}
