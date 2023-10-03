package com.google.common.collect;

/* renamed from: com.google.common.collect.T */
public class C1624T extends C1582M {
    public C1624T() {
        super(4);
    }

    public C1624T addAll(Iterable iterable) {
        super.addAll(iterable);
        return this;
    }

    public ImmutableSet build() {
        ImmutableSet a = ImmutableSet.m4232b(this.size, this.contents);
        this.size = a.size();
        return a;
    }

    /* renamed from: d */
    public C1624T mo8823d(Object... objArr) {
        super.mo8823d(objArr);
        return this;
    }

    public C1624T add(Object obj) {
        super.add(obj);
        return this;
    }
}
