package com.google.common.util.concurrent;

import com.google.common.base.MoreObjects;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class ImmediateFuture<V> extends FluentFuture<V> {
    private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

    static final class ImmediateCancelledFuture<V> extends AbstractFuture.TrustedFuture<V> {
        ImmediateCancelledFuture() {
            cancel(false);
        }
    }

    static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
        static final ImmediateSuccessfulFuture<Object> NULL = new ImmediateSuccessfulFuture<>((Object) null);
        private final V value;

        ImmediateSuccessfulFuture(V v) {
            this.value = v;
        }

        public V get() {
            return this.value;
        }
    }

    ImmediateFuture() {
    }

    public void addListener(Runnable runnable, Executor executor) {
        MoreObjects.checkNotNull(runnable, "Runnable was null.");
        MoreObjects.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    public boolean cancel(boolean z) {
        return false;
    }

    public abstract V get() throws ExecutionException;

    public V get(long j, TimeUnit timeUnit) throws ExecutionException {
        if (timeUnit != null) {
            return get();
        }
        throw new NullPointerException();
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }
}
