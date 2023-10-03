package com.android.settings.wifi.slice;

import android.content.Context;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import android.util.Log;
import androidx.slice.Slice;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.slices.CustomSliceRegistry;

public class ContextualWifiSlice extends WifiSlice {
    static long sActiveUiSession = -1000;
    static boolean sPreviouslyDisplayed;

    public ContextualWifiSlice(Context context) {
        super(context);
    }

    public Uri getUri() {
        return CustomSliceRegistry.CONTEXTUAL_WIFI_SLICE_URI;
    }

    public Slice getSlice() {
        long uiSessionToken = FeatureFactory.getFactory(this.mContext).getSlicesFeatureProvider().getUiSessionToken();
        if (uiSessionToken != sActiveUiSession) {
            sActiveUiSession = uiSessionToken;
            sPreviouslyDisplayed = false;
        }
        if (sPreviouslyDisplayed || !hasWorkingNetwork()) {
            sPreviouslyDisplayed = true;
            return super.getSlice();
        }
        Log.d("ContextualWifiSlice", "Wifi is connected, no point showing any suggestion.");
        return null;
    }

    private boolean hasWorkingNetwork() {
        return !TextUtils.equals(getActiveSSID(), "<unknown ssid>") && hasInternetAccess();
    }

    private String getActiveSSID() {
        if (this.mWifiManager.getWifiState() != 3) {
            return "<unknown ssid>";
        }
        return WifiInfo.removeDoubleQuotes(this.mWifiManager.getConnectionInfo().getSSID());
    }

    private boolean hasInternetAccess() {
        NetworkCapabilities networkCapabilities = this.mConnectivityManager.getNetworkCapabilities(this.mWifiManager.getCurrentNetwork());
        return networkCapabilities != null && !networkCapabilities.hasCapability(17) && !networkCapabilities.hasCapability(24) && networkCapabilities.hasCapability(16);
    }

    public Class getBackgroundWorkerClass() {
        return ContextualWifiScanWorker.class;
    }
}
