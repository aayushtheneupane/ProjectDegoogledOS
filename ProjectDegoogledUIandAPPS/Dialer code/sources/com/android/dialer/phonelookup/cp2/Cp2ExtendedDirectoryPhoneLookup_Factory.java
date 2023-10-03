package com.android.dialer.phonelookup.cp2;

import android.content.Context;
import com.android.dialer.configprovider.ConfigProvider;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;

public final class Cp2ExtendedDirectoryPhoneLookup_Factory implements Factory<Cp2ExtendedDirectoryPhoneLookup> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;
    private final Provider<ConfigProvider> configProvider;
    private final Provider<ListeningExecutorService> lightweightExecutorServiceProvider;
    private final Provider<MissingPermissionsOperations> missingPermissionsOperationsProvider;
    private final Provider<ScheduledExecutorService> scheduledExecutorServiceProvider;

    public Cp2ExtendedDirectoryPhoneLookup_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2, Provider<ListeningExecutorService> provider3, Provider<ScheduledExecutorService> provider4, Provider<ConfigProvider> provider5, Provider<MissingPermissionsOperations> provider6) {
        this.appContextProvider = provider;
        this.backgroundExecutorServiceProvider = provider2;
        this.lightweightExecutorServiceProvider = provider3;
        this.scheduledExecutorServiceProvider = provider4;
        this.configProvider = provider5;
        this.missingPermissionsOperationsProvider = provider6;
    }

    public Object get() {
        return new Cp2ExtendedDirectoryPhoneLookup(this.appContextProvider.get(), this.backgroundExecutorServiceProvider.get(), this.lightweightExecutorServiceProvider.get(), this.scheduledExecutorServiceProvider.get(), this.configProvider.get(), this.missingPermissionsOperationsProvider.get());
    }
}
