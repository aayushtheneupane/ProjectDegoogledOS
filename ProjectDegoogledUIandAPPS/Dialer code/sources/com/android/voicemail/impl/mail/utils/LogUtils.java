package com.android.voicemail.impl.mail.utils;

import android.util.Log;
import com.android.voicemail.impl.VvmLog;

public class LogUtils {
    public static boolean buildPreventsDebugLogging() {
        return true;
    }

    /* renamed from: d */
    public static void m51d(String str, String str2, Object... objArr) {
        if (isLoggable(str, 3)) {
            String.format(str2, objArr);
        }
    }

    /* renamed from: e */
    public static void m52e(String str, String str2, Object... objArr) {
        if (isLoggable(str, 6)) {
            VvmLog.m43e(str, String.format(str2, objArr));
        }
    }

    /* renamed from: i */
    public static void m54i(String str, String str2, Object... objArr) {
        if (isLoggable(str, 4)) {
            VvmLog.m45i(str, String.format(str2, objArr));
        }
    }

    public static boolean isLoggable(String str, int i) {
        if (3 > i) {
            return false;
        }
        return Log.isLoggable(str, i) || Log.isLoggable("Email Log", i);
    }

    public static void setDebugLoggingEnabledForTests(boolean z) {
        Boolean.valueOf(z);
    }

    /* renamed from: v */
    public static void m55v(String str, String str2, Object... objArr) {
        if (isLoggable(str, 2)) {
            String.format(str2, objArr);
        }
    }

    /* renamed from: w */
    public static void m56w(String str, String str2, Object... objArr) {
        if (isLoggable(str, 5)) {
            VvmLog.m46w(str, String.format(str2, objArr));
        }
    }

    /* renamed from: e */
    public static void m53e(String str, Throwable th, String str2, Object... objArr) {
        if (isLoggable(str, 6)) {
            VvmLog.m44e(str, String.format(str2, objArr), th);
        }
    }
}
