package com.google.common.base;

import java.io.Serializable;
import java.util.List;

class Predicates$AndPredicate<T> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 0;
    private final List<? extends Predicate<? super T>> components;

    /* synthetic */ Predicates$AndPredicate(List list, Predicates$1 predicates$1) {
        this.components = list;
    }

    public boolean apply(T t) {
        for (int i = 0; i < this.components.size(); i++) {
            if (!((Predicate) this.components.get(i)).apply(t)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Predicates$AndPredicate) {
            return this.components.equals(((Predicates$AndPredicate) obj).components);
        }
        return false;
    }

    public int hashCode() {
        return this.components.hashCode() + 306654252;
    }

    public String toString() {
        List<? extends Predicate<? super T>> list = this.components;
        StringBuilder sb = new StringBuilder("Predicates.");
        sb.append("and");
        sb.append('(');
        boolean z = true;
        for (T next : list) {
            if (!z) {
                sb.append(',');
            }
            sb.append(next);
            z = false;
        }
        sb.append(')');
        return sb.toString();
    }
}
