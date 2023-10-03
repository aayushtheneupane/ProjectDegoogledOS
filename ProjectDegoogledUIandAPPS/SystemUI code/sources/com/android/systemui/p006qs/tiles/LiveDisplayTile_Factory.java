package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.LiveDisplayTile_Factory */
public final class LiveDisplayTile_Factory implements Factory<LiveDisplayTile> {
    private final Provider<QSHost> hostProvider;

    public LiveDisplayTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public LiveDisplayTile get() {
        return provideInstance(this.hostProvider);
    }

    public static LiveDisplayTile provideInstance(Provider<QSHost> provider) {
        return new LiveDisplayTile(provider.get());
    }

    public static LiveDisplayTile_Factory create(Provider<QSHost> provider) {
        return new LiveDisplayTile_Factory(provider);
    }
}
