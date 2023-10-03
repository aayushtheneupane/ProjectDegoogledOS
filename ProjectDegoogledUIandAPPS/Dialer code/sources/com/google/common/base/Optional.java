package com.google.common.base;

import java.io.Serializable;

public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    Optional() {
    }

    public static <T> Optional<T> absent() {
        return Absent.INSTANCE;
    }

    public static <T> Optional<T> fromNullable(T t) {
        if (t == null) {
            return Absent.INSTANCE;
        }
        return new Present(t);
    }

    /* renamed from: of */
    public static <T> Optional<T> m67of(T t) {
        if (t != null) {
            return new Present(t);
        }
        throw new NullPointerException();
    }

    public abstract boolean equals(Object obj);

    public abstract T get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    /* renamed from: or */
    public abstract T mo10247or(T t);

    public abstract T orNull();
}
