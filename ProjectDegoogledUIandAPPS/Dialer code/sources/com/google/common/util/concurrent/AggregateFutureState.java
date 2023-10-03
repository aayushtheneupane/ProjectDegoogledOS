package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AggregateFuture;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AggregateFutureState {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final Logger log = Logger.getLogger(AggregateFutureState.class.getName());
    /* access modifiers changed from: private */
    public volatile int remaining;
    /* access modifiers changed from: private */
    public volatile Set<Throwable> seenExceptions = null;

    private static abstract class AtomicHelper {
        /* synthetic */ AtomicHelper(C08941 r1) {
        }

        /* access modifiers changed from: package-private */
        public abstract void compareAndSetSeenExceptions(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2);

        /* access modifiers changed from: package-private */
        public abstract int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState);
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicIntegerFieldUpdater<AggregateFutureState> remainingCountUpdater;
        final AtomicReferenceFieldUpdater<AggregateFutureState, Set<Throwable>> seenExceptionsUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super((C08941) null);
            this.seenExceptionsUpdater = atomicReferenceFieldUpdater;
            this.remainingCountUpdater = atomicIntegerFieldUpdater;
        }

        /* access modifiers changed from: package-private */
        public void compareAndSetSeenExceptions(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2) {
            this.seenExceptionsUpdater.compareAndSet(aggregateFutureState, set, set2);
        }

        /* access modifiers changed from: package-private */
        public int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState) {
            return this.remainingCountUpdater.decrementAndGet(aggregateFutureState);
        }
    }

    private static final class SynchronizedAtomicHelper extends AtomicHelper {
        /* synthetic */ SynchronizedAtomicHelper(C08941 r1) {
            super((C08941) null);
        }

        /* access modifiers changed from: package-private */
        public void compareAndSetSeenExceptions(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2) {
            synchronized (aggregateFutureState) {
                if (aggregateFutureState.seenExceptions == set) {
                    Set unused = aggregateFutureState.seenExceptions = set2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState) {
            int access$300;
            synchronized (aggregateFutureState) {
                AggregateFutureState.access$310(aggregateFutureState);
                access$300 = aggregateFutureState.remaining;
            }
            return access$300;
        }
    }

    static {
        AtomicHelper atomicHelper;
        try {
            atomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remaining"));
        } catch (Throwable th) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
            atomicHelper = new SynchronizedAtomicHelper((C08941) null);
        }
        ATOMIC_HELPER = atomicHelper;
    }

    AggregateFutureState(int i) {
        this.remaining = i;
    }

    static /* synthetic */ int access$310(AggregateFutureState aggregateFutureState) {
        int i = aggregateFutureState.remaining;
        aggregateFutureState.remaining = i - 1;
        return i;
    }

    /* access modifiers changed from: package-private */
    public final int decrementRemainingAndGet() {
        return ATOMIC_HELPER.decrementAndGetRemainingCount(this);
    }

    /* access modifiers changed from: package-private */
    public final Set<Throwable> getOrInitSeenExceptions() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        AggregateFuture.RunningState runningState = (AggregateFuture.RunningState) this;
        if (!AggregateFuture.this.isCancelled()) {
            AggregateFuture.access$400(newSetFromMap, AggregateFuture.this.trustedGetException());
        }
        ATOMIC_HELPER.compareAndSetSeenExceptions(this, (Set<Throwable>) null, newSetFromMap);
        return this.seenExceptions;
    }
}
