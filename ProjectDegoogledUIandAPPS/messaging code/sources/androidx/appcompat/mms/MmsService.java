package androidx.appcompat.mms;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class MmsService extends Service {
    private static final int DEFAULT_THREAD_POOL_SIZE = 4;
    private static final String EXTRA_MYPID = "mypid";
    private static final String EXTRA_REQUEST = "request";
    private static final int SERVICE_STOP_DELAY_MILLIS = 2000;
    static final String TAG = "MmsLib";
    private static final String WAKELOCK_ID = "mmslib_wakelock";
    private static volatile ApnSettingsLoader sApnSettingsLoader = null;
    private static volatile CarrierConfigValuesLoader sCarrierConfigValuesLoader = null;
    private static volatile int sMyPid = -1;
    private static volatile int sThreadPoolSize = 4;
    private static volatile boolean sUseWakeLock = true;
    private static volatile UserAgentInfoLoader sUserAgentInfoLoader = null;
    private static volatile PowerManager.WakeLock sWakeLock;
    private static final Object sWakeLockLock = new Object();
    private int mActiveRequestCount;
    private ExecutorService[] mExecutors = new ExecutorService[2];
    private final Handler mHandler = new Handler();
    private int mLastStartId;
    /* access modifiers changed from: private */
    public MmsNetworkManager mNetworkManager;
    private final Runnable mServiceStopRunnable = new Runnable() {
        public void run() {
            MmsService.this.tryStopService();
        }
    };

    private static void acquireWakeLock(Context context) {
        synchronized (sWakeLockLock) {
            if (sWakeLock == null) {
                sWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, WAKELOCK_ID);
            }
            sWakeLock.acquire();
        }
    }

    private static void ensureLoaders(Context context) {
        if (sUserAgentInfoLoader == null) {
            sUserAgentInfoLoader = new DefaultUserAgentInfoLoader(context);
        }
        if (sCarrierConfigValuesLoader == null) {
            sCarrierConfigValuesLoader = new DefaultCarrierConfigValuesLoader(context);
        }
        if (sApnSettingsLoader == null) {
            sApnSettingsLoader = new DefaultApnSettingsLoader(context);
        }
    }

    private static boolean fromThisProcess(Intent intent) {
        return intent.getIntExtra(EXTRA_MYPID, -1) == getMyPid();
    }

    static ApnSettingsLoader getApnSettingsLoader() {
        return sApnSettingsLoader;
    }

    static CarrierConfigValuesLoader getCarrierConfigValuesLoader() {
        return sCarrierConfigValuesLoader;
    }

    private static int getMyPid() {
        if (sMyPid < 0) {
            sMyPid = Process.myPid();
        }
        return sMyPid;
    }

    private ExecutorService getRequestExecutor(MmsRequest mmsRequest) {
        if (mmsRequest instanceof SendRequest) {
            return this.mExecutors[0];
        }
        return this.mExecutors[1];
    }

    static UserAgentInfoLoader getUserAgentInfoLoader() {
        return sUserAgentInfoLoader;
    }

    private void logServiceStop(Boolean bool) {
        if (bool == null) {
            return;
        }
        if (bool.booleanValue()) {
            Log.i(TAG, "Service successfully stopped");
            verifyWakeLockNotHeld();
            return;
        }
        Log.i(TAG, "Service stopping cancelled");
    }

    /* access modifiers changed from: private */
    public void releaseService() {
        synchronized (this) {
            this.mActiveRequestCount--;
            if (this.mActiveRequestCount <= 0) {
                this.mActiveRequestCount = 0;
                rescheduleServiceStop();
            }
        }
    }

    /* access modifiers changed from: private */
    public static void releaseWakeLock() {
        boolean z;
        synchronized (sWakeLockLock) {
            if (sWakeLock != null) {
                sWakeLock.release();
                z = false;
            } else {
                z = true;
            }
        }
        if (z) {
            Log.w(TAG, "Releasing empty wake lock");
        }
    }

    private void rescheduleServiceStop() {
        this.mHandler.removeCallbacks(this.mServiceStopRunnable);
        this.mHandler.postDelayed(this.mServiceStopRunnable, 2000);
    }

    private void retainService(MmsRequest mmsRequest, Runnable runnable) {
        ExecutorService requestExecutor = getRequestExecutor(mmsRequest);
        synchronized (this) {
            requestExecutor.execute(runnable);
            this.mActiveRequestCount++;
        }
    }

    static void setApnSettingsLoader(ApnSettingsLoader apnSettingsLoader) {
        sApnSettingsLoader = apnSettingsLoader;
    }

    static void setCarrierConfigValuesLoader(CarrierConfigValuesLoader carrierConfigValuesLoader) {
        sCarrierConfigValuesLoader = carrierConfigValuesLoader;
    }

    static void setThreadPoolSize(int i) {
        sThreadPoolSize = i;
    }

    static void setUseWakeLock(boolean z) {
        sUseWakeLock = z;
    }

    static void setUserAgentInfoLoader(UserAgentInfoLoader userAgentInfoLoader) {
        sUserAgentInfoLoader = userAgentInfoLoader;
    }

    public static void startRequest(Context context, MmsRequest mmsRequest) {
        boolean z = sUseWakeLock;
        mmsRequest.setUseWakeLock(z);
        Intent intent = new Intent(context, MmsService.class);
        intent.putExtra(EXTRA_REQUEST, mmsRequest);
        intent.putExtra(EXTRA_MYPID, getMyPid());
        if (z) {
            acquireWakeLock(context);
        }
        if (context.startService(intent) == null && z) {
            releaseWakeLock();
        }
    }

    private void tryScheduleStop() {
        synchronized (this) {
            if (this.mActiveRequestCount == 0) {
                rescheduleServiceStop();
            }
        }
    }

    /* access modifiers changed from: private */
    public void tryStopService() {
        Boolean valueOf;
        synchronized (this) {
            valueOf = this.mActiveRequestCount == 0 ? Boolean.valueOf(stopSelfResult(this.mLastStartId)) : null;
        }
        logServiceStop(valueOf);
    }

    private static void verifyWakeLockNotHeld() {
        boolean z;
        synchronized (sWakeLockLock) {
            z = sWakeLock != null && sWakeLock.isHeld();
        }
        if (z) {
            Log.e(TAG, "Wake lock still held!");
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        ensureLoaders(this);
        int i = 0;
        while (true) {
            ExecutorService[] executorServiceArr = this.mExecutors;
            if (i < executorServiceArr.length) {
                executorServiceArr[i] = Executors.newFixedThreadPool(sThreadPoolSize);
                i++;
            } else {
                this.mNetworkManager = new MmsNetworkManager(this);
                synchronized (this) {
                    this.mActiveRequestCount = 0;
                    this.mLastStartId = -1;
                }
                return;
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        for (ExecutorService shutdown : this.mExecutors) {
            shutdown.shutdown();
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this) {
            this.mLastStartId = i2;
        }
        boolean z = false;
        if (intent == null) {
            Log.w(TAG, "Empty intent");
        } else if (fromThisProcess(intent)) {
            final MmsRequest mmsRequest = (MmsRequest) intent.getParcelableExtra(EXTRA_REQUEST);
            if (mmsRequest != null) {
                try {
                    retainService(mmsRequest, new Runnable() {
                        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
                            if (r4.getUseWakeLock() == false) goto L_0x0037;
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
                            androidx.appcompat.mms.MmsService.access$200();
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
                            androidx.appcompat.mms.MmsService.access$300(r6.this$0);
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
                            return;
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:3:0x001f, code lost:
                            if (r4.getUseWakeLock() != false) goto L_0x0034;
                         */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void run() {
                            /*
                                r6 = this;
                                androidx.appcompat.mms.MmsRequest r0 = r4     // Catch:{ Exception -> 0x0024 }
                                androidx.appcompat.mms.MmsService r1 = androidx.appcompat.mms.MmsService.this     // Catch:{ Exception -> 0x0024 }
                                androidx.appcompat.mms.MmsService r2 = androidx.appcompat.mms.MmsService.this     // Catch:{ Exception -> 0x0024 }
                                androidx.appcompat.mms.MmsNetworkManager r2 = r2.mNetworkManager     // Catch:{ Exception -> 0x0024 }
                                androidx.appcompat.mms.ApnSettingsLoader r3 = androidx.appcompat.mms.MmsService.getApnSettingsLoader()     // Catch:{ Exception -> 0x0024 }
                                androidx.appcompat.mms.CarrierConfigValuesLoader r4 = androidx.appcompat.mms.MmsService.getCarrierConfigValuesLoader()     // Catch:{ Exception -> 0x0024 }
                                androidx.appcompat.mms.UserAgentInfoLoader r5 = androidx.appcompat.mms.MmsService.getUserAgentInfoLoader()     // Catch:{ Exception -> 0x0024 }
                                r0.execute(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0024 }
                                androidx.appcompat.mms.MmsRequest r0 = r4
                                boolean r0 = r0.getUseWakeLock()
                                if (r0 == 0) goto L_0x0037
                                goto L_0x0034
                            L_0x0022:
                                r0 = move-exception
                                goto L_0x003d
                            L_0x0024:
                                r0 = move-exception
                                java.lang.String r1 = "MmsLib"
                                java.lang.String r2 = "Unexpected execution failure"
                                android.util.Log.w(r1, r2, r0)     // Catch:{ all -> 0x0022 }
                                androidx.appcompat.mms.MmsRequest r0 = r4
                                boolean r0 = r0.getUseWakeLock()
                                if (r0 == 0) goto L_0x0037
                            L_0x0034:
                                androidx.appcompat.mms.MmsService.releaseWakeLock()
                            L_0x0037:
                                androidx.appcompat.mms.MmsService r6 = androidx.appcompat.mms.MmsService.this
                                r6.releaseService()
                                return
                            L_0x003d:
                                androidx.appcompat.mms.MmsRequest r1 = r4
                                boolean r1 = r1.getUseWakeLock()
                                if (r1 == 0) goto L_0x0048
                                androidx.appcompat.mms.MmsService.releaseWakeLock()
                            L_0x0048:
                                androidx.appcompat.mms.MmsService r6 = androidx.appcompat.mms.MmsService.this
                                r6.releaseService()
                                throw r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.MmsService.C01812.run():void");
                        }
                    });
                    z = true;
                } catch (RejectedExecutionException e) {
                    Log.w(TAG, "Executing request failed " + e);
                    mmsRequest.returnResult(this, 1, (byte[]) null, 0);
                    if (mmsRequest.getUseWakeLock()) {
                        releaseWakeLock();
                    }
                }
            } else {
                Log.w(TAG, "Empty request");
            }
        } else {
            Log.w(TAG, "Got a restarted intent from previous incarnation");
        }
        if (z) {
            return 2;
        }
        tryScheduleStop();
        return 2;
    }
}
