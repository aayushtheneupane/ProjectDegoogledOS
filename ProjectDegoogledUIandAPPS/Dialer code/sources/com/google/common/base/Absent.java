package com.google.common.base;

final class Absent<T> extends Optional<T> {
    static final Absent<Object> INSTANCE = new Absent<>();
    private static final long serialVersionUID = 0;

    private Absent() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public int hashCode() {
        return 2040732332;
    }

    public boolean isPresent() {
        return false;
    }

    /* renamed from: or */
    public T mo10247or(T t) {
        MoreObjects.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
        return t;
    }

    public T orNull() {
        return null;
    }

    public String toString() {
        return "Optional.absent()";
    }
}
