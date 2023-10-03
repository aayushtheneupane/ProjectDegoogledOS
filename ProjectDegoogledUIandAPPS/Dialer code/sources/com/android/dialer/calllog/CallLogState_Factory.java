package com.android.dialer.calllog;

import android.content.SharedPreferences;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CallLogState_Factory implements Factory<CallLogState> {
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public CallLogState_Factory(Provider<SharedPreferences> provider, Provider<ListeningExecutorService> provider2) {
        this.sharedPreferencesProvider = provider;
        this.backgroundExecutorProvider = provider2;
    }

    public Object get() {
        return new CallLogState(this.sharedPreferencesProvider.get(), this.backgroundExecutorProvider.get());
    }
}
