package com.google.common.base;

import java.io.Serializable;
import p026b.p027a.p030b.p031a.C0632a;

class Predicates$IsEqualToPredicate implements C1509F, Serializable {
    private static final long serialVersionUID = 0;
    private final Object target;

    /* synthetic */ Predicates$IsEqualToPredicate(Object obj, C1510G g) {
        this.target = obj;
    }

    public boolean apply(Object obj) {
        return this.target.equals(obj);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Predicates$IsEqualToPredicate) {
            return this.target.equals(((Predicates$IsEqualToPredicate) obj).target);
        }
        return false;
    }

    public int hashCode() {
        return this.target.hashCode();
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Predicates.equalTo(");
        Pa.append(this.target);
        Pa.append(")");
        return Pa.toString();
    }
}
