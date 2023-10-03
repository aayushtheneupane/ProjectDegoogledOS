package com.android.settings.development.featureflags;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.development.DevelopmentSettingsEnabler;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class FeatureFlagsDashboard extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.feature_flags_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(context);
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return FeatureFlagsDashboard.buildPrefControllers(context, (Lifecycle) null);
        }
    };

    public int getHelpResource() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "FeatureFlagsDashboard";
    }

    public int getMetricsCategory() {
        return 1217;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.feature_flags_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((FeatureFlagFooterPreferenceController) use(FeatureFlagFooterPreferenceController.class)).setFooterMixin(this.mFooterPreferenceMixin);
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPrefControllers(context, getSettingsLifecycle());
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPrefControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        FeatureFlagFooterPreferenceController featureFlagFooterPreferenceController = new FeatureFlagFooterPreferenceController(context);
        if (lifecycle != null) {
            lifecycle.addObserver(featureFlagFooterPreferenceController);
        }
        arrayList.add(featureFlagFooterPreferenceController);
        return arrayList;
    }
}
