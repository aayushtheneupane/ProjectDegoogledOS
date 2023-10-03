package com.android.settings.privacy;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.View;
import com.android.settings.Utils;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.notification.LockScreenNotificationPreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class PrivacyDashboardFragment extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.privacy_dashboard_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return PrivacyDashboardFragment.buildPreferenceControllers(context, (Lifecycle) null);
        }
    };
    View mProgressAnimation;
    View mProgressHeader;

    public int getHelpResource() {
        return C1715R.string.help_url_privacy_dashboard;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "PrivacyDashboardFrag";
    }

    public int getMetricsCategory() {
        return 1587;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.privacy_dashboard_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((PermissionBarChartPreferenceController) use(PermissionBarChartPreferenceController.class)).setFragment(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Utils.setActionBarShadowAnimation(getActivity(), getSettingsLifecycle(), getListView());
        initLoadingBar();
    }

    /* access modifiers changed from: package-private */
    public void initLoadingBar() {
        this.mProgressHeader = setPinnedHeaderView((int) C1715R.layout.progress_header);
        this.mProgressAnimation = this.mProgressHeader.findViewById(C1715R.C1718id.progress_bar_animation);
        setLoadingEnabled(false);
    }

    /* access modifiers changed from: package-private */
    public void setLoadingEnabled(boolean z) {
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
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        LockScreenNotificationPreferenceController lockScreenNotificationPreferenceController = new LockScreenNotificationPreferenceController(context, "privacy_lock_screen_notifications", "privacy_work_profile_notifications_category", "privacy_lock_screen_work_profile_notifications");
        if (lifecycle != null) {
            lifecycle.addObserver(lockScreenNotificationPreferenceController);
        }
        arrayList.add(lockScreenNotificationPreferenceController);
        return arrayList;
    }
}
