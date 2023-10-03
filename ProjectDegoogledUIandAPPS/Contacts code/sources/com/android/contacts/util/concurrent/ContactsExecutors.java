package com.android.contacts.util.concurrent;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.ForwardingFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ContactsExecutors {
    private static final int CORE_POOL_SIZE = (CPU_COUNT + 1);
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final ListeningExecutorService DEFAULT_THREAD_POOL_EXECUTOR;
    private static ListeningExecutorService sSimExecutor;

    static {
        ListeningExecutorService listeningExecutorService;
        Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
        if (executor instanceof ExecutorService) {
            listeningExecutorService = MoreExecutors.listeningDecorator((ExecutorService) executor);
        } else {
            listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(CORE_POOL_SIZE));
        }
        DEFAULT_THREAD_POOL_EXECUTOR = listeningExecutorService;
    }

    public static ListeningExecutorService getDefaultThreadPoolExecutor() {
        return DEFAULT_THREAD_POOL_EXECUTOR;
    }

    public static ScheduledExecutorService newUiThreadExecutor() {
        return newHandlerExecutor(new Handler(Looper.getMainLooper()));
    }

    public static ScheduledExecutorService newHandlerExecutor(Handler handler) {
        return new HandlerExecutorService(handler);
    }

    public static synchronized ListeningExecutorService getSimReadExecutor() {
        ListeningExecutorService listeningExecutorService;
        synchronized (ContactsExecutors.class) {
            if (sSimExecutor == null) {
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
                threadPoolExecutor.allowCoreThreadTimeOut(true);
                sSimExecutor = MoreExecutors.listeningDecorator((ExecutorService) threadPoolExecutor);
            }
            listeningExecutorService = sSimExecutor;
        }
        return listeningExecutorService;
    }

    private static class HandlerExecutorService extends AbstractExecutorService implements ScheduledExecutorService {
        private final Handler mHandler;

        public boolean isShutdown() {
            return false;
        }

        public boolean isTerminated() {
            return false;
        }

        public void shutdown() {
        }

        public List<Runnable> shutdownNow() {
            return null;
        }

        private HandlerExecutorService(Handler handler) {
            this.mHandler = handler;
        }

        public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            HandlerFuture<Void> fromRunnable = HandlerFuture.fromRunnable(this.mHandler, j, timeUnit, runnable);
            this.mHandler.postDelayed(fromRunnable, timeUnit.toMillis(j));
            return fromRunnable;
        }

        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            HandlerFuture handlerFuture = new HandlerFuture(this.mHandler, j, timeUnit, callable);
            this.mHandler.postDelayed(handlerFuture, timeUnit.toMillis(j));
            return handlerFuture;
        }

        public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            throw new UnsupportedOperationException();
        }

        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            throw new UnsupportedOperationException();
        }

        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            throw new UnsupportedOperationException();
        }

        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    private static class HandlerFuture<T> extends ForwardingFuture<T> implements RunnableScheduledFuture<T> {
        private final long mDelayMillis;
        private final SettableFuture<T> mDelegate;
        private final Handler mHandler;
        private final AtomicLong mStart;
        private final Callable<T> mTask;

        public boolean isPeriodic() {
            return false;
        }

        private HandlerFuture(Handler handler, long j, TimeUnit timeUnit, Callable<T> callable) {
            this.mDelegate = SettableFuture.create();
            this.mStart = new AtomicLong(-1);
            this.mHandler = handler;
            this.mDelayMillis = timeUnit.toMillis(j);
            this.mTask = callable;
        }

        public long getDelay(TimeUnit timeUnit) {
            long j = this.mStart.get();
            if (j < 0) {
                return this.mDelayMillis;
            }
            return TimeUnit.MILLISECONDS.convert(this.mDelayMillis - (System.currentTimeMillis() - j), timeUnit);
        }

        public int compareTo(Delayed delayed) {
            return Long.compare(getDelay(TimeUnit.MILLISECONDS), delayed.getDelay(TimeUnit.MILLISECONDS));
        }

        /* access modifiers changed from: protected */
        public Future<T> delegate() {
            return this.mDelegate;
        }

        public boolean cancel(boolean z) {
            this.mHandler.removeCallbacks(this);
            return super.cancel(z);
        }

        public void run() {
            if (this.mStart.compareAndSet(-1, System.currentTimeMillis())) {
                try {
                    this.mDelegate.set(this.mTask.call());
                } catch (Exception e) {
                    this.mDelegate.setException(e);
                }
            }
        }

        public static HandlerFuture<Void> fromRunnable(Handler handler, long j, TimeUnit timeUnit, final Runnable runnable) {
            return new HandlerFuture(handler, j, timeUnit, new Callable<Void>() {
                public Void call() throws Exception {
                    runnable.run();
                    return null;
                }
            });
        }
    }
}
