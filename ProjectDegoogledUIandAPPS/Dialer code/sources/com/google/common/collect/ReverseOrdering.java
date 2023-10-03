package com.google.common.collect;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.Serializable;

final class ReverseOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final Ordering<? super T> forwardOrder;

    ReverseOrdering(Ordering<? super T> ordering) {
        if (ordering != null) {
            this.forwardOrder = ordering;
            return;
        }
        throw new NullPointerException();
    }

    public int compare(T t, T t2) {
        return this.forwardOrder.compare(t2, t);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReverseOrdering) {
            return this.forwardOrder.equals(((ReverseOrdering) obj).forwardOrder);
        }
        return false;
    }

    public int hashCode() {
        return -this.forwardOrder.hashCode();
    }

    public <S extends T> Ordering<S> reverse() {
        return this.forwardOrder;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline11(new StringBuilder(), this.forwardOrder, ".reverse()");
    }
}
