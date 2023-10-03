package com.android.settings.wifi.tether;

import android.net.wifi.WifiManager;
import android.os.Handler;

public class WifiTetherSoftApManager {
    private Handler mHandler;
    private WifiManager.SoftApCallback mSoftApCallback = new WifiManager.SoftApCallback() {
        public void onStateChanged(int i, int i2) {
            WifiTetherSoftApManager.this.mWifiTetherSoftApCallback.onStateChanged(i, i2);
        }

        public void onNumClientsChanged(int i) {
            WifiTetherSoftApManager.this.mWifiTetherSoftApCallback.onNumClientsChanged(i);
        }
    };
    private WifiManager mWifiManager;
    /* access modifiers changed from: private */
    public WifiTetherSoftApCallback mWifiTetherSoftApCallback;

    public interface WifiTetherSoftApCallback {
        void onNumClientsChanged(int i);

        void onStateChanged(int i, int i2);
    }

    WifiTetherSoftApManager(WifiManager wifiManager, WifiTetherSoftApCallback wifiTetherSoftApCallback) {
        this.mWifiManager = wifiManager;
        this.mWifiTetherSoftApCallback = wifiTetherSoftApCallback;
        this.mHandler = new Handler();
    }

    public void registerSoftApCallback() {
        this.mWifiManager.registerSoftApCallback(this.mSoftApCallback, this.mHandler);
    }

    public void unRegisterSoftApCallback() {
        this.mWifiManager.unregisterSoftApCallback(this.mSoftApCallback);
    }
}
