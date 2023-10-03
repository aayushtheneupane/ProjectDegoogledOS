package com.android.settings.wifi.slice;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;

public class ContextualWifiScanWorker extends WifiScanWorker {
    private static long sActiveSession;
    private static long sVisibleUiSessionToken;

    /* access modifiers changed from: protected */
    public void clearClickedWifiOnSliceUnpinned() {
    }

    public ContextualWifiScanWorker(Context context, Uri uri) {
        super(context, uri);
    }

    public static void newVisibleUiSession() {
        sVisibleUiSessionToken = SystemClock.elapsedRealtime();
    }

    static void saveSession() {
        sActiveSession = sVisibleUiSessionToken;
    }

    /* access modifiers changed from: protected */
    public boolean isSessionValid() {
        if (sVisibleUiSessionToken == sActiveSession) {
            return true;
        }
        WifiScanWorker.clearClickedWifi();
        return false;
    }
}
