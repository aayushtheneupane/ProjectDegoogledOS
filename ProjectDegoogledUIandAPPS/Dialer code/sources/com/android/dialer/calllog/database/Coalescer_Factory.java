package com.android.dialer.calllog.database;

import com.android.dialer.metrics.FutureTimer;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class Coalescer_Factory implements Factory<Coalescer> {
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;
    private final Provider<FutureTimer> futureTimerProvider;

    public Coalescer_Factory(Provider<ListeningExecutorService> provider, Provider<FutureTimer> provider2) {
        this.backgroundExecutorServiceProvider = provider;
        this.futureTimerProvider = provider2;
    }

    public Object get() {
        return new Coalescer(this.backgroundExecutorServiceProvider.get(), this.futureTimerProvider.get());
    }
}
