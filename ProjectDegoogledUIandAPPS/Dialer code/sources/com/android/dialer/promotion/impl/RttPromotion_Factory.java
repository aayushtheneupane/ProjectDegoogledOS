package com.android.dialer.promotion.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.configprovider.ConfigProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RttPromotion_Factory implements Factory<RttPromotion> {
    private final Provider<ConfigProvider> configProvider;
    private final Provider<Context> contextProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public RttPromotion_Factory(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<ConfigProvider> provider3) {
        this.contextProvider = provider;
        this.sharedPreferencesProvider = provider2;
        this.configProvider = provider3;
    }

    public Object get() {
        return new RttPromotion(this.contextProvider.get(), this.sharedPreferencesProvider.get(), this.configProvider.get());
    }
}
