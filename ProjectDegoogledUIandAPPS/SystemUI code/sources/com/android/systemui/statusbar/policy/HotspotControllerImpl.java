package com.android.systemui.statusbar.policy;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.UserManager;
import android.util.Log;
import com.android.systemui.statusbar.policy.HotspotController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class HotspotControllerImpl implements HotspotController, WifiManager.SoftApCallback {
    /* access modifiers changed from: private */
    public static final boolean DEBUG = Log.isLoggable("HotspotController", 3);
    private final ArrayList<HotspotController.Callback> mCallbacks = new ArrayList<>();
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private int mHotspotState;
    private final Handler mMainHandler;
    private int mNumConnectedDevices;
    private boolean mWaitingForTerminalState;
    private final WifiManager mWifiManager;

    private static String stateToString(int i) {
        switch (i) {
            case 10:
                return "DISABLING";
            case 11:
                return "DISABLED";
            case 12:
                return "ENABLING";
            case 13:
                return "ENABLED";
            case 14:
                return "FAILED";
            default:
                return null;
        }
    }

    public HotspotControllerImpl(Context context, Handler handler) {
        this.mContext = context;
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mMainHandler = handler;
    }

    public boolean isHotspotSupported() {
        return this.mConnectivityManager.isTetheringSupported() && this.mConnectivityManager.getTetherableWifiRegexs().length != 0 && UserManager.get(this.mContext).isUserAdmin(ActivityManager.getCurrentUser());
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("HotspotController state:");
        printWriter.print("  mHotspotState=");
        printWriter.println(stateToString(this.mHotspotState));
        printWriter.print("  mNumConnectedDevices=");
        printWriter.println(this.mNumConnectedDevices);
        printWriter.print("  mWaitingForTerminalState=");
        printWriter.println(this.mWaitingForTerminalState);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addCallback(com.android.systemui.statusbar.policy.HotspotController.Callback r5) {
        /*
            r4 = this;
            java.util.ArrayList<com.android.systemui.statusbar.policy.HotspotController$Callback> r0 = r4.mCallbacks
            monitor-enter(r0)
            if (r5 == 0) goto L_0x004e
            java.util.ArrayList<com.android.systemui.statusbar.policy.HotspotController$Callback> r1 = r4.mCallbacks     // Catch:{ all -> 0x0050 }
            boolean r1 = r1.contains(r5)     // Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x000e
            goto L_0x004e
        L_0x000e:
            boolean r1 = DEBUG     // Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x0028
            java.lang.String r1 = "HotspotController"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0050 }
            r2.<init>()     // Catch:{ all -> 0x0050 }
            java.lang.String r3 = "addCallback "
            r2.append(r3)     // Catch:{ all -> 0x0050 }
            r2.append(r5)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0050 }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x0050 }
        L_0x0028:
            java.util.ArrayList<com.android.systemui.statusbar.policy.HotspotController$Callback> r1 = r4.mCallbacks     // Catch:{ all -> 0x0050 }
            r1.add(r5)     // Catch:{ all -> 0x0050 }
            android.net.wifi.WifiManager r1 = r4.mWifiManager     // Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x004c
            java.util.ArrayList<com.android.systemui.statusbar.policy.HotspotController$Callback> r1 = r4.mCallbacks     // Catch:{ all -> 0x0050 }
            int r1 = r1.size()     // Catch:{ all -> 0x0050 }
            r2 = 1
            if (r1 != r2) goto L_0x0042
            android.net.wifi.WifiManager r5 = r4.mWifiManager     // Catch:{ all -> 0x0050 }
            android.os.Handler r1 = r4.mMainHandler     // Catch:{ all -> 0x0050 }
            r5.registerSoftApCallback(r4, r1)     // Catch:{ all -> 0x0050 }
            goto L_0x004c
        L_0x0042:
            android.os.Handler r1 = r4.mMainHandler     // Catch:{ all -> 0x0050 }
            com.android.systemui.statusbar.policy.-$$Lambda$HotspotControllerImpl$C17PPPxxCR-pTmr2izVaDhyC9AQ r2 = new com.android.systemui.statusbar.policy.-$$Lambda$HotspotControllerImpl$C17PPPxxCR-pTmr2izVaDhyC9AQ     // Catch:{ all -> 0x0050 }
            r2.<init>(r5)     // Catch:{ all -> 0x0050 }
            r1.post(r2)     // Catch:{ all -> 0x0050 }
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x004e:
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x0050:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.HotspotControllerImpl.addCallback(com.android.systemui.statusbar.policy.HotspotController$Callback):void");
    }

    public /* synthetic */ void lambda$addCallback$0$HotspotControllerImpl(HotspotController.Callback callback) {
        callback.onHotspotChanged(isHotspotEnabled(), this.mNumConnectedDevices);
    }

    public void removeCallback(HotspotController.Callback callback) {
        if (callback != null) {
            if (DEBUG) {
                Log.d("HotspotController", "removeCallback " + callback);
            }
            synchronized (this.mCallbacks) {
                this.mCallbacks.remove(callback);
                if (this.mCallbacks.isEmpty() && this.mWifiManager != null) {
                    this.mWifiManager.unregisterSoftApCallback(this);
                }
            }
        }
    }

    public boolean isHotspotEnabled() {
        return this.mHotspotState == 13;
    }

    public boolean isHotspotTransient() {
        return this.mWaitingForTerminalState || this.mHotspotState == 12;
    }

    public void setHotspotEnabled(boolean z) {
        if (this.mWaitingForTerminalState) {
            if (DEBUG) {
                Log.d("HotspotController", "Ignoring setHotspotEnabled; waiting for terminal state.");
            }
        } else if (z) {
            this.mWaitingForTerminalState = true;
            if (DEBUG) {
                Log.d("HotspotController", "Starting tethering");
            }
            this.mConnectivityManager.startTethering(0, false, new ConnectivityManager.OnStartTetheringCallback() {
                public void onTetheringFailed() {
                    if (HotspotControllerImpl.DEBUG) {
                        Log.d("HotspotController", "onTetheringFailed");
                    }
                    HotspotControllerImpl.this.maybeResetSoftApState();
                    HotspotControllerImpl.this.fireHotspotChangedCallback();
                }
            });
        } else {
            this.mConnectivityManager.stopTethering(0);
        }
    }

    public int getNumConnectedDevices() {
        return this.mNumConnectedDevices;
    }

    /* access modifiers changed from: private */
    public void fireHotspotChangedCallback() {
        synchronized (this.mCallbacks) {
            Iterator<HotspotController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onHotspotChanged(isHotspotEnabled(), this.mNumConnectedDevices);
            }
        }
    }

    public void onStateChanged(int i, int i2) {
        this.mHotspotState = i;
        maybeResetSoftApState();
        if (!isHotspotEnabled()) {
            this.mNumConnectedDevices = 0;
        }
        fireHotspotChangedCallback();
    }

    /* access modifiers changed from: private */
    public void maybeResetSoftApState() {
        if (this.mWaitingForTerminalState) {
            int i = this.mHotspotState;
            if (!(i == 11 || i == 13)) {
                if (i == 14) {
                    this.mConnectivityManager.stopTethering(0);
                } else {
                    return;
                }
            }
            this.mWaitingForTerminalState = false;
        }
    }

    public void onNumClientsChanged(int i) {
        this.mNumConnectedDevices = i;
        fireHotspotChangedCallback();
    }
}
