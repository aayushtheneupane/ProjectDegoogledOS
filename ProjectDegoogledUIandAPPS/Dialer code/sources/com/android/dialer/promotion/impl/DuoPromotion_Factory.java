package com.android.dialer.promotion.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.duo.Duo;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DuoPromotion_Factory implements Factory<DuoPromotion> {
    private final Provider<ConfigProvider> configProvider;
    private final Provider<Context> contextProvider;
    private final Provider<Duo> duoProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public DuoPromotion_Factory(Provider<Context> provider, Provider<ConfigProvider> provider2, Provider<Duo> provider3, Provider<SharedPreferences> provider4) {
        this.contextProvider = provider;
        this.configProvider = provider2;
        this.duoProvider = provider3;
        this.sharedPreferencesProvider = provider4;
    }

    public Object get() {
        return new DuoPromotion(this.contextProvider.get(), this.configProvider.get(), this.duoProvider.get(), this.sharedPreferencesProvider.get());
    }
}
