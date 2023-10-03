package com.android.dialer.preferredsim.impl;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PreferredAccountWorkerImpl_Factory implements Factory<PreferredAccountWorkerImpl> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;

    public PreferredAccountWorkerImpl_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2) {
        this.appContextProvider = provider;
        this.backgroundExecutorProvider = provider2;
    }

    public Object get() {
        return new PreferredAccountWorkerImpl(this.appContextProvider.get(), this.backgroundExecutorProvider.get());
    }
}
