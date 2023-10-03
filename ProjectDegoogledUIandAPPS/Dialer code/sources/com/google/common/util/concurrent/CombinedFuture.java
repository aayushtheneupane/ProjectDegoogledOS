package com.google.common.util.concurrent;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableCollection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

final class CombinedFuture<V> extends AggregateFuture<Object, V> {

    private final class CallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask {
        private final Callable<V> callable;

        public CallableInterruptibleTask(Callable<V> callable2, Executor executor) {
            super(executor);
            if (callable2 != null) {
                this.callable = callable2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public void setValue() throws Exception {
            CombinedFuture.this.set(this.callable.call());
        }

        public String toString() {
            return this.callable.toString();
        }
    }

    private abstract class CombinedFutureInterruptibleTask extends InterruptibleTask {
        private final Executor listenerExecutor;
        volatile boolean thrownByExecute = true;

        public CombinedFutureInterruptibleTask(Executor executor) {
            if (executor != null) {
                this.listenerExecutor = executor;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public final void execute() {
            try {
                this.listenerExecutor.execute(this);
            } catch (RejectedExecutionException e) {
                if (this.thrownByExecute) {
                    CombinedFuture.this.setException(e);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void runInterruptibly() {
            this.thrownByExecute = false;
            if (!CombinedFuture.this.isDone()) {
                try {
                    setValue();
                } catch (ExecutionException e) {
                    CombinedFuture.this.setException(e.getCause());
                } catch (CancellationException unused) {
                    CombinedFuture.this.cancel(false);
                } catch (Throwable th) {
                    CombinedFuture.this.setException(th);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public abstract void setValue() throws Exception;

        /* access modifiers changed from: package-private */
        public final boolean wasInterrupted() {
            return CombinedFuture.this.wasInterrupted();
        }
    }

    private final class CombinedFutureRunningState extends AggregateFuture<Object, V>.RunningState {
        private CombinedFuture<V>.CombinedFutureInterruptibleTask task;

        CombinedFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends Object>> immutableCollection, boolean z, CombinedFuture<V>.CombinedFutureInterruptibleTask combinedFutureInterruptibleTask) {
            super(immutableCollection, z, false);
            this.task = combinedFutureInterruptibleTask;
        }

        /* access modifiers changed from: package-private */
        public void collectOneValue(boolean z, int i, Object obj) {
        }

        /* access modifiers changed from: package-private */
        public void handleAllCompleted() {
            CombinedFuture<V>.CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
            if (combinedFutureInterruptibleTask != null) {
                combinedFutureInterruptibleTask.execute();
            } else {
                MoreObjects.checkState(CombinedFuture.this.isDone());
            }
        }

        /* access modifiers changed from: package-private */
        public void interruptTask() {
            CombinedFuture<V>.CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
            if (combinedFutureInterruptibleTask != null) {
                combinedFutureInterruptibleTask.interruptTask();
            }
        }

        /* access modifiers changed from: package-private */
        public void releaseResourcesAfterFailure() {
            super.releaseResourcesAfterFailure();
            this.task = null;
        }
    }

    CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, Executor executor, Callable<V> callable) {
        init(new CombinedFutureRunningState(immutableCollection, z, new CallableInterruptibleTask(callable, executor)));
    }
}
