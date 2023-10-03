package com.android.settings.network;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.SearchIndexableResource;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.development.featureflags.FeatureFlagPersistent;
import com.android.settings.network.MobilePlanPreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.wifi.WifiMasterSwitchPreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkDashboardFragment extends DashboardFragment implements MobilePlanPreferenceController.MobilePlanPreferenceHost {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            if (FeatureFlagPersistent.isEnabled(context, "settings_network_and_internet_v2")) {
                searchIndexableResource.xmlResId = C1715R.xml.network_and_internet_v2;
            } else {
                searchIndexableResource.xmlResId = C1715R.xml.network_and_internet;
            }
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return NetworkDashboardFragment.buildPreferenceControllers(context, (Lifecycle) null, (MetricsFeatureProvider) null, (Fragment) null, (MobilePlanPreferenceController.MobilePlanPreferenceHost) null);
        }

        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            nonIndexableKeys.add("toggle_wifi");
            return nonIndexableKeys;
        }
    };

    public int getDialogMetricsCategory(int i) {
        return 1 == i ? 609 : 0;
    }

    public int getHelpResource() {
        return C1715R.string.help_url_network_dashboard;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "NetworkDashboardFrag";
    }

    public int getMetricsCategory() {
        return 746;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") ? C1715R.xml.network_and_internet_v2 : C1715R.xml.network_and_internet;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (FeatureFlagPersistent.isEnabled(context, "settings_network_and_internet_v2")) {
            ((MultiNetworkHeaderController) use(MultiNetworkHeaderController.class)).init(getSettingsLifecycle());
        }
        ((AirplaneModePreferenceController) use(AirplaneModePreferenceController.class)).setFragment(this);
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle(), this.mMetricsFeatureProvider, this, this);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle, MetricsFeatureProvider metricsFeatureProvider, Fragment fragment, MobilePlanPreferenceController.MobilePlanPreferenceHost mobilePlanPreferenceHost) {
        MobilePlanPreferenceController mobilePlanPreferenceController = new MobilePlanPreferenceController(context, mobilePlanPreferenceHost);
        WifiMasterSwitchPreferenceController wifiMasterSwitchPreferenceController = new WifiMasterSwitchPreferenceController(context, metricsFeatureProvider);
        MobileNetworkPreferenceController mobileNetworkPreferenceController = !FeatureFlagPersistent.isEnabled(context, "settings_network_and_internet_v2") ? new MobileNetworkPreferenceController(context) : null;
        VpnPreferenceController vpnPreferenceController = new VpnPreferenceController(context);
        PrivateDnsPreferenceController privateDnsPreferenceController = new PrivateDnsPreferenceController(context);
        if (lifecycle != null) {
            lifecycle.addObserver(mobilePlanPreferenceController);
            lifecycle.addObserver(wifiMasterSwitchPreferenceController);
            if (mobileNetworkPreferenceController != null) {
                lifecycle.addObserver(mobileNetworkPreferenceController);
            }
            lifecycle.addObserver(vpnPreferenceController);
            lifecycle.addObserver(privateDnsPreferenceController);
        }
        ArrayList arrayList = new ArrayList();
        if (FeatureFlagPersistent.isEnabled(context, "settings_network_and_internet_v2")) {
            arrayList.add(new MobileNetworkSummaryController(context, lifecycle));
        }
        if (mobileNetworkPreferenceController != null) {
            arrayList.add(mobileNetworkPreferenceController);
        }
        arrayList.add(new TetherPreferenceController(context, lifecycle));
        arrayList.add(vpnPreferenceController);
        arrayList.add(new ProxyPreferenceController(context));
        arrayList.add(mobilePlanPreferenceController);
        arrayList.add(wifiMasterSwitchPreferenceController);
        arrayList.add(privateDnsPreferenceController);
        return arrayList;
    }

    public void showMobilePlanMessageDialog() {
        showDialog(1);
    }

    public Dialog onCreateDialog(int i) {
        Log.d("NetworkDashboardFrag", "onCreateDialog: dialogId=" + i);
        if (i != 1) {
            return super.onCreateDialog(i);
        }
        MobilePlanPreferenceController mobilePlanPreferenceController = (MobilePlanPreferenceController) use(MobilePlanPreferenceController.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage((CharSequence) mobilePlanPreferenceController.getMobilePlanDialogMessage());
        builder.setCancelable(false);
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                MobilePlanPreferenceController.this.setMobilePlanDialogMessage((String) null);
            }
        });
        return builder.create();
    }
}
