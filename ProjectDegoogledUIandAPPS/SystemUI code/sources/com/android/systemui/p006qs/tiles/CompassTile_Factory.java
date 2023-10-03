package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.CompassTile_Factory */
public final class CompassTile_Factory implements Factory<CompassTile> {
    private final Provider<QSHost> hostProvider;

    public CompassTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public CompassTile get() {
        return provideInstance(this.hostProvider);
    }

    public static CompassTile provideInstance(Provider<QSHost> provider) {
        return new CompassTile(provider.get());
    }

    public static CompassTile_Factory create(Provider<QSHost> provider) {
        return new CompassTile_Factory(provider);
    }
}
