package com.android.settings.location;

import android.content.Context;
import android.provider.SearchIndexableResource;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecentLocationRequestSeeAllFragment extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.location_recent_requests_see_all;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> getPreferenceControllers(Context context) {
            return RecentLocationRequestSeeAllFragment.buildPreferenceControllers(context, (Lifecycle) null, (RecentLocationRequestSeeAllFragment) null);
        }
    };
    private RecentLocationRequestSeeAllPreferenceController mController;
    private MenuItem mHideSystemMenu;
    private boolean mShowSystem = false;
    private MenuItem mShowSystemMenu;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "RecentLocationReqAll";
    }

    public int getMetricsCategory() {
        return 1325;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.location_recent_requests_see_all;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle(), this);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != 2 && itemId != 3) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.mShowSystem = menuItem.getItemId() == 2;
        updateMenu();
        RecentLocationRequestSeeAllPreferenceController recentLocationRequestSeeAllPreferenceController = this.mController;
        if (recentLocationRequestSeeAllPreferenceController != null) {
            recentLocationRequestSeeAllPreferenceController.setShowSystem(this.mShowSystem);
        }
        return true;
    }

    private void updateMenu() {
        this.mShowSystemMenu.setVisible(!this.mShowSystem);
        this.mHideSystemMenu.setVisible(this.mShowSystem);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle, RecentLocationRequestSeeAllFragment recentLocationRequestSeeAllFragment) {
        ArrayList arrayList = new ArrayList();
        RecentLocationRequestSeeAllPreferenceController recentLocationRequestSeeAllPreferenceController = new RecentLocationRequestSeeAllPreferenceController(context, lifecycle, recentLocationRequestSeeAllFragment);
        arrayList.add(recentLocationRequestSeeAllPreferenceController);
        if (recentLocationRequestSeeAllFragment != null) {
            recentLocationRequestSeeAllFragment.mController = recentLocationRequestSeeAllPreferenceController;
        }
        return arrayList;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        this.mShowSystemMenu = menu.add(0, 2, 0, C1715R.string.menu_show_system);
        this.mHideSystemMenu = menu.add(0, 3, 0, C1715R.string.menu_hide_system);
        updateMenu();
    }
}
