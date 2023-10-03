package com.android.dialer.calllog;

import android.content.Context;
import com.android.dialer.calllog.datasources.DataSources;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CallLogFramework_Factory implements Factory<CallLogFramework> {
    private final Provider<AnnotatedCallLogMigrator> annotatedCallLogMigratorProvider;
    private final Provider<Context> appContextProvider;
    private final Provider<CallLogState> callLogStateProvider;
    private final Provider<DataSources> dataSourcesProvider;
    private final Provider<ListeningExecutorService> uiExecutorProvider;

    public CallLogFramework_Factory(Provider<Context> provider, Provider<DataSources> provider2, Provider<AnnotatedCallLogMigrator> provider3, Provider<ListeningExecutorService> provider4, Provider<CallLogState> provider5) {
        this.appContextProvider = provider;
        this.dataSourcesProvider = provider2;
        this.annotatedCallLogMigratorProvider = provider3;
        this.uiExecutorProvider = provider4;
        this.callLogStateProvider = provider5;
    }

    public Object get() {
        return new CallLogFramework(this.appContextProvider.get(), this.dataSourcesProvider.get(), this.annotatedCallLogMigratorProvider.get(), this.uiExecutorProvider.get(), this.callLogStateProvider.get());
    }
}
