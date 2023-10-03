package com.android.settings.applications.appinfo;

import android.app.AppLockManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.Utils;
import com.android.settings.applications.AppInfoWithHeader;
import com.android.settings.applications.AppStateAppOpsBridge;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settings.applications.AppStateOverlayBridge;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class DrawOverlayDetails extends AppInfoWithHeader implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private AppLockManager mAppLockManager;
    private AppOpsManager mAppOpsManager;
    private AppStateOverlayBridge mOverlayBridge;
    private AppStateOverlayBridge.OverlayState mOverlayState;
    private SwitchPreference mSwitchPref;

    /* access modifiers changed from: protected */
    public AlertDialog createDialog(int i, int i2) {
        return null;
    }

    public int getMetricsCategory() {
        return 221;
    }

    public boolean onPreferenceClick(Preference preference) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mOverlayBridge = new AppStateOverlayBridge(activity, this.mState, (AppStateBaseBridge.Callback) null);
        this.mAppOpsManager = (AppOpsManager) activity.getSystemService("appops");
        this.mAppLockManager = (AppLockManager) activity.getSystemService("applock");
        if (!Utils.isSystemAlertWindowEnabled(activity) || this.mAppLockManager.isAppLocked(this.mPackageInfo.packageName)) {
            this.mPackageInfo = null;
            return;
        }
        addPreferencesFromResource(C1715R.xml.draw_overlay_permissions_details);
        this.mSwitchPref = (SwitchPreference) findPreference("app_ops_settings_switch");
        this.mSwitchPref.setOnPreferenceChangeListener(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mPackageInfo == null) {
            return layoutInflater.inflate(C1715R.layout.manage_applications_apps_unsupported, (ViewGroup) null);
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mOverlayBridge.release();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean z = false;
        if (preference != this.mSwitchPref) {
            return false;
        }
        if (!(this.mOverlayState == null || ((Boolean) obj).booleanValue() == this.mOverlayState.isPermissible())) {
            if (!this.mOverlayState.isPermissible()) {
                z = true;
            }
            setCanDrawOverlay(z);
            refreshUi();
        }
        return true;
    }

    private void setCanDrawOverlay(boolean z) {
        logSpecialPermissionChange(z, this.mPackageName);
        this.mAppOpsManager.setMode(24, this.mPackageInfo.applicationInfo.uid, this.mPackageName, z ? 0 : 2);
    }

    /* access modifiers changed from: package-private */
    public void logSpecialPermissionChange(boolean z, String str) {
        int i = z ? 770 : 771;
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(getContext()).getMetricsFeatureProvider();
        metricsFeatureProvider.action(metricsFeatureProvider.getAttribution(getActivity()), i, getMetricsCategory(), str, 0);
    }

    /* access modifiers changed from: protected */
    public boolean refreshUi() {
        PackageInfo packageInfo = this.mPackageInfo;
        if (packageInfo == null) {
            return true;
        }
        this.mOverlayState = this.mOverlayBridge.getOverlayInfo(this.mPackageName, packageInfo.applicationInfo.uid);
        this.mSwitchPref.setChecked(this.mOverlayState.isPermissible());
        SwitchPreference switchPreference = this.mSwitchPref;
        AppStateOverlayBridge.OverlayState overlayState = this.mOverlayState;
        switchPreference.setEnabled(overlayState.permissionDeclared && overlayState.controlEnabled);
        return true;
    }

    public static CharSequence getSummary(Context context, ApplicationsState.AppEntry appEntry) {
        AppStateOverlayBridge.OverlayState overlayState;
        Object obj = appEntry.extraInfo;
        if (obj instanceof AppStateOverlayBridge.OverlayState) {
            overlayState = (AppStateOverlayBridge.OverlayState) obj;
        } else if (obj instanceof AppStateAppOpsBridge.PermissionState) {
            overlayState = new AppStateOverlayBridge.OverlayState((AppStateAppOpsBridge.PermissionState) obj);
        } else {
            overlayState = new AppStateOverlayBridge(context, (ApplicationsState) null, (AppStateBaseBridge.Callback) null).getOverlayInfo(appEntry.info.packageName, appEntry.info.uid);
        }
        return getSummary(context, overlayState);
    }

    public static CharSequence getSummary(Context context, AppStateOverlayBridge.OverlayState overlayState) {
        return context.getString(overlayState.isPermissible() ? C1715R.string.app_permission_summary_allowed : C1715R.string.app_permission_summary_not_allowed);
    }
}
