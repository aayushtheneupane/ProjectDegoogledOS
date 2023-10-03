package com.android.messaging.sms;

import android.text.TextUtils;
import android.util.Patterns;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.android.messaging.sms.w */
public class C1027w {
    public static final Pattern NAME_ADDR_EMAIL_PATTERN = Pattern.compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");

    /* renamed from: c */
    public static boolean m2399c(boolean z, int i) {
        if (!TextUtils.isEmpty(C1024t.get(i).mo6840ki())) {
            return false;
        }
        return z;
    }

    public static boolean isEmailAddress(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Matcher matcher = NAME_ADDR_EMAIL_PATTERN.matcher(str);
        if (matcher.matches()) {
            str = matcher.group(2);
        }
        return Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public static boolean isPhoneNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Patterns.PHONE.matcher(str).matches();
    }
}
