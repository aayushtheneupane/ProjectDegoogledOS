package com.google.common.base;

@FunctionalInterface
public interface Function<F, T> extends java.util.function.Function<F, T> {
    T apply(F f);

    boolean equals(Object obj);
}
