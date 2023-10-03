package com.android.dialer.calllog;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.calllog.database.MutationApplier;
import com.android.dialer.calllog.datasources.DataSources;
import com.android.dialer.metrics.FutureTimer;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RefreshAnnotatedCallLogWorker_Factory implements Factory<RefreshAnnotatedCallLogWorker> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;
    private final Provider<CallLogCacheUpdater> callLogCacheUpdaterProvider;
    private final Provider<CallLogState> callLogStateProvider;
    private final Provider<DataSources> dataSourcesProvider;
    private final Provider<FutureTimer> futureTimerProvider;
    private final Provider<ListeningExecutorService> lightweightExecutorServiceProvider;
    private final Provider<MutationApplier> mutationApplierProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public RefreshAnnotatedCallLogWorker_Factory(Provider<Context> provider, Provider<DataSources> provider2, Provider<SharedPreferences> provider3, Provider<MutationApplier> provider4, Provider<FutureTimer> provider5, Provider<CallLogState> provider6, Provider<CallLogCacheUpdater> provider7, Provider<ListeningExecutorService> provider8, Provider<ListeningExecutorService> provider9) {
        this.appContextProvider = provider;
        this.dataSourcesProvider = provider2;
        this.sharedPreferencesProvider = provider3;
        this.mutationApplierProvider = provider4;
        this.futureTimerProvider = provider5;
        this.callLogStateProvider = provider6;
        this.callLogCacheUpdaterProvider = provider7;
        this.backgroundExecutorServiceProvider = provider8;
        this.lightweightExecutorServiceProvider = provider9;
    }

    public Object get() {
        return new RefreshAnnotatedCallLogWorker(this.appContextProvider.get(), this.dataSourcesProvider.get(), this.sharedPreferencesProvider.get(), this.mutationApplierProvider.get(), this.futureTimerProvider.get(), this.callLogStateProvider.get(), this.callLogCacheUpdaterProvider.get(), this.backgroundExecutorServiceProvider.get(), this.lightweightExecutorServiceProvider.get());
    }
}
