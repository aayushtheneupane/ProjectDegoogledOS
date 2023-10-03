package com.android.settings.notification;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ZenModeSoundVibrationSettings extends ZenModeSettingsBase implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.zen_mode_sound_vibration_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        public List<String> getNonIndexableKeys(Context context) {
            return super.getNonIndexableKeys(context);
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return ZenModeSoundVibrationSettings.buildPreferenceControllers(context, (Lifecycle) null);
        }
    };

    public int getMetricsCategory() {
        return 141;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.zen_mode_sound_vibration_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ZenModeCallsPreferenceController(context, lifecycle, "zen_mode_calls_settings"));
        arrayList.add(new ZenModeMessagesPreferenceController(context, lifecycle, "zen_mode_messages_settings"));
        arrayList.add(new ZenModeAlarmsPreferenceController(context, lifecycle, "zen_mode_alarms"));
        arrayList.add(new ZenModeMediaPreferenceController(context, lifecycle));
        arrayList.add(new ZenModeSystemPreferenceController(context, lifecycle));
        arrayList.add(new ZenModeRemindersPreferenceController(context, lifecycle));
        arrayList.add(new ZenModeEventsPreferenceController(context, lifecycle));
        arrayList.add(new ZenModeBehaviorFooterPreferenceController(context, lifecycle, C1715R.string.zen_sound_footer));
        arrayList.add(new ZenModeBypassingAppsPreferenceController(context, lifecycle));
        return arrayList;
    }
}
