package com.google.common.base;

import com.android.tools.p006r8.GeneratedOutlineSupport;

class Suppliers$NonSerializableMemoizingSupplier<T> implements Supplier<T> {
    volatile Supplier<T> delegate;
    volatile boolean initialized;
    T value;

    public T get() {
        if (!this.initialized) {
            synchronized (this) {
                if (!this.initialized) {
                    T t = this.delegate.get();
                    this.value = t;
                    this.initialized = true;
                    this.delegate = null;
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
