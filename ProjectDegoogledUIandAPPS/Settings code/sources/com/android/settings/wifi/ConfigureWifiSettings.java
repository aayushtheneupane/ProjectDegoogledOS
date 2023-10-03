package com.android.settings.wifi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.wifi.p2p.WifiP2pPreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigureWifiSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.wifi_configure_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || activeNetworkInfo.getType() == 1) {
                nonIndexableKeys.add("current_ip_address");
            }
            return nonIndexableKeys;
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return context.getResources().getBoolean(C1715R.bool.config_show_wifi_settings);
        }
    };
    private UseOpenWifiPreferenceController mUseOpenWifiPreferenceController;
    private WifiWakeupPreferenceController mWifiWakeupPreferenceController;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "ConfigureWifiSettings";
    }

    public int getMetricsCategory() {
        return 338;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.wifi_configure_settings;
    }

    public int getInitialExpandedChildCount() {
        return this.mUseOpenWifiPreferenceController.isAvailable() ? 3 : 2;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mWifiWakeupPreferenceController = new WifiWakeupPreferenceController(context, this, getSettingsLifecycle());
        this.mUseOpenWifiPreferenceController = new UseOpenWifiPreferenceController(context, this, getSettingsLifecycle());
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mWifiWakeupPreferenceController);
        arrayList.add(new NotifyOpenNetworksPreferenceController(context, getSettingsLifecycle()));
        arrayList.add(this.mUseOpenWifiPreferenceController);
        arrayList.add(new WifiInfoPreferenceController(context, getSettingsLifecycle(), wifiManager));
        arrayList.add(new WifiP2pPreferenceController(context, getSettingsLifecycle(), wifiManager));
        return arrayList;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        UseOpenWifiPreferenceController useOpenWifiPreferenceController;
        WifiWakeupPreferenceController wifiWakeupPreferenceController;
        if (i == 600 && (wifiWakeupPreferenceController = this.mWifiWakeupPreferenceController) != null) {
            wifiWakeupPreferenceController.onActivityResult(i, i2);
        } else if (i != 400 || (useOpenWifiPreferenceController = this.mUseOpenWifiPreferenceController) == null) {
            super.onActivityResult(i, i2, intent);
        } else {
            useOpenWifiPreferenceController.onActivityResult(i, i2);
        }
    }
}
