package com.android.dialer.searchfragment.common;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryBoldingUtil {
    private static SpannableString getBoldedString(String str, int i, int i2) {
        if (i2 + i > str.length()) {
            LogUtil.m8e("QueryBoldingUtil#getBoldedString", "number of bolded characters exceeded length of string.", new Object[0]);
            i2 = str.length() - i;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new StyleSpan(1), i, i2 + i, 17);
        return spannableString;
    }

    public static CharSequence getNameWithQueryBolded(String str, String str2, Context context) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (!QueryFilteringUtil.nameMatchesT9Query(str, str2, context)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("(^|\\s)");
            outline13.append(Pattern.quote(str.toLowerCase()));
            Matcher matcher = Pattern.compile(outline13.toString()).matcher(str2.toLowerCase());
            return matcher.find() ? getBoldedString(str2, matcher.start(), str.length()) : str2;
        }
        int indexOfT9Substring = QueryFilteringUtil.getIndexOfT9Substring(str, str2, context);
        int i = -1;
        if (indexOfT9Substring != -1) {
            int length = str.length();
            int i2 = indexOfT9Substring;
            while (i2 <= indexOfT9Substring + length && i2 < str2.length()) {
                if (!Character.isLetter(str2.charAt(i2))) {
                    length++;
                }
                i2++;
            }
            return getBoldedString(str2, indexOfT9Substring, length);
        }
        SpannableString spannableString = new SpannableString(str2);
        String lowerCase = str2.toLowerCase();
        int i3 = 0;
        while (true) {
            i++;
            if (i >= lowerCase.length() || i3 >= str.length()) {
                return spannableString;
            }
            if ((i == 0 || lowerCase.charAt(i - 1) == ' ') && QueryFilteringUtil.getDigit(lowerCase.charAt(i), context) == str.charAt(i3)) {
                spannableString.setSpan(new StyleSpan(1), i, i + 1, 18);
                i3++;
            }
        }
        return spannableString;
    }

    public static CharSequence getNumberWithQueryBolded(String str, String str2) {
        if (TextUtils.isEmpty(str) || !QueryFilteringUtil.numberMatchesNumberQuery(str, str2)) {
            return str2;
        }
        int indexOf = QueryFilteringUtil.digitsOnly(str2).indexOf(QueryFilteringUtil.digitsOnly(str));
        int length = str.length();
        int i = length;
        for (char isDigit : str.toCharArray()) {
            if (!Character.isDigit(isDigit)) {
                i--;
            }
        }
        for (int i2 = 0; i2 < indexOf + i; i2++) {
            if (!Character.isDigit(str2.charAt(i2))) {
                if (i2 <= indexOf) {
                    indexOf++;
                } else {
                    i++;
                }
            }
        }
        return getBoldedString(str2, indexOf, i);
    }
}
