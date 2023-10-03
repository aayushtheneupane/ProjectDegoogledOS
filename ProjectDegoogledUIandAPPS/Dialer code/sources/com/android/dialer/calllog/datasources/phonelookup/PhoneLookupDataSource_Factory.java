package com.android.dialer.calllog.datasources.phonelookup;

import android.content.Context;
import com.android.dialer.phonelookup.composite.CompositePhoneLookup;
import com.android.dialer.phonelookup.database.PhoneLookupHistoryDatabaseHelper;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PhoneLookupDataSource_Factory implements Factory<PhoneLookupDataSource> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;
    private final Provider<CompositePhoneLookup> compositePhoneLookupProvider;
    private final Provider<ListeningExecutorService> lightweightExecutorServiceProvider;
    private final Provider<PhoneLookupHistoryDatabaseHelper> phoneLookupHistoryDatabaseHelperProvider;

    public PhoneLookupDataSource_Factory(Provider<Context> provider, Provider<CompositePhoneLookup> provider2, Provider<ListeningExecutorService> provider3, Provider<ListeningExecutorService> provider4, Provider<PhoneLookupHistoryDatabaseHelper> provider5) {
        this.appContextProvider = provider;
        this.compositePhoneLookupProvider = provider2;
        this.backgroundExecutorServiceProvider = provider3;
        this.lightweightExecutorServiceProvider = provider4;
        this.phoneLookupHistoryDatabaseHelperProvider = provider5;
    }

    public Object get() {
        return new PhoneLookupDataSource(this.appContextProvider.get(), this.compositePhoneLookupProvider.get(), this.backgroundExecutorServiceProvider.get(), this.lightweightExecutorServiceProvider.get(), this.phoneLookupHistoryDatabaseHelperProvider.get());
    }
}
