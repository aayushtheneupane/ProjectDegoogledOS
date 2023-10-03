package com.google.common.base;

import java.util.Arrays;

/* renamed from: com.google.common.base.s */
class C1544s extends C1545t {

    /* renamed from: NM */
    private final char[] f2410NM;

    /* renamed from: OM */
    private final char[] f2411OM;

    C1544s(String str, char[] cArr, char[] cArr2) {
        super(str);
        this.f2410NM = cArr;
        this.f2411OM = cArr2;
        C1508E.checkArgument(cArr.length == cArr2.length);
        int i = 0;
        while (i < cArr.length) {
            C1508E.checkArgument(cArr[i] <= cArr2[i]);
            int i2 = i + 1;
            if (i2 < cArr.length) {
                C1508E.checkArgument(cArr2[i] < cArr[i2]);
            }
            i = i2;
        }
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        int binarySearch = Arrays.binarySearch(this.f2410NM, c);
        if (binarySearch >= 0) {
            return true;
        }
        int i = (~binarySearch) - 1;
        if (i < 0 || c > this.f2411OM[i]) {
            return false;
        }
        return true;
    }
}
