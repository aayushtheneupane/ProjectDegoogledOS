package com.google.common.base;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.Serializable;

class Suppliers$ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {
    private static final long serialVersionUID = 0;
    final Supplier<T> delegate;
    final long durationNanos;
    volatile transient long expirationNanos;
    volatile transient T value;

    public T get() {
        long j = this.expirationNanos;
        long systemNanoTime = Platform.systemNanoTime();
        if (j == 0 || systemNanoTime - j >= 0) {
            synchronized (this) {
                if (j == this.expirationNanos) {
                    T t = this.delegate.get();
                    this.value = t;
                    long j2 = systemNanoTime + this.durationNanos;
                    if (j2 == 0) {
                        j2 = 1;
                    }
                    this.expirationNanos = j2;
                    return t;
                }
            }
        }
        return this.value;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Suppliers.memoizeWithExpiration(");
        outline13.append(this.delegate);
        outline13.append(", ");
        outline13.append(this.durationNanos);
        outline13.append(", NANOS)");
        return outline13.toString();
    }
}
