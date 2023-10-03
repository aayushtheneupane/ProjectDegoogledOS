package com.google.common.base;

import java.io.Serializable;
import p026b.p027a.p030b.p031a.C0632a;

class Predicates$NotPredicate implements C1509F, Serializable {
    private static final long serialVersionUID = 0;
    final C1509F predicate;

    Predicates$NotPredicate(C1509F f) {
        if (f != null) {
            this.predicate = f;
            return;
        }
        throw new NullPointerException();
    }

    public boolean apply(Object obj) {
        return !this.predicate.apply(obj);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Predicates$NotPredicate) {
            return this.predicate.equals(((Predicates$NotPredicate) obj).predicate);
        }
        return false;
    }

    public int hashCode() {
        return ~this.predicate.hashCode();
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Predicates.not(");
        Pa.append(this.predicate.toString());
        Pa.append(")");
        return Pa.toString();
    }
}
