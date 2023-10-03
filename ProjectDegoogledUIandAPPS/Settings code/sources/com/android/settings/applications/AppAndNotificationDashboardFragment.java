package com.android.settings.applications;

import android.app.usage.UsageStats;
import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.View;
import com.android.settings.Utils;
import com.android.settings.applications.RecentAppStatsMixin;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.notification.EmergencyBroadcastPreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppAndNotificationDashboardFragment extends DashboardFragment implements RecentAppStatsMixin.RecentAppStatsListener {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.app_and_notification;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return AppAndNotificationDashboardFragment.buildPreferenceControllers(context);
        }
    };
    private AllAppsInfoPreferenceController mAllAppsInfoPreferenceController;
    private View mProgressAnimation;
    private View mProgressHeader;
    private RecentAppStatsMixin mRecentAppStatsMixin;
    private RecentAppsPreferenceController mRecentAppsPreferenceController;

    public int getHelpResource() {
        return C1715R.string.help_url_apps_and_notifications;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "AppAndNotifDashboard";
    }

    public int getMetricsCategory() {
        return 748;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.app_and_notification;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((SpecialAppAccessPreferenceController) use(SpecialAppAccessPreferenceController.class)).setSession(getSettingsLifecycle());
        this.mRecentAppStatsMixin = new RecentAppStatsMixin(context, 3);
        getSettingsLifecycle().addObserver(this.mRecentAppStatsMixin);
        this.mRecentAppStatsMixin.addListener(this);
        this.mRecentAppsPreferenceController = (RecentAppsPreferenceController) use(RecentAppsPreferenceController.class);
        this.mRecentAppsPreferenceController.setFragment(this);
        this.mRecentAppStatsMixin.addListener(this.mRecentAppsPreferenceController);
        this.mAllAppsInfoPreferenceController = (AllAppsInfoPreferenceController) use(AllAppsInfoPreferenceController.class);
        this.mRecentAppStatsMixin.addListener(this.mAllAppsInfoPreferenceController);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mProgressHeader = setPinnedHeaderView((int) C1715R.layout.progress_header);
        this.mProgressAnimation = this.mProgressHeader.findViewById(C1715R.C1718id.progress_bar_animation);
        setLoadingEnabled(false);
    }

    public void onStart() {
        super.onStart();
        setLoadingEnabled(true);
    }

    public void onReloadDataCompleted(List<UsageStats> list) {
        setLoadingEnabled(false);
        if (!list.isEmpty()) {
            Utils.setActionBarShadowAnimation(getActivity(), getSettingsLifecycle(), getListView());
        }
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context);
    }

    private void setLoadingEnabled(boolean z) {
        View view = this.mProgressHeader;
        if (view != null && this.mProgressAnimation != null) {
            int i = 0;
            view.setVisibility(z ? 0 : 4);
            View view2 = this.mProgressAnimation;
            if (!z) {
                i = 4;
            }
            view2.setVisibility(i);
        }
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EmergencyBroadcastPreferenceController(context, "app_and_notif_cell_broadcast_settings"));
        return arrayList;
    }
}
