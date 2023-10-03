package com.android.settings.enterprise;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.PreferenceCategoryController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnterprisePrivacySettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return EnterprisePrivacySettings.isPageEnabled(context);
        }

        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.enterprise_privacy_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return EnterprisePrivacySettings.buildPreferenceControllers(context, false);
        }
    };

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "EnterprisePrivacySettings";
    }

    public int getMetricsCategory() {
        return 628;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.enterprise_privacy_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, true);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new NetworkLogsPreferenceController(context));
        arrayList.add(new BugReportsPreferenceController(context));
        arrayList.add(new SecurityLogsPreferenceController(context));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new EnterpriseInstalledPackagesPreferenceController(context, z));
        arrayList2.add(new AdminGrantedLocationPermissionsPreferenceController(context, z));
        arrayList2.add(new AdminGrantedMicrophonePermissionPreferenceController(context, z));
        arrayList2.add(new AdminGrantedCameraPermissionPreferenceController(context, z));
        arrayList2.add(new EnterpriseSetDefaultAppsPreferenceController(context));
        arrayList2.add(new AlwaysOnVpnCurrentUserPreferenceController(context));
        arrayList2.add(new AlwaysOnVpnManagedProfilePreferenceController(context));
        arrayList2.add(new ImePreferenceController(context));
        arrayList2.add(new GlobalHttpProxyPreferenceController(context));
        arrayList2.add(new CaCertsCurrentUserPreferenceController(context));
        arrayList2.add(new CaCertsManagedProfilePreferenceController(context));
        arrayList.addAll(arrayList2);
        arrayList.add(new PreferenceCategoryController(context, "exposure_changes_category").setChildren(arrayList2));
        arrayList.add(new FailedPasswordWipeCurrentUserPreferenceController(context));
        arrayList.add(new FailedPasswordWipeManagedProfilePreferenceController(context));
        return arrayList;
    }

    public static boolean isPageEnabled(Context context) {
        return FeatureFactory.getFactory(context).getEnterprisePrivacyFeatureProvider(context).hasDeviceOwner();
    }
}
