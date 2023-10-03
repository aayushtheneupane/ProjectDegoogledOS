package com.android.dialer.oem;

import android.os.Build;
import com.google.common.collect.ImmutableSet;

public final class TranssionUtils {
    public static final ImmutableSet<String> TRANSSION_DEVICE_MANUFACTURERS = ImmutableSet.m88of("INFINIX MOBILITY LIMITED", "itel", "TECNO");
    public static final ImmutableSet<String> TRANSSION_SECRET_CODES = ImmutableSet.m89of("*#07#", "*#87#", "*#43#", "*#2727#", "*#88#");

    public static boolean isTranssionSecretCode(String str) {
        return TRANSSION_DEVICE_MANUFACTURERS.contains(Build.MANUFACTURER) && TRANSSION_SECRET_CODES.contains(str);
    }
}
