package com.google.common.base;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.Serializable;

class Suppliers$MemoizingSupplier<T> implements Supplier<T>, Serializable {
    private static final long serialVersionUID = 0;
    final Supplier<T> delegate;
    volatile transient boolean initialized;
    transient T value;

    public T get() {
        if (!this.initialized) {
            synchronized (this) {
                if (!this.initialized) {
                    T t = this.delegate.get();
                    this.value = t;
                    this.initialized = true;
                    return t;
                }
            }
        }
        return this.value;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("Suppliers.memoize("), this.delegate, ")");
    }
}
