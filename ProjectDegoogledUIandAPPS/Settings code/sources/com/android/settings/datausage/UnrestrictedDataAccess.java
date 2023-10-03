package com.android.settings.datausage;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.applications.ApplicationsState;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class UnrestrictedDataAccess extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.unrestricted_data_access_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private ApplicationsState.AppFilter mFilter;
    private boolean mShowSystem;

    public int getHelpResource() {
        return C1715R.string.help_url_unrestricted_data_access;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "UnrestrictedDataAccess";
    }

    public int getMetricsCategory() {
        return 349;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.unrestricted_data_access_settings;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mShowSystem = bundle != null && bundle.getBoolean("show_system");
        ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).setParentFragment(this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 43, 0, this.mShowSystem ? C1715R.string.menu_hide_system : C1715R.string.menu_show_system);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ApplicationsState.AppFilter appFilter;
        if (menuItem.getItemId() == 43) {
            this.mShowSystem = !this.mShowSystem;
            menuItem.setTitle(this.mShowSystem ? C1715R.string.menu_hide_system : C1715R.string.menu_show_system);
            if (this.mShowSystem) {
                appFilter = ApplicationsState.FILTER_ALL_ENABLED;
            } else {
                appFilter = ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER;
            }
            this.mFilter = appFilter;
            ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).setFilter(this.mFilter);
            ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).rebuild();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("show_system", this.mShowSystem);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onAttach(Context context) {
        ApplicationsState.AppFilter appFilter;
        super.onAttach(context);
        if (this.mShowSystem) {
            appFilter = ApplicationsState.FILTER_ALL_ENABLED;
        } else {
            appFilter = ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER;
        }
        this.mFilter = appFilter;
        ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).setSession(getSettingsLifecycle());
        ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).setFilter(this.mFilter);
    }
}
