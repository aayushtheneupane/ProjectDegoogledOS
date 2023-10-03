package com.google.common.base;

import java.io.Serializable;
import p026b.p027a.p030b.p031a.C0632a;

class Suppliers$ExpiringMemoizingSupplier implements C1519P, Serializable {
    private static final long serialVersionUID = 0;
    final C1519P delegate;
    final long durationNanos;

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Suppliers.memoizeWithExpiration(");
        Pa.append(this.delegate);
        Pa.append(", ");
        Pa.append(this.durationNanos);
        Pa.append(", NANOS)");
        return Pa.toString();
    }
}
