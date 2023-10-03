package com.google.common.collect;

import com.google.common.base.C1547v;
import java.util.Comparator;

/* renamed from: com.google.common.collect.bb */
public abstract class C1644bb implements Comparator {
    protected C1644bb() {
    }

    /* renamed from: Bl */
    public static C1644bb m4561Bl() {
        return UsingToStringOrdering.INSTANCE;
    }

    /* renamed from: b */
    public static C1644bb m4562b(Comparator comparator) {
        if (comparator instanceof C1644bb) {
            return (C1644bb) comparator;
        }
        return new ComparatorOrdering(comparator);
    }

    /* renamed from: zl */
    public static C1644bb m4563zl() {
        return NaturalOrdering.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Al */
    public C1644bb mo9139Al() {
        return mo9140a(C1633Xa.m4548xl());
    }

    /* renamed from: a */
    public C1644bb mo9140a(C1547v vVar) {
        return new ByFunctionOrdering(vVar, this);
    }

    public abstract int compare(Object obj, Object obj2);

    public C1644bb reverse() {
        return new ReverseOrdering(this);
    }
}
