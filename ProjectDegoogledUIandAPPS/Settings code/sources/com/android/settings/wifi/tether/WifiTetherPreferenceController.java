package com.android.settings.wifi.tether;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.text.BidiFormatter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.wifi.tether.WifiTetherSoftApManager;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;

public class WifiTetherPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart, OnStop {
    private final ConnectivityManager mConnectivityManager;
    private final Lifecycle mLifecycle;
    Preference mPreference;
    /* access modifiers changed from: private */
    public int mSoftApState;
    private final WifiManager mWifiManager;
    private final String[] mWifiRegexs;
    WifiTetherSoftApManager mWifiTetherSoftApManager;

    public String getPreferenceKey() {
        return "wifi_tether";
    }

    public WifiTetherPreferenceController(Context context, Lifecycle lifecycle) {
        this(context, lifecycle, true);
    }

    WifiTetherPreferenceController(Context context, Lifecycle lifecycle, boolean z) {
        super(context);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mWifiRegexs = this.mConnectivityManager.getTetherableWifiRegexs();
        this.mLifecycle = lifecycle;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
        if (z) {
            initWifiTetherSoftApManager();
        }
    }

    public boolean isAvailable() {
        String[] strArr = this.mWifiRegexs;
        return (strArr == null || strArr.length == 0 || Utils.isMonkeyRunning()) ? false : true;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference("wifi_tether");
        if (this.mPreference == null) {
        }
    }

    public void onStart() {
        WifiTetherSoftApManager wifiTetherSoftApManager;
        if (this.mPreference != null && (wifiTetherSoftApManager = this.mWifiTetherSoftApManager) != null) {
            wifiTetherSoftApManager.registerSoftApCallback();
        }
    }

    public void onStop() {
        WifiTetherSoftApManager wifiTetherSoftApManager;
        if (this.mPreference != null && (wifiTetherSoftApManager = this.mWifiTetherSoftApManager) != null) {
            wifiTetherSoftApManager.unRegisterSoftApCallback();
        }
    }

    /* access modifiers changed from: package-private */
    public void initWifiTetherSoftApManager() {
        this.mWifiTetherSoftApManager = new WifiTetherSoftApManager(this.mWifiManager, new WifiTetherSoftApManager.WifiTetherSoftApCallback() {
            public void onStateChanged(int i, int i2) {
                int unused = WifiTetherPreferenceController.this.mSoftApState = i;
                WifiTetherPreferenceController.this.handleWifiApStateChanged(i, i2);
            }

            public void onNumClientsChanged(int i) {
                WifiTetherPreferenceController wifiTetherPreferenceController = WifiTetherPreferenceController.this;
                if (wifiTetherPreferenceController.mPreference != null && wifiTetherPreferenceController.mSoftApState == 13) {
                    WifiTetherPreferenceController wifiTetherPreferenceController2 = WifiTetherPreferenceController.this;
                    wifiTetherPreferenceController2.mPreference.setSummary((CharSequence) wifiTetherPreferenceController2.mContext.getResources().getQuantityString(C1715R.plurals.wifi_tether_connected_summary, i, new Object[]{Integer.valueOf(i)}));
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void handleWifiApStateChanged(int i, int i2) {
        switch (i) {
            case 10:
                this.mPreference.setSummary((int) C1715R.string.wifi_tether_stopping);
                return;
            case 11:
                this.mPreference.setSummary((int) C1715R.string.wifi_hotspot_off_subtext);
                return;
            case 12:
                this.mPreference.setSummary((int) C1715R.string.wifi_tether_starting);
                return;
            case 13:
                updateConfigSummary(this.mWifiManager.getWifiApConfiguration());
                return;
            default:
                if (i2 == 1) {
                    this.mPreference.setSummary((int) C1715R.string.wifi_sap_no_channel_error);
                    return;
                } else {
                    this.mPreference.setSummary((int) C1715R.string.wifi_error);
                    return;
                }
        }
    }

    private void updateConfigSummary(WifiConfiguration wifiConfiguration) {
        String string = this.mContext.getString(17041402);
        Preference preference = this.mPreference;
        Context context = this.mContext;
        Object[] objArr = new Object[1];
        BidiFormatter instance = BidiFormatter.getInstance();
        if (wifiConfiguration != null) {
            string = wifiConfiguration.SSID;
        }
        objArr[0] = instance.unicodeWrap(string);
        preference.setSummary((CharSequence) context.getString(C1715R.string.wifi_tether_enabled_subtext, objArr));
    }
}
