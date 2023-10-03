package com.bumptech.glide.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.SystemClock;

public final class LogTime {
    private static final double MILLIS_MULTIPLIER = (1.0d / Math.pow(10.0d, 6.0d));

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public static double getElapsedMillis(long j) {
        return ((double) (getLogTime() - j)) * MILLIS_MULTIPLIER;
    }

    @TargetApi(17)
    public static long getLogTime() {
        int i = Build.VERSION.SDK_INT;
        return SystemClock.elapsedRealtimeNanos();
    }
}
