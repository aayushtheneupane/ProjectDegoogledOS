package com.android.settings.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import androidx.core.text.BidiFormatter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.havoc.config.center.C1715R;

public class WifiInfoPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnResume, OnPause {
    private final IntentFilter mFilter;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.net.wifi.LINK_CONFIGURATION_CHANGED") || action.equals("android.net.wifi.STATE_CHANGE")) {
                WifiInfoPreferenceController.this.updateWifiInfo();
            }
        }
    };
    private Preference mWifiIpAddressPref;
    private Preference mWifiMacAddressPref;
    private final WifiManager mWifiManager;

    public String getPreferenceKey() {
        return null;
    }

    public boolean isAvailable() {
        return true;
    }

    public WifiInfoPreferenceController(Context context, Lifecycle lifecycle, WifiManager wifiManager) {
        super(context);
        this.mWifiManager = wifiManager;
        this.mFilter = new IntentFilter();
        this.mFilter.addAction("android.net.wifi.LINK_CONFIGURATION_CHANGED");
        this.mFilter.addAction("android.net.wifi.STATE_CHANGE");
        lifecycle.addObserver(this);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mWifiMacAddressPref = preferenceScreen.findPreference("mac_address");
        this.mWifiMacAddressPref.setSelectable(false);
        this.mWifiIpAddressPref = preferenceScreen.findPreference("current_ip_address");
        this.mWifiIpAddressPref.setSelectable(false);
    }

    public void onResume() {
        this.mContext.registerReceiver(this.mReceiver, this.mFilter);
        updateWifiInfo();
    }

    public void onPause() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public void updateWifiInfo() {
        String str;
        String str2;
        if (this.mWifiMacAddressPref != null) {
            WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
            boolean z = this.mContext.getResources().getBoolean(17891612);
            if (connectionInfo == null) {
                str2 = null;
            } else {
                str2 = connectionInfo.getMacAddress();
            }
            if (z && "02:00:00:00:00:00".equals(str2)) {
                this.mWifiMacAddressPref.setSummary((int) C1715R.string.wifi_status_mac_randomized);
            } else if (TextUtils.isEmpty(str2) || "02:00:00:00:00:00".equals(str2)) {
                this.mWifiMacAddressPref.setSummary((int) C1715R.string.status_unavailable);
            } else {
                this.mWifiMacAddressPref.setSummary((CharSequence) str2);
            }
        }
        if (this.mWifiIpAddressPref != null) {
            String wifiIpAddresses = Utils.getWifiIpAddresses(this.mContext);
            Preference preference = this.mWifiIpAddressPref;
            if (wifiIpAddresses == null) {
                str = this.mContext.getString(C1715R.string.status_unavailable);
            } else {
                str = BidiFormatter.getInstance().unicodeWrap(wifiIpAddresses);
            }
            preference.setSummary((CharSequence) str);
        }
    }
}
