package com.android.settings.applications.appinfo;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.applications.appinfo.ButtonActionDialogFragment;
import com.android.settings.applications.specialaccess.pictureinpicture.PictureInPictureDetailPreferenceController;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.Utils;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppInfoDashboardFragment extends DashboardFragment implements ApplicationsState.Callbacks, ButtonActionDialogFragment.AppButtonsDialogListener {
    static final int REQUEST_UNINSTALL = 0;
    static final int UNINSTALL_ALL_USERS_MENU = 1;
    static final int UNINSTALL_UPDATES = 2;
    private AppButtonsPreferenceController mAppButtonsPreferenceController;
    /* access modifiers changed from: private */
    public ApplicationsState.AppEntry mAppEntry;
    private RestrictedLockUtils.EnforcedAdmin mAppsControlDisallowedAdmin;
    private boolean mAppsControlDisallowedBySystem;
    private List<Callback> mCallbacks = new ArrayList();
    private DevicePolicyManager mDpm;
    boolean mFinishing;
    private boolean mInitialized;
    private InstantAppButtonsPreferenceController mInstantAppButtonPreferenceController;
    private boolean mListeningToPackageRemove;
    /* access modifiers changed from: private */
    public PackageInfo mPackageInfo;
    private String mPackageName;
    final BroadcastReceiver mPackageRemovedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (!AppInfoDashboardFragment.this.mFinishing) {
                String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                if (AppInfoDashboardFragment.this.mAppEntry == null || AppInfoDashboardFragment.this.mAppEntry.info == null || TextUtils.equals(AppInfoDashboardFragment.this.mAppEntry.info.packageName, schemeSpecificPart)) {
                    AppInfoDashboardFragment.this.onPackageRemoved();
                } else if (AppInfoDashboardFragment.this.mAppEntry.info.isResourceOverlay() && TextUtils.equals(AppInfoDashboardFragment.this.mPackageInfo.overlayTarget, schemeSpecificPart)) {
                    AppInfoDashboardFragment.this.refreshUi();
                }
            }
        }
    };
    private PackageManager mPm;
    private ApplicationsState.Session mSession;
    private boolean mShowUninstalled;
    private ApplicationsState mState;
    private boolean mUpdatedSysApp = false;
    private int mUserId;
    private UserManager mUserManager;

    public interface Callback {
        void refreshUi();
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "AppInfoDashboard";
    }

    public int getMetricsCategory() {
        return 20;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.app_info_settings;
    }

    public void onAllSizesComputed() {
    }

    public void onLauncherInfoChanged() {
    }

    public void onLoadEntriesCompleted() {
    }

    public void onPackageIconChanged() {
    }

    public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> arrayList) {
    }

    public void onRunningStateChanged(boolean z) {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        String packageName = getPackageName();
        ((TimeSpentInAppPreferenceController) use(TimeSpentInAppPreferenceController.class)).setPackageName(packageName);
        ((AppDataUsagePreferenceController) use(AppDataUsagePreferenceController.class)).setParentFragment(this);
        AppInstallerInfoPreferenceController appInstallerInfoPreferenceController = (AppInstallerInfoPreferenceController) use(AppInstallerInfoPreferenceController.class);
        appInstallerInfoPreferenceController.setPackageName(packageName);
        appInstallerInfoPreferenceController.setParentFragment(this);
        ((AppInstallerPreferenceCategoryController) use(AppInstallerPreferenceCategoryController.class)).setChildren(Arrays.asList(new AbstractPreferenceController[]{appInstallerInfoPreferenceController}));
        ((AppNotificationPreferenceController) use(AppNotificationPreferenceController.class)).setParentFragment(this);
        ((AppOpenByDefaultPreferenceController) use(AppOpenByDefaultPreferenceController.class)).setParentFragment(this);
        ((AppPermissionPreferenceController) use(AppPermissionPreferenceController.class)).setParentFragment(this);
        ((AppPermissionPreferenceController) use(AppPermissionPreferenceController.class)).setPackageName(packageName);
        ((AppSettingPreferenceController) use(AppSettingPreferenceController.class)).setPackageName(packageName).setParentFragment(this);
        ((AppStoragePreferenceController) use(AppStoragePreferenceController.class)).setParentFragment(this);
        ((AppVersionPreferenceController) use(AppVersionPreferenceController.class)).setParentFragment(this);
        ((AppPackageNamePreferenceController) use(AppPackageNamePreferenceController.class)).setParentFragment(this);
        ((InstantAppDomainsPreferenceController) use(InstantAppDomainsPreferenceController.class)).setParentFragment(this);
        WriteSystemSettingsPreferenceController writeSystemSettingsPreferenceController = (WriteSystemSettingsPreferenceController) use(WriteSystemSettingsPreferenceController.class);
        writeSystemSettingsPreferenceController.setParentFragment(this);
        DrawOverlayDetailPreferenceController drawOverlayDetailPreferenceController = (DrawOverlayDetailPreferenceController) use(DrawOverlayDetailPreferenceController.class);
        drawOverlayDetailPreferenceController.setParentFragment(this);
        PictureInPictureDetailPreferenceController pictureInPictureDetailPreferenceController = (PictureInPictureDetailPreferenceController) use(PictureInPictureDetailPreferenceController.class);
        pictureInPictureDetailPreferenceController.setPackageName(packageName);
        pictureInPictureDetailPreferenceController.setParentFragment(this);
        ExternalSourceDetailPreferenceController externalSourceDetailPreferenceController = (ExternalSourceDetailPreferenceController) use(ExternalSourceDetailPreferenceController.class);
        externalSourceDetailPreferenceController.setPackageName(packageName);
        externalSourceDetailPreferenceController.setParentFragment(this);
        ((AdvancedAppInfoPreferenceCategoryController) use(AdvancedAppInfoPreferenceCategoryController.class)).setChildren(Arrays.asList(new AbstractPreferenceController[]{writeSystemSettingsPreferenceController, drawOverlayDetailPreferenceController, pictureInPictureDetailPreferenceController, externalSourceDetailPreferenceController}));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFinishing = false;
        FragmentActivity activity = getActivity();
        this.mDpm = (DevicePolicyManager) activity.getSystemService("device_policy");
        this.mUserManager = (UserManager) activity.getSystemService("user");
        this.mPm = activity.getPackageManager();
        if (ensurePackageInfoAvailable(activity) && ensureDisplayableModule(activity)) {
            startListeningToPackageRemove();
            setHasOptionsMenu(true);
        }
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        if (ensurePackageInfoAvailable(getActivity())) {
            super.onCreatePreferences(bundle, str);
        }
    }

    public void onDestroy() {
        stopListeningToPackageRemove();
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        this.mAppsControlDisallowedAdmin = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(activity, "no_control_apps", this.mUserId);
        this.mAppsControlDisallowedBySystem = RestrictedLockUtilsInternal.hasBaseUserRestriction(activity, "no_control_apps", this.mUserId);
        if (!refreshUi()) {
            setIntentAndFinish(true, true);
        }
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        retrieveAppEntry();
        if (this.mPackageInfo == null) {
            return null;
        }
        String packageName = getPackageName();
        ArrayList<AbstractPreferenceController> arrayList = new ArrayList<>();
        Lifecycle settingsLifecycle = getSettingsLifecycle();
        arrayList.add(new AppHeaderViewPreferenceController(context, this, packageName, settingsLifecycle));
        for (AbstractPreferenceController abstractPreferenceController : arrayList) {
            this.mCallbacks.add((Callback) abstractPreferenceController);
        }
        this.mInstantAppButtonPreferenceController = new InstantAppButtonsPreferenceController(context, this, packageName, settingsLifecycle);
        arrayList.add(this.mInstantAppButtonPreferenceController);
        this.mAppButtonsPreferenceController = new AppButtonsPreferenceController((SettingsActivity) getActivity(), this, settingsLifecycle, packageName, this.mState, 0, 5);
        arrayList.add(this.mAppButtonsPreferenceController);
        arrayList.add(new AppBatteryPreferenceController(context, this, packageName, settingsLifecycle));
        arrayList.add(new AppMemoryPreferenceController(context, this, settingsLifecycle));
        arrayList.add(new DefaultHomeShortcutPreferenceController(context, packageName));
        arrayList.add(new DefaultBrowserShortcutPreferenceController(context, packageName));
        arrayList.add(new DefaultPhoneShortcutPreferenceController(context, packageName));
        arrayList.add(new DefaultEmergencyShortcutPreferenceController(context, packageName));
        arrayList.add(new DefaultSmsShortcutPreferenceController(context, packageName));
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void addToCallbackList(Callback callback) {
        if (callback != null) {
            this.mCallbacks.add(callback);
        }
    }

    /* access modifiers changed from: package-private */
    public ApplicationsState.AppEntry getAppEntry() {
        return this.mAppEntry;
    }

    public PackageInfo getPackageInfo() {
        return this.mPackageInfo;
    }

    public void onPackageSizeChanged(String str) {
        if (!TextUtils.equals(str, this.mPackageName)) {
            Log.d("AppInfoDashboard", "Package change irrelevant, skipping");
        } else {
            refreshUi();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean ensurePackageInfoAvailable(Activity activity) {
        if (this.mPackageInfo != null) {
            return true;
        }
        this.mFinishing = true;
        Log.w("AppInfoDashboard", "Package info not available. Is this package already uninstalled?");
        activity.finishAndRemoveTask();
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean ensureDisplayableModule(Activity activity) {
        if (!AppUtils.isHiddenSystemModule(activity.getApplicationContext(), this.mPackageName)) {
            return true;
        }
        this.mFinishing = true;
        Log.w("AppInfoDashboard", "Package is hidden module, exiting: " + this.mPackageName);
        activity.finishAndRemoveTask();
        return false;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menu.add(0, 4, 0, C1715R.string.app_play_store).setIcon(C1715R.C1717drawable.ic_menu_play_store).setShowAsAction(2);
        menu.add(0, 2, 0, C1715R.string.app_factory_reset).setShowAsAction(0);
        menu.add(0, 1, 1, C1715R.string.uninstall_all_users_text).setShowAsAction(0);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (!this.mFinishing) {
            super.onPrepareOptionsMenu(menu);
            boolean z = true;
            menu.findItem(1).setVisible(shouldShowUninstallForAll(this.mAppEntry));
            this.mUpdatedSysApp = (this.mAppEntry.info.flags & 128) != 0;
            MenuItem findItem = menu.findItem(2);
            findItem.setVisible(this.mUserManager.isAdminUser() && this.mUpdatedSysApp && !this.mAppsControlDisallowedBySystem && !getContext().getResources().getBoolean(C1715R.bool.config_disable_uninstall_update));
            if (findItem.isVisible()) {
                RestrictedLockUtilsInternal.setMenuItemAsDisabledByAdmin(getActivity(), findItem, this.mAppsControlDisallowedAdmin);
            }
            MenuItem findItem2 = menu.findItem(4);
            if (Utils.isSystemPackage(getContext().getResources(), this.mPm, this.mPackageInfo) || isAospOrStore(this.mAppEntry.info.packageName)) {
                z = false;
            }
            findItem2.setVisible(z);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            uninstallPkg(this.mAppEntry.info.packageName, true, false);
            return true;
        } else if (itemId == 2) {
            uninstallPkg(this.mAppEntry.info.packageName, false, false);
            return true;
        } else if (itemId != 4) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            openPlayStore(this.mAppEntry.info.packageName);
            return true;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0) {
            getActivity().invalidateOptionsMenu();
        }
        AppButtonsPreferenceController appButtonsPreferenceController = this.mAppButtonsPreferenceController;
        if (appButtonsPreferenceController != null) {
            appButtonsPreferenceController.handleActivityResult(i, i2, intent);
        }
    }

    public void handleDialogClick(int i) {
        AppButtonsPreferenceController appButtonsPreferenceController = this.mAppButtonsPreferenceController;
        if (appButtonsPreferenceController != null) {
            appButtonsPreferenceController.handleDialogClick(i);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldShowUninstallForAll(ApplicationsState.AppEntry appEntry) {
        PackageInfo packageInfo;
        if (this.mUpdatedSysApp || appEntry == null || (appEntry.info.flags & 1) != 0 || (packageInfo = this.mPackageInfo) == null || this.mDpm.packageHasActiveAdmins(packageInfo.packageName) || UserHandle.myUserId() != 0 || this.mUserManager.getUsers().size() < 2) {
            return false;
        }
        if ((getNumberOfUserWithPackageInstalled(this.mPackageName) >= 2 || (appEntry.info.flags & 8388608) == 0) && !AppUtils.isInstant(appEntry.info)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean refreshUi() {
        retrieveAppEntry();
        ApplicationsState.AppEntry appEntry = this.mAppEntry;
        boolean z = false;
        if (appEntry == null || this.mPackageInfo == null) {
            return false;
        }
        this.mState.ensureIcon(appEntry);
        for (Callback refreshUi : this.mCallbacks) {
            refreshUi.refreshUi();
        }
        if (this.mAppButtonsPreferenceController.isAvailable()) {
            this.mAppButtonsPreferenceController.refreshUi();
        }
        if (!this.mInitialized) {
            this.mInitialized = true;
            if ((this.mAppEntry.info.flags & 8388608) == 0) {
                z = true;
            }
            this.mShowUninstalled = z;
        } else {
            try {
                ApplicationInfo applicationInfo = getActivity().getPackageManager().getApplicationInfo(this.mAppEntry.info.packageName, 4194816);
                if (!this.mShowUninstalled) {
                    if ((applicationInfo.flags & 8388608) != 0) {
                        return true;
                    }
                    return false;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            }
        }
        return true;
    }

    private void openPlayStore(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + str));
        startActivity(intent);
    }

    private boolean isAospOrStore(String str) {
        return str.contains("com.android");
    }

    private void uninstallPkg(String str, boolean z, boolean z2) {
        stopListeningToPackageRemove();
        Intent intent = new Intent("android.intent.action.UNINSTALL_PACKAGE", Uri.parse("package:" + str));
        intent.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", z);
        this.mMetricsFeatureProvider.action(getContext(), 872, (Pair<Integer, Object>[]) new Pair[0]);
        startActivityForResult(intent, 0);
    }

    public static void startAppInfoFragment(Class<?> cls, int i, Bundle bundle, SettingsPreferenceFragment settingsPreferenceFragment, ApplicationsState.AppEntry appEntry) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("package", appEntry.info.packageName);
        bundle.putInt("uid", appEntry.info.uid);
        new SubSettingLauncher(settingsPreferenceFragment.getContext()).setDestination(cls.getName()).setArguments(bundle).setTitleRes(i).setResultListener(settingsPreferenceFragment, 1).setSourceMetricsCategory(settingsPreferenceFragment.getMetricsCategory()).launch();
    }

    /* access modifiers changed from: private */
    public void onPackageRemoved() {
        getActivity().finishActivity(1);
        getActivity().finishAndRemoveTask();
    }

    /* access modifiers changed from: package-private */
    public int getNumberOfUserWithPackageInstalled(String str) {
        int i = 0;
        for (UserInfo userInfo : this.mUserManager.getUsers(true)) {
            try {
                if ((this.mPm.getApplicationInfoAsUser(str, 128, userInfo.id).flags & 8388608) != 0) {
                    i++;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("AppInfoDashboard", "Package: " + str + " not found for user: " + userInfo.id);
            }
        }
        return i;
    }

    private String getPackageName() {
        String str = this.mPackageName;
        if (str != null) {
            return str;
        }
        Bundle arguments = getArguments();
        this.mPackageName = arguments != null ? arguments.getString("package") : null;
        if (this.mPackageName == null) {
            Intent intent = arguments == null ? getActivity().getIntent() : (Intent) arguments.getParcelable("intent");
            if (intent != null) {
                this.mPackageName = intent.getData().getSchemeSpecificPart();
            }
        }
        return this.mPackageName;
    }

    /* access modifiers changed from: package-private */
    public void retrieveAppEntry() {
        FragmentActivity activity = getActivity();
        if (activity != null && !this.mFinishing) {
            if (this.mState == null) {
                this.mState = ApplicationsState.getInstance(activity.getApplication());
                this.mSession = this.mState.newSession(this, getSettingsLifecycle());
            }
            this.mUserId = UserHandle.myUserId();
            this.mAppEntry = this.mState.getEntry(getPackageName(), UserHandle.myUserId());
            if (this.mAppEntry != null) {
                try {
                    this.mPackageInfo = activity.getPackageManager().getPackageInfo(this.mAppEntry.info.packageName, 4198976);
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("AppInfoDashboard", "Exception when retrieving package:" + this.mAppEntry.info.packageName, e);
                }
            } else {
                Log.w("AppInfoDashboard", "Missing AppEntry; maybe reinstalling?");
                this.mPackageInfo = null;
            }
        }
    }

    private void setIntentAndFinish(boolean z, boolean z2) {
        Intent intent = new Intent();
        intent.putExtra(AppButtonsPreferenceController.APP_CHG, z2);
        ((SettingsActivity) getActivity()).finishPreferencePanel(-1, intent);
        this.mFinishing = true;
    }

    public void onPackageListChanged() {
        if (!refreshUi()) {
            setIntentAndFinish(true, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void startListeningToPackageRemove() {
        if (!this.mListeningToPackageRemove) {
            this.mListeningToPackageRemove = true;
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addDataScheme("package");
            getContext().registerReceiver(this.mPackageRemovedReceiver, intentFilter);
        }
    }

    private void stopListeningToPackageRemove() {
        if (this.mListeningToPackageRemove) {
            this.mListeningToPackageRemove = false;
            getContext().unregisterReceiver(this.mPackageRemovedReceiver);
        }
    }
}
