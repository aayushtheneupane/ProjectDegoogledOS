package com.android.dialer.common.concurrent;

import com.google.common.base.Predicate;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class DialerFutures$1 implements Runnable {
    final /* synthetic */ Object val$defaultValue;
    final /* synthetic */ ListenableFuture val$future;
    final /* synthetic */ AtomicInteger val$pending;
    final /* synthetic */ Predicate val$predicate;
    final /* synthetic */ AtomicReference val$ref;

    DialerFutures$1(AtomicReference atomicReference, ListenableFuture listenableFuture, Predicate predicate, AtomicInteger atomicInteger, Object obj) {
        this.val$ref = atomicReference;
        this.val$future = listenableFuture;
        this.val$predicate = predicate;
        this.val$pending = atomicInteger;
        this.val$defaultValue = obj;
    }

    public void run() {
        DialerFutures$AggregateFuture dialerFutures$AggregateFuture = (DialerFutures$AggregateFuture) this.val$ref.get();
        if (dialerFutures$AggregateFuture != null) {
            try {
                Object done = Futures.getDone(this.val$future);
                if (this.val$predicate.apply(done)) {
                    this.val$ref.set((Object) null);
                    dialerFutures$AggregateFuture.set(done);
                } else if (this.val$pending.decrementAndGet() == 0) {
                    dialerFutures$AggregateFuture.set(this.val$defaultValue);
                }
            } catch (ExecutionException e) {
                this.val$ref.set((Object) null);
                dialerFutures$AggregateFuture.setException(e);
            }
        }
    }
}
