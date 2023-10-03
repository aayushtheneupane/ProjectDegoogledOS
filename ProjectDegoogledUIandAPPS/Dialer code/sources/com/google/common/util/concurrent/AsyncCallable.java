package com.google.common.util.concurrent;

@FunctionalInterface
public interface AsyncCallable<V> {
    ListenableFuture<V> call() throws Exception;
}
