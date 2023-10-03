package com.android.settings;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import com.android.internal.custom.hardware.LineageHardwareManager;
import com.android.settings.custom.touch.HighTouchSensitivitySettingsPreferenceController;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.display.BrightnessLevelPreferenceController;
import com.android.settings.display.CameraGesturePreferenceController;
import com.android.settings.display.LiftToWakePreferenceController;
import com.android.settings.display.NightDisplayPreferenceController;
import com.android.settings.display.NightModePreferenceController;
import com.android.settings.display.RefreshRatePreferenceController;
import com.android.settings.display.ScreenSaverPreferenceController;
import com.android.settings.display.ShowOperatorNamePreferenceController;
import com.android.settings.display.TapToWakePreferenceController;
import com.android.settings.display.ThemePreferenceController;
import com.android.settings.display.TimeoutLockscreenPreferenceController;
import com.android.settings.display.TimeoutPreferenceController;
import com.android.settings.display.VrDisplayPreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class DisplaySettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.display_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            if (!LineageHardwareManager.getInstance(context).isSupported(16)) {
                nonIndexableKeys.add(HighTouchSensitivitySettingsPreferenceController.KEY);
            }
            return nonIndexableKeys;
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return DisplaySettings.buildPreferenceControllers(context, (Lifecycle) null);
        }
    };

    public int getHelpResource() {
        return C1715R.string.help_uri_display;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "DisplaySettings";
    }

    public int getMetricsCategory() {
        return 46;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.display_settings;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CameraGesturePreferenceController(context));
        arrayList.add(new LiftToWakePreferenceController(context));
        arrayList.add(new NightDisplayPreferenceController(context));
        arrayList.add(new NightModePreferenceController(context));
        arrayList.add(new ScreenSaverPreferenceController(context));
        arrayList.add(new TapToWakePreferenceController(context));
        arrayList.add(new TimeoutLockscreenPreferenceController(context, "lockscreen_timeout"));
        arrayList.add(new TimeoutPreferenceController(context, "screen_timeout"));
        arrayList.add(new VrDisplayPreferenceController(context));
        arrayList.add(new ShowOperatorNamePreferenceController(context));
        arrayList.add(new ThemePreferenceController(context));
        arrayList.add(new BrightnessLevelPreferenceController(context, lifecycle));
        arrayList.add(new RefreshRatePreferenceController(context));
        return arrayList;
    }
}
