package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: com.google.common.collect.Va */
class C1629Va extends C1625Ta implements SortedSet {
    C1629Va(SortedMap sortedMap) {
        super(sortedMap);
    }

    public Comparator comparator() {
        return map().comparator();
    }

    public Object first() {
        return map().firstKey();
    }

    public Object last() {
        return map().lastKey();
    }

    /* access modifiers changed from: package-private */
    public SortedMap map() {
        throw null;
    }
}
