package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.MusicTile_Factory */
public final class MusicTile_Factory implements Factory<MusicTile> {
    private final Provider<QSHost> hostProvider;

    public MusicTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public MusicTile get() {
        return provideInstance(this.hostProvider);
    }

    public static MusicTile provideInstance(Provider<QSHost> provider) {
        return new MusicTile(provider.get());
    }

    public static MusicTile_Factory create(Provider<QSHost> provider) {
        return new MusicTile_Factory(provider);
    }
}
