package com.android.dialer.configprovider;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SharedPrefConfigProvider_Factory implements Factory<SharedPrefConfigProvider> {
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public SharedPrefConfigProvider_Factory(Provider<SharedPreferences> provider) {
        this.sharedPreferencesProvider = provider;
    }

    public Object get() {
        return new SharedPrefConfigProvider(this.sharedPreferencesProvider.get());
    }
}
