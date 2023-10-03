package com.android.dialer.calllog;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CallLogCacheUpdater_Factory implements Factory<CallLogCacheUpdater> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final Provider<CallLogState> callLogStateProvider;

    public CallLogCacheUpdater_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2, Provider<CallLogState> provider3) {
        this.appContextProvider = provider;
        this.backgroundExecutorProvider = provider2;
        this.callLogStateProvider = provider3;
    }

    public Object get() {
        return new CallLogCacheUpdater(this.appContextProvider.get(), this.backgroundExecutorProvider.get(), this.callLogStateProvider.get());
    }
}
