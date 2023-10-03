package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.AdbOverNetworkTile_Factory */
public final class AdbOverNetworkTile_Factory implements Factory<AdbOverNetworkTile> {
    private final Provider<QSHost> hostProvider;

    public AdbOverNetworkTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public AdbOverNetworkTile get() {
        return provideInstance(this.hostProvider);
    }

    public static AdbOverNetworkTile provideInstance(Provider<QSHost> provider) {
        return new AdbOverNetworkTile(provider.get());
    }

    public static AdbOverNetworkTile_Factory create(Provider<QSHost> provider) {
        return new AdbOverNetworkTile_Factory(provider);
    }
}
