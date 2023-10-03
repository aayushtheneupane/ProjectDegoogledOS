package com.android.dialer.calllog;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ClearMissedCalls_Factory implements Factory<ClearMissedCalls> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final Provider<ListeningExecutorService> uiThreadExecutorProvider;

    public ClearMissedCalls_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2, Provider<ListeningExecutorService> provider3) {
        this.appContextProvider = provider;
        this.backgroundExecutorProvider = provider2;
        this.uiThreadExecutorProvider = provider3;
    }

    public Object get() {
        return new ClearMissedCalls(this.appContextProvider.get(), this.backgroundExecutorProvider.get(), this.uiThreadExecutorProvider.get());
    }
}
