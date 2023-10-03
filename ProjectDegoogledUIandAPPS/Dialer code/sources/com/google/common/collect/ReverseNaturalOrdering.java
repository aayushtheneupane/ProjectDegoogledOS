package com.google.common.collect;

import java.io.Serializable;

final class ReverseNaturalOrdering extends Ordering<Comparable> implements Serializable {
    static final ReverseNaturalOrdering INSTANCE = new ReverseNaturalOrdering();
    private static final long serialVersionUID = 0;

    private ReverseNaturalOrdering() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public <S extends Comparable> Ordering<S> reverse() {
        return NaturalOrdering.INSTANCE;
    }

    public String toString() {
        return "Ordering.natural().reverse()";
    }

    public int compare(Comparable comparable, Comparable comparable2) {
        if (comparable == null) {
            throw new NullPointerException();
        } else if (comparable == comparable2) {
            return 0;
        } else {
            return comparable2.compareTo(comparable);
        }
    }
}
