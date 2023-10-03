package com.google.common.base;

import java.io.Serializable;

final class Equivalence$Equals extends C1546u implements Serializable {
    static final Equivalence$Equals INSTANCE = new Equivalence$Equals();
    private static final long serialVersionUID = 1;

    Equivalence$Equals() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo8522c(Object obj, Object obj2) {
        return obj.equals(obj2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: u */
    public int mo8523u(Object obj) {
        return obj.hashCode();
    }
}
