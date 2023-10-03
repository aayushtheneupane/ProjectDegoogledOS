package com.android.dialer.common.concurrent;

import android.os.AsyncTask;
import com.android.dialer.common.Assert;
import java.util.concurrent.Executor;

public final class AsyncTaskExecutors {

    static class SimpleAsyncTaskExecutor implements AsyncTaskExecutor {
        private final Executor executor;

        public SimpleAsyncTaskExecutor(Executor executor2) {
            this.executor = executor2;
        }

        public <T> AsyncTask<T, ?, ?> submit(Object obj, AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            Assert.isMainThread();
            return asyncTask.executeOnExecutor(this.executor, tArr);
        }
    }

    public static AsyncTaskExecutor createAsyncTaskExecutor() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor;
        synchronized (AsyncTaskExecutors.class) {
            simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor(AsyncTask.SERIAL_EXECUTOR);
        }
        return simpleAsyncTaskExecutor;
    }

    public static AsyncTaskExecutor createThreadPoolExecutor() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor;
        synchronized (AsyncTaskExecutors.class) {
            simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        return simpleAsyncTaskExecutor;
    }
}
