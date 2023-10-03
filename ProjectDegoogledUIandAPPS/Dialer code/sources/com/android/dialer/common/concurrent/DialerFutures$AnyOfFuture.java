package com.android.dialer.common.concurrent;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenableFuture;

final class DialerFutures$AnyOfFuture<T> extends DialerFutures$AggregateFuture<T> {
    DialerFutures$AnyOfFuture(Iterable<? extends ListenableFuture<? extends T>> iterable) {
        super(iterable);
    }

    /* access modifiers changed from: protected */
    public void afterDone() {
        ImmutableList<ListenableFuture<? extends T>> immutableList = this.futures;
        this.futures = null;
        if (immutableList != null) {
            boolean wasInterrupted = wasInterrupted() | (!isCancelled());
            UnmodifiableIterator<ListenableFuture<? extends T>> it = immutableList.iterator();
            while (it.hasNext()) {
                it.next().cancel(wasInterrupted);
            }
        }
    }
}
