package com.google.common.base;

/* renamed from: com.google.common.base.u */
public abstract class C1546u {
    protected C1546u() {
    }

    public static C1546u equals() {
        return Equivalence$Equals.INSTANCE;
    }

    public static C1546u identity() {
        return Equivalence$Identity.INSTANCE;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract boolean mo8522c(Object obj, Object obj2);

    /* renamed from: d */
    public final boolean mo8558d(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return mo8522c(obj, obj2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: u */
    public abstract int mo8523u(Object obj);

    /* renamed from: v */
    public final int mo8559v(Object obj) {
        if (obj == null) {
            return 0;
        }
        return mo8523u(obj);
    }
}
