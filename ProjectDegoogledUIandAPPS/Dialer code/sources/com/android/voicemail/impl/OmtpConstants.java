package com.android.voicemail.impl;

import android.os.Build;

public class OmtpConstants {
    static {
        new String[]{"NM", "MBU", "GU"};
        new String[]{"v", "o", "f", "i", "e"};
        new String[]{"N", "R", "P", "U", "B"};
        new String[]{"0", "1", "2", "3", "4", "5", "6", "7"};
    }

    public static String getClientType() {
        String truncate = truncate(Build.MANUFACTURER.replace('=', '_').replace(';', '_').replace('.', '_').replace(' ', '_'), 12);
        String truncate2 = truncate(Build.VERSION.RELEASE.replace('=', '_').replace(';', '_').replace('.', '_').replace(' ', '_'), 8);
        return String.format("%s.%s.%s", new Object[]{truncate, truncate(Build.MODEL.replace('=', '_').replace(';', '_').replace('.', '_').replace(' ', '_'), (28 - truncate.length()) - truncate2.length()), truncate2});
    }

    private static final String truncate(String str, int i) {
        return str.substring(0, Math.min(i, str.length()));
    }
}
