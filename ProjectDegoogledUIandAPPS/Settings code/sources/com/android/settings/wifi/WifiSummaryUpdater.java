package com.android.settings.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkScoreManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.android.settings.widget.SummaryUpdater;
import com.android.settingslib.wifi.WifiStatusTracker;
import com.havoc.config.center.C1715R;

public final class WifiSummaryUpdater extends SummaryUpdater {
    private static final IntentFilter INTENT_FILTER = new IntentFilter();
    private final BroadcastReceiver mReceiver;
    /* access modifiers changed from: private */
    public final WifiStatusTracker mWifiTracker;

    static {
        INTENT_FILTER.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        INTENT_FILTER.addAction("android.net.wifi.STATE_CHANGE");
        INTENT_FILTER.addAction("android.net.wifi.RSSI_CHANGED");
    }

    public WifiSummaryUpdater(Context context, SummaryUpdater.OnSummaryChangeListener onSummaryChangeListener) {
        this(context, onSummaryChangeListener, (WifiStatusTracker) null);
    }

    public WifiSummaryUpdater(Context context, SummaryUpdater.OnSummaryChangeListener onSummaryChangeListener, WifiStatusTracker wifiStatusTracker) {
        super(context, onSummaryChangeListener);
        WifiStatusTracker wifiStatusTracker2;
        if (wifiStatusTracker != null) {
            wifiStatusTracker2 = wifiStatusTracker;
        } else {
            wifiStatusTracker2 = new WifiStatusTracker(context, (WifiManager) context.getSystemService(WifiManager.class), (NetworkScoreManager) context.getSystemService(NetworkScoreManager.class), (ConnectivityManager) context.getSystemService(ConnectivityManager.class), new Runnable() {
                public final void run() {
                    WifiSummaryUpdater.this.notifyChangeIfNeeded();
                }
            });
        }
        this.mWifiTracker = wifiStatusTracker2;
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                WifiSummaryUpdater.this.mWifiTracker.handleBroadcast(intent);
                WifiSummaryUpdater.this.notifyChangeIfNeeded();
            }
        };
    }

    public void register(boolean z) {
        if (z) {
            notifyChangeIfNeeded();
            this.mContext.registerReceiver(this.mReceiver, INTENT_FILTER);
        } else {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
        this.mWifiTracker.setListening(z);
    }

    public String getSummary() {
        WifiStatusTracker wifiStatusTracker = this.mWifiTracker;
        if (!wifiStatusTracker.enabled) {
            return this.mContext.getString(C1715R.string.switch_off_text);
        }
        if (!wifiStatusTracker.connected) {
            return this.mContext.getString(C1715R.string.disconnected);
        }
        String removeDoubleQuotes = WifiInfo.removeDoubleQuotes(wifiStatusTracker.ssid);
        if (TextUtils.isEmpty(this.mWifiTracker.statusLabel)) {
            return removeDoubleQuotes;
        }
        return this.mContext.getResources().getString(C1715R.string.preference_summary_default_combination, new Object[]{removeDoubleQuotes, this.mWifiTracker.statusLabel});
    }
}
