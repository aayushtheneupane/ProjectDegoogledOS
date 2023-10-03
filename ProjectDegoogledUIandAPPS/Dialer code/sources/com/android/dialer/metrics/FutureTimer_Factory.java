package com.android.dialer.metrics;

import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FutureTimer_Factory implements Factory<FutureTimer> {
    private final Provider<ListeningExecutorService> lightweightExecutorServiceProvider;
    private final Provider<Metrics> metricsProvider;

    public FutureTimer_Factory(Provider<Metrics> provider, Provider<ListeningExecutorService> provider2) {
        this.metricsProvider = provider;
        this.lightweightExecutorServiceProvider = provider2;
    }

    public Object get() {
        return new FutureTimer(this.metricsProvider.get(), this.lightweightExecutorServiceProvider.get());
    }
}
