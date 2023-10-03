package com.android.settings.wifi.tether;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.wifi.tether.WifiTetherBasePreferenceController;
import com.havoc.config.center.C1715R;

public class WifiTetherSecurityPreferenceController extends WifiTetherBasePreferenceController {
    private final String[] mSecurityEntries = this.mContext.getResources().getStringArray(C1715R.array.wifi_tether_security);
    private int mSecurityValue;

    public String getPreferenceKey() {
        return "wifi_tether_security";
    }

    public WifiTetherSecurityPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
    }

    public void updateDisplay() {
        WifiConfiguration wifiApConfiguration = this.mWifiManager.getWifiApConfiguration();
        if (wifiApConfiguration == null || wifiApConfiguration.getAuthType() != 0) {
            this.mSecurityValue = 4;
        } else {
            this.mSecurityValue = 0;
        }
        ListPreference listPreference = (ListPreference) this.mPreference;
        listPreference.setSummary(getSummaryForSecurityType(this.mSecurityValue));
        listPreference.setValue(String.valueOf(this.mSecurityValue));
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mSecurityValue = Integer.parseInt((String) obj);
        preference.setSummary((CharSequence) getSummaryForSecurityType(this.mSecurityValue));
        this.mListener.onTetherConfigUpdated();
        return true;
    }

    public int getSecurityType() {
        return this.mSecurityValue;
    }

    private String getSummaryForSecurityType(int i) {
        if (i == 0) {
            return this.mSecurityEntries[1];
        }
        return this.mSecurityEntries[0];
    }
}
