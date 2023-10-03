package com.android.dialer.calllog.database;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Provider;

public final class AnnotatedCallLogDatabaseHelper_Factory implements Factory<AnnotatedCallLogDatabaseHelper> {
    private final MembersInjector<AnnotatedCallLogDatabaseHelper> annotatedCallLogDatabaseHelperMembersInjector;
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final Provider<Integer> maxRowsProvider;

    public AnnotatedCallLogDatabaseHelper_Factory(MembersInjector<AnnotatedCallLogDatabaseHelper> membersInjector, Provider<Context> provider, Provider<Integer> provider2, Provider<ListeningExecutorService> provider3) {
        this.annotatedCallLogDatabaseHelperMembersInjector = membersInjector;
        this.appContextProvider = provider;
        this.maxRowsProvider = provider2;
        this.backgroundExecutorProvider = provider3;
    }

    public Object get() {
        MembersInjector<AnnotatedCallLogDatabaseHelper> membersInjector = this.annotatedCallLogDatabaseHelperMembersInjector;
        AnnotatedCallLogDatabaseHelper annotatedCallLogDatabaseHelper = new AnnotatedCallLogDatabaseHelper(this.appContextProvider.get(), this.maxRowsProvider.get().intValue(), this.backgroundExecutorProvider.get());
        MembersInjectors.injectMembers(membersInjector, annotatedCallLogDatabaseHelper);
        return annotatedCallLogDatabaseHelper;
    }
}
