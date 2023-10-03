package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.util.Arrays;

public final class Ints {
    private static final byte[] asciiDigits = new byte[128];

    public static int saturatedCast(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    public static int indexOf(int[] iArr, int i) {
        return indexOf(iArr, i, 0, iArr.length);
    }

    private static int indexOf(int[] iArr, int i, int i2, int i3) {
        while (i2 < i3) {
            if (iArr[i2] == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    static {
        Arrays.fill(asciiDigits, (byte) -1);
        for (int i = 0; i <= 9; i++) {
            asciiDigits[i + 48] = (byte) i;
        }
        for (int i2 = 0; i2 <= 26; i2++) {
            byte[] bArr = asciiDigits;
            byte b = (byte) (i2 + 10);
            bArr[i2 + 65] = b;
            bArr[i2 + 97] = b;
        }
    }

    private static int digit(char c) {
        if (c < 128) {
            return asciiDigits[c];
        }
        return -1;
    }

    public static Integer tryParse(String str) {
        return tryParse(str, 10);
    }

    static Integer tryParse(String str, int i) {
        int i2;
        Preconditions.checkNotNull(str);
        if (str.isEmpty()) {
            return null;
        }
        if (i < 2 || i > 36) {
            throw new IllegalArgumentException("radix must be between MIN_RADIX and MAX_RADIX but was " + i);
        }
        int i3 = 0;
        if (str.charAt(0) == '-') {
            i3 = 1;
        }
        if (i3 == str.length()) {
            return null;
        }
        int i4 = i3 + 1;
        int digit = digit(str.charAt(i3));
        if (digit < 0 || digit >= i) {
            return null;
        }
        int i5 = -digit;
        int i6 = Integer.MIN_VALUE / i;
        while (i4 < str.length()) {
            int i7 = i4 + 1;
            int digit2 = digit(str.charAt(i4));
            if (digit2 < 0 || digit2 >= i || i5 < i6 || (i2 = i5 * i) < digit2 - 2147483648) {
                return null;
            }
            i5 = i2 - digit2;
            i4 = i7;
        }
        if (i3 != 0) {
            return Integer.valueOf(i5);
        }
        if (i5 == Integer.MIN_VALUE) {
            return null;
        }
        return Integer.valueOf(-i5);
    }
}
