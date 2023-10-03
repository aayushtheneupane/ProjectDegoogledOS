package com.android.settings.applications.defaultapps;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class AutofillPicker extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.default_autofill_picker_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> getPreferenceControllers(Context context) {
            return AutofillPicker.buildPreferenceControllers(context);
        }
    };

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "AutofillPicker";
    }

    public int getMetricsCategory() {
        return 792;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.default_autofill_picker_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context) {
        return Arrays.asList(new AbstractPreferenceController[]{new DefaultAutofillPreferenceController(context), new DefaultWorkAutofillPreferenceController(context)});
    }
}
