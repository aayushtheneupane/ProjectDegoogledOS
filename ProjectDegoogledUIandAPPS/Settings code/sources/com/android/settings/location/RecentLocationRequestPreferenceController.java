package com.android.settings.location;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.appinfo.AppInfoDashboardFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.location.RecentLocationApps;
import com.android.settingslib.widget.apppreference.AppPreference;
import com.havoc.config.center.C1715R;
import java.util.List;

public class RecentLocationRequestPreferenceController extends LocationBasePreferenceController {
    static final String KEY_SEE_ALL_BUTTON = "recent_location_requests_see_all_button";
    private PreferenceCategory mCategoryRecentLocationRequests;
    private final LocationSettings mFragment;
    private final RecentLocationApps mRecentLocationApps;

    public String getPreferenceKey() {
        return "recent_location_requests";
    }

    static class PackageEntryClickedListener implements Preference.OnPreferenceClickListener {
        private final DashboardFragment mFragment;
        private final String mPackage;
        private final UserHandle mUserHandle;

        public PackageEntryClickedListener(DashboardFragment dashboardFragment, String str, UserHandle userHandle) {
            this.mFragment = dashboardFragment;
            this.mPackage = str;
            this.mUserHandle = userHandle;
        }

        public boolean onPreferenceClick(Preference preference) {
            Bundle bundle = new Bundle();
            bundle.putString("package", this.mPackage);
            new SubSettingLauncher(this.mFragment.getContext()).setDestination(AppInfoDashboardFragment.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.application_info_label).setUserHandle(this.mUserHandle).setSourceMetricsCategory(this.mFragment.getMetricsCategory()).launch();
            return true;
        }
    }

    public RecentLocationRequestPreferenceController(Context context, LocationSettings locationSettings, Lifecycle lifecycle) {
        this(context, locationSettings, lifecycle, new RecentLocationApps(context));
    }

    RecentLocationRequestPreferenceController(Context context, LocationSettings locationSettings, Lifecycle lifecycle, RecentLocationApps recentLocationApps) {
        super(context, lifecycle);
        this.mFragment = locationSettings;
        this.mRecentLocationApps = recentLocationApps;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mCategoryRecentLocationRequests = (PreferenceCategory) preferenceScreen.findPreference("recent_location_requests");
    }

    public void updateState(Preference preference) {
        this.mCategoryRecentLocationRequests.removeAll();
        Context context = preference.getContext();
        List<RecentLocationApps.Request> appListSorted = this.mRecentLocationApps.getAppListSorted(false);
        if (appListSorted.size() > 3) {
            for (int i = 0; i < 3; i++) {
                this.mCategoryRecentLocationRequests.addPreference(createAppPreference(context, appListSorted.get(i)));
            }
        } else if (appListSorted.size() > 0) {
            for (RecentLocationApps.Request createAppPreference : appListSorted) {
                this.mCategoryRecentLocationRequests.addPreference(createAppPreference(context, createAppPreference));
            }
        } else {
            AppPreference createAppPreference2 = createAppPreference(context);
            createAppPreference2.setTitle((int) C1715R.string.location_no_recent_apps);
            createAppPreference2.setSelectable(false);
            this.mCategoryRecentLocationRequests.addPreference(createAppPreference2);
        }
    }

    public void onLocationModeChanged(int i, boolean z) {
        this.mCategoryRecentLocationRequests.setEnabled(this.mLocationEnabler.isEnabled(i));
    }

    /* access modifiers changed from: package-private */
    public AppPreference createAppPreference(Context context) {
        return new AppPreference(context);
    }

    /* access modifiers changed from: package-private */
    public AppPreference createAppPreference(Context context, RecentLocationApps.Request request) {
        AppPreference createAppPreference = createAppPreference(context);
        createAppPreference.setSummary(request.contentDescription);
        createAppPreference.setIcon(request.icon);
        createAppPreference.setTitle(request.label);
        createAppPreference.setOnPreferenceClickListener(new PackageEntryClickedListener(this.mFragment, request.packageName, request.userHandle));
        return createAppPreference;
    }
}
