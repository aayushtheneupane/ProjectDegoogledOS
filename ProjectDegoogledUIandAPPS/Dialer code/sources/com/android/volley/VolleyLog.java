package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VolleyLog {
    private static final String CLASS_NAME = VolleyLog.class.getName();
    public static boolean DEBUG = Log.isLoggable(TAG, 2);
    public static String TAG = "Volley";

    static class MarkerLog {
        public static final boolean ENABLED = VolleyLog.DEBUG;
        private boolean mFinished = false;
        private final List<Marker> mMarkers = new ArrayList();

        private static class Marker {
            public final String name;
            public final long thread;
            public final long time;

            public Marker(String str, long j, long j2) {
                this.name = str;
                this.thread = j;
                this.time = j2;
            }
        }

        MarkerLog() {
        }

        public synchronized void add(String str, long j) {
            if (!this.mFinished) {
                this.mMarkers.add(new Marker(str, j, SystemClock.elapsedRealtime()));
            } else {
                throw new IllegalStateException("Marker added to finished log");
            }
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            if (!this.mFinished) {
                finish("Request on the loose");
                VolleyLog.m59e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        public synchronized void finish(String str) {
            long j;
            this.mFinished = true;
            if (this.mMarkers.size() == 0) {
                j = 0;
            } else {
                long j2 = this.mMarkers.get(0).time;
                List<Marker> list = this.mMarkers;
                j = list.get(list.size() - 1).time - j2;
            }
            if (j > 0) {
                long j3 = this.mMarkers.get(0).time;
                VolleyLog.m58d("(%-4d ms) %s", Long.valueOf(j), str);
                for (Marker next : this.mMarkers) {
                    long j4 = next.time;
                    VolleyLog.m58d("(+%-4d) [%2d] %s", Long.valueOf(j4 - j3), Long.valueOf(next.thread), next.name);
                    j3 = j4;
                }
            }
        }
    }

    private static String buildMessage(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str2 = "<unknown>";
                break;
            } else if (!stackTrace[i].getClassName().equals(CLASS_NAME)) {
                String className = stackTrace[i].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                StringBuilder outline14 = GeneratedOutlineSupport.outline14(substring.substring(substring.lastIndexOf(36) + 1), ".");
                outline14.append(stackTrace[i].getMethodName());
                str2 = outline14.toString();
                break;
            } else {
                i++;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), str2, str});
    }

    /* renamed from: d */
    public static void m58d(String str, Object... objArr) {
        Log.d("Volley", buildMessage(str, objArr));
    }

    /* renamed from: e */
    public static void m59e(String str, Object... objArr) {
        Log.e("Volley", buildMessage(str, objArr));
    }

    /* renamed from: v */
    public static void m61v(String str, Object... objArr) {
        if (DEBUG) {
            Log.v("Volley", buildMessage(str, objArr));
        }
    }

    /* renamed from: e */
    public static void m60e(Throwable th, String str, Object... objArr) {
        Log.e("Volley", buildMessage(str, objArr), th);
    }
}
