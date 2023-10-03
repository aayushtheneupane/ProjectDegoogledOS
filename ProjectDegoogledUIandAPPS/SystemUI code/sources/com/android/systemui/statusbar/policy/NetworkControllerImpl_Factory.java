package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Looper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NetworkControllerImpl_Factory implements Factory<NetworkControllerImpl> {
    private final Provider<Looper> bgLooperProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceProvisionedController> deviceProvisionedControllerProvider;

    public NetworkControllerImpl_Factory(Provider<Context> provider, Provider<Looper> provider2, Provider<DeviceProvisionedController> provider3) {
        this.contextProvider = provider;
        this.bgLooperProvider = provider2;
        this.deviceProvisionedControllerProvider = provider3;
    }

    public NetworkControllerImpl get() {
        return provideInstance(this.contextProvider, this.bgLooperProvider, this.deviceProvisionedControllerProvider);
    }

    public static NetworkControllerImpl provideInstance(Provider<Context> provider, Provider<Looper> provider2, Provider<DeviceProvisionedController> provider3) {
        return new NetworkControllerImpl(provider.get(), provider2.get(), provider3.get());
    }

    public static NetworkControllerImpl_Factory create(Provider<Context> provider, Provider<Looper> provider2, Provider<DeviceProvisionedController> provider3) {
        return new NetworkControllerImpl_Factory(provider, provider2, provider3);
    }
}
