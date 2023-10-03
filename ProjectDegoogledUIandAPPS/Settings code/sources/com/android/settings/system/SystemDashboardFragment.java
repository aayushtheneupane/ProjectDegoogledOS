package com.android.settings.system;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class SystemDashboardFragment extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.system_dashboard_fragment;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }
    };

    public int getHelpResource() {
        return C1715R.string.help_url_system_dashboard;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "SystemDashboardFrag";
    }

    public int getMetricsCategory() {
        return 744;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.system_dashboard_fragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (getVisiblePreferenceCount(preferenceScreen) == preferenceScreen.getInitialExpandedChildrenCount() + 1) {
            preferenceScreen.setInitialExpandedChildrenCount(Integer.MAX_VALUE);
        }
        showRestrictionDialog();
    }

    public void showRestrictionDialog() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getBoolean("show_aware_dialog_disabled", false)) {
            FeatureFactory.getFactory(getContext()).getAwareFeatureProvider().showRestrictionDialog(this);
        }
    }

    private int getVisiblePreferenceCount(PreferenceGroup preferenceGroup) {
        int i = 0;
        for (int i2 = 0; i2 < preferenceGroup.getPreferenceCount(); i2++) {
            Preference preference = preferenceGroup.getPreference(i2);
            if (preference instanceof PreferenceGroup) {
                i += getVisiblePreferenceCount((PreferenceGroup) preference);
            } else if (preference.isVisible()) {
                i++;
            }
        }
        return i;
    }
}
