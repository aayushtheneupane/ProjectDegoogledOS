package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.NavBarTile_Factory */
public final class NavBarTile_Factory implements Factory<NavBarTile> {
    private final Provider<QSHost> hostProvider;

    public NavBarTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public NavBarTile get() {
        return provideInstance(this.hostProvider);
    }

    public static NavBarTile provideInstance(Provider<QSHost> provider) {
        return new NavBarTile(provider.get());
    }

    public static NavBarTile_Factory create(Provider<QSHost> provider) {
        return new NavBarTile_Factory(provider);
    }
}
