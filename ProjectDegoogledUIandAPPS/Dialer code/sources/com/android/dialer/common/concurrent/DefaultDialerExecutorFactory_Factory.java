package com.android.dialer.common.concurrent;

import dagger.internal.Factory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;

public final class DefaultDialerExecutorFactory_Factory implements Factory<DefaultDialerExecutorFactory> {
    private final Provider<ScheduledExecutorService> nonUiSerialExecutorProvider;
    private final Provider<ExecutorService> nonUiThreadPoolProvider;
    private final Provider<ScheduledExecutorService> uiSerialExecutorProvider;
    private final Provider<ExecutorService> uiThreadPoolProvider;

    public DefaultDialerExecutorFactory_Factory(Provider<ExecutorService> provider, Provider<ScheduledExecutorService> provider2, Provider<ExecutorService> provider3, Provider<ScheduledExecutorService> provider4) {
        this.nonUiThreadPoolProvider = provider;
        this.nonUiSerialExecutorProvider = provider2;
        this.uiThreadPoolProvider = provider3;
        this.uiSerialExecutorProvider = provider4;
    }

    public Object get() {
        return new DefaultDialerExecutorFactory(this.nonUiThreadPoolProvider.get(), this.nonUiSerialExecutorProvider.get(), this.uiThreadPoolProvider.get(), this.uiSerialExecutorProvider.get());
    }
}
