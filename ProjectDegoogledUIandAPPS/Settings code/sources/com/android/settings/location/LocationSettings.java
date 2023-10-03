package com.android.settings.location;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.settings.SettingsActivity;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocationSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.location_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return LocationSettings.buildPreferenceControllers(context, (LocationSettings) null, (Lifecycle) null);
        }
    };
    private LocationSwitchBarController mSwitchBarController;

    public int getHelpResource() {
        return C1715R.string.help_url_location_access;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "LocationSettings";
    }

    public int getMetricsCategory() {
        return 63;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.location_settings;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        SwitchBar switchBar = settingsActivity.getSwitchBar();
        switchBar.setSwitchBarText(C1715R.string.location_settings_master_switch_title, C1715R.string.location_settings_master_switch_title);
        this.mSwitchBarController = new LocationSwitchBarController(settingsActivity, switchBar, getSettingsLifecycle());
        switchBar.show();
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, this, getSettingsLifecycle());
    }

    static void addPreferencesSorted(List<Preference> list, PreferenceGroup preferenceGroup) {
        Collections.sort(list, Comparator.comparing($$Lambda$LocationSettings$b5ICKITzeuDqJ5adUiGbEMZMKw.INSTANCE));
        for (Preference addPreference : list) {
            preferenceGroup.addPreference(addPreference);
        }
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, LocationSettings locationSettings, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AppLocationPermissionPreferenceController(context, lifecycle));
        arrayList.add(new LocationForWorkPreferenceController(context, lifecycle));
        arrayList.add(new RecentLocationRequestPreferenceController(context, locationSettings, lifecycle));
        arrayList.add(new LocationScanningPreferenceController(context));
        arrayList.add(new LocationServicePreferenceController(context, locationSettings, lifecycle));
        arrayList.add(new LocationFooterPreferenceController(context, lifecycle));
        return arrayList;
    }
}
