package com.android.settings.applications.appinfo;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.om.OverlayManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsActivity;
import com.android.settings.applications.ApplicationFeatureProvider;
import com.android.settings.applications.appinfo.ButtonActionDialogFragment;
import com.android.settings.applications.specialaccess.deviceadmin.DeviceAdminAdd;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.InstrumentedPreferenceFragment;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.Utils;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnDestroy;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.widget.ActionButtonsPreference;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.HashSet;

public class AppButtonsPreferenceController extends BasePreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnResume, OnDestroy, ApplicationsState.Callbacks {
    public static final String APP_CHG = "chg";
    private static final String KEY_ACTION_BUTTONS = "action_buttons";
    private static final boolean LOCAL_LOGV = false;
    private static final String TAG = "AppButtonsPrefCtl";
    /* access modifiers changed from: private */
    public final SettingsActivity mActivity;
    ApplicationsState.AppEntry mAppEntry;
    private Intent mAppLaunchIntent;
    private final ApplicationFeatureProvider mApplicationFeatureProvider;
    /* access modifiers changed from: private */
    public RestrictedLockUtils.EnforcedAdmin mAppsControlDisallowedAdmin;
    /* access modifiers changed from: private */
    public boolean mAppsControlDisallowedBySystem;
    ActionButtonsPreference mButtonsPref;
    private final BroadcastReceiver mCheckKillProcessesReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean z = getResultCode() != 0 ? true : AppButtonsPreferenceController.LOCAL_LOGV;
            Log.d(AppButtonsPreferenceController.TAG, "Got broadcast response: Restart status for " + AppButtonsPreferenceController.this.mAppEntry.info.packageName + " " + z);
            AppButtonsPreferenceController.this.updateForceStopButtonInner(z);
        }
    };
    boolean mDisableAfterUninstall = LOCAL_LOGV;
    /* access modifiers changed from: private */
    public final DevicePolicyManager mDpm;
    /* access modifiers changed from: private */
    public boolean mFinishing = LOCAL_LOGV;
    /* access modifiers changed from: private */
    public final InstrumentedPreferenceFragment mFragment;
    final HashSet<String> mHomePackages = new HashSet<>();
    private boolean mListeningToPackageRemove = LOCAL_LOGV;
    /* access modifiers changed from: private */
    public final MetricsFeatureProvider mMetricsFeatureProvider;
    private final OverlayManager mOverlayManager;
    PackageInfo mPackageInfo;
    String mPackageName;
    private final BroadcastReceiver mPackageRemovedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (!AppButtonsPreferenceController.this.mFinishing && AppButtonsPreferenceController.this.mAppEntry.info.packageName.equals(schemeSpecificPart)) {
                AppButtonsPreferenceController.this.mActivity.finishAndRemoveTask();
            }
        }
    };
    /* access modifiers changed from: private */
    public final PackageManager mPm;
    /* access modifiers changed from: private */
    public final int mRequestRemoveDeviceAdmin;
    private final int mRequestUninstall;
    private ApplicationsState.Session mSession;
    ApplicationsState mState;
    /* access modifiers changed from: private */
    public boolean mUpdatedSysApp = LOCAL_LOGV;
    /* access modifiers changed from: private */
    public final int mUserId;
    private final UserManager mUserManager;

    public String getPreferenceKey() {
        return KEY_ACTION_BUTTONS;
    }

    public void onAllSizesComputed() {
    }

    public void onLauncherInfoChanged() {
    }

    public void onLoadEntriesCompleted() {
    }

    public void onPackageIconChanged() {
    }

    public void onPackageSizeChanged(String str) {
    }

    public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> arrayList) {
    }

    public void onRunningStateChanged(boolean z) {
    }

    public AppButtonsPreferenceController(SettingsActivity settingsActivity, InstrumentedPreferenceFragment instrumentedPreferenceFragment, Lifecycle lifecycle, String str, ApplicationsState applicationsState, int i, int i2) {
        super(settingsActivity, KEY_ACTION_BUTTONS);
        if (instrumentedPreferenceFragment instanceof ButtonActionDialogFragment.AppButtonsDialogListener) {
            FeatureFactory factory = FeatureFactory.getFactory(settingsActivity);
            this.mMetricsFeatureProvider = factory.getMetricsFeatureProvider();
            this.mApplicationFeatureProvider = factory.getApplicationFeatureProvider(settingsActivity);
            this.mState = applicationsState;
            this.mDpm = (DevicePolicyManager) settingsActivity.getSystemService("device_policy");
            this.mUserManager = (UserManager) settingsActivity.getSystemService("user");
            this.mPm = settingsActivity.getPackageManager();
            this.mOverlayManager = (OverlayManager) settingsActivity.getSystemService(OverlayManager.class);
            this.mPackageName = str;
            this.mActivity = settingsActivity;
            this.mFragment = instrumentedPreferenceFragment;
            this.mUserId = UserHandle.myUserId();
            this.mRequestUninstall = i;
            this.mRequestRemoveDeviceAdmin = i2;
            this.mAppLaunchIntent = this.mPm.getLaunchIntentForPackage(this.mPackageName);
            if (str != null) {
                this.mAppEntry = this.mState.getEntry(str, this.mUserId);
                this.mSession = this.mState.newSession(this, lifecycle);
                lifecycle.addObserver(this);
                return;
            }
            this.mFinishing = true;
            return;
        }
        throw new IllegalArgumentException("Fragment should implement AppButtonsDialogListener");
    }

    public int getAvailabilityStatus() {
        return (this.mFinishing || isInstantApp() || isSystemModule()) ? 4 : 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            this.mButtonsPref = ((ActionButtonsPreference) preferenceScreen.findPreference(KEY_ACTION_BUTTONS)).setButton1Text(C1715R.string.launch_instant_app).setButton1Icon(C1715R.C1717drawable.ic_settings_open).setButton1OnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    AppButtonsPreferenceController.this.lambda$displayPreference$0$AppButtonsPreferenceController(view);
                }
            }).setButton2Text(C1715R.string.uninstall_text).setButton2Icon(C1715R.C1717drawable.ic_settings_delete).setButton2OnClickListener(new UninstallAndDisableButtonListener()).setButton3Text(C1715R.string.force_stop).setButton3Icon(C1715R.C1717drawable.ic_settings_force_stop).setButton3OnClickListener(new ForceStopButtonListener()).setButton3Enabled(LOCAL_LOGV);
        }
    }

    public /* synthetic */ void lambda$displayPreference$0$AppButtonsPreferenceController(View view) {
        launchApplication();
    }

    public void onResume() {
        if (isAvailable()) {
            this.mAppsControlDisallowedBySystem = RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mActivity, "no_control_apps", this.mUserId);
            this.mAppsControlDisallowedAdmin = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mActivity, "no_control_apps", this.mUserId);
            if (!refreshUi()) {
                setIntentAndFinish(true);
            }
        }
    }

    public void onDestroy() {
        stopListeningToPackageRemove();
    }

    private class UninstallAndDisableButtonListener implements View.OnClickListener {
        private UninstallAndDisableButtonListener() {
        }

        public void onClick(View view) {
            String str = AppButtonsPreferenceController.this.mAppEntry.info.packageName;
            if (AppButtonsPreferenceController.this.mDpm.packageHasActiveAdmins(AppButtonsPreferenceController.this.mPackageInfo.packageName)) {
                AppButtonsPreferenceController.this.stopListeningToPackageRemove();
                Intent intent = new Intent(AppButtonsPreferenceController.this.mActivity, DeviceAdminAdd.class);
                intent.putExtra("android.app.extra.DEVICE_ADMIN_PACKAGE_NAME", str);
                AppButtonsPreferenceController.this.mMetricsFeatureProvider.action((Context) AppButtonsPreferenceController.this.mActivity, 873, (Pair<Integer, Object>[]) new Pair[0]);
                AppButtonsPreferenceController.this.mFragment.startActivityForResult(intent, AppButtonsPreferenceController.this.mRequestRemoveDeviceAdmin);
                return;
            }
            RestrictedLockUtils.EnforcedAdmin checkIfUninstallBlocked = RestrictedLockUtilsInternal.checkIfUninstallBlocked(AppButtonsPreferenceController.this.mActivity, str, AppButtonsPreferenceController.this.mUserId);
            boolean z = AppButtonsPreferenceController.this.mAppsControlDisallowedBySystem || RestrictedLockUtilsInternal.hasBaseUserRestriction(AppButtonsPreferenceController.this.mActivity, str, AppButtonsPreferenceController.this.mUserId);
            if (checkIfUninstallBlocked == null || z) {
                AppButtonsPreferenceController appButtonsPreferenceController = AppButtonsPreferenceController.this;
                ApplicationInfo applicationInfo = appButtonsPreferenceController.mAppEntry.info;
                int i = applicationInfo.flags;
                if ((i & 1) != 0) {
                    if (!applicationInfo.enabled || appButtonsPreferenceController.isDisabledUntilUsed()) {
                        AppButtonsPreferenceController.this.mMetricsFeatureProvider.action((Context) AppButtonsPreferenceController.this.mActivity, AppButtonsPreferenceController.this.mAppEntry.info.enabled ? 874 : 875, (Pair<Integer, Object>[]) new Pair[0]);
                        AppButtonsPreferenceController appButtonsPreferenceController2 = AppButtonsPreferenceController.this;
                        AsyncTask.execute(new DisableChangerRunnable(appButtonsPreferenceController2.mPm, AppButtonsPreferenceController.this.mAppEntry.info.packageName, 0));
                    } else if (!AppButtonsPreferenceController.this.mUpdatedSysApp || !AppButtonsPreferenceController.this.isSingleUser()) {
                        AppButtonsPreferenceController.this.showDialogInner(0);
                    } else {
                        AppButtonsPreferenceController.this.showDialogInner(1);
                    }
                } else if ((8388608 & i) == 0) {
                    appButtonsPreferenceController.uninstallPkg(str, true, AppButtonsPreferenceController.LOCAL_LOGV);
                } else {
                    appButtonsPreferenceController.uninstallPkg(str, AppButtonsPreferenceController.LOCAL_LOGV, AppButtonsPreferenceController.LOCAL_LOGV);
                }
            } else {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(AppButtonsPreferenceController.this.mActivity, checkIfUninstallBlocked);
            }
        }
    }

    private class ForceStopButtonListener implements View.OnClickListener {
        private ForceStopButtonListener() {
        }

        public void onClick(View view) {
            if (AppButtonsPreferenceController.this.mAppsControlDisallowedAdmin == null || AppButtonsPreferenceController.this.mAppsControlDisallowedBySystem) {
                AppButtonsPreferenceController.this.showDialogInner(2);
            } else {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(AppButtonsPreferenceController.this.mActivity, AppButtonsPreferenceController.this.mAppsControlDisallowedAdmin);
            }
        }
    }

    public void handleActivityResult(int i, int i2, Intent intent) {
        if (i == this.mRequestUninstall) {
            if (this.mDisableAfterUninstall) {
                this.mDisableAfterUninstall = LOCAL_LOGV;
                AsyncTask.execute(new DisableChangerRunnable(this.mPm, this.mAppEntry.info.packageName, 3));
            }
            refreshAndFinishIfPossible();
        } else if (i == this.mRequestRemoveDeviceAdmin) {
            refreshAndFinishIfPossible();
        }
    }

    public void handleDialogClick(int i) {
        if (i == 0) {
            this.mMetricsFeatureProvider.action((Context) this.mActivity, 874, (Pair<Integer, Object>[]) new Pair[0]);
            AsyncTask.execute(new DisableChangerRunnable(this.mPm, this.mAppEntry.info.packageName, 3));
        } else if (i == 1) {
            this.mMetricsFeatureProvider.action((Context) this.mActivity, 874, (Pair<Integer, Object>[]) new Pair[0]);
            uninstallPkg(this.mAppEntry.info.packageName, LOCAL_LOGV, true);
        } else if (i == 2) {
            forceStopPackage(this.mAppEntry.info.packageName);
        }
    }

    public void onPackageListChanged() {
        if (isAvailable()) {
            refreshUi();
        }
    }

    /* access modifiers changed from: package-private */
    public void retrieveAppEntry() {
        this.mAppEntry = this.mState.getEntry(this.mPackageName, this.mUserId);
        ApplicationsState.AppEntry appEntry = this.mAppEntry;
        if (appEntry != null) {
            try {
                this.mPackageInfo = this.mPm.getPackageInfo(appEntry.info.packageName, 4198976);
                this.mPackageName = this.mAppEntry.info.packageName;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Exception when retrieving package:" + this.mAppEntry.info.packageName, e);
                this.mPackageInfo = null;
            }
        } else {
            this.mPackageInfo = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateOpenButton() {
        this.mAppLaunchIntent = this.mPm.getLaunchIntentForPackage(this.mPackageName);
        this.mButtonsPref.setButton1Visible(this.mAppLaunchIntent != null ? true : LOCAL_LOGV);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0091, code lost:
        if (r7.mHomePackages.size() > 1) goto L_0x00a3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateUninstallButton() {
        /*
            r7 = this;
            com.android.settingslib.applications.ApplicationsState$AppEntry r0 = r7.mAppEntry
            android.content.pm.ApplicationInfo r0 = r0.info
            int r0 = r0.flags
            r1 = 1
            r0 = r0 & r1
            r2 = 0
            if (r0 == 0) goto L_0x000d
            r0 = r1
            goto L_0x000e
        L_0x000d:
            r0 = r2
        L_0x000e:
            if (r0 == 0) goto L_0x0015
            boolean r3 = r7.handleDisableable()
            goto L_0x0030
        L_0x0015:
            android.content.pm.PackageInfo r3 = r7.mPackageInfo
            android.content.pm.ApplicationInfo r3 = r3.applicationInfo
            int r3 = r3.flags
            r4 = 8388608(0x800000, float:1.17549435E-38)
            r3 = r3 & r4
            if (r3 != 0) goto L_0x002f
            android.os.UserManager r3 = r7.mUserManager
            java.util.List r3 = r3.getUsers()
            int r3 = r3.size()
            r4 = 2
            if (r3 < r4) goto L_0x002f
            r3 = r2
            goto L_0x0030
        L_0x002f:
            r3 = r1
        L_0x0030:
            if (r0 == 0) goto L_0x003f
            android.app.admin.DevicePolicyManager r4 = r7.mDpm
            android.content.pm.PackageInfo r5 = r7.mPackageInfo
            java.lang.String r5 = r5.packageName
            boolean r4 = r4.packageHasActiveAdmins(r5)
            if (r4 == 0) goto L_0x003f
            r3 = r2
        L_0x003f:
            android.os.UserManager r4 = r7.mUserManager
            android.app.admin.DevicePolicyManager r5 = r7.mDpm
            android.content.pm.PackageInfo r6 = r7.mPackageInfo
            java.lang.String r6 = r6.packageName
            boolean r4 = com.android.settings.Utils.isProfileOrDeviceOwner(r4, r5, r6)
            if (r4 == 0) goto L_0x004e
            r3 = r2
        L_0x004e:
            android.content.Context r4 = r7.mContext
            android.content.res.Resources r4 = r4.getResources()
            com.android.settingslib.applications.ApplicationsState$AppEntry r5 = r7.mAppEntry
            android.content.pm.ApplicationInfo r5 = r5.info
            java.lang.String r5 = r5.packageName
            boolean r4 = com.android.settingslib.Utils.isDeviceProvisioningPackage(r4, r5)
            if (r4 == 0) goto L_0x0061
            r3 = r2
        L_0x0061:
            android.app.admin.DevicePolicyManager r4 = r7.mDpm
            java.lang.String r5 = r7.mPackageName
            boolean r4 = r4.isUninstallInQueue(r5)
            if (r4 == 0) goto L_0x006c
            r3 = r2
        L_0x006c:
            if (r3 == 0) goto L_0x00a2
            java.util.HashSet<java.lang.String> r4 = r7.mHomePackages
            android.content.pm.PackageInfo r5 = r7.mPackageInfo
            java.lang.String r5 = r5.packageName
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x00a2
            if (r0 == 0) goto L_0x007e
        L_0x007c:
            r1 = r2
            goto L_0x00a3
        L_0x007e:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            android.content.pm.PackageManager r4 = r7.mPm
            android.content.ComponentName r3 = r4.getHomeActivities(r3)
            if (r3 != 0) goto L_0x0094
            java.util.HashSet<java.lang.String> r3 = r7.mHomePackages
            int r3 = r3.size()
            if (r3 <= r1) goto L_0x007c
            goto L_0x00a3
        L_0x0094:
            android.content.pm.PackageInfo r4 = r7.mPackageInfo
            java.lang.String r4 = r4.packageName
            java.lang.String r3 = r3.getPackageName()
            boolean r3 = r4.equals(r3)
            r1 = r1 ^ r3
            goto L_0x00a3
        L_0x00a2:
            r1 = r3
        L_0x00a3:
            boolean r3 = r7.mAppsControlDisallowedBySystem
            if (r3 == 0) goto L_0x00a8
            r1 = r2
        L_0x00a8:
            com.android.settingslib.applications.ApplicationsState$AppEntry r3 = r7.mAppEntry
            android.content.pm.ApplicationInfo r3 = r3.info
            boolean r3 = r3.isResourceOverlay()
            if (r3 == 0) goto L_0x00e8
            if (r0 == 0) goto L_0x00b5
            goto L_0x00e9
        L_0x00b5:
            com.android.settingslib.applications.ApplicationsState$AppEntry r0 = r7.mAppEntry
            android.content.pm.ApplicationInfo r0 = r0.info
            java.lang.String r0 = r0.packageName
            com.android.settingslib.applications.ApplicationsState$AppEntry r3 = r7.mAppEntry
            android.content.pm.ApplicationInfo r3 = r3.info
            int r3 = r3.uid
            android.os.UserHandle r3 = android.os.UserHandle.getUserHandleForUid(r3)
            android.content.om.OverlayManager r4 = r7.mOverlayManager
            android.content.om.OverlayInfo r0 = r4.getOverlayInfo(r0, r3)
            if (r0 == 0) goto L_0x00e8
            boolean r3 = r0.isEnabled()
            if (r3 == 0) goto L_0x00e8
            com.android.settingslib.applications.ApplicationsState r3 = r7.mState
            java.lang.String r0 = r0.targetPackageName
            com.android.settingslib.applications.ApplicationsState$AppEntry r4 = r7.mAppEntry
            android.content.pm.ApplicationInfo r4 = r4.info
            int r4 = r4.uid
            int r4 = android.os.UserHandle.getUserId(r4)
            com.android.settingslib.applications.ApplicationsState$AppEntry r0 = r3.getEntry(r0, r4)
            if (r0 == 0) goto L_0x00e8
            goto L_0x00e9
        L_0x00e8:
            r2 = r1
        L_0x00e9:
            com.android.settingslib.widget.ActionButtonsPreference r7 = r7.mButtonsPref
            r7.setButton2Enabled(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.applications.appinfo.AppButtonsPreferenceController.updateUninstallButton():void");
    }

    private void setIntentAndFinish(boolean z) {
        Intent intent = new Intent();
        intent.putExtra(APP_CHG, z);
        this.mActivity.finishPreferencePanel(-1, intent);
        this.mFinishing = true;
    }

    private void refreshAndFinishIfPossible() {
        if (!refreshUi()) {
            setIntentAndFinish(true);
        } else {
            startListeningToPackageRemove();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateForceStopButton() {
        if (this.mDpm.packageHasActiveAdmins(this.mPackageInfo.packageName)) {
            Log.w(TAG, "User can't force stop device admin");
            updateForceStopButtonInner(LOCAL_LOGV);
            return;
        }
        ApplicationInfo applicationInfo = this.mAppEntry.info;
        if ((applicationInfo.flags & 2097152) == 0) {
            Log.w(TAG, "App is not explicitly stopped");
            updateForceStopButtonInner(true);
            return;
        }
        Intent intent = new Intent("android.intent.action.QUERY_PACKAGE_RESTART", Uri.fromParts("package", applicationInfo.packageName, (String) null));
        intent.putExtra("android.intent.extra.PACKAGES", new String[]{this.mAppEntry.info.packageName});
        intent.putExtra("android.intent.extra.UID", this.mAppEntry.info.uid);
        intent.putExtra("android.intent.extra.user_handle", UserHandle.getUserId(this.mAppEntry.info.uid));
        Log.d(TAG, "Sending broadcast to query restart status for " + this.mAppEntry.info.packageName);
        this.mActivity.sendOrderedBroadcastAsUser(intent, UserHandle.CURRENT, (String) null, this.mCheckKillProcessesReceiver, (Handler) null, 0, (String) null, (Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public void updateForceStopButtonInner(boolean z) {
        if (this.mAppsControlDisallowedBySystem) {
            this.mButtonsPref.setButton3Enabled(LOCAL_LOGV);
        } else {
            this.mButtonsPref.setButton3Enabled(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void uninstallPkg(String str, boolean z, boolean z2) {
        stopListeningToPackageRemove();
        Intent intent = new Intent("android.intent.action.UNINSTALL_PACKAGE", Uri.parse("package:" + str));
        intent.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", z);
        this.mMetricsFeatureProvider.action((Context) this.mActivity, 872, (Pair<Integer, Object>[]) new Pair[0]);
        this.mFragment.startActivityForResult(intent, this.mRequestUninstall);
        this.mDisableAfterUninstall = z2;
    }

    /* access modifiers changed from: package-private */
    public void forceStopPackage(String str) {
        MetricsFeatureProvider metricsFeatureProvider = this.mMetricsFeatureProvider;
        metricsFeatureProvider.action(metricsFeatureProvider.getAttribution(this.mActivity), 807, this.mFragment.getMetricsCategory(), str, 0);
        Log.d(TAG, "Stopping package " + str);
        ((ActivityManager) this.mActivity.getSystemService("activity")).forceStopPackage(str);
        int userId = UserHandle.getUserId(this.mAppEntry.info.uid);
        this.mState.invalidatePackage(str, userId);
        ApplicationsState.AppEntry entry = this.mState.getEntry(str, userId);
        if (entry != null) {
            this.mAppEntry = entry;
        }
        updateForceStopButton();
    }

    /* access modifiers changed from: package-private */
    public boolean handleDisableable() {
        if (this.mHomePackages.contains(this.mAppEntry.info.packageName) || this.mAppEntry.info.packageName.equals("com.google.android.inputmethod.latin") || isSystemPackage(this.mActivity.getResources(), this.mPm, this.mPackageInfo)) {
            this.mButtonsPref.setButton2Text(C1715R.string.disable_text).setButton2Icon(C1715R.C1717drawable.ic_settings_disable);
            return LOCAL_LOGV;
        } else if (!this.mAppEntry.info.enabled || isDisabledUntilUsed()) {
            this.mButtonsPref.setButton2Text(C1715R.string.enable_text).setButton2Icon(C1715R.C1717drawable.ic_settings_enable);
            return true;
        } else {
            this.mButtonsPref.setButton2Text(C1715R.string.disable_text).setButton2Icon(C1715R.C1717drawable.ic_settings_disable);
            return true ^ this.mApplicationFeatureProvider.getKeepEnabledPackages().contains(this.mAppEntry.info.packageName);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isSystemPackage(Resources resources, PackageManager packageManager, PackageInfo packageInfo) {
        return Utils.isSystemPackage(resources, packageManager, packageInfo);
    }

    /* access modifiers changed from: private */
    public boolean isDisabledUntilUsed() {
        if (this.mAppEntry.info.enabledSetting == 4) {
            return true;
        }
        return LOCAL_LOGV;
    }

    /* access modifiers changed from: private */
    public void showDialogInner(int i) {
        ButtonActionDialogFragment newInstance = ButtonActionDialogFragment.newInstance(i);
        newInstance.setTargetFragment(this.mFragment, 0);
        FragmentManager supportFragmentManager = this.mActivity.getSupportFragmentManager();
        newInstance.show(supportFragmentManager, "dialog " + i);
    }

    /* access modifiers changed from: private */
    public boolean isSingleUser() {
        int userCount = this.mUserManager.getUserCount();
        if (userCount == 1) {
            return true;
        }
        if (!UserManager.isSplitSystemUser() || userCount != 2) {
            return LOCAL_LOGV;
        }
        return true;
    }

    private boolean signaturesMatch(String str, String str2) {
        if (str == null || str2 == null) {
            return LOCAL_LOGV;
        }
        try {
            if (this.mPm.checkSignatures(str, str2) >= 0) {
                return true;
            }
            return LOCAL_LOGV;
        } catch (Exception unused) {
            return LOCAL_LOGV;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean refreshUi() {
        if (this.mPackageName == null) {
            return LOCAL_LOGV;
        }
        retrieveAppEntry();
        if (this.mAppEntry == null || this.mPackageInfo == null) {
            return LOCAL_LOGV;
        }
        ArrayList arrayList = new ArrayList();
        this.mPm.getHomeActivities(arrayList);
        this.mHomePackages.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = (ResolveInfo) arrayList.get(i);
            String str = resolveInfo.activityInfo.packageName;
            this.mHomePackages.add(str);
            Bundle bundle = resolveInfo.activityInfo.metaData;
            if (bundle != null) {
                String string = bundle.getString("android.app.home.alternate");
                if (signaturesMatch(string, str)) {
                    this.mHomePackages.add(string);
                }
            }
        }
        updateOpenButton();
        updateUninstallButton();
        updateForceStopButton();
        return true;
    }

    private void startListeningToPackageRemove() {
        if (!this.mListeningToPackageRemove) {
            this.mListeningToPackageRemove = true;
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addDataScheme("package");
            this.mActivity.registerReceiver(this.mPackageRemovedReceiver, intentFilter);
        }
    }

    /* access modifiers changed from: private */
    public void stopListeningToPackageRemove() {
        if (this.mListeningToPackageRemove) {
            this.mListeningToPackageRemove = LOCAL_LOGV;
            this.mActivity.unregisterReceiver(this.mPackageRemovedReceiver);
        }
    }

    private void launchApplication() {
        Intent intent = this.mAppLaunchIntent;
        if (intent != null) {
            this.mContext.startActivityAsUser(intent, new UserHandle(this.mUserId));
        }
    }

    private boolean isInstantApp() {
        ApplicationsState.AppEntry appEntry = this.mAppEntry;
        if (appEntry == null || !AppUtils.isInstant(appEntry.info)) {
            return LOCAL_LOGV;
        }
        return true;
    }

    private boolean isSystemModule() {
        ApplicationsState.AppEntry appEntry = this.mAppEntry;
        if (appEntry == null || !AppUtils.isSystemModule(this.mContext, appEntry.info.packageName)) {
            return LOCAL_LOGV;
        }
        return true;
    }

    private class DisableChangerRunnable implements Runnable {
        final String mPackageName;
        final PackageManager mPm;
        final int mState;

        public DisableChangerRunnable(PackageManager packageManager, String str, int i) {
            this.mPm = packageManager;
            this.mPackageName = str;
            this.mState = i;
        }

        public void run() {
            this.mPm.setApplicationEnabledSetting(this.mPackageName, this.mState, 0);
        }
    }
}
