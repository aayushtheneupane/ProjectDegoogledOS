package com.android.settings.notification;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.util.ArraySet;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.AppInfoBase;
import com.android.settings.applications.specialaccess.zenaccess.ZenAccessController;
import com.android.settings.applications.specialaccess.zenaccess.ZenAccessDetails;
import com.android.settings.applications.specialaccess.zenaccess.ZenAccessSettingObserverMixin;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.EmptyTextSettings;
import com.android.settingslib.widget.apppreference.AppPreference;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ZenAccessSettings extends EmptyTextSettings implements ZenAccessSettingObserverMixin.Listener {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.zen_access_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private final String TAG = "ZenAccessSettings";
    private Context mContext;
    private NotificationManager mNoMan;
    private PackageManager mPkgMan;

    public int getMetricsCategory() {
        return 180;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.zen_access_settings;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        this.mPkgMan = this.mContext.getPackageManager();
        this.mNoMan = (NotificationManager) this.mContext.getSystemService(NotificationManager.class);
        getSettingsLifecycle().addObserver(new ZenAccessSettingObserverMixin(getContext(), this));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setEmptyText(C1715R.string.zen_access_empty_text);
    }

    public void onResume() {
        super.onResume();
        if (!ActivityManager.isLowRamDeviceStatic()) {
            reloadList();
        } else {
            setEmptyText(C1715R.string.disabled_low_ram_device);
        }
    }

    public void onZenAccessPolicyChanged() {
        reloadList();
    }

    private void reloadList() {
        List<ApplicationInfo> installedApplications;
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        ArrayList arrayList = new ArrayList();
        Set<String> packagesRequestingNotificationPolicyAccess = ZenAccessController.getPackagesRequestingNotificationPolicyAccess();
        if (!packagesRequestingNotificationPolicyAccess.isEmpty() && (installedApplications = this.mPkgMan.getInstalledApplications(0)) != null) {
            for (ApplicationInfo next : installedApplications) {
                if (packagesRequestingNotificationPolicyAccess.contains(next.packageName)) {
                    arrayList.add(next);
                }
            }
        }
        ArraySet arraySet = new ArraySet();
        arraySet.addAll(this.mNoMan.getEnabledNotificationListenerPackages());
        packagesRequestingNotificationPolicyAccess.addAll(arraySet);
        Collections.sort(arrayList, new PackageItemInfo.DisplayNameComparator(this.mPkgMan));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ApplicationInfo applicationInfo = (ApplicationInfo) it.next();
            String str = applicationInfo.packageName;
            CharSequence loadLabel = applicationInfo.loadLabel(this.mPkgMan);
            AppPreference appPreference = new AppPreference(getPrefContext());
            appPreference.setKey(str);
            appPreference.setIcon(applicationInfo.loadIcon(this.mPkgMan));
            appPreference.setTitle(loadLabel);
            if (arraySet.contains(str)) {
                appPreference.setEnabled(false);
                appPreference.setSummary((CharSequence) getString(C1715R.string.zen_access_disabled_package_warning));
            } else {
                appPreference.setSummary(getPreferenceSummary(str));
            }
            appPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(str, applicationInfo) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ ApplicationInfo f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final boolean onPreferenceClick(Preference preference) {
                    return ZenAccessSettings.this.lambda$reloadList$0$ZenAccessSettings(this.f$1, this.f$2, preference);
                }
            });
            preferenceScreen.addPreference(appPreference);
        }
    }

    public /* synthetic */ boolean lambda$reloadList$0$ZenAccessSettings(String str, ApplicationInfo applicationInfo, Preference preference) {
        AppInfoBase.startAppInfoFragment(ZenAccessDetails.class, C1715R.string.manage_zen_access_title, str, applicationInfo.uid, this, -1, getMetricsCategory());
        return true;
    }

    private int getPreferenceSummary(String str) {
        return ZenAccessController.hasAccess(getContext(), str) ? C1715R.string.app_permission_summary_allowed : C1715R.string.app_permission_summary_not_allowed;
    }
}
