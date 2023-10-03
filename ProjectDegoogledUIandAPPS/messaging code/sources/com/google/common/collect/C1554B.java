package com.google.common.collect;

/* renamed from: com.google.common.collect.B */
class C1554B extends C1560D {
    C1554B() {
        super((C1554B) null);
    }

    public C1560D compare(Comparable comparable, Comparable comparable2) {
        int compareTo = comparable.compareTo(comparable2);
        if (compareTo < 0) {
            return C1560D.LESS;
        }
        return compareTo > 0 ? C1560D.GREATER : C1560D.ACTIVE;
    }

    /* renamed from: il */
    public int mo8591il() {
        return 0;
    }
}
