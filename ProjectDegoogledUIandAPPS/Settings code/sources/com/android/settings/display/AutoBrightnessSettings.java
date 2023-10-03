package com.android.settings.display;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class AutoBrightnessSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.auto_brightness_detail;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }
    };

    public int getHelpResource() {
        return C1715R.string.help_url_auto_brightness;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "AutoBrightnessSettings";
    }

    public int getMetricsCategory() {
        return 1381;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.auto_brightness_detail;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.auto_brightness_description_new);
    }
}
