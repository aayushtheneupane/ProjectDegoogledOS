package com.android.settings.applications.assist;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.gestures.AssistGestureSettingsPreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageAssist extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.manage_assist;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return ManageAssist.buildPreferenceControllers(context, (Lifecycle) null);
        }

        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            nonIndexableKeys.add("gesture_assist_application");
            return nonIndexableKeys;
        }
    };

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "ManageAssist";
    }

    public int getMetricsCategory() {
        return 201;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.manage_assist;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((AssistGestureSettingsPreferenceController) use(AssistGestureSettingsPreferenceController.class)).setAssistOnly(true);
    }

    public void onResume() {
        super.onResume();
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.assist_footer);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DefaultAssistPreferenceController(context, "default_assist", true));
        arrayList.add(new AssistContextPreferenceController(context, lifecycle));
        arrayList.add(new AssistScreenshotPreferenceController(context, lifecycle));
        arrayList.add(new AssistFlashScreenPreferenceController(context, lifecycle));
        arrayList.add(new DefaultVoiceInputPreferenceController(context, lifecycle));
        return arrayList;
    }
}
