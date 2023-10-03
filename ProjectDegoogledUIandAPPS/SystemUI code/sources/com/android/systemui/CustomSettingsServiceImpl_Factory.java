package com.android.systemui;

import android.content.Context;
import android.os.Handler;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CustomSettingsServiceImpl_Factory implements Factory<CustomSettingsServiceImpl> {
    private final Provider<Handler> bgHandlerProvider;
    private final Provider<Context> contextProvider;

    public CustomSettingsServiceImpl_Factory(Provider<Context> provider, Provider<Handler> provider2) {
        this.contextProvider = provider;
        this.bgHandlerProvider = provider2;
    }

    public CustomSettingsServiceImpl get() {
        return provideInstance(this.contextProvider, this.bgHandlerProvider);
    }

    public static CustomSettingsServiceImpl provideInstance(Provider<Context> provider, Provider<Handler> provider2) {
        return new CustomSettingsServiceImpl(provider.get(), provider2.get());
    }

    public static CustomSettingsServiceImpl_Factory create(Provider<Context> provider, Provider<Handler> provider2) {
        return new CustomSettingsServiceImpl_Factory(provider, provider2);
    }
}
