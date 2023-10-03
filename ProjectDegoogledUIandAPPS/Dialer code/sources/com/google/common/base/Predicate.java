package com.google.common.base;

@FunctionalInterface
public interface Predicate<T> extends java.util.function.Predicate<T> {
    boolean apply(T t);

    boolean equals(Object obj);

    boolean test(T t) {
        return apply(t);
    }
}
