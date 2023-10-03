package com.google.common.primitives;

import java.util.Comparator;

class UnsignedBytes$LexicographicalComparatorHolder {

    enum PureJavaComparator implements Comparator {
        INSTANCE;

        public int compare(Object obj, Object obj2) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = (byte[]) obj2;
            int min = Math.min(bArr.length, bArr2.length);
            for (int i = 0; i < min; i++) {
                int a = C1723b.m4653a(bArr[i]) - C1723b.m4653a(bArr2[i]);
                if (a != 0) {
                    return a;
                }
            }
            return bArr.length - bArr2.length;
        }
    }

    static {
        PureJavaComparator pureJavaComparator = PureJavaComparator.INSTANCE;
    }

    UnsignedBytes$LexicographicalComparatorHolder() {
    }
}
