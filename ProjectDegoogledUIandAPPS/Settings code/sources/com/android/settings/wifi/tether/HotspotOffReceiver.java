package com.android.settings.wifi.tether;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;

public class HotspotOffReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = Log.isLoggable("HotspotOffReceiver", 3);
    private Context mContext;
    private boolean mRegistered;

    public HotspotOffReceiver(Context context) {
        this.mContext = context;
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(intent.getAction()) && ((WifiManager) context.getSystemService("wifi")).getWifiApState() == 11) {
            if (DEBUG) {
                Log.d("HotspotOffReceiver", "TetherService.cancelRecheckAlarmIfNecessary called");
            }
            TetherService.cancelRecheckAlarmIfNecessary(context, 0);
        }
    }

    public void register() {
        if (!this.mRegistered) {
            this.mContext.registerReceiver(this, new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED"));
            this.mRegistered = true;
        }
    }

    public void unregister() {
        if (this.mRegistered) {
            this.mContext.unregisterReceiver(this);
            this.mRegistered = false;
        }
    }
}
