package com.android.dialer.calllog.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.calllog.CallLogFramework;
import com.android.dialer.configprovider.ConfigProvider;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CallLogConfigImpl_Factory implements Factory<CallLogConfigImpl> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final Provider<CallLogFramework> callLogFrameworkProvider;
    private final Provider<ConfigProvider> configProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public CallLogConfigImpl_Factory(Provider<Context> provider, Provider<CallLogFramework> provider2, Provider<SharedPreferences> provider3, Provider<ConfigProvider> provider4, Provider<ListeningExecutorService> provider5) {
        this.appContextProvider = provider;
        this.callLogFrameworkProvider = provider2;
        this.sharedPreferencesProvider = provider3;
        this.configProvider = provider4;
        this.backgroundExecutorProvider = provider5;
    }

    public Object get() {
        return new CallLogConfigImpl(this.appContextProvider.get(), this.callLogFrameworkProvider.get(), this.sharedPreferencesProvider.get(), this.configProvider.get(), this.backgroundExecutorProvider.get());
    }
}
