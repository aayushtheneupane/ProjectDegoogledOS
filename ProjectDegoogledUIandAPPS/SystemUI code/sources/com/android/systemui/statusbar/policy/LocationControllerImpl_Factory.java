package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Looper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LocationControllerImpl_Factory implements Factory<LocationControllerImpl> {
    private final Provider<Looper> bgLooperProvider;
    private final Provider<Context> contextProvider;

    public LocationControllerImpl_Factory(Provider<Context> provider, Provider<Looper> provider2) {
        this.contextProvider = provider;
        this.bgLooperProvider = provider2;
    }

    public LocationControllerImpl get() {
        return provideInstance(this.contextProvider, this.bgLooperProvider);
    }

    public static LocationControllerImpl provideInstance(Provider<Context> provider, Provider<Looper> provider2) {
        return new LocationControllerImpl(provider.get(), provider2.get());
    }

    public static LocationControllerImpl_Factory create(Provider<Context> provider, Provider<Looper> provider2) {
        return new LocationControllerImpl_Factory(provider, provider2);
    }
}
