package com.google.common.base;

import java.io.Serializable;

public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    public abstract T get();

    public abstract boolean isPresent();

    public abstract T orNull();

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    /* renamed from: of */
    public static <T> Optional<T> m8of(T t) {
        Preconditions.checkNotNull(t);
        return new Present(t);
    }

    public static <T> Optional<T> fromNullable(T t) {
        if (t == null) {
            return absent();
        }
        return new Present(t);
    }

    Optional() {
    }
}
