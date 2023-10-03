package com.android.dialer.common;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class LogUtil {
    /* renamed from: e */
    public static void m8e(String str, String str2, Object... objArr) {
        println(6, "Dialer", str, str2, objArr);
    }

    public static void enterBlock(String str) {
        println(4, "Dialer", str, "enter", new Object[0]);
    }

    /* renamed from: i */
    public static void m9i(String str, String str2, Object... objArr) {
        println(4, "Dialer", str, str2, objArr);
    }

    public static boolean isDebugEnabled() {
        return Log.isLoggable("Dialer", 3);
    }

    public static boolean isVerboseEnabled() {
        return Log.isLoggable("Dialer", 2);
    }

    private static void println(int i, String str, String str2, String str3, Object... objArr) {
        boolean z = objArr == null || objArr.length > 0;
        if (i >= 4 || Log.isLoggable(str, i)) {
            if (!TextUtils.isEmpty(str3)) {
                StringBuilder outline14 = GeneratedOutlineSupport.outline14(str2, " - ");
                if (z) {
                    str3 = String.format(str3, objArr);
                }
                outline14.append(str3);
                str2 = outline14.toString();
            }
            Log.println(i, str, str2);
        }
    }

    public static String sanitizePhoneNumber(String str) {
        if (isDebugEnabled()) {
            return str;
        }
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (char c : str.toCharArray()) {
            if (!isDebugEnabled() && PhoneNumberUtils.is12Key(c)) {
                c = '*';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String sanitizePii(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (isDebugEnabled()) {
            return obj.toString();
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Redacted-");
        outline13.append(obj.toString().length());
        outline13.append("-chars");
        return outline13.toString();
    }

    /* renamed from: w */
    public static void m10w(String str, String str2, Object... objArr) {
        println(5, "Dialer", str, str2, objArr);
    }

    /* renamed from: e */
    public static void m7e(String str, String str2, Throwable th) {
        if (!TextUtils.isEmpty(str2)) {
            StringBuilder outline14 = GeneratedOutlineSupport.outline14(str2, "\n");
            outline14.append(Log.getStackTraceString(th));
            println(6, "Dialer", str, outline14.toString(), new Object[0]);
        }
    }
}
