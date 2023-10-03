package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import com.android.systemui.statusbar.policy.BatteryController;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.PowerShareTile_Factory */
public final class PowerShareTile_Factory implements Factory<PowerShareTile> {
    private final Provider<BatteryController> batteryControllerProvider;
    private final Provider<QSHost> hostProvider;

    public PowerShareTile_Factory(Provider<QSHost> provider, Provider<BatteryController> provider2) {
        this.hostProvider = provider;
        this.batteryControllerProvider = provider2;
    }

    public PowerShareTile get() {
        return provideInstance(this.hostProvider, this.batteryControllerProvider);
    }

    public static PowerShareTile provideInstance(Provider<QSHost> provider, Provider<BatteryController> provider2) {
        return new PowerShareTile(provider.get(), provider2.get());
    }

    public static PowerShareTile_Factory create(Provider<QSHost> provider, Provider<BatteryController> provider2) {
        return new PowerShareTile_Factory(provider, provider2);
    }
}
