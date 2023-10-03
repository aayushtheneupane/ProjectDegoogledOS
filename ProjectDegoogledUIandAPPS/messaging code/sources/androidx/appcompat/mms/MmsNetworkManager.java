package androidx.appcompat.mms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import androidx.core.p024os.EnvironmentCompat;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

class MmsNetworkManager {
    private static final int APN_ALREADY_ACTIVE = 0;
    private static final int APN_ALREADY_INACTIVE = 4;
    private static final int APN_REQUEST_FAILED = 3;
    private static final int APN_REQUEST_STARTED = 1;
    private static final String[] APN_RESULT_STRING = {"already active", "request started", "type not available", "request failed", "already inactive", EnvironmentCompat.MEDIA_UNKNOWN};
    private static final int APN_TYPE_NOT_AVAILABLE = 2;
    private static final long DEFAULT_NETWORK_ACQUIRE_TIMEOUT_MS = 180000;
    private static final String FEATURE_ENABLE_MMS = "enableMMS";
    private static final String MMS_NETWORK_EXTENSION_TIMER = "mms_network_extension_timer";
    private static final long MMS_NETWORK_EXTENSION_TIMER_WAIT_MS = 30000;
    private static final long NETWORK_ACQUIRE_WAIT_INTERVAL_MS = 15000;
    private static final String REASON_VOICE_CALL_ENDED = "2GVoiceCallEnded";
    private static final int TYPE_NONE = -1;
    private static volatile long sNetworkAcquireTimeoutMs = DEFAULT_NETWORK_ACQUIRE_TIMEOUT_MS;
    private final BroadcastReceiver mConnectivityChangeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && MmsNetworkManager.getConnectivityChangeNetworkType(intent) == 2) {
                MmsNetworkManager.this.onMmsConnectivityChange(context, intent);
            }
        }
    };
    private final IntentFilter mConnectivityIntentFilter;
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private Timer mExtensionTimer;
    private final MmsHttpClient mHttpClient;
    private boolean mReceiverRegistered;
    /* access modifiers changed from: private */
    public int mUseCount;
    private int mWaitCount;

    MmsNetworkManager(Context context) {
        this.mContext = context;
        this.mConnectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        this.mHttpClient = new MmsHttpClient(this.mContext);
        this.mConnectivityIntentFilter = new IntentFilter();
        this.mConnectivityIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.mUseCount = 0;
        this.mWaitCount = 0;
    }

    private void endMmsConnectivity() {
        Log.i("MmsLib", "End MMS connectivity");
        try {
            Method method = this.mConnectivityManager.getClass().getMethod("stopUsingNetworkFeature", new Class[]{Integer.TYPE, String.class});
            if (method != null) {
                method.invoke(this.mConnectivityManager, new Object[]{0, FEATURE_ENABLE_MMS});
            }
        } catch (Exception e) {
            Log.w("MmsLib", "ConnectivityManager.stopUsingNetworkFeature failed " + e);
        }
    }

    /* access modifiers changed from: private */
    public boolean extendMmsConnectivityLocked() {
        int startMmsConnectivity = startMmsConnectivity();
        if (startMmsConnectivity == 0) {
            startNetworkExtensionTimerLocked();
            return true;
        } else if (startMmsConnectivity == 1) {
            return false;
        } else {
            stopNetworkExtensionTimerLocked();
            throw new MmsNetworkException("Cannot acquire MMS network: " + startMmsConnectivity + " - " + getMmsConnectivityResultString(startMmsConnectivity));
        }
    }

    /* access modifiers changed from: private */
    public static int getConnectivityChangeNetworkType(Intent intent) {
        int i = Build.VERSION.SDK_INT;
        return intent.getIntExtra("networkType", -1);
    }

    private static String getMmsConnectivityResultString(int i) {
        if (i < 0 || i >= APN_RESULT_STRING.length) {
            i = APN_RESULT_STRING.length - 1;
        }
        return APN_RESULT_STRING[i];
    }

    private boolean isMobileDataEnabled() {
        try {
            Method declaredMethod = this.mConnectivityManager.getClass().getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(this.mConnectivityManager, new Object[0])).booleanValue();
        } catch (Exception e) {
            Log.w("MmsLib", "TelephonyManager.getMobileDataEnabled failed", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void onMmsConnectivityChange(Context context, Intent intent) {
        NetworkInfo networkInfo;
        if (this.mUseCount < 1 || (networkInfo = this.mConnectivityManager.getNetworkInfo(2)) == null) {
            return;
        }
        if (REASON_VOICE_CALL_ENDED.equals(networkInfo.getReason())) {
            unblockWait();
            return;
        }
        NetworkInfo.State state = networkInfo.getState();
        if (state == NetworkInfo.State.CONNECTED || (state == NetworkInfo.State.DISCONNECTED && !isMobileDataEnabled())) {
            unblockWait();
        }
    }

    private void registerConnectivityChangeReceiverLocked() {
        if (!this.mReceiverRegistered) {
            this.mContext.registerReceiver(this.mConnectivityChangeReceiver, this.mConnectivityIntentFilter);
            this.mReceiverRegistered = true;
        }
    }

    static void setNetworkAcquireTimeout(long j) {
        sNetworkAcquireTimeoutMs = j;
    }

    private int startMmsConnectivity() {
        Log.i("MmsLib", "Start MMS connectivity");
        try {
            Method method = this.mConnectivityManager.getClass().getMethod("startUsingNetworkFeature", new Class[]{Integer.TYPE, String.class});
            if (method == null) {
                return 3;
            }
            return ((Integer) method.invoke(this.mConnectivityManager, new Object[]{0, FEATURE_ENABLE_MMS})).intValue();
        } catch (Exception e) {
            Log.w("MmsLib", "ConnectivityManager.startUsingNetworkFeature failed " + e);
            return 3;
        }
    }

    private void startNetworkExtensionTimerLocked() {
        if (this.mExtensionTimer == null) {
            this.mExtensionTimer = new Timer(MMS_NETWORK_EXTENSION_TIMER, true);
            this.mExtensionTimer.schedule(new TimerTask() {
                /* JADX WARNING: Can't wrap try/catch for region: R(5:1|2|(2:4|5)|6|7) */
                /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000e */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r1 = this;
                        monitor-enter(r1)
                        androidx.appcompat.mms.MmsNetworkManager r0 = androidx.appcompat.mms.MmsNetworkManager.this     // Catch:{ all -> 0x0010 }
                        int r0 = r0.mUseCount     // Catch:{ all -> 0x0010 }
                        if (r0 <= 0) goto L_0x000e
                        androidx.appcompat.mms.MmsNetworkManager r0 = androidx.appcompat.mms.MmsNetworkManager.this     // Catch:{ MmsNetworkException -> 0x000e }
                        boolean unused = r0.extendMmsConnectivityLocked()     // Catch:{ MmsNetworkException -> 0x000e }
                    L_0x000e:
                        monitor-exit(r1)     // Catch:{ all -> 0x0010 }
                        return
                    L_0x0010:
                        r0 = move-exception
                        monitor-exit(r1)     // Catch:{ all -> 0x0010 }
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.MmsNetworkManager.C01792.run():void");
                }
            }, MMS_NETWORK_EXTENSION_TIMER_WAIT_MS);
        }
    }

    private void stopNetworkExtensionTimerLocked() {
        Timer timer = this.mExtensionTimer;
        if (timer != null) {
            timer.cancel();
            this.mExtensionTimer = null;
        }
    }

    private void unblockWait() {
        synchronized (this) {
            notifyAll();
        }
    }

    private void unregisterConnectivityChangeReceiverLocked() {
        if (this.mReceiverRegistered) {
            this.mContext.unregisterReceiver(this.mConnectivityChangeReceiver);
            this.mReceiverRegistered = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void acquireNetwork() {
        /*
            r7 = this;
            java.lang.String r0 = "MmsLib"
            java.lang.String r1 = "Acquire MMS network"
            android.util.Log.i(r0, r1)
            monitor-enter(r7)
            r0 = 1
            int r1 = r7.mUseCount     // Catch:{ all -> 0x007e }
            int r1 = r1 + r0
            r7.mUseCount = r1     // Catch:{ all -> 0x007e }
            int r1 = r7.mWaitCount     // Catch:{ all -> 0x007e }
            int r1 = r1 + r0
            r7.mWaitCount = r1     // Catch:{ all -> 0x007e }
            int r1 = r7.mWaitCount     // Catch:{ all -> 0x007e }
            if (r1 != r0) goto L_0x001a
            r7.registerConnectivityChangeReceiverLocked()     // Catch:{ all -> 0x007e }
        L_0x001a:
            long r1 = sNetworkAcquireTimeoutMs     // Catch:{ all -> 0x007e }
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x007e }
        L_0x0020:
            boolean r5 = r7.isMobileDataEnabled()     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0076
            boolean r5 = r7.extendMmsConnectivityLocked()     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x003a
            int r1 = r7.mWaitCount     // Catch:{ all -> 0x008c }
            int r1 = r1 - r0
            r7.mWaitCount = r1     // Catch:{ all -> 0x008c }
            int r0 = r7.mWaitCount     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x0038
            r7.unregisterConnectivityChangeReceiverLocked()     // Catch:{ all -> 0x008c }
        L_0x0038:
            monitor-exit(r7)     // Catch:{ all -> 0x008c }
            return
        L_0x003a:
            r5 = 15000(0x3a98, double:7.411E-320)
            long r1 = java.lang.Math.min(r1, r5)     // Catch:{ InterruptedException -> 0x0044 }
            r7.wait(r1)     // Catch:{ InterruptedException -> 0x0044 }
            goto L_0x004c
        L_0x0044:
            r1 = move-exception
            java.lang.String r2 = "MmsLib"
            java.lang.String r5 = "Unexpected exception"
            android.util.Log.w(r2, r5, r1)     // Catch:{ all -> 0x007e }
        L_0x004c:
            long r1 = sNetworkAcquireTimeoutMs     // Catch:{ all -> 0x007e }
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x007e }
            long r5 = r5 - r3
            long r1 = r1 - r5
            r5 = 0
            int r5 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x0020
            boolean r1 = r7.extendMmsConnectivityLocked()     // Catch:{ all -> 0x007e }
            if (r1 == 0) goto L_0x006e
            int r1 = r7.mWaitCount     // Catch:{ all -> 0x008c }
            int r1 = r1 - r0
            r7.mWaitCount = r1     // Catch:{ all -> 0x008c }
            int r0 = r7.mWaitCount     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x006c
            r7.unregisterConnectivityChangeReceiverLocked()     // Catch:{ all -> 0x008c }
        L_0x006c:
            monitor-exit(r7)     // Catch:{ all -> 0x008c }
            return
        L_0x006e:
            androidx.appcompat.mms.MmsNetworkException r1 = new androidx.appcompat.mms.MmsNetworkException     // Catch:{ all -> 0x007e }
            java.lang.String r2 = "Acquiring MMS network timed out"
            r1.<init>((java.lang.String) r2)     // Catch:{ all -> 0x007e }
            throw r1     // Catch:{ all -> 0x007e }
        L_0x0076:
            androidx.appcompat.mms.MmsNetworkException r1 = new androidx.appcompat.mms.MmsNetworkException     // Catch:{ all -> 0x007e }
            java.lang.String r2 = "Mobile data is disabled"
            r1.<init>((java.lang.String) r2)     // Catch:{ all -> 0x007e }
            throw r1     // Catch:{ all -> 0x007e }
        L_0x007e:
            r1 = move-exception
            int r2 = r7.mWaitCount     // Catch:{ all -> 0x008c }
            int r2 = r2 - r0
            r7.mWaitCount = r2     // Catch:{ all -> 0x008c }
            int r0 = r7.mWaitCount     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x008b
            r7.unregisterConnectivityChangeReceiverLocked()     // Catch:{ all -> 0x008c }
        L_0x008b:
            throw r1     // Catch:{ all -> 0x008c }
        L_0x008c:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x008c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.MmsNetworkManager.acquireNetwork():void");
    }

    /* access modifiers changed from: package-private */
    public String getApnName() {
        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(2);
        if (networkInfo != null) {
            return networkInfo.getExtraInfo();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ConnectivityManager getConnectivityManager() {
        return this.mConnectivityManager;
    }

    /* access modifiers changed from: package-private */
    public MmsHttpClient getHttpClient() {
        return this.mHttpClient;
    }

    /* access modifiers changed from: package-private */
    public void releaseNetwork() {
        Log.i("MmsLib", "release MMS network");
        synchronized (this) {
            this.mUseCount--;
            if (this.mUseCount == 0) {
                stopNetworkExtensionTimerLocked();
                endMmsConnectivity();
            }
        }
    }
}
