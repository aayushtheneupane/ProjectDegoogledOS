package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import com.android.systemui.statusbar.policy.BatteryController;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.AODTile_Factory */
public final class AODTile_Factory implements Factory<AODTile> {
    private final Provider<BatteryController> batteryControllerProvider;
    private final Provider<QSHost> hostProvider;

    public AODTile_Factory(Provider<QSHost> provider, Provider<BatteryController> provider2) {
        this.hostProvider = provider;
        this.batteryControllerProvider = provider2;
    }

    public AODTile get() {
        return provideInstance(this.hostProvider, this.batteryControllerProvider);
    }

    public static AODTile provideInstance(Provider<QSHost> provider, Provider<BatteryController> provider2) {
        return new AODTile(provider.get(), provider2.get());
    }

    public static AODTile_Factory create(Provider<QSHost> provider, Provider<BatteryController> provider2) {
        return new AODTile_Factory(provider, provider2);
    }
}
