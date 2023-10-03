package com.android.systemui;

import android.content.Context;
import android.os.Handler;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DependencyProvider_ProvideLocalBluetoothControllerFactory implements Factory<LocalBluetoothManager> {
    private final Provider<Handler> bgHandlerProvider;
    private final Provider<Context> contextProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProvideLocalBluetoothControllerFactory(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        this.module = dependencyProvider;
        this.contextProvider = provider;
        this.bgHandlerProvider = provider2;
    }

    public LocalBluetoothManager get() {
        return provideInstance(this.module, this.contextProvider, this.bgHandlerProvider);
    }

    public static LocalBluetoothManager provideInstance(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return proxyProvideLocalBluetoothController(dependencyProvider, provider.get(), provider2.get());
    }

    public static DependencyProvider_ProvideLocalBluetoothControllerFactory create(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return new DependencyProvider_ProvideLocalBluetoothControllerFactory(dependencyProvider, provider, provider2);
    }

    public static LocalBluetoothManager proxyProvideLocalBluetoothController(DependencyProvider dependencyProvider, Context context, Handler handler) {
        return dependencyProvider.provideLocalBluetoothController(context, handler);
    }
}
