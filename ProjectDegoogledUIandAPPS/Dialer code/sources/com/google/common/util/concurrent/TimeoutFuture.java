package com.google.common.util.concurrent;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class TimeoutFuture<V> extends AbstractFuture.TrustedFuture<V> {
    /* access modifiers changed from: private */
    public ListenableFuture<V> delegateRef;
    private Future<?> timer;

    private static final class Fire<V> implements Runnable {
        TimeoutFuture<V> timeoutFutureRef;

        Fire(TimeoutFuture<V> timeoutFuture) {
            this.timeoutFutureRef = timeoutFuture;
        }

        public void run() {
            ListenableFuture access$000;
            TimeoutFuture<V> timeoutFuture = this.timeoutFutureRef;
            if (timeoutFuture != null && (access$000 = timeoutFuture.delegateRef) != null) {
                this.timeoutFutureRef = null;
                if (access$000.isDone()) {
                    timeoutFuture.setFuture(access$000);
                    return;
                }
                try {
                    timeoutFuture.setException(new TimeoutException("Future timed out: " + access$000));
                } finally {
                    access$000.cancel(true);
                }
            }
        }
    }

    private TimeoutFuture(ListenableFuture<V> listenableFuture) {
        if (listenableFuture != null) {
            this.delegateRef = listenableFuture;
            return;
        }
        throw new NullPointerException();
    }

    static <V> ListenableFuture<V> create(ListenableFuture<V> listenableFuture, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        TimeoutFuture timeoutFuture = new TimeoutFuture(listenableFuture);
        Fire fire = new Fire(timeoutFuture);
        timeoutFuture.timer = scheduledExecutorService.schedule(fire, j, timeUnit);
        listenableFuture.addListener(fire, MoreExecutors.DirectExecutor.INSTANCE);
        return timeoutFuture;
    }

    /* access modifiers changed from: protected */
    public void afterDone() {
        maybePropagateCancellation(this.delegateRef);
        Future<?> future = this.timer;
        if (future != null) {
            future.cancel(false);
        }
        this.delegateRef = null;
        this.timer = null;
    }

    /* access modifiers changed from: protected */
    public String pendingToString() {
        ListenableFuture<V> listenableFuture = this.delegateRef;
        if (listenableFuture != null) {
            return GeneratedOutlineSupport.outline7("inputFuture=[", listenableFuture, "]");
        }
        return null;
    }
}
