package com.google.common.collect;

/* renamed from: com.google.common.collect.db */
class C1650db extends C1644bb {
    C1650db() {
    }

    public int compare(Object obj, Object obj2) {
        Range range = (Range) obj;
        Range range2 = (Range) obj2;
        return C1560D.start().compare(range.lowerBound, range2.lowerBound).compare(range.upperBound, range2.upperBound).mo8591il();
    }
}
