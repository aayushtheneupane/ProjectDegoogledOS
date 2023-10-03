package com.google.common.base;

import java.io.Serializable;

final class Equivalence$Identity extends C1546u implements Serializable {
    static final Equivalence$Identity INSTANCE = new Equivalence$Identity();
    private static final long serialVersionUID = 1;

    Equivalence$Identity() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo8522c(Object obj, Object obj2) {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: u */
    public int mo8523u(Object obj) {
        return System.identityHashCode(obj);
    }
}
