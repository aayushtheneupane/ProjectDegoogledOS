package com.android.settings.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.utils.AnnotationSpan;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class WifiWakeupPreferenceController extends AbstractPreferenceController implements LifecycleObserver, OnPause, OnResume {
    private final Fragment mFragment;
    private final IntentFilter mLocationFilter = new IntentFilter("android.location.MODE_CHANGED");
    LocationManager mLocationManager;
    private final BroadcastReceiver mLocationReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            WifiWakeupPreferenceController wifiWakeupPreferenceController = WifiWakeupPreferenceController.this;
            wifiWakeupPreferenceController.updateState(wifiWakeupPreferenceController.mPreference);
        }
    };
    SwitchPreference mPreference;

    public String getPreferenceKey() {
        return "enable_wifi_wakeup";
    }

    public boolean isAvailable() {
        return true;
    }

    public WifiWakeupPreferenceController(Context context, DashboardFragment dashboardFragment, Lifecycle lifecycle) {
        super(context);
        this.mFragment = dashboardFragment;
        this.mLocationManager = (LocationManager) context.getSystemService("location");
        lifecycle.addObserver(this);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (SwitchPreference) preferenceScreen.findPreference("enable_wifi_wakeup");
        updateState(this.mPreference);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), "enable_wifi_wakeup") || !(preference instanceof SwitchPreference)) {
            return false;
        }
        if (isWifiWakeupAvailable()) {
            setWifiWakeupEnabled(false);
        } else if (!this.mLocationManager.isLocationEnabled()) {
            this.mFragment.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 600);
            return true;
        } else if (!getWifiScanningEnabled()) {
            showScanningDialog();
        } else {
            setWifiWakeupEnabled(true);
        }
        updateState(this.mPreference);
        return true;
    }

    public void updateState(Preference preference) {
        if (preference instanceof SwitchPreference) {
            ((SwitchPreference) preference).setChecked(isWifiWakeupAvailable());
            if (!this.mLocationManager.isLocationEnabled()) {
                preference.setSummary(getNoLocationSummary());
            } else {
                preference.setSummary((int) C1715R.string.wifi_wakeup_summary);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public CharSequence getNoLocationSummary() {
        AnnotationSpan.LinkInfo linkInfo = new AnnotationSpan.LinkInfo("link", (View.OnClickListener) null);
        return AnnotationSpan.linkify(this.mContext.getText(C1715R.string.wifi_wakeup_summary_no_location), linkInfo);
    }

    public void onActivityResult(int i, int i2) {
        if (i == 600) {
            if (this.mLocationManager.isLocationEnabled() && getWifiScanningEnabled()) {
                setWifiWakeupEnabled(true);
            }
            updateState(this.mPreference);
        }
    }

    private boolean getWifiScanningEnabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "wifi_scan_always_enabled", 0) == 1;
    }

    private void showScanningDialog() {
        WifiScanningRequiredFragment newInstance = WifiScanningRequiredFragment.newInstance();
        newInstance.setTargetFragment(this.mFragment, 600);
        newInstance.show(this.mFragment.getFragmentManager(), "WifiWakeupPrefController");
    }

    private boolean getWifiWakeupEnabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "wifi_wakeup_enabled", 0) == 1;
    }

    private boolean isWifiWakeupAvailable() {
        return getWifiWakeupEnabled() && getWifiScanningEnabled() && this.mLocationManager.isLocationEnabled();
    }

    private void setWifiWakeupEnabled(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "wifi_wakeup_enabled", z ? 1 : 0);
    }

    public void onResume() {
        this.mContext.registerReceiver(this.mLocationReceiver, this.mLocationFilter);
    }

    public void onPause() {
        this.mContext.unregisterReceiver(this.mLocationReceiver);
    }
}
