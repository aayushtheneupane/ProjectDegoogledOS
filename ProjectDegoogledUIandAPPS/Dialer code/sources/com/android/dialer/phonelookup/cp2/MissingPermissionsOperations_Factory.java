package com.android.dialer.phonelookup.cp2;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MissingPermissionsOperations_Factory implements Factory<MissingPermissionsOperations> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final Provider<ListeningExecutorService> lightweightExecutorProvider;

    public MissingPermissionsOperations_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2, Provider<ListeningExecutorService> provider3) {
        this.appContextProvider = provider;
        this.backgroundExecutorProvider = provider2;
        this.lightweightExecutorProvider = provider3;
    }

    public Object get() {
        return new MissingPermissionsOperations(this.appContextProvider.get(), this.backgroundExecutorProvider.get(), this.lightweightExecutorProvider.get());
    }
}
