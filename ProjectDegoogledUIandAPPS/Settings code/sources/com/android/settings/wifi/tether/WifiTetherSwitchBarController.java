package com.android.settings.wifi.tether;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import com.android.settings.datausage.DataSaverBackend;
import com.android.settings.widget.SwitchWidgetController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

public class WifiTetherSwitchBarController implements SwitchWidgetController.OnSwitchChangeListener, LifecycleObserver, OnStart, OnStop, DataSaverBackend.Listener {
    private static final IntentFilter WIFI_INTENT_FILTER = new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED");
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    final DataSaverBackend mDataSaverBackend;
    final ConnectivityManager.OnStartTetheringCallback mOnStartTetheringCallback = new ConnectivityManager.OnStartTetheringCallback() {
        public void onTetheringFailed() {
            WifiTetherSwitchBarController.super.onTetheringFailed();
            WifiTetherSwitchBarController.this.mSwitchBar.setChecked(false);
            WifiTetherSwitchBarController.this.updateWifiSwitch();
        }
    };
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(intent.getAction())) {
                WifiTetherSwitchBarController.this.handleWifiApStateChanged(intent.getIntExtra("wifi_state", 14));
            }
        }
    };
    /* access modifiers changed from: private */
    public final SwitchWidgetController mSwitchBar;
    private final WifiManager mWifiManager;

    public void onBlacklistStatusChanged(int i, boolean z) {
    }

    public void onWhitelistStatusChanged(int i, boolean z) {
    }

    WifiTetherSwitchBarController(Context context, SwitchWidgetController switchWidgetController) {
        this.mContext = context;
        this.mSwitchBar = switchWidgetController;
        this.mDataSaverBackend = new DataSaverBackend(context);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mSwitchBar.setChecked(this.mWifiManager.getWifiApState() == 13);
        this.mSwitchBar.setListener(this);
        updateWifiSwitch();
    }

    public void onStart() {
        this.mDataSaverBackend.addListener(this);
        this.mSwitchBar.startListening();
        this.mContext.registerReceiver(this.mReceiver, WIFI_INTENT_FILTER);
    }

    public void onStop() {
        this.mDataSaverBackend.remListener(this);
        this.mSwitchBar.stopListening();
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public boolean onSwitchToggled(boolean z) {
        if (!z) {
            stopTether();
            return true;
        } else if (this.mWifiManager.isWifiApEnabled()) {
            return true;
        } else {
            startTether();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void stopTether() {
        this.mSwitchBar.setEnabled(false);
        this.mConnectivityManager.stopTethering(0);
    }

    /* access modifiers changed from: package-private */
    public void startTether() {
        this.mSwitchBar.setEnabled(false);
        this.mConnectivityManager.startTethering(0, true, this.mOnStartTetheringCallback, new Handler(Looper.getMainLooper()));
    }

    /* access modifiers changed from: private */
    public void handleWifiApStateChanged(int i) {
        switch (i) {
            case 10:
                if (this.mSwitchBar.isChecked()) {
                    this.mSwitchBar.setChecked(false);
                }
                this.mSwitchBar.setEnabled(false);
                return;
            case 11:
                this.mSwitchBar.setChecked(false);
                updateWifiSwitch();
                return;
            case 12:
                this.mSwitchBar.setEnabled(false);
                return;
            case 13:
                if (!this.mSwitchBar.isChecked()) {
                    this.mSwitchBar.setChecked(true);
                }
                updateWifiSwitch();
                return;
            default:
                this.mSwitchBar.setChecked(false);
                updateWifiSwitch();
                return;
        }
    }

    /* access modifiers changed from: private */
    public void updateWifiSwitch() {
        this.mSwitchBar.setEnabled(!this.mDataSaverBackend.isDataSaverEnabled());
    }

    public void onDataSaverChanged(boolean z) {
        updateWifiSwitch();
    }
}
