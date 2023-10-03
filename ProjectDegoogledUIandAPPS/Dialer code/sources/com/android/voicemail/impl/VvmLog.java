package com.android.voicemail.impl;

import com.android.dialer.common.LogUtil;
import com.android.dialer.persistentlog.PersistentLogger;
import java.util.ArrayDeque;

public class VvmLog {

    public static class LocalLog {
        private final int maxLines;

        public LocalLog(int i) {
            this.maxLines = Math.max(0, i);
            new ArrayDeque(this.maxLines);
        }
    }

    static {
        new LocalLog(100);
    }

    /* renamed from: e */
    public static void m43e(String str, String str2) {
        PersistentLogger.logText(str, str2);
        LogUtil.m8e(str, str2, new Object[0]);
    }

    /* renamed from: i */
    public static void m45i(String str, String str2) {
        PersistentLogger.logText(str, str2);
        LogUtil.m9i(str, str2, new Object[0]);
    }

    public static String pii(Object obj) {
        return obj == null ? String.valueOf(obj) : "[PII]";
    }

    /* renamed from: w */
    public static void m46w(String str, String str2) {
        PersistentLogger.logText(str, str2);
        LogUtil.m10w(str, str2, new Object[0]);
    }

    public static void wtf(String str, String str2) {
        PersistentLogger.logText(str, str2);
        LogUtil.m8e(str, str2, new Object[0]);
    }

    /* renamed from: e */
    public static void m44e(String str, String str2, Throwable th) {
        PersistentLogger.logText(str, str2 + " " + th);
        LogUtil.m7e(str, str2, th);
    }

    /* renamed from: w */
    public static void m47w(String str, String str2, Throwable th) {
        PersistentLogger.logText(str, str2 + " " + th);
        LogUtil.m10w(str, str2, th);
    }
}
