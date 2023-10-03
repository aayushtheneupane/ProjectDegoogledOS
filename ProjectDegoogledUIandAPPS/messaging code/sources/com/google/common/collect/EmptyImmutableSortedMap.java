package com.google.common.collect;

import java.util.Comparator;

final class EmptyImmutableSortedMap extends ImmutableSortedMap {
    private final transient ImmutableSortedSet keySet;

    EmptyImmutableSortedMap(Comparator comparator) {
        this.keySet = ImmutableSortedSet.m4246c(comparator);
    }

    public Object get(Object obj) {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ol */
    public ImmutableSet mo8644ol() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8645pl() {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: rl */
    public ImmutableSortedMap mo8660rl() {
        return new EmptyImmutableSortedMap(C1644bb.m4562b(comparator()).reverse(), this);
    }

    public int size() {
        return 0;
    }

    public String toString() {
        return "{}";
    }

    public ImmutableSet entrySet() {
        return ImmutableSet.m4235ql();
    }

    public ImmutableSortedMap headMap(Object obj, boolean z) {
        if (obj != null) {
            return this;
        }
        throw new NullPointerException();
    }

    public ImmutableSortedMap tailMap(Object obj, boolean z) {
        if (obj != null) {
            return this;
        }
        throw new NullPointerException();
    }

    public ImmutableCollection values() {
        return ImmutableList.m4204ql();
    }

    EmptyImmutableSortedMap(Comparator comparator, ImmutableSortedMap immutableSortedMap) {
        super(immutableSortedMap);
        this.keySet = ImmutableSortedSet.m4246c(comparator);
    }

    public ImmutableSortedSet keySet() {
        return this.keySet;
    }
}
