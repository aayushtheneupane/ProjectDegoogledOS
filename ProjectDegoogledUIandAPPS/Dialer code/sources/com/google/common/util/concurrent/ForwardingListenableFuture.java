package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public abstract class ForwardingListenableFuture<V> extends ForwardingFuture<V> implements ListenableFuture<V> {

    public static abstract class SimpleForwardingListenableFuture<V> extends ForwardingListenableFuture<V> {
        private final ListenableFuture<V> delegate;

        protected SimpleForwardingListenableFuture(ListenableFuture<V> listenableFuture) {
            if (listenableFuture != null) {
                this.delegate = listenableFuture;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final ListenableFuture<V> delegate() {
            return this.delegate;
        }

        /* access modifiers changed from: protected */
        /* renamed from: delegate  reason: collision with other method in class */
        public Object m137delegate() {
            return this.delegate;
        }

        /* access modifiers changed from: protected */
        /* renamed from: delegate  reason: collision with other method in class */
        public Future m138delegate() {
            return this.delegate;
        }
    }

    protected ForwardingListenableFuture() {
    }

    public void addListener(Runnable runnable, Executor executor) {
        delegate().addListener(runnable, executor);
    }

    /* access modifiers changed from: protected */
    public abstract ListenableFuture<? extends V> delegate();
}
