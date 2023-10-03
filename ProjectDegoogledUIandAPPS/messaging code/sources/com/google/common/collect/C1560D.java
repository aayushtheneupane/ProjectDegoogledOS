package com.google.common.collect;

/* renamed from: com.google.common.collect.D */
public abstract class C1560D {
    /* access modifiers changed from: private */
    public static final C1560D ACTIVE = new C1554B();
    /* access modifiers changed from: private */
    public static final C1560D GREATER = new C1558C(1);
    /* access modifiers changed from: private */
    public static final C1560D LESS = new C1558C(-1);

    /* synthetic */ C1560D(C1554B b) {
    }

    public static C1560D start() {
        return ACTIVE;
    }

    public abstract C1560D compare(Comparable comparable, Comparable comparable2);

    /* renamed from: il */
    public abstract int mo8591il();
}
