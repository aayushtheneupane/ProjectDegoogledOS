package com.google.common.collect;

/* renamed from: com.google.common.collect.K */
final class C1578K {
    /* renamed from: A */
    static int m4253A(Object obj) {
        return m4255cb(obj == null ? 0 : obj.hashCode());
    }

    /* renamed from: a */
    static int m4254a(int i, double d) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max <= ((int) (d * ((double) highestOneBit)))) {
            return highestOneBit;
        }
        int i2 = highestOneBit << 1;
        if (i2 > 0) {
            return i2;
        }
        return 1073741824;
    }

    /* renamed from: cb */
    static int m4255cb(int i) {
        return Integer.rotateLeft(i * -862048943, 15) * 461845907;
    }
}
