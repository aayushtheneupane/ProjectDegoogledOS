package com.android.dialer.common.concurrent;

import android.os.AsyncTask;
import android.support.p002v7.appcompat.R$style;
import dagger.internal.Factory;
import java.util.concurrent.ExecutorService;

public enum DialerExecutorModule_ProvideUiThreadPoolFactory implements Factory<ExecutorService> {
    INSTANCE;

    public static Factory<ExecutorService> create() {
        return INSTANCE;
    }

    public Object get() {
        ExecutorService executorService = (ExecutorService) AsyncTask.THREAD_POOL_EXECUTOR;
        R$style.checkNotNull1(executorService, "Cannot return null from a non-@Nullable @Provides method");
        return executorService;
    }
}
