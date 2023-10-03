package com.android.dialer.calllog;

import android.content.SharedPreferences;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AnnotatedCallLogMigrator_Factory implements Factory<AnnotatedCallLogMigrator> {
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final Provider<RefreshAnnotatedCallLogWorker> refreshAnnotatedCallLogWorkerProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public AnnotatedCallLogMigrator_Factory(Provider<SharedPreferences> provider, Provider<ListeningExecutorService> provider2, Provider<RefreshAnnotatedCallLogWorker> provider3) {
        this.sharedPreferencesProvider = provider;
        this.backgroundExecutorProvider = provider2;
        this.refreshAnnotatedCallLogWorkerProvider = provider3;
    }

    public Object get() {
        return new AnnotatedCallLogMigrator(this.sharedPreferencesProvider.get(), this.backgroundExecutorProvider.get(), this.refreshAnnotatedCallLogWorkerProvider.get());
    }
}
