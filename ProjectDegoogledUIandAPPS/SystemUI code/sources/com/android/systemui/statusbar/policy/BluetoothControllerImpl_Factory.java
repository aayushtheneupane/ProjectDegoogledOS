package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Looper;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BluetoothControllerImpl_Factory implements Factory<BluetoothControllerImpl> {
    private final Provider<Looper> bgLooperProvider;
    private final Provider<Context> contextProvider;
    private final Provider<LocalBluetoothManager> localBluetoothManagerProvider;

    public BluetoothControllerImpl_Factory(Provider<Context> provider, Provider<Looper> provider2, Provider<LocalBluetoothManager> provider3) {
        this.contextProvider = provider;
        this.bgLooperProvider = provider2;
        this.localBluetoothManagerProvider = provider3;
    }

    public BluetoothControllerImpl get() {
        return provideInstance(this.contextProvider, this.bgLooperProvider, this.localBluetoothManagerProvider);
    }

    public static BluetoothControllerImpl provideInstance(Provider<Context> provider, Provider<Looper> provider2, Provider<LocalBluetoothManager> provider3) {
        return new BluetoothControllerImpl(provider.get(), provider2.get(), provider3.get());
    }

    public static BluetoothControllerImpl_Factory create(Provider<Context> provider, Provider<Looper> provider2, Provider<LocalBluetoothManager> provider3) {
        return new BluetoothControllerImpl_Factory(provider, provider2, provider3);
    }
}
