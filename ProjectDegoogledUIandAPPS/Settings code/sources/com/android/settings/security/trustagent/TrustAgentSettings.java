package com.android.settings.security.trustagent;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class TrustAgentSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.trust_agent_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };

    public int getHelpResource() {
        return C1715R.string.help_url_trust_agent;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "TrustAgentSettings";
    }

    public int getMetricsCategory() {
        return 91;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.trust_agent_settings;
    }
}
