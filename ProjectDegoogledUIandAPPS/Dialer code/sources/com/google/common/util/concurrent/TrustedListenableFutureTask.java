package com.google.common.util.concurrent;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

class TrustedListenableFutureTask<V> extends AbstractFuture.TrustedFuture<V> implements RunnableFuture<V> {
    private InterruptibleTask task;

    private final class TrustedFutureInterruptibleAsyncTask extends InterruptibleTask {
        private final AsyncCallable<V> callable;

        TrustedFutureInterruptibleAsyncTask(AsyncCallable<V> asyncCallable) {
            if (asyncCallable != null) {
                this.callable = asyncCallable;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public void runInterruptibly() {
            if (!TrustedListenableFutureTask.this.isDone()) {
                try {
                    ListenableFuture<V> call = this.callable.call();
                    MoreObjects.checkNotNull(call, "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)?");
                    TrustedListenableFutureTask.this.setFuture(call);
                } catch (Throwable th) {
                    TrustedListenableFutureTask.this.setException(th);
                }
            }
        }

        public String toString() {
            return this.callable.toString();
        }

        /* access modifiers changed from: package-private */
        public boolean wasInterrupted() {
            return TrustedListenableFutureTask.this.wasInterrupted();
        }
    }

    private final class TrustedFutureInterruptibleTask extends InterruptibleTask {
        private final Callable<V> callable;

        TrustedFutureInterruptibleTask(Callable<V> callable2) {
            if (callable2 != null) {
                this.callable = callable2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public void runInterruptibly() {
            if (!TrustedListenableFutureTask.this.isDone()) {
                try {
                    TrustedListenableFutureTask.this.set(this.callable.call());
                } catch (Throwable th) {
                    TrustedListenableFutureTask.this.setException(th);
                }
            }
        }

        public String toString() {
            return this.callable.toString();
        }

        /* access modifiers changed from: package-private */
        public boolean wasInterrupted() {
            return TrustedListenableFutureTask.this.wasInterrupted();
        }
    }

    TrustedListenableFutureTask(Callable<V> callable) {
        this.task = new TrustedFutureInterruptibleTask(callable);
    }

    static <V> TrustedListenableFutureTask<V> create(Callable<V> callable) {
        return new TrustedListenableFutureTask<>(callable);
    }

    /* access modifiers changed from: protected */
    public void afterDone() {
        InterruptibleTask interruptibleTask;
        if (wasInterrupted() && (interruptibleTask = this.task) != null) {
            interruptibleTask.interruptTask();
        }
        this.task = null;
    }

    /* access modifiers changed from: protected */
    public String pendingToString() {
        InterruptibleTask interruptibleTask = this.task;
        if (interruptibleTask != null) {
            return GeneratedOutlineSupport.outline7("task=[", interruptibleTask, "]");
        }
        return null;
    }

    public void run() {
        InterruptibleTask interruptibleTask = this.task;
        if (interruptibleTask != null) {
            interruptibleTask.run();
        }
    }

    static <V> TrustedListenableFutureTask<V> create(Runnable runnable, V v) {
        return new TrustedListenableFutureTask<>(Executors.callable(runnable, v));
    }

    TrustedListenableFutureTask(AsyncCallable<V> asyncCallable) {
        this.task = new TrustedFutureInterruptibleAsyncTask(asyncCallable);
    }
}
