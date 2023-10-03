package com.android.settings.wifi;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.wifi.details.WifiNetworkDetailsFragment;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import com.havoc.config.center.C1715R;

public class WifiConnectionPreferenceController extends AbstractPreferenceController implements WifiTracker.WifiListener {
    private AccessPointPreference.UserBadgeCache mBadgeCache;
    private int mMetricsCategory;
    private Context mPrefContext;
    private AccessPointPreference mPreference;
    private PreferenceGroup mPreferenceGroup;
    private String mPreferenceGroupKey;
    private UpdateListener mUpdateListener;
    private WifiTracker mWifiTracker;
    private int order;

    public interface UpdateListener {
        void onChildrenUpdated();
    }

    public String getPreferenceKey() {
        return "active_wifi_connection";
    }

    public WifiConnectionPreferenceController(Context context, Lifecycle lifecycle, UpdateListener updateListener, String str, int i, int i2) {
        super(context);
        this.mUpdateListener = updateListener;
        this.mPreferenceGroupKey = str;
        this.mWifiTracker = WifiTrackerFactory.create(context, this, lifecycle, true, true);
        this.order = i;
        this.mMetricsCategory = i2;
        this.mBadgeCache = new AccessPointPreference.UserBadgeCache(context.getPackageManager());
    }

    public boolean isAvailable() {
        return this.mWifiTracker.isConnected() && getCurrentAccessPoint() != null;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(this.mPreferenceGroupKey);
        this.mPrefContext = preferenceScreen.getContext();
        update();
    }

    private AccessPoint getCurrentAccessPoint() {
        for (AccessPoint next : this.mWifiTracker.getAccessPoints()) {
            if (next.isActive()) {
                return next;
            }
        }
        return null;
    }

    private void updatePreference(AccessPoint accessPoint) {
        Context context;
        AccessPointPreference accessPointPreference = this.mPreference;
        if (accessPointPreference != null) {
            this.mPreferenceGroup.removePreference(accessPointPreference);
            this.mPreference = null;
        }
        if (accessPoint != null && (context = this.mPrefContext) != null) {
            this.mPreference = new AccessPointPreference(accessPoint, context, this.mBadgeCache, C1715R.C1717drawable.ic_wifi_signal_0, false);
            this.mPreference.setKey("active_wifi_connection");
            this.mPreference.refresh();
            this.mPreference.setOrder(this.order);
            this.mPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public final boolean onPreferenceClick(Preference preference) {
                    return WifiConnectionPreferenceController.this.lambda$updatePreference$0$WifiConnectionPreferenceController(preference);
                }
            });
            this.mPreferenceGroup.addPreference(this.mPreference);
        }
    }

    public /* synthetic */ boolean lambda$updatePreference$0$WifiConnectionPreferenceController(Preference preference) {
        Bundle bundle = new Bundle();
        this.mPreference.getAccessPoint().saveWifiState(bundle);
        new SubSettingLauncher(this.mPrefContext).setTitleRes(C1715R.string.pref_title_network_details).setDestination(WifiNetworkDetailsFragment.class.getName()).setArguments(bundle).setSourceMetricsCategory(this.mMetricsCategory).launch();
        return true;
    }

    private void update() {
        AccessPoint currentAccessPoint = this.mWifiTracker.isConnected() ? getCurrentAccessPoint() : null;
        if (currentAccessPoint == null) {
            updatePreference((AccessPoint) null);
        } else {
            AccessPointPreference accessPointPreference = this.mPreference;
            if (accessPointPreference == null || !accessPointPreference.getAccessPoint().equals(currentAccessPoint)) {
                updatePreference(currentAccessPoint);
            } else {
                AccessPointPreference accessPointPreference2 = this.mPreference;
                if (accessPointPreference2 != null) {
                    accessPointPreference2.refresh();
                }
            }
        }
        this.mUpdateListener.onChildrenUpdated();
    }

    public void onWifiStateChanged(int i) {
        update();
    }

    public void onConnectedChanged() {
        update();
    }

    public void onAccessPointsChanged() {
        update();
    }
}
