package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.VpnTile_Factory */
public final class VpnTile_Factory implements Factory<VpnTile> {
    private final Provider<QSHost> hostProvider;

    public VpnTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public VpnTile get() {
        return provideInstance(this.hostProvider);
    }

    public static VpnTile provideInstance(Provider<QSHost> provider) {
        return new VpnTile(provider.get());
    }

    public static VpnTile_Factory create(Provider<QSHost> provider) {
        return new VpnTile_Factory(provider);
    }
}
