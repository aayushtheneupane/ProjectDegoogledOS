package com.android.settings.location;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.settings.location.RecentLocationRequestPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.location.RecentLocationApps;
import com.android.settingslib.widget.apppreference.AppPreference;
import com.havoc.config.center.C1715R;
import java.util.List;

public class RecentLocationRequestSeeAllPreferenceController extends LocationBasePreferenceController {
    private PreferenceCategory mCategoryAllRecentLocationRequests;
    private final RecentLocationRequestSeeAllFragment mFragment;
    private Preference mPreference;
    private RecentLocationApps mRecentLocationApps;
    private boolean mShowSystem;

    public String getPreferenceKey() {
        return "all_recent_location_requests";
    }

    public RecentLocationRequestSeeAllPreferenceController(Context context, Lifecycle lifecycle, RecentLocationRequestSeeAllFragment recentLocationRequestSeeAllFragment) {
        this(context, lifecycle, recentLocationRequestSeeAllFragment, new RecentLocationApps(context));
    }

    RecentLocationRequestSeeAllPreferenceController(Context context, Lifecycle lifecycle, RecentLocationRequestSeeAllFragment recentLocationRequestSeeAllFragment, RecentLocationApps recentLocationApps) {
        super(context, lifecycle);
        this.mShowSystem = false;
        this.mFragment = recentLocationRequestSeeAllFragment;
        this.mRecentLocationApps = recentLocationApps;
    }

    public void onLocationModeChanged(int i, boolean z) {
        this.mCategoryAllRecentLocationRequests.setEnabled(this.mLocationEnabler.isEnabled(i));
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mCategoryAllRecentLocationRequests = (PreferenceCategory) preferenceScreen.findPreference("all_recent_location_requests");
    }

    public void updateState(Preference preference) {
        this.mCategoryAllRecentLocationRequests.removeAll();
        this.mPreference = preference;
        List<RecentLocationApps.Request> appListSorted = this.mRecentLocationApps.getAppListSorted(this.mShowSystem);
        if (appListSorted.isEmpty()) {
            AppPreference appPreference = new AppPreference(this.mContext);
            appPreference.setTitle((int) C1715R.string.location_no_recent_apps);
            appPreference.setSelectable(false);
            this.mCategoryAllRecentLocationRequests.addPreference(appPreference);
            return;
        }
        for (RecentLocationApps.Request createAppPreference : appListSorted) {
            this.mCategoryAllRecentLocationRequests.addPreference(createAppPreference(preference.getContext(), createAppPreference));
        }
    }

    /* access modifiers changed from: package-private */
    public AppPreference createAppPreference(Context context, RecentLocationApps.Request request) {
        AppPreference appPreference = new AppPreference(context);
        appPreference.setSummary(request.contentDescription);
        appPreference.setIcon(request.icon);
        appPreference.setTitle(request.label);
        appPreference.setOnPreferenceClickListener(new RecentLocationRequestPreferenceController.PackageEntryClickedListener(this.mFragment, request.packageName, request.userHandle));
        return appPreference;
    }

    public void setShowSystem(boolean z) {
        this.mShowSystem = z;
        Preference preference = this.mPreference;
        if (preference != null) {
            updateState(preference);
        }
    }
}
