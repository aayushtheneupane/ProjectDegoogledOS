package com.android.dialer.common.concurrent;

import android.os.AsyncTask;

public interface AsyncTaskExecutor {
    <T> AsyncTask<T, ?, ?> submit(Object obj, AsyncTask<T, ?, ?> asyncTask, T... tArr);
}
