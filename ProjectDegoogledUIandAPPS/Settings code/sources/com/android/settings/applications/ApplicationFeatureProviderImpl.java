package com.android.settings.applications;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.location.LocationManager;
import android.os.RemoteException;
import android.os.UserManager;
import android.telecom.DefaultDialerManager;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.internal.telephony.SmsApplication;
import com.android.settings.applications.ApplicationFeatureProvider;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ApplicationFeatureProviderImpl implements ApplicationFeatureProvider {
    protected final Context mContext;
    private final DevicePolicyManager mDpm;
    private final PackageManager mPm;
    private final IPackageManager mPms;
    private final UserManager mUm = UserManager.get(this.mContext);

    public ApplicationFeatureProviderImpl(Context context, PackageManager packageManager, IPackageManager iPackageManager, DevicePolicyManager devicePolicyManager) {
        this.mContext = context.getApplicationContext();
        this.mPm = packageManager;
        this.mPms = iPackageManager;
        this.mDpm = devicePolicyManager;
    }

    public void calculateNumberOfPolicyInstalledApps(boolean z, ApplicationFeatureProvider.NumberOfAppsCallback numberOfAppsCallback) {
        CurrentUserAndManagedProfilePolicyInstalledAppCounter currentUserAndManagedProfilePolicyInstalledAppCounter = new CurrentUserAndManagedProfilePolicyInstalledAppCounter(this.mContext, this.mPm, numberOfAppsCallback);
        if (z) {
            currentUserAndManagedProfilePolicyInstalledAppCounter.execute(new Void[0]);
        } else {
            currentUserAndManagedProfilePolicyInstalledAppCounter.executeInForeground();
        }
    }

    public void listPolicyInstalledApps(ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
        new CurrentUserPolicyInstalledAppLister(this.mPm, this.mUm, listOfAppsCallback).execute(new Void[0]);
    }

    public void calculateNumberOfAppsWithAdminGrantedPermissions(String[] strArr, boolean z, ApplicationFeatureProvider.NumberOfAppsCallback numberOfAppsCallback) {
        C0531xa96aca6e currentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter = new C0531xa96aca6e(this.mContext, strArr, this.mPm, this.mPms, this.mDpm, numberOfAppsCallback);
        if (z) {
            currentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter.execute(new Void[0]);
        } else {
            currentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter.executeInForeground();
        }
    }

    public void listAppsWithAdminGrantedPermissions(String[] strArr, ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
        new CurrentUserAppWithAdminGrantedPermissionsLister(strArr, this.mPm, this.mPms, this.mDpm, this.mUm, listOfAppsCallback).execute(new Void[0]);
    }

    public List<UserAppInfo> findPersistentPreferredActivities(int i, Intent[] intentArr) {
        ArrayList arrayList = new ArrayList();
        ArraySet arraySet = new ArraySet();
        UserInfo userInfo = this.mUm.getUserInfo(i);
        for (Intent findPersistentPreferredActivity : intentArr) {
            try {
                ResolveInfo findPersistentPreferredActivity2 = this.mPms.findPersistentPreferredActivity(findPersistentPreferredActivity, i);
                if (findPersistentPreferredActivity2 != null) {
                    ComponentInfo componentInfo = null;
                    if (findPersistentPreferredActivity2.activityInfo != null) {
                        componentInfo = findPersistentPreferredActivity2.activityInfo;
                    } else if (findPersistentPreferredActivity2.serviceInfo != null) {
                        componentInfo = findPersistentPreferredActivity2.serviceInfo;
                    } else if (findPersistentPreferredActivity2.providerInfo != null) {
                        componentInfo = findPersistentPreferredActivity2.providerInfo;
                    }
                    if (componentInfo != null) {
                        UserAppInfo userAppInfo = new UserAppInfo(userInfo, componentInfo.applicationInfo);
                        if (arraySet.add(userAppInfo)) {
                            arrayList.add(userAppInfo);
                        }
                    }
                }
            } catch (RemoteException unused) {
            }
        }
        return arrayList;
    }

    public Set<String> getKeepEnabledPackages() {
        ArraySet arraySet = new ArraySet();
        String defaultDialerApplication = DefaultDialerManager.getDefaultDialerApplication(this.mContext);
        if (!TextUtils.isEmpty(defaultDialerApplication)) {
            arraySet.add(defaultDialerApplication);
        }
        ComponentName defaultSmsApplication = SmsApplication.getDefaultSmsApplication(this.mContext, true);
        if (defaultSmsApplication != null) {
            arraySet.add(defaultSmsApplication.getPackageName());
        }
        arraySet.addAll(getEnabledPackageWhitelist());
        String extraLocationControllerPackage = ((LocationManager) this.mContext.getSystemService("location")).getExtraLocationControllerPackage();
        if (extraLocationControllerPackage != null) {
            arraySet.add(extraLocationControllerPackage);
        }
        return arraySet;
    }

    private Set<String> getEnabledPackageWhitelist() {
        ArraySet arraySet = new ArraySet();
        arraySet.add(this.mContext.getString(C1715R.string.config_settingsintelligence_package_name));
        arraySet.add(this.mContext.getString(C1715R.string.config_package_installer_package_name));
        return arraySet;
    }

    private static class CurrentUserAndManagedProfilePolicyInstalledAppCounter extends InstalledAppCounter {
        private ApplicationFeatureProvider.NumberOfAppsCallback mCallback;

        CurrentUserAndManagedProfilePolicyInstalledAppCounter(Context context, PackageManager packageManager, ApplicationFeatureProvider.NumberOfAppsCallback numberOfAppsCallback) {
            super(context, 1, packageManager);
            this.mCallback = numberOfAppsCallback;
        }

        /* access modifiers changed from: protected */
        public void onCountComplete(int i) {
            this.mCallback.onNumberOfAppsResult(i);
        }
    }

    /* renamed from: com.android.settings.applications.ApplicationFeatureProviderImpl$CurrentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter */
    private static class C0531xa96aca6e extends AppWithAdminGrantedPermissionsCounter {
        private ApplicationFeatureProvider.NumberOfAppsCallback mCallback;

        C0531xa96aca6e(Context context, String[] strArr, PackageManager packageManager, IPackageManager iPackageManager, DevicePolicyManager devicePolicyManager, ApplicationFeatureProvider.NumberOfAppsCallback numberOfAppsCallback) {
            super(context, strArr, packageManager, iPackageManager, devicePolicyManager);
            this.mCallback = numberOfAppsCallback;
        }

        /* access modifiers changed from: protected */
        public void onCountComplete(int i) {
            this.mCallback.onNumberOfAppsResult(i);
        }
    }

    private static class CurrentUserPolicyInstalledAppLister extends InstalledAppLister {
        private ApplicationFeatureProvider.ListOfAppsCallback mCallback;

        CurrentUserPolicyInstalledAppLister(PackageManager packageManager, UserManager userManager, ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
            super(packageManager, userManager);
            this.mCallback = listOfAppsCallback;
        }

        /* access modifiers changed from: protected */
        public void onAppListBuilt(List<UserAppInfo> list) {
            this.mCallback.onListOfAppsResult(list);
        }
    }

    private static class CurrentUserAppWithAdminGrantedPermissionsLister extends AppWithAdminGrantedPermissionsLister {
        private ApplicationFeatureProvider.ListOfAppsCallback mCallback;

        CurrentUserAppWithAdminGrantedPermissionsLister(String[] strArr, PackageManager packageManager, IPackageManager iPackageManager, DevicePolicyManager devicePolicyManager, UserManager userManager, ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
            super(strArr, packageManager, iPackageManager, devicePolicyManager, userManager);
            this.mCallback = listOfAppsCallback;
        }

        /* access modifiers changed from: protected */
        public void onAppListBuilt(List<UserAppInfo> list) {
            this.mCallback.onListOfAppsResult(list);
        }
    }
}
