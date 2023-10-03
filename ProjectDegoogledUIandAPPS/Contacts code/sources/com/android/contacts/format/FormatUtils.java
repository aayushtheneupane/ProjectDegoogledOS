package com.android.contacts.format;

import android.database.CharArrayBuffer;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import java.util.Arrays;

public class FormatUtils {
    public static int overlapPoint(CharArrayBuffer charArrayBuffer, CharArrayBuffer charArrayBuffer2) {
        if (charArrayBuffer == null || charArrayBuffer2 == null) {
            return -1;
        }
        return overlapPoint(Arrays.copyOfRange(charArrayBuffer.data, 0, charArrayBuffer.sizeCopied), Arrays.copyOfRange(charArrayBuffer2.data, 0, charArrayBuffer2.sizeCopied));
    }

    public static int overlapPoint(String str, String str2) {
        if (str == null || str2 == null) {
            return -1;
        }
        return overlapPoint(str.toCharArray(), str2.toCharArray());
    }

    public static int overlapPoint(char[] cArr, char[] cArr2) {
        if (!(cArr == null || cArr2 == null)) {
            int length = cArr.length;
            int length2 = cArr2.length;
            while (length > 0 && length2 > 0 && cArr[length - 1] == cArr2[length2 - 1]) {
                length--;
                length2--;
            }
            int i = length2;
            int i2 = 0;
            while (i2 < length) {
                if (i2 + i > length) {
                    i = length - i2;
                }
                int i3 = 0;
                while (i3 < i && cArr[i2 + i3] == cArr2[i3]) {
                    i3++;
                }
                if (i3 == i) {
                    return i2;
                }
                i2++;
            }
        }
        return -1;
    }

    public static CharSequence applyStyleToSpan(int i, CharSequence charSequence, int i2, int i3, int i4) {
        int max = Math.max(0, i2);
        int min = Math.min(charSequence.length(), i3);
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new StyleSpan(i), max, min, i4);
        return spannableString;
    }

    public static void copyToCharArrayBuffer(String str, CharArrayBuffer charArrayBuffer) {
        if (str != null) {
            char[] cArr = charArrayBuffer.data;
            if (cArr == null || cArr.length < str.length()) {
                charArrayBuffer.data = str.toCharArray();
            } else {
                str.getChars(0, str.length(), cArr, 0);
            }
            charArrayBuffer.sizeCopied = str.length();
            return;
        }
        charArrayBuffer.sizeCopied = 0;
    }

    public static String charArrayBufferToString(CharArrayBuffer charArrayBuffer) {
        return new String(charArrayBuffer.data, 0, charArrayBuffer.sizeCopied);
    }

    public static int indexOfWordPrefix(CharSequence charSequence, String str) {
        if (!(str == null || charSequence == null)) {
            int length = charSequence.length();
            int length2 = str.length();
            if (length2 != 0 && length >= length2) {
                int i = 0;
                while (i < length) {
                    while (i < length && !Character.isLetterOrDigit(charSequence.charAt(i))) {
                        i++;
                    }
                    if (i + length2 > length) {
                        return -1;
                    }
                    int i2 = 0;
                    while (i2 < length2 && Character.toUpperCase(charSequence.charAt(i + i2)) == str.charAt(i2)) {
                        i2++;
                    }
                    if (i2 == length2) {
                        return i;
                    }
                    while (i < length && Character.isLetterOrDigit(charSequence.charAt(i))) {
                        i++;
                    }
                }
            }
        }
        return -1;
    }
}
