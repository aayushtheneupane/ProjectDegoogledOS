package com.android.dialer.calllog.datasources.systemcalllog;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.calllog.database.AnnotatedCallLogDatabaseHelper;
import com.android.dialer.calllog.observer.MarkDirtyObserver;
import com.android.dialer.duo.Duo;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SystemCallLogDataSource_Factory implements Factory<SystemCallLogDataSource> {
    private final Provider<AnnotatedCallLogDatabaseHelper> annotatedCallLogDatabaseHelperProvider;
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;
    private final Provider<Duo> duoProvider;
    private final Provider<MarkDirtyObserver> markDirtyObserverProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public SystemCallLogDataSource_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2, Provider<MarkDirtyObserver> provider3, Provider<SharedPreferences> provider4, Provider<AnnotatedCallLogDatabaseHelper> provider5, Provider<Duo> provider6) {
        this.appContextProvider = provider;
        this.backgroundExecutorServiceProvider = provider2;
        this.markDirtyObserverProvider = provider3;
        this.sharedPreferencesProvider = provider4;
        this.annotatedCallLogDatabaseHelperProvider = provider5;
        this.duoProvider = provider6;
    }

    public Object get() {
        return new SystemCallLogDataSource(this.appContextProvider.get(), this.backgroundExecutorServiceProvider.get(), this.markDirtyObserverProvider.get(), this.sharedPreferencesProvider.get(), this.annotatedCallLogDatabaseHelperProvider.get(), this.duoProvider.get());
    }
}
