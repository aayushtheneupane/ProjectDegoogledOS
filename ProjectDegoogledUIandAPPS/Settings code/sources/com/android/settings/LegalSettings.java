package com.android.settings;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class LegalSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.about_legal;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }
    };

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "LegalSettings";
    }

    public int getMetricsCategory() {
        return 225;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.about_legal;
    }
}
