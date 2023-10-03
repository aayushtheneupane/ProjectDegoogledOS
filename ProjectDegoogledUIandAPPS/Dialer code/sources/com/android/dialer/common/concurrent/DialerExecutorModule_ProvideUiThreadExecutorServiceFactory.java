package com.android.dialer.common.concurrent;

import android.support.p002v7.appcompat.R$style;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;

public enum DialerExecutorModule_ProvideUiThreadExecutorServiceFactory implements Factory<ListeningExecutorService> {
    INSTANCE;

    public Object get() {
        UiThreadExecutor uiThreadExecutor = new UiThreadExecutor();
        R$style.checkNotNull1(uiThreadExecutor, "Cannot return null from a non-@Nullable @Provides method");
        return uiThreadExecutor;
    }
}
