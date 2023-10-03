package com.android.dialer.phonelookup.cp2;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.configprovider.ConfigProvider;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class Cp2DefaultDirectoryPhoneLookup_Factory implements Factory<Cp2DefaultDirectoryPhoneLookup> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;
    private final Provider<ConfigProvider> configProvider;
    private final Provider<ListeningExecutorService> lightweightExecutorServiceProvider;
    private final Provider<MissingPermissionsOperations> missingPermissionsOperationsProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public Cp2DefaultDirectoryPhoneLookup_Factory(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<ListeningExecutorService> provider3, Provider<ListeningExecutorService> provider4, Provider<ConfigProvider> provider5, Provider<MissingPermissionsOperations> provider6) {
        this.appContextProvider = provider;
        this.sharedPreferencesProvider = provider2;
        this.backgroundExecutorServiceProvider = provider3;
        this.lightweightExecutorServiceProvider = provider4;
        this.configProvider = provider5;
        this.missingPermissionsOperationsProvider = provider6;
    }

    public Object get() {
        return new Cp2DefaultDirectoryPhoneLookup(this.appContextProvider.get(), this.sharedPreferencesProvider.get(), this.backgroundExecutorServiceProvider.get(), this.lightweightExecutorServiceProvider.get(), this.configProvider.get(), this.missingPermissionsOperationsProvider.get());
    }
}
