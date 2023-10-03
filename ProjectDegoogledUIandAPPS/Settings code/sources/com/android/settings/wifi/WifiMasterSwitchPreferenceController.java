package com.android.settings.wifi;

import android.content.Context;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.widget.MasterSwitchController;
import com.android.settings.widget.MasterSwitchPreference;
import com.android.settings.widget.SummaryUpdater;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;

public class WifiMasterSwitchPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, SummaryUpdater.OnSummaryChangeListener, LifecycleObserver, OnResume, OnPause, OnStart, OnStop {
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private final WifiSummaryUpdater mSummaryHelper = new WifiSummaryUpdater(this.mContext, this);
    private WifiEnabler mWifiEnabler;
    private MasterSwitchPreference mWifiPreference;

    public String getPreferenceKey() {
        return "toggle_wifi";
    }

    public WifiMasterSwitchPreferenceController(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        super(context);
        this.mMetricsFeatureProvider = metricsFeatureProvider;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mWifiPreference = (MasterSwitchPreference) preferenceScreen.findPreference("toggle_wifi");
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_wifi_settings);
    }

    public void onResume() {
        this.mSummaryHelper.register(true);
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.resume(this.mContext);
        }
    }

    public void onPause() {
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.pause();
        }
        this.mSummaryHelper.register(false);
    }

    public void onStart() {
        this.mWifiEnabler = new WifiEnabler(this.mContext, new MasterSwitchController(this.mWifiPreference), this.mMetricsFeatureProvider);
    }

    public void onStop() {
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.teardownSwitchController();
        }
    }

    public void onSummaryChanged(String str) {
        MasterSwitchPreference masterSwitchPreference = this.mWifiPreference;
        if (masterSwitchPreference != null) {
            masterSwitchPreference.setSummary((CharSequence) str);
        }
    }
}
