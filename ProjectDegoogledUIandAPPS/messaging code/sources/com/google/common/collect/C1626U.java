package com.google.common.collect;

import java.util.Comparator;

/* renamed from: com.google.common.collect.U */
public class C1626U extends C1614S {
    private final Comparator comparator;

    public C1626U(Comparator comparator2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            return;
        }
        throw new NullPointerException();
    }

    public ImmutableMap build() {
        return ImmutableSortedMap.m4241a(this.comparator, false, this.size, this.entries);
    }

    public C1614S put(Object obj, Object obj2) {
        super.put(obj, obj2);
        return this;
    }
}
