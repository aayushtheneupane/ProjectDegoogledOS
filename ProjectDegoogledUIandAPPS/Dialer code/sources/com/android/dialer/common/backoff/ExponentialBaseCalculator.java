package com.android.dialer.common.backoff;

import com.android.dialer.common.Assert;

public final class ExponentialBaseCalculator {
    /* renamed from: f */
    private static double m11f(double d, long j, double d2) {
        return ((1.0d - d2) / (1.0d - d)) - ((double) j);
    }

    public static double findBase(long j, long j2, int i) {
        long j3 = j;
        long j4 = j2;
        int i2 = i;
        int i3 = 0;
        boolean z = true;
        Assert.checkArgument(j3 < j4);
        if (i2 <= 1) {
            z = false;
        }
        Assert.checkArgument(z);
        double d = (double) j3;
        long round = Math.round(((double) j4) / d);
        double d2 = 1.0d / d;
        double d3 = (double) i2;
        double pow = Math.pow(2.0d, d3);
        double d4 = 2.0d;
        double f = m11f(2.0d, round, pow);
        if (Math.abs(f) >= d2) {
            while (i3 < 1000) {
                double d5 = d4 - (f / (((((double) round) + f) - ((pow * d3) / d4)) / (1.0d - d4)));
                pow = Math.pow(d5, d3);
                d4 = d5;
                f = m11f(d5, round, pow);
                if (Math.abs(f) >= d2) {
                    i3++;
                }
            }
            throw new IllegalStateException("Failed to find base. Too many iterations.");
        }
        return d4;
    }
}
