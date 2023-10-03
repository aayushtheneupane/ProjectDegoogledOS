package com.google.common.util.concurrent;

public interface FutureFallback<V> {
    ListenableFuture<V> create(Throwable th) throws Exception;
}
