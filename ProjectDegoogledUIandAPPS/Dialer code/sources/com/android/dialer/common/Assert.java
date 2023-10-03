package com.android.dialer.common;

import android.os.Looper;

public class Assert {
    public static void checkArgument(boolean z) {
        checkArgument(z, (String) null, new Object[0]);
    }

    public static void checkState(boolean z) {
        checkState(z, (String) null, new Object[0]);
    }

    @Deprecated
    public static void fail() {
        throw new AssertionError("Fail");
    }

    private static String format(String str, Object... objArr) {
        if (str == null) {
            return null;
        }
        return String.format(str, objArr);
    }

    public static void isMainThread() {
        checkState(Looper.getMainLooper().equals(Looper.myLooper()), (String) null, new Object[0]);
    }

    public static <T> T isNotNull(T t) {
        isNotNull(t, (String) null, new Object[0]);
        return t;
    }

    public static void isWorkerThread() {
        checkState(!Looper.getMainLooper().equals(Looper.myLooper()), (String) null, new Object[0]);
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(format(str, objArr));
        }
    }

    public static void checkState(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(format(str, objArr));
        }
    }

    public static <T> T isNotNull(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, objArr));
    }
}
