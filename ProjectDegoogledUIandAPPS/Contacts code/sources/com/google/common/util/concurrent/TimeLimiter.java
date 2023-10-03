package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public interface TimeLimiter {
    <T> T callWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit, boolean z) throws Exception;

    <T> T newProxy(T t, Class<T> cls, long j, TimeUnit timeUnit);
}
