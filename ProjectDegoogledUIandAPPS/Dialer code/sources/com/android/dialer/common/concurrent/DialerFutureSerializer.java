package com.android.dialer.common.concurrent;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class DialerFutureSerializer {
    private final AtomicReference<ListenableFuture<?>> ref = new AtomicReference<>(Futures.immediateFuture(null));

    static /* synthetic */ ListenableFuture lambda$submitAsync$1(AtomicBoolean atomicBoolean, AsyncCallable asyncCallable) throws Exception {
        if (atomicBoolean.get()) {
            return Futures.immediateCancelledFuture();
        }
        return asyncCallable.call();
    }

    static /* synthetic */ void lambda$submitAsync$3(ListenableFuture listenableFuture, AtomicBoolean atomicBoolean, SettableFuture settableFuture, ListenableFuture listenableFuture2) {
        if (listenableFuture.isCancelled()) {
            atomicBoolean.set(true);
            settableFuture.setFuture(listenableFuture2);
            return;
        }
        settableFuture.set(null);
    }

    public <T> ListenableFuture<T> submit(Callable<T> callable, Executor executor) {
        return submitAsync(new AsyncCallable(callable) {
            private final /* synthetic */ Callable f$0;

            {
                this.f$0 = r1;
            }

            public final ListenableFuture call() {
                return Futures.immediateFuture(this.f$0.call());
            }
        }, executor);
    }

    public <T> ListenableFuture<T> submitAsync(AsyncCallable<T> asyncCallable, Executor executor) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        $$Lambda$DialerFutureSerializer$mZ547wlf6jCARjQFuFLZvI0GdUM r1 = new AsyncCallable(atomicBoolean, asyncCallable) {
            private final /* synthetic */ AtomicBoolean f$0;
            private final /* synthetic */ AsyncCallable f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final ListenableFuture call() {
                return DialerFutureSerializer.lambda$submitAsync$1(this.f$0, this.f$1);
            }
        };
        SettableFuture create = SettableFuture.create();
        ListenableFuture andSet = this.ref.getAndSet(create);
        ListenableFuture<T> nonCancellationPropagating = Futures.nonCancellationPropagating(Futures.submitAsync(r1, new Executor(executor) {
            private final /* synthetic */ Executor f$1;

            {
                this.f$1 = r2;
            }

            public final void execute(Runnable runnable) {
                ListenableFuture.this.addListener(runnable, this.f$1);
            }
        }));
        nonCancellationPropagating.addListener(new Runnable(atomicBoolean, create, andSet) {
            private final /* synthetic */ AtomicBoolean f$1;
            private final /* synthetic */ SettableFuture f$2;
            private final /* synthetic */ ListenableFuture f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                DialerFutureSerializer.lambda$submitAsync$3(ListenableFuture.this, this.f$1, this.f$2, this.f$3);
            }
        }, MoreExecutors.directExecutor());
        return nonCancellationPropagating;
    }
}
