package com.android.settings.applications.specialaccess.vrlistener;

import android.content.ComponentName;
import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.utils.ManagedServiceSettings;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class VrListenerSettings extends ManagedServiceSettings {
    private static final ManagedServiceSettings.Config CONFIG;
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.vr_listeners_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private static final String TAG = "VrListenerSettings";

    public int getMetricsCategory() {
        return 334;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.vr_listeners_settings;
    }

    static {
        ManagedServiceSettings.Config.Builder builder = new ManagedServiceSettings.Config.Builder();
        builder.setTag(TAG);
        builder.setSetting("enabled_vr_listeners");
        builder.setIntentAction("android.service.vr.VrListenerService");
        builder.setPermission("android.permission.BIND_VR_LISTENER_SERVICE");
        builder.setNoun("vr listener");
        builder.setWarningDialogTitle(C1715R.string.vr_listener_security_warning_title);
        builder.setWarningDialogSummary(C1715R.string.vr_listener_security_warning_summary);
        builder.setEmptyText(C1715R.string.no_vr_listeners);
        CONFIG = builder.build();
    }

    /* access modifiers changed from: protected */
    public ManagedServiceSettings.Config getConfig() {
        return CONFIG;
    }

    /* access modifiers changed from: protected */
    public boolean setEnabled(ComponentName componentName, String str, boolean z) {
        logSpecialPermissionChange(z, componentName.getPackageName());
        return super.setEnabled(componentName, str, z);
    }

    /* access modifiers changed from: package-private */
    public void logSpecialPermissionChange(boolean z, String str) {
        int i = z ? 772 : 773;
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(getContext()).getMetricsFeatureProvider();
        metricsFeatureProvider.action(metricsFeatureProvider.getAttribution(getActivity()), i, getMetricsCategory(), str, 0);
    }
}
