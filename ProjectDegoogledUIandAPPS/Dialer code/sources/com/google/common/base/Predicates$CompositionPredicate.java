package com.google.common.base;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.Serializable;

class Predicates$CompositionPredicate<A, B> implements Predicate<A>, Serializable {
    private static final long serialVersionUID = 0;

    /* renamed from: f */
    final Function<A, ? extends B> f65f;

    /* renamed from: p */
    final Predicate<B> f66p;

    /* synthetic */ Predicates$CompositionPredicate(Predicate predicate, Function function, Predicates$1 predicates$1) {
        if (predicate != null) {
            this.f66p = predicate;
            if (function != null) {
                this.f65f = function;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public boolean apply(A a) {
        return this.f66p.apply(this.f65f.apply(a));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Predicates$CompositionPredicate)) {
            return false;
        }
        Predicates$CompositionPredicate predicates$CompositionPredicate = (Predicates$CompositionPredicate) obj;
        if (!this.f65f.equals(predicates$CompositionPredicate.f65f) || !this.f66p.equals(predicates$CompositionPredicate.f66p)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f66p.hashCode() ^ this.f65f.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f66p);
        sb.append("(");
        return GeneratedOutlineSupport.outline11(sb, this.f65f, ")");
    }
}
