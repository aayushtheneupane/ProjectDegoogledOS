package com.android.dialer.strictmode;

import android.os.Looper;
import android.os.StrictMode;
import com.android.dialer.buildtype.BuildType;
import com.android.dialer.function.Supplier;

public final class StrictModeUtils {
    private static final StrictMode.ThreadPolicy THREAD_NO_PENALTY = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    public static <T> T bypass(Supplier<T> supplier) {
        if (!isStrictModeAllowed() || !Looper.getMainLooper().equals(Looper.myLooper())) {
            return supplier.get();
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(THREAD_NO_PENALTY);
        try {
            return supplier.get();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static boolean isStrictModeAllowed() {
        return BuildType.get() == 1;
    }

    public static void bypass(Runnable runnable) {
        if (!isStrictModeAllowed() || !Looper.getMainLooper().equals(Looper.myLooper())) {
            runnable.run();
            return;
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(THREAD_NO_PENALTY);
        try {
            runnable.run();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
}
