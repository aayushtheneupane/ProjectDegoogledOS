package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.SoundSearchTile_Factory */
public final class SoundSearchTile_Factory implements Factory<SoundSearchTile> {
    private final Provider<QSHost> hostProvider;

    public SoundSearchTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public SoundSearchTile get() {
        return provideInstance(this.hostProvider);
    }

    public static SoundSearchTile provideInstance(Provider<QSHost> provider) {
        return new SoundSearchTile(provider.get());
    }

    public static SoundSearchTile_Factory create(Provider<QSHost> provider) {
        return new SoundSearchTile_Factory(provider);
    }
}
