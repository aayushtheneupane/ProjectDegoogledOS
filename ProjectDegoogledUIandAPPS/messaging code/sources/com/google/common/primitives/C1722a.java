package com.google.common.primitives;

import java.util.Arrays;

/* renamed from: com.google.common.primitives.a */
public final class C1722a {

    /* renamed from: sO */
    private static final byte[] f2565sO = new byte[128];

    static {
        Arrays.fill(f2565sO, (byte) -1);
        for (int i = 0; i <= 9; i++) {
            f2565sO[i + 48] = (byte) i;
        }
        for (int i2 = 0; i2 <= 26; i2++) {
            byte[] bArr = f2565sO;
            byte b = (byte) (i2 + 10);
            bArr[i2 + 65] = b;
            bArr[i2 + 97] = b;
        }
    }

    /* renamed from: G */
    public static int m4652G(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return j < -2147483648L ? RtlSpacingHelper.UNDEFINED : (int) j;
    }
}
