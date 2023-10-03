package com.android.messaging.util;

import android.webkit.MimeTypeMap;

/* renamed from: com.android.messaging.util.w */
public final class C1481w {
    /* renamed from: Aa */
    public static boolean m3826Aa(String str) {
        return m3831za(str) || m3830Ea(str) || isImageType(str) || m3829Da(str);
    }

    /* renamed from: Ba */
    public static boolean m3827Ba(String str) {
        return isImageType(str) || m3830Ea(str) || m3831za(str) || m3829Da(str);
    }

    /* renamed from: Ca */
    public static boolean m3828Ca(String str) {
        return "text/plain".equals(str) || "text/html".equals(str) || "application/vnd.wap.xhtml+xml".equals(str);
    }

    /* renamed from: Da */
    public static boolean m3829Da(String str) {
        return str != null && str.equalsIgnoreCase("text/x-vCard");
    }

    /* renamed from: Ea */
    public static boolean m3830Ea(String str) {
        return str != null && str.startsWith("video/");
    }

    public static String getExtensionFromMimeType(String str) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
    }

    public static boolean isImageType(String str) {
        return str != null && str.startsWith("image/");
    }

    /* renamed from: za */
    public static boolean m3831za(String str) {
        return str != null && (str.startsWith("audio/") || str.equalsIgnoreCase("application/ogg"));
    }
}
