package com.android.dialer.common.concurrent;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;

class DialerFutures$AggregateFuture<T> extends AbstractFuture<T> {
    ImmutableList<ListenableFuture<? extends T>> futures;

    DialerFutures$AggregateFuture(Iterable<? extends ListenableFuture<? extends T>> iterable) {
        ImmutableList<ListenableFuture<? extends T>> copyOf = ImmutableList.copyOf(iterable);
        if (!copyOf.isEmpty()) {
            this.futures = copyOf;
            return;
        }
        throw new IllegalArgumentException("Expected at least one future, got 0.");
    }

    /* access modifiers changed from: protected */
    public boolean set(T t) {
        return super.set(t);
    }

    /* access modifiers changed from: protected */
    public boolean setException(Throwable th) {
        return super.setException(th);
    }
}
