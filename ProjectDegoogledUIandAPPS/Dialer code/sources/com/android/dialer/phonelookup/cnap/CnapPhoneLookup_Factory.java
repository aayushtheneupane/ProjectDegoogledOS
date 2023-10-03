package com.android.dialer.phonelookup.cnap;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CnapPhoneLookup_Factory implements Factory<CnapPhoneLookup> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;

    public CnapPhoneLookup_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2) {
        this.appContextProvider = provider;
        this.backgroundExecutorServiceProvider = provider2;
    }

    public Object get() {
        return new CnapPhoneLookup(this.appContextProvider.get(), this.backgroundExecutorServiceProvider.get());
    }
}
