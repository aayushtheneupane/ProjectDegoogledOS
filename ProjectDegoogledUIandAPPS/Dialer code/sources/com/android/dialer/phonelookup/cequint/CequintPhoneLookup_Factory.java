package com.android.dialer.phonelookup.cequint;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CequintPhoneLookup_Factory implements Factory<CequintPhoneLookup> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;
    private final Provider<ListeningExecutorService> lightweightExecutorServiceProvider;

    public CequintPhoneLookup_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2, Provider<ListeningExecutorService> provider3) {
        this.appContextProvider = provider;
        this.backgroundExecutorServiceProvider = provider2;
        this.lightweightExecutorServiceProvider = provider3;
    }

    public Object get() {
        return new CequintPhoneLookup(this.appContextProvider.get(), this.backgroundExecutorServiceProvider.get(), this.lightweightExecutorServiceProvider.get());
    }
}
