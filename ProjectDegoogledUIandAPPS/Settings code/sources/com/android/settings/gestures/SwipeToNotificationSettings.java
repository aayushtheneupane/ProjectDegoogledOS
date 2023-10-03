package com.android.settings.gestures;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class SwipeToNotificationSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.swipe_to_notification_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return SwipeToNotificationPreferenceController.isAvailable(context);
        }
    };

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "SwipeToNotifSettings";
    }

    public int getMetricsCategory() {
        return 751;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.swipe_to_notification_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        FeatureFactory.getFactory(context).getSuggestionFeatureProvider(context).getSharedPrefs(context).edit().putBoolean("pref_swipe_to_notification_suggestion_complete", true).apply();
    }
}
