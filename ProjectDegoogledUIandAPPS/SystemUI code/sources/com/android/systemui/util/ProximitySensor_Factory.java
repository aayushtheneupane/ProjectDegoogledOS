package com.android.systemui.util;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ProximitySensor_Factory implements Factory<ProximitySensor> {
    private final Provider<Context> contextProvider;
    private final Provider<AsyncSensorManager> sensorManagerProvider;

    public ProximitySensor_Factory(Provider<Context> provider, Provider<AsyncSensorManager> provider2) {
        this.contextProvider = provider;
        this.sensorManagerProvider = provider2;
    }

    public ProximitySensor get() {
        return provideInstance(this.contextProvider, this.sensorManagerProvider);
    }

    public static ProximitySensor provideInstance(Provider<Context> provider, Provider<AsyncSensorManager> provider2) {
        return new ProximitySensor(provider.get(), provider2.get());
    }

    public static ProximitySensor_Factory create(Provider<Context> provider, Provider<AsyncSensorManager> provider2) {
        return new ProximitySensor_Factory(provider, provider2);
    }
}
