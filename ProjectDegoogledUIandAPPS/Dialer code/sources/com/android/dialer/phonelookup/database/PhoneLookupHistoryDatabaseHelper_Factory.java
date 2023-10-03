package com.android.dialer.phonelookup.database;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Provider;

public final class PhoneLookupHistoryDatabaseHelper_Factory implements Factory<PhoneLookupHistoryDatabaseHelper> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final MembersInjector<PhoneLookupHistoryDatabaseHelper> phoneLookupHistoryDatabaseHelperMembersInjector;

    public PhoneLookupHistoryDatabaseHelper_Factory(MembersInjector<PhoneLookupHistoryDatabaseHelper> membersInjector, Provider<Context> provider, Provider<ListeningExecutorService> provider2) {
        this.phoneLookupHistoryDatabaseHelperMembersInjector = membersInjector;
        this.appContextProvider = provider;
        this.backgroundExecutorProvider = provider2;
    }

    public Object get() {
        MembersInjector<PhoneLookupHistoryDatabaseHelper> membersInjector = this.phoneLookupHistoryDatabaseHelperMembersInjector;
        PhoneLookupHistoryDatabaseHelper phoneLookupHistoryDatabaseHelper = new PhoneLookupHistoryDatabaseHelper(this.appContextProvider.get(), this.backgroundExecutorProvider.get());
        MembersInjectors.injectMembers(membersInjector, phoneLookupHistoryDatabaseHelper);
        return phoneLookupHistoryDatabaseHelper;
    }
}
