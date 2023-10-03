package com.google.common.collect;

import com.google.common.base.C1547v;
import java.io.Serializable;
import java.util.Arrays;

final class ByFunctionOrdering extends C1644bb implements Serializable {
    private static final long serialVersionUID = 0;
    final C1547v function;
    final C1644bb ordering;

    ByFunctionOrdering(C1547v vVar, C1644bb bbVar) {
        if (vVar != null) {
            this.function = vVar;
            if (bbVar != null) {
                this.ordering = bbVar;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public int compare(Object obj, Object obj2) {
        return this.ordering.compare(this.function.apply(obj), this.function.apply(obj2));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByFunctionOrdering)) {
            return false;
        }
        ByFunctionOrdering byFunctionOrdering = (ByFunctionOrdering) obj;
        if (!this.function.equals(byFunctionOrdering.function) || !this.ordering.equals(byFunctionOrdering.ordering)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.function, this.ordering});
    }

    public String toString() {
        return this.ordering + ".onResultOf(" + this.function + ")";
    }
}
