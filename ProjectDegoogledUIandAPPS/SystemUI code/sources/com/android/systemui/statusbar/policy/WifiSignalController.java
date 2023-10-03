package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkScoreManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.wifi.WifiStatusTracker;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1784R$string;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.SignalController;
import java.util.Objects;

public class WifiSignalController extends SignalController<WifiState, SignalController.IconGroup> {
    private final boolean mHasMobileData;
    private final WifiStatusTracker mWifiTracker;

    public WifiSignalController(Context context, boolean z, CallbackHandler callbackHandler, NetworkControllerImpl networkControllerImpl, WifiManager wifiManager) {
        super("WifiSignalController", context, 1, callbackHandler, networkControllerImpl);
        this.mWifiTracker = new WifiStatusTracker(this.mContext, wifiManager, (NetworkScoreManager) context.getSystemService(NetworkScoreManager.class), (ConnectivityManager) context.getSystemService(ConnectivityManager.class), new Runnable() {
            public final void run() {
                WifiSignalController.this.handleStatusUpdated();
            }
        });
        this.mWifiTracker.setListening(true);
        this.mHasMobileData = z;
        if (wifiManager != null) {
            wifiManager.registerTrafficStateCallback(new WifiTrafficStateCallback(), (Handler) null);
        }
        SignalController.IconGroup iconGroup = new SignalController.IconGroup("Wi-Fi Icons", WifiIcons.WIFI_SIGNAL_STRENGTH, WifiIcons.QS_WIFI_SIGNAL_STRENGTH, AccessibilityContentDescriptions.WIFI_CONNECTION_STRENGTH, 17302858, 17302858, 17302858, 17302858, AccessibilityContentDescriptions.WIFI_NO_CONNECTION);
        ((WifiState) this.mLastState).iconGroup = iconGroup;
        ((WifiState) this.mCurrentState).iconGroup = iconGroup;
    }

    /* access modifiers changed from: protected */
    public WifiState cleanState() {
        return new WifiState();
    }

    /* access modifiers changed from: package-private */
    public void refreshLocale() {
        this.mWifiTracker.refreshLocale();
    }

    public void notifyListeners(NetworkController.SignalCallback signalCallback) {
        boolean z = this.mContext.getResources().getBoolean(C1773R$bool.config_showWifiIndicatorWhenEnabled);
        T t = this.mCurrentState;
        boolean z2 = ((WifiState) t).enabled && (((WifiState) t).connected || !this.mHasMobileData || z);
        String str = z2 ? ((WifiState) this.mCurrentState).ssid : null;
        boolean z3 = z2 && ((WifiState) this.mCurrentState).ssid != null;
        String charSequence = getStringIfExists(getContentDescription()).toString();
        if (((WifiState) this.mCurrentState).inetCondition == 0) {
            charSequence = charSequence + "," + this.mContext.getString(C1784R$string.data_connection_no_internet);
        }
        NetworkController.IconState iconState = new NetworkController.IconState(z2, getCurrentIconId(), charSequence);
        NetworkController.IconState iconState2 = new NetworkController.IconState(((WifiState) this.mCurrentState).connected, getQsCurrentIconId(), charSequence);
        T t2 = this.mCurrentState;
        boolean z4 = ((WifiState) t2).enabled;
        boolean z5 = z3 && ((WifiState) t2).activityIn;
        boolean z6 = z3 && ((WifiState) this.mCurrentState).activityOut;
        T t3 = this.mCurrentState;
        signalCallback.setWifiIndicators(z4, iconState, iconState2, z5, z6, str, ((WifiState) t3).isTransient, ((WifiState) t3).statusLabel);
    }

    public void handleBroadcast(Intent intent) {
        this.mWifiTracker.handleBroadcast(intent);
        T t = this.mCurrentState;
        WifiStatusTracker wifiStatusTracker = this.mWifiTracker;
        ((WifiState) t).enabled = wifiStatusTracker.enabled;
        ((WifiState) t).connected = wifiStatusTracker.connected;
        ((WifiState) t).ssid = wifiStatusTracker.ssid;
        ((WifiState) t).rssi = wifiStatusTracker.rssi;
        ((WifiState) t).level = wifiStatusTracker.level;
        ((WifiState) t).statusLabel = wifiStatusTracker.statusLabel;
        notifyListenersIfNecessary();
    }

    /* access modifiers changed from: private */
    public void handleStatusUpdated() {
        ((WifiState) this.mCurrentState).statusLabel = this.mWifiTracker.statusLabel;
        notifyListenersIfNecessary();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setActivity(int i) {
        boolean z = false;
        ((WifiState) this.mCurrentState).activityIn = i == 3 || i == 1;
        WifiState wifiState = (WifiState) this.mCurrentState;
        if (i == 3 || i == 2) {
            z = true;
        }
        wifiState.activityOut = z;
        notifyListenersIfNecessary();
    }

    private class WifiTrafficStateCallback implements WifiManager.TrafficStateCallback {
        private WifiTrafficStateCallback() {
        }

        public void onStateChanged(int i) {
            WifiSignalController.this.setActivity(i);
        }
    }

    static class WifiState extends SignalController.State {
        boolean isTransient;
        String ssid;
        String statusLabel;

        WifiState() {
        }

        public void copyFrom(SignalController.State state) {
            super.copyFrom(state);
            WifiState wifiState = (WifiState) state;
            this.ssid = wifiState.ssid;
            this.isTransient = wifiState.isTransient;
            this.statusLabel = wifiState.statusLabel;
        }

        /* access modifiers changed from: protected */
        public void toString(StringBuilder sb) {
            super.toString(sb);
            sb.append(",ssid=");
            sb.append(this.ssid);
            sb.append(",isTransient=");
            sb.append(this.isTransient);
            sb.append(",statusLabel=");
            sb.append(this.statusLabel);
        }

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            WifiState wifiState = (WifiState) obj;
            if (!Objects.equals(wifiState.ssid, this.ssid) || wifiState.isTransient != this.isTransient || !TextUtils.equals(wifiState.statusLabel, this.statusLabel)) {
                return false;
            }
            return true;
        }
    }
}
