package com.android.settings.wifi.slice;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.Preconditions;
import com.android.settings.slices.SliceBackgroundWorker;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import java.util.ArrayList;
import java.util.List;

public class WifiScanWorker extends SliceBackgroundWorker<AccessPoint> implements WifiTracker.WifiListener {
    private static String sClickedWifiSsid;
    private final ConnectivityManager mConnectivityManager;
    /* access modifiers changed from: private */
    public final Context mContext;
    WifiNetworkCallback mNetworkCallback;
    /* access modifiers changed from: private */
    public final WifiTracker mWifiTracker = new WifiTracker(this.mContext, this, true, true);

    /* access modifiers changed from: protected */
    public boolean isSessionValid() {
        return true;
    }

    public void onConnectedChanged() {
    }

    public WifiScanWorker(Context context, Uri uri) {
        super(context, uri);
        this.mContext = context;
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
    }

    /* access modifiers changed from: protected */
    public void onSlicePinned() {
        this.mWifiTracker.onStart();
        onAccessPointsChanged();
    }

    /* access modifiers changed from: protected */
    public void onSliceUnpinned() {
        this.mWifiTracker.onStop();
        unregisterNetworkCallback();
        clearClickedWifiOnSliceUnpinned();
    }

    public void close() {
        this.mWifiTracker.onDestroy();
    }

    public void onWifiStateChanged(int i) {
        notifySliceChange();
    }

    public void onAccessPointsChanged() {
        if (!this.mWifiTracker.getManager().isWifiEnabled()) {
            updateResults((List) null);
            return;
        }
        List<AccessPoint> accessPoints = this.mWifiTracker.getAccessPoints();
        ArrayList arrayList = new ArrayList();
        for (AccessPoint next : accessPoints) {
            if (next.isReachable()) {
                arrayList.add(clone(next));
                if (arrayList.size() >= 3) {
                    break;
                }
            }
        }
        updateResults(arrayList);
    }

    private AccessPoint clone(AccessPoint accessPoint) {
        Bundle bundle = new Bundle();
        accessPoint.saveWifiState(bundle);
        return new AccessPoint(this.mContext, bundle);
    }

    /* access modifiers changed from: protected */
    public boolean areListsTheSame(List<AccessPoint> list, List<AccessPoint> list2) {
        if (!list.equals(list2)) {
            return false;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getDetailedState() != list2.get(i).getDetailedState()) {
                return false;
            }
        }
        return true;
    }

    static void saveClickedWifi(AccessPoint accessPoint) {
        sClickedWifiSsid = accessPoint.getSsidStr();
    }

    static void clearClickedWifi() {
        sClickedWifiSsid = null;
    }

    static boolean isWifiClicked(WifiInfo wifiInfo) {
        String removeDoubleQuotes = WifiInfo.removeDoubleQuotes(wifiInfo.getSSID());
        return !TextUtils.isEmpty(removeDoubleQuotes) && TextUtils.equals(removeDoubleQuotes, sClickedWifiSsid);
    }

    /* access modifiers changed from: protected */
    public void clearClickedWifiOnSliceUnpinned() {
        clearClickedWifi();
    }

    public void registerNetworkCallback(Network network) {
        if (network != null) {
            WifiNetworkCallback wifiNetworkCallback = this.mNetworkCallback;
            if (wifiNetworkCallback == null || !wifiNetworkCallback.isSameNetwork(network)) {
                unregisterNetworkCallback();
                this.mNetworkCallback = new WifiNetworkCallback(network);
                this.mConnectivityManager.registerNetworkCallback(new NetworkRequest.Builder().clearCapabilities().addTransportType(1).build(), this.mNetworkCallback, new Handler(Looper.getMainLooper()));
            }
        }
    }

    public void unregisterNetworkCallback() {
        WifiNetworkCallback wifiNetworkCallback = this.mNetworkCallback;
        if (wifiNetworkCallback != null) {
            try {
                this.mConnectivityManager.unregisterNetworkCallback(wifiNetworkCallback);
            } catch (RuntimeException e) {
                Log.e("WifiScanWorker", "Unregistering CaptivePortalNetworkCallback failed.", e);
            }
            this.mNetworkCallback = null;
        }
    }

    class WifiNetworkCallback extends ConnectivityManager.NetworkCallback {
        private boolean mHasPartialConnectivity;
        private boolean mIsCaptivePortal;
        private boolean mIsValidated;
        private final Network mNetwork;

        WifiNetworkCallback(Network network) {
            this.mNetwork = (Network) Preconditions.checkNotNull(network);
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            if (isSameNetwork(network)) {
                boolean z = this.mIsCaptivePortal;
                boolean z2 = this.mHasPartialConnectivity;
                boolean z3 = this.mIsValidated;
                this.mIsCaptivePortal = networkCapabilities.hasCapability(17);
                this.mHasPartialConnectivity = networkCapabilities.hasCapability(24);
                this.mIsValidated = networkCapabilities.hasCapability(16);
                if (z != this.mIsCaptivePortal || z2 != this.mHasPartialConnectivity || z3 != this.mIsValidated) {
                    WifiScanWorker.this.notifySliceChange();
                    if (!z && this.mIsCaptivePortal && WifiScanWorker.isWifiClicked(WifiScanWorker.this.mWifiTracker.getManager().getConnectionInfo()) && WifiScanWorker.this.isSessionValid()) {
                        WifiScanWorker.this.mContext.sendBroadcastAsUser(new Intent(WifiScanWorker.this.mContext, ConnectToWifiHandler.class).putExtra("android.net.extra.NETWORK", network).addFlags(268435456), UserHandle.CURRENT);
                    }
                }
            }
        }

        public boolean isSameNetwork(Network network) {
            return this.mNetwork.equals(network);
        }
    }
}
