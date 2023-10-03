package com.google.common.primitives;

import com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder;
import java.util.Comparator;

/* renamed from: com.google.common.primitives.b */
public final class C1723b {
    /* renamed from: a */
    public static int m4653a(byte b) {
        return b & 255;
    }

    static Comparator lexicographicalComparatorJavaImpl() {
        return UnsignedBytes$LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }
}
