package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.ScreenStabilizationTile_Factory */
public final class ScreenStabilizationTile_Factory implements Factory<ScreenStabilizationTile> {
    private final Provider<QSHost> hostProvider;

    public ScreenStabilizationTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public ScreenStabilizationTile get() {
        return provideInstance(this.hostProvider);
    }

    public static ScreenStabilizationTile provideInstance(Provider<QSHost> provider) {
        return new ScreenStabilizationTile(provider.get());
    }

    public static ScreenStabilizationTile_Factory create(Provider<QSHost> provider) {
        return new ScreenStabilizationTile_Factory(provider);
    }
}
