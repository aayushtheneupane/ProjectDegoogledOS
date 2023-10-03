package com.google.common.collect;

import java.io.Serializable;

final class NaturalOrdering extends C1644bb implements Serializable {
    static final NaturalOrdering INSTANCE = new NaturalOrdering();
    private static final long serialVersionUID = 0;

    private NaturalOrdering() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public C1644bb reverse() {
        return ReverseNaturalOrdering.INSTANCE;
    }

    public String toString() {
        return "Ordering.natural()";
    }

    public int compare(Comparable comparable, Comparable comparable2) {
        if (comparable == null) {
            throw new NullPointerException();
        } else if (comparable2 != null) {
            return comparable.compareTo(comparable2);
        } else {
            throw new NullPointerException();
        }
    }
}
