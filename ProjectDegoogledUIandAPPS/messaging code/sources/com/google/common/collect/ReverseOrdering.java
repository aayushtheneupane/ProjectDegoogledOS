package com.google.common.collect;

import java.io.Serializable;

final class ReverseOrdering extends C1644bb implements Serializable {
    private static final long serialVersionUID = 0;
    final C1644bb forwardOrder;

    ReverseOrdering(C1644bb bbVar) {
        if (bbVar != null) {
            this.forwardOrder = bbVar;
            return;
        }
        throw new NullPointerException();
    }

    public int compare(Object obj, Object obj2) {
        return this.forwardOrder.compare(obj2, obj);
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

    public C1644bb reverse() {
        return this.forwardOrder;
    }

    public String toString() {
        return this.forwardOrder + ".reverse()";
    }
}
