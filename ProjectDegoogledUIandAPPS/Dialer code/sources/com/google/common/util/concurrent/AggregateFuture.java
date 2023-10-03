package com.google.common.util.concurrent;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AggregateFuture<InputT, OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
    /* access modifiers changed from: private */
    public AggregateFuture<InputT, OutputT>.RunningState runningState;

    abstract class RunningState extends AggregateFutureState implements Runnable {
        private final boolean allMustSucceed;
        private final boolean collectsValues;
        /* access modifiers changed from: private */
        public ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;

        RunningState(ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection, boolean z, boolean z2) {
            super(immutableCollection.size());
            this.futures = immutableCollection;
            this.allMustSucceed = z;
            this.collectsValues = z2;
        }

        static /* synthetic */ void access$100(RunningState runningState) {
            if (runningState.futures.isEmpty()) {
                runningState.handleAllCompleted();
            } else if (runningState.allMustSucceed) {
                final int i = 0;
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = runningState.futures.iterator();
                while (it.hasNext()) {
                    final ListenableFuture listenableFuture = (ListenableFuture) it.next();
                    listenableFuture.addListener(new Runnable() {
                        public void run() {
                            try {
                                RunningState.this.handleOneInputDone(i, listenableFuture);
                            } finally {
                                RunningState.this.decrementCountAndMaybeComplete();
                            }
                        }
                    }, MoreExecutors.DirectExecutor.INSTANCE);
                    i++;
                }
            } else {
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it2 = runningState.futures.iterator();
                while (it2.hasNext()) {
                    ((ListenableFuture) it2.next()).addListener(runningState, MoreExecutors.DirectExecutor.INSTANCE);
                }
            }
        }

        /* access modifiers changed from: private */
        public void decrementCountAndMaybeComplete() {
            int decrementRemainingAndGet = decrementRemainingAndGet();
            int i = 0;
            MoreObjects.checkState(decrementRemainingAndGet >= 0, "Less than 0 remaining futures");
            if (decrementRemainingAndGet == 0) {
                if (this.collectsValues && (true ^ this.allMustSucceed)) {
                    UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
                    while (it.hasNext()) {
                        handleOneInputDone(i, (ListenableFuture) it.next());
                        i++;
                    }
                }
                handleAllCompleted();
            }
        }

        private void handleException(Throwable th) {
            boolean z;
            boolean z2;
            if (th != null) {
                boolean z3 = true;
                if (this.allMustSucceed) {
                    z2 = AggregateFuture.this.setException(th);
                    if (z2) {
                        releaseResourcesAfterFailure();
                        z = true;
                    } else {
                        z = AggregateFuture.access$400(getOrInitSeenExceptions(), th);
                    }
                } else {
                    z = true;
                    z2 = false;
                }
                boolean z4 = th instanceof Error;
                boolean z5 = this.allMustSucceed;
                if (z2) {
                    z3 = false;
                }
                if ((z5 & z3 & z) || z4) {
                    AggregateFuture.logger.log(Level.SEVERE, z4 ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first", th);
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void handleOneInputDone(int i, Future<? extends InputT> future) {
            MoreObjects.checkState(this.allMustSucceed || !AggregateFuture.this.isDone() || AggregateFuture.this.isCancelled(), "Future was done before all dependencies completed");
            try {
                MoreObjects.checkState(future.isDone(), "Tried to set value from future which is not done");
                if (this.allMustSucceed) {
                    if (future.isCancelled()) {
                        RunningState unused = AggregateFuture.this.runningState = null;
                        AggregateFuture.this.cancel(false);
                        return;
                    }
                    Object done = Futures.getDone(future);
                    if (this.collectsValues) {
                        collectOneValue(this.allMustSucceed, i, done);
                    }
                } else if (this.collectsValues && !future.isCancelled()) {
                    collectOneValue(this.allMustSucceed, i, Futures.getDone(future));
                }
            } catch (ExecutionException e) {
                handleException(e.getCause());
            } catch (Throwable th) {
                handleException(th);
            }
        }

        /* access modifiers changed from: package-private */
        public abstract void collectOneValue(boolean z, int i, InputT inputt);

        /* access modifiers changed from: package-private */
        public abstract void handleAllCompleted();

        /* access modifiers changed from: package-private */
        public void interruptTask() {
        }

        /* access modifiers changed from: package-private */
        public void releaseResourcesAfterFailure() {
            this.futures = null;
        }

        public final void run() {
            decrementCountAndMaybeComplete();
        }
    }

    AggregateFuture() {
    }

    static /* synthetic */ boolean access$400(Set set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        AggregateFuture<InputT, OutputT>.RunningState runningState2 = this.runningState;
        if (runningState2 != null) {
            this.runningState = null;
            ImmutableCollection access$000 = runningState2.futures;
            boolean wasInterrupted = wasInterrupted();
            if (wasInterrupted()) {
                runningState2.interruptTask();
            }
            if (isCancelled() && (access$000 != null)) {
                UnmodifiableIterator it = access$000.iterator();
                while (it.hasNext()) {
                    ((ListenableFuture) it.next()).cancel(wasInterrupted);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void init(AggregateFuture<InputT, OutputT>.RunningState runningState2) {
        this.runningState = runningState2;
        RunningState.access$100(runningState2);
    }

    /* access modifiers changed from: protected */
    public String pendingToString() {
        ImmutableCollection access$000;
        AggregateFuture<InputT, OutputT>.RunningState runningState2 = this.runningState;
        if (runningState2 == null || (access$000 = runningState2.futures) == null) {
            return null;
        }
        return GeneratedOutlineSupport.outline7("futures=[", access$000, "]");
    }
}
