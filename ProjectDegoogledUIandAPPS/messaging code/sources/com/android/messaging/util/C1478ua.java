package com.android.messaging.util;

import android.os.AsyncTask;
import android.os.Debug;
import android.os.SystemClock;

/* renamed from: com.android.messaging.util.ua */
public abstract class C1478ua extends AsyncTask {
    private static C1398Ba sWakeLock = new C1398Ba("bugle_safe_async_task_wakelock");

    /* renamed from: Dd */
    private final long f2343Dd;

    /* renamed from: Ed */
    private final boolean f2344Ed;

    /* renamed from: Fd */
    private boolean f2345Fd;

    public C1478ua() {
        this(10000, false);
    }

    /* renamed from: a */
    public static void m3823a(Runnable runnable) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(runnable);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo6323a(Object... objArr);

    /* renamed from: b */
    public final C1478ua mo8233b(Object... objArr) {
        C1424b.m3593oc();
        this.f2345Fd = true;
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objArr);
        return this;
    }

    /* access modifiers changed from: protected */
    public final Object doInBackground(Object... objArr) {
        C1424b.m3592ia(this.f2345Fd);
        if (this.f2344Ed) {
            C1480va.getMainThreadHandler().postDelayed(new C1476ta(this), this.f2343Dd);
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            return mo6323a(objArr);
        } finally {
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
            if (elapsedRealtime2 > this.f2343Dd) {
                C1430e.m3630w("MessagingApp", String.format("%s took %dms", new Object[]{this, Long.valueOf(elapsedRealtime2)}));
                if (!Debug.isDebuggerConnected() && !this.f2344Ed) {
                    C1424b.fail(this + " took too long");
                }
            }
        }
    }

    public C1478ua(long j, boolean z) {
        C1424b.m3593oc();
        this.f2343Dd = j;
        this.f2344Ed = z;
    }
}
