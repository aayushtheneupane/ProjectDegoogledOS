package com.android.dialer.common.concurrent;

import android.os.Handler;
import android.os.Looper;
import com.google.common.base.Predicate;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DialerExecutorModule {
    private static volatile Handler mainThreadHandler;

    public static <T> ListenableFuture<T> firstMatching(Iterable<? extends ListenableFuture<? extends T>> iterable, Predicate<T> predicate, T t) {
        DialerFutures$AnyOfFuture dialerFutures$AnyOfFuture = new DialerFutures$AnyOfFuture(iterable);
        AtomicReference atomicReference = new AtomicReference(dialerFutures$AnyOfFuture);
        AtomicInteger atomicInteger = new AtomicInteger(dialerFutures$AnyOfFuture.futures.size());
        UnmodifiableIterator<ListenableFuture<? extends T>> it = dialerFutures$AnyOfFuture.futures.iterator();
        while (it.hasNext()) {
            ListenableFuture next = it.next();
            next.addListener(new DialerFutures$1(atomicReference, next, predicate, atomicInteger, t), MoreExecutors.directExecutor());
        }
        return dialerFutures$AnyOfFuture;
    }

    public static Handler getUiThreadHandler() {
        if (mainThreadHandler == null) {
            mainThreadHandler = new Handler(Looper.getMainLooper());
        }
        return mainThreadHandler;
    }

    public static void postDelayedOnUiThread(Runnable runnable, long j) {
        getUiThreadHandler().postDelayed(runnable, j);
    }

    public static void postOnUiThread(Runnable runnable) {
        getUiThreadHandler().post(runnable);
    }
}
