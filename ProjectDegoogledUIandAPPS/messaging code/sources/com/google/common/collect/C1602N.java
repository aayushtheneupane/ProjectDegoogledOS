package com.google.common.collect;

/* renamed from: com.google.common.collect.N */
public abstract class C1602N {
    C1602N() {
    }

    /* renamed from: L */
    static int m4410L(int i, int i2) {
        if (i2 >= 0) {
            int i3 = i + (i >> 1) + 1;
            if (i3 < i2) {
                i3 = Integer.highestOneBit(i2 - 1) << 1;
            }
            if (i3 < 0) {
                return Integer.MAX_VALUE;
            }
            return i3;
        }
        throw new AssertionError("cannot store more than MAX_VALUE elements");
    }

    public abstract C1602N add(Object obj);
}
