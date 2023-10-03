package com.google.common.base;

import java.io.Serializable;
import p026b.p027a.p030b.p031a.C0632a;

class Suppliers$MemoizingSupplier implements C1519P, Serializable {
    private static final long serialVersionUID = 0;
    final C1519P delegate;

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Suppliers.memoize(");
        Pa.append(this.delegate);
        Pa.append(")");
        return Pa.toString();
    }
}
