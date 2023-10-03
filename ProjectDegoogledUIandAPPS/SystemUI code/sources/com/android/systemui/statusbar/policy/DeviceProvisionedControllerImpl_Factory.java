package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DeviceProvisionedControllerImpl_Factory implements Factory<DeviceProvisionedControllerImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<Handler> mainHandlerProvider;

    public DeviceProvisionedControllerImpl_Factory(Provider<Context> provider, Provider<Handler> provider2) {
        this.contextProvider = provider;
        this.mainHandlerProvider = provider2;
    }

    public DeviceProvisionedControllerImpl get() {
        return provideInstance(this.contextProvider, this.mainHandlerProvider);
    }

    public static DeviceProvisionedControllerImpl provideInstance(Provider<Context> provider, Provider<Handler> provider2) {
        return new DeviceProvisionedControllerImpl(provider.get(), provider2.get());
    }

    public static DeviceProvisionedControllerImpl_Factory create(Provider<Context> provider, Provider<Handler> provider2) {
        return new DeviceProvisionedControllerImpl_Factory(provider, provider2);
    }
}
