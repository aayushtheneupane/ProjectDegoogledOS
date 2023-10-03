package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.HeadsUpTile_Factory */
public final class HeadsUpTile_Factory implements Factory<HeadsUpTile> {
    private final Provider<QSHost> hostProvider;

    public HeadsUpTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public HeadsUpTile get() {
        return provideInstance(this.hostProvider);
    }

    public static HeadsUpTile provideInstance(Provider<QSHost> provider) {
        return new HeadsUpTile(provider.get());
    }

    public static HeadsUpTile_Factory create(Provider<QSHost> provider) {
        return new HeadsUpTile_Factory(provider);
    }
}
