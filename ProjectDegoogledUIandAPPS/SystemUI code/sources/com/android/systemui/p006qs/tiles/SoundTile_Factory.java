package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.SoundTile_Factory */
public final class SoundTile_Factory implements Factory<SoundTile> {
    private final Provider<QSHost> hostProvider;

    public SoundTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public SoundTile get() {
        return provideInstance(this.hostProvider);
    }

    public static SoundTile provideInstance(Provider<QSHost> provider) {
        return new SoundTile(provider.get());
    }

    public static SoundTile_Factory create(Provider<QSHost> provider) {
        return new SoundTile_Factory(provider);
    }
}
