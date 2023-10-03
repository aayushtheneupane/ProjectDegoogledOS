package com.android.dialer.phonelookup.emergency;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class EmergencyPhoneLookup_Factory implements Factory<EmergencyPhoneLookup> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;

    public EmergencyPhoneLookup_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2) {
        this.appContextProvider = provider;
        this.backgroundExecutorServiceProvider = provider2;
    }

    public Object get() {
        return new EmergencyPhoneLookup(this.appContextProvider.get(), this.backgroundExecutorServiceProvider.get());
    }
}
