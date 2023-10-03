package com.android.settings.wifi.tether;

import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiConfiguration;
import android.util.Log;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.wifi.tether.WifiTetherBasePreferenceController;
import com.havoc.config.center.C1715R;

public class WifiTetherApBandPreferenceController extends WifiTetherBasePreferenceController {
    private boolean isDualMode = this.mWifiManager.isDualModeSupported();
    private String[] mBandEntries;
    private int mBandIndex;
    private String[] mBandSummaries;

    public String getPreferenceKey() {
        return "wifi_tether_network_ap_band";
    }

    public WifiTetherApBandPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
        updatePreferenceEntries();
    }

    public void updateDisplay() {
        WifiConfiguration wifiApConfiguration = this.mWifiManager.getWifiApConfiguration();
        if (wifiApConfiguration == null) {
            this.mBandIndex = 0;
            Log.d("WifiTetherApBandPref", "Updating band index to 0 because no config");
        } else if (is5GhzBandSupported()) {
            this.mBandIndex = validateSelection(wifiApConfiguration.apBand);
            Log.d("WifiTetherApBandPref", "Updating band index to " + this.mBandIndex);
        } else {
            wifiApConfiguration.apBand = 0;
            this.mWifiManager.setWifiApConfiguration(wifiApConfiguration);
            this.mBandIndex = wifiApConfiguration.apBand;
            Log.d("WifiTetherApBandPref", "5Ghz not supported, updating band index to " + this.mBandIndex);
        }
        ListPreference listPreference = (ListPreference) this.mPreference;
        listPreference.setEntries((CharSequence[]) this.mBandSummaries);
        listPreference.setEntryValues((CharSequence[]) this.mBandEntries);
        if (!is5GhzBandSupported()) {
            listPreference.setEnabled(false);
            listPreference.setSummary((int) C1715R.string.wifi_ap_choose_2G);
            return;
        }
        listPreference.setValue(Integer.toString(wifiApConfiguration.apBand));
        listPreference.setSummary(getConfigSummary());
    }

    /* access modifiers changed from: package-private */
    public String getConfigSummary() {
        int i = this.mBandIndex;
        if (i == -1) {
            return this.mContext.getString(C1715R.string.wifi_ap_prefer_5G);
        }
        return this.mBandSummaries[i];
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mBandIndex = validateSelection(Integer.parseInt((String) obj));
        Log.d("WifiTetherApBandPref", "Band preference changed, updating band index to " + this.mBandIndex);
        preference.setSummary((CharSequence) getConfigSummary());
        this.mListener.onTetherConfigUpdated();
        return true;
    }

    private int validateSelection(int i) {
        boolean isDualModeSupported = this.mWifiManager.isDualModeSupported();
        if (!isDualModeSupported && -1 == i) {
            return 1;
        }
        if (!is5GhzBandSupported() && 1 == i) {
            return 0;
        }
        if (!isDualModeSupported || 1 != i) {
            return i;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void updatePreferenceEntries() {
        int i;
        int i2;
        Resources resources = this.mContext.getResources();
        if (this.isDualMode) {
            i2 = C1715R.array.wifi_ap_band_dual_mode;
            i = C1715R.array.wifi_ap_band_dual_mode_summary;
        } else {
            i2 = C1715R.array.wifi_ap_band_config_full;
            i = C1715R.array.wifi_ap_band_summary_full;
        }
        this.mBandEntries = resources.getStringArray(i2);
        this.mBandSummaries = resources.getStringArray(i);
    }

    private boolean is5GhzBandSupported() {
        return this.mWifiManager.isDualBandSupported();
    }

    public int getBandIndex() {
        return this.mBandIndex;
    }
}
