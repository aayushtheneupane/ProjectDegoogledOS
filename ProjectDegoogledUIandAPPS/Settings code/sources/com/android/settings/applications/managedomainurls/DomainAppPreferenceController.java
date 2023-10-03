package com.android.settings.applications.managedomainurls;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.IconDrawableFactory;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.AppInfoBase;
import com.android.settings.applications.AppLaunchSettings;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.applications.ApplicationsState;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Map;

public class DomainAppPreferenceController extends BasePreferenceController implements ApplicationsState.Callbacks {
    private static final int INSTALLED_APP_DETAILS = 1;
    private ApplicationsState mApplicationsState = ApplicationsState.getInstance((Application) this.mContext.getApplicationContext());
    private PreferenceGroup mDomainAppList;
    private ManageDomainUrls mFragment;
    private int mMetricsCategory;
    private Map<String, Preference> mPreferenceCache;
    private ApplicationsState.Session mSession;

    public int getAvailabilityStatus() {
        return 0;
    }

    public void onAllSizesComputed() {
    }

    public void onLauncherInfoChanged() {
    }

    public void onPackageIconChanged() {
    }

    public void onPackageListChanged() {
    }

    public void onPackageSizeChanged(String str) {
    }

    public void onRunningStateChanged(boolean z) {
    }

    public DomainAppPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mDomainAppList = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!(preference instanceof DomainAppPreference)) {
            return false;
        }
        ApplicationsState.AppEntry entry = ((DomainAppPreference) preference).getEntry();
        AppInfoBase.startAppInfoFragment(AppLaunchSettings.class, C1715R.string.auto_launch_label, entry.info.packageName, entry.info.uid, this.mFragment, 1, this.mMetricsCategory);
        return true;
    }

    public void setFragment(ManageDomainUrls manageDomainUrls) {
        this.mFragment = manageDomainUrls;
        this.mMetricsCategory = manageDomainUrls.getMetricsCategory();
        this.mSession = this.mApplicationsState.newSession(this, this.mFragment.getSettingsLifecycle());
    }

    public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> arrayList) {
        if (this.mContext != null) {
            rebuildAppList(this.mDomainAppList, arrayList);
        }
    }

    public void onLoadEntriesCompleted() {
        rebuild();
    }

    private void cacheAllPrefs(PreferenceGroup preferenceGroup) {
        this.mPreferenceCache = new ArrayMap();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            if (!TextUtils.isEmpty(preference.getKey())) {
                this.mPreferenceCache.put(preference.getKey(), preference);
            }
        }
    }

    private Preference getCachedPreference(String str) {
        Map<String, Preference> map = this.mPreferenceCache;
        if (map != null) {
            return map.remove(str);
        }
        return null;
    }

    private void removeCachedPrefs(PreferenceGroup preferenceGroup) {
        for (Preference removePreference : this.mPreferenceCache.values()) {
            preferenceGroup.removePreference(removePreference);
        }
        this.mPreferenceCache = null;
    }

    private void rebuild() {
        ArrayList<ApplicationsState.AppEntry> rebuild = this.mSession.rebuild(ApplicationsState.FILTER_WITH_DOMAIN_URLS, ApplicationsState.ALPHA_COMPARATOR);
        if (rebuild != null) {
            onRebuildComplete(rebuild);
        }
    }

    private void rebuildAppList(PreferenceGroup preferenceGroup, ArrayList<ApplicationsState.AppEntry> arrayList) {
        cacheAllPrefs(preferenceGroup);
        int size = arrayList.size();
        Context context = preferenceGroup.getContext();
        IconDrawableFactory newInstance = IconDrawableFactory.newInstance(context);
        for (int i = 0; i < size; i++) {
            ApplicationsState.AppEntry appEntry = arrayList.get(i);
            String str = appEntry.info.packageName + "|" + appEntry.info.uid;
            DomainAppPreference domainAppPreference = (DomainAppPreference) getCachedPreference(str);
            if (domainAppPreference == null) {
                domainAppPreference = new DomainAppPreference(context, newInstance, appEntry);
                domainAppPreference.setKey(str);
                preferenceGroup.addPreference(domainAppPreference);
            } else {
                domainAppPreference.reuse();
            }
            domainAppPreference.setOrder(i);
        }
        removeCachedPrefs(preferenceGroup);
    }
}
