package com.google.common.base;

import java.io.Serializable;
import java.util.Collection;
import p026b.p027a.p030b.p031a.C0632a;

class Predicates$InPredicate implements C1509F, Serializable {
    private static final long serialVersionUID = 0;
    private final Collection target;

    /* synthetic */ Predicates$InPredicate(Collection collection, C1510G g) {
        if (collection != null) {
            this.target = collection;
            return;
        }
        throw new NullPointerException();
    }

    public boolean apply(Object obj) {
        try {
            return this.target.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Predicates$InPredicate) {
            return this.target.equals(((Predicates$InPredicate) obj).target);
        }
        return false;
    }

    public int hashCode() {
        return this.target.hashCode();
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Predicates.in(");
        Pa.append(this.target);
        Pa.append(")");
        return Pa.toString();
    }
}
