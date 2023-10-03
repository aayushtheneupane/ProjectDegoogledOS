package com.android.settings.wifi.savedaccesspoints;

import android.content.Context;
import android.os.Bundle;
import android.util.FeatureFlagUtils;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.development.featureflags.FeatureFlagPersistent;
import com.android.settings.wifi.details.WifiNetworkDetailsFragment;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;
import com.havoc.config.center.C1715R;

public class SavedAccessPointsWifiSettings extends DashboardFragment {
    private Bundle mAccessPointSavedState;
    private AccessPoint mSelectedAccessPoint;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "SavedAccessPoints";
    }

    public int getMetricsCategory() {
        return 106;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.wifi_display_saved_access_points;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((SavedAccessPointsPreferenceController) use(SavedAccessPointsPreferenceController.class)).setHost(this);
        ((SubscribedAccessPointsPreferenceController) use(SubscribedAccessPointsPreferenceController.class)).setHost(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && bundle.containsKey("wifi_ap_state")) {
            this.mAccessPointSavedState = bundle.getBundle("wifi_ap_state");
        }
    }

    public void showWifiPage(AccessPointPreference accessPointPreference) {
        removeDialog(1);
        if (accessPointPreference != null) {
            this.mSelectedAccessPoint = accessPointPreference.getAccessPoint();
        } else {
            this.mSelectedAccessPoint = null;
            this.mAccessPointSavedState = null;
        }
        if (this.mSelectedAccessPoint == null) {
            this.mSelectedAccessPoint = new AccessPoint((Context) getActivity(), this.mAccessPointSavedState);
        }
        Bundle bundle = new Bundle();
        this.mSelectedAccessPoint.saveWifiState(bundle);
        new SubSettingLauncher(getContext()).setTitleText(this.mSelectedAccessPoint.getTitle()).setDestination(WifiNetworkDetailsFragment.class.getName()).setArguments(bundle).setSourceMetricsCategory(getMetricsCategory()).launch();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mSelectedAccessPoint != null) {
            this.mAccessPointSavedState = new Bundle();
            this.mSelectedAccessPoint.saveWifiState(this.mAccessPointSavedState);
            bundle.putBundle("wifi_ap_state", this.mAccessPointSavedState);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isSubscriptionsFeatureEnabled() {
        return FeatureFlagUtils.isEnabled(getContext(), "settings_mobile_network_v2") && FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2");
    }
}
