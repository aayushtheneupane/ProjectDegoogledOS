package com.android.settings.gestures;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreventRingingGestureSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.prevent_ringing_gesture_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return PreventRingingGestureSettings.buildPreferenceControllers(context, (Lifecycle) null);
        }
    };

    public int getHelpResource() {
        return C1715R.string.help_uri_prevent_ringing_gesture;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "RingingGestureSettings";
    }

    public int getMetricsCategory() {
        return 1360;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.prevent_ringing_gesture_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PreventRingingGesturePreferenceController(context, lifecycle));
        arrayList.add(new PreventRingingSwitchPreferenceController(context));
        return arrayList;
    }
}
