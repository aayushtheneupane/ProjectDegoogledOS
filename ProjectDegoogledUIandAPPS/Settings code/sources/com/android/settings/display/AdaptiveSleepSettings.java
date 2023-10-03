package com.android.settings.display;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import androidx.preference.Preference;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.widget.FooterPreference;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class AdaptiveSleepSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.adaptive_sleep_detail;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }
    };
    private Context mContext;

    public int getHelpResource() {
        return C1715R.string.help_url_adaptive_sleep;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "AdaptiveSleepSettings";
    }

    public int getMetricsCategory() {
        return 1628;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.adaptive_sleep_detail;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FooterPreference createFooterPreference = this.mFooterPreferenceMixin.createFooterPreference();
        this.mContext = getContext();
        createFooterPreference.setIcon((int) C1715R.C1717drawable.ic_privacy_shield_24dp);
        createFooterPreference.setTitle((int) C1715R.string.adaptive_sleep_privacy);
        Preference findPreference = findPreference("adaptive_sleep_permission");
        if (findPreference != null) {
            findPreference.setVisible(false);
        }
        this.mContext.getSharedPreferences("adaptive_sleep_slice", 0).edit().putBoolean("adaptive_sleep_interacted", true).apply();
    }
}
