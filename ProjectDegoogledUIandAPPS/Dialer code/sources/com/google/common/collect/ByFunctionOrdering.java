package com.google.common.collect;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Function;
import java.io.Serializable;
import java.util.Arrays;

final class ByFunctionOrdering<F, T> extends Ordering<F> implements Serializable {
    private static final long serialVersionUID = 0;
    final Function<F, ? extends T> function;
    final Ordering<T> ordering;

    ByFunctionOrdering(Function<F, ? extends T> function2, Ordering<T> ordering2) {
        if (function2 != null) {
            this.function = function2;
            if (ordering2 != null) {
                this.ordering = ordering2;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public int compare(F f, F f2) {
        return this.ordering.compare(this.function.apply(f), this.function.apply(f2));
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
        StringBuilder sb = new StringBuilder();
        sb.append(this.ordering);
        sb.append(".onResultOf(");
        return GeneratedOutlineSupport.outline11(sb, this.function, ")");
    }
}
