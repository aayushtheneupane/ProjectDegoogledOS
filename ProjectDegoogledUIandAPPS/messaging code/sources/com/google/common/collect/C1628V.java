package com.google.common.collect;

import java.util.Comparator;

/* renamed from: com.google.common.collect.V */
public final class C1628V extends C1624T {
    private final Comparator comparator;

    public C1628V(Comparator comparator2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            return;
        }
        throw new NullPointerException();
    }

    public C1602N add(Object obj) {
        super.add(obj);
        return this;
    }

    public C1624T addAll(Iterable iterable) {
        super.addAll(iterable);
        return this;
    }

    /* renamed from: d */
    public C1628V mo8823d(Object... objArr) {
        super.mo8823d(objArr);
        return this;
    }

    /* renamed from: add  reason: collision with other method in class */
    public C1624T m4708add(Object obj) {
        super.add(obj);
        return this;
    }

    public ImmutableSortedSet build() {
        ImmutableSortedSet a = ImmutableSortedSet.m4245a(this.comparator, this.size, this.contents);
        this.size = a.size();
        return a;
    }

    /* renamed from: add  reason: collision with other method in class */
    public C1628V m4709add(Object obj) {
        super.add(obj);
        return this;
    }
}
