package com.android.dialer.searchfragment.common;

import android.content.Context;
import android.support.p000v4.util.SimpleArrayMap;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.dialer.dialpadview.DialpadCharMappings;
import java.util.regex.Pattern;

public class QueryFilteringUtil {
    private static final SimpleArrayMap<Character, Character> DEFAULT_CHAR_TO_DIGIT_MAP = DialpadCharMappings.getDefaultCharToKeyMap();
    private static final Pattern T9_PATTERN = Pattern.compile("[\\-()2-9]+");

    public static String digitsOnly(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isDigit(charAt)) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
        r3 = r3.get(java.lang.Character.valueOf(r2));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static char getDigit(char r2, android.content.Context r3) {
        /*
            android.support.v4.util.SimpleArrayMap<java.lang.Character, java.lang.Character> r0 = DEFAULT_CHAR_TO_DIGIT_MAP
            java.lang.Character r1 = java.lang.Character.valueOf(r2)
            java.lang.Object r0 = r0.get(r1)
            java.lang.Character r0 = (java.lang.Character) r0
            if (r0 == 0) goto L_0x0013
            char r2 = r0.charValue()
            return r2
        L_0x0013:
            android.support.v4.util.SimpleArrayMap r3 = com.android.dialer.dialpadview.DialpadCharMappings.getCharToKeyMap((android.content.Context) r3)
            if (r3 == 0) goto L_0x0029
            java.lang.Character r0 = java.lang.Character.valueOf(r2)
            java.lang.Object r3 = r3.get(r0)
            java.lang.Character r3 = (java.lang.Character) r3
            if (r3 == 0) goto L_0x0029
            char r2 = r3.charValue()
        L_0x0029:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.searchfragment.common.QueryFilteringUtil.getDigit(char, android.content.Context):char");
    }

    public static int getIndexOfT9Substring(String str, String str2, Context context) {
        String digitsOnly = digitsOnly(str);
        StringBuilder sb = new StringBuilder(str2.length());
        int i = 0;
        for (char digit : str2.toLowerCase().toCharArray()) {
            sb.append(getDigit(digit, context));
        }
        String sb2 = sb.toString();
        String digitsOnly2 = digitsOnly(sb2);
        if (digitsOnly2.startsWith(digitsOnly)) {
            return 0;
        }
        for (int i2 = 1; i2 < digitsOnly2.length(); i2++) {
            if (!Character.isDigit(sb2.charAt(i2))) {
                i++;
            } else {
                int i3 = i2 - i;
                if (!Character.isDigit(sb2.charAt(i2 - 1)) && digitsOnly2.startsWith(digitsOnly, i3)) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public static boolean nameMatchesT9Query(String str, String str2, Context context) {
        if (!T9_PATTERN.matcher(str).matches()) {
            return false;
        }
        String digitsOnly = digitsOnly(str);
        if (getIndexOfT9Substring(digitsOnly, str2, context) != -1) {
            return true;
        }
        String[] split = str2.toLowerCase().split("\\s");
        int i = 0;
        for (int i2 = 0; i2 < split.length && i < digitsOnly.length(); i2++) {
            if (!TextUtils.isEmpty(split[i2]) && getDigit(split[i2].charAt(0), context) == digitsOnly.charAt(i)) {
                i++;
            }
        }
        if (i == digitsOnly.length()) {
            return true;
        }
        return false;
    }

    public static boolean numberMatchesNumberQuery(String str, String str2) {
        return PhoneNumberUtils.isGlobalPhoneNumber(str) && digitsOnly(str2).indexOf(digitsOnly(str)) != -1;
    }
}
