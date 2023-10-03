package com.android.settings.notification;

import android.app.Application;
import android.content.Context;
import android.provider.SearchIndexableResource;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ZenModeBypassingAppsSettings extends ZenModeSettingsBase implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.zen_mode_bypassing_apps;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return ZenModeBypassingAppsSettings.buildPreferenceControllers(context, (Application) null, (Fragment) null);
        }
    };
    private final String TAG = "ZenBypassingApps";

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "ZenBypassingApps";
    }

    public int getMetricsCategory() {
        return 1588;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.zen_mode_bypassing_apps;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        FragmentActivity activity = getActivity();
        return buildPreferenceControllers(context, activity != null ? activity.getApplication() : null, this);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Application application, Fragment fragment) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ZenModeAllBypassingAppsPreferenceController(context, application, fragment));
        return arrayList;
    }
}
