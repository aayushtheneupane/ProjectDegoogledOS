package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.ScreenshotTile_Factory */
public final class ScreenshotTile_Factory implements Factory<ScreenshotTile> {
    private final Provider<QSHost> hostProvider;

    public ScreenshotTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public ScreenshotTile get() {
        return provideInstance(this.hostProvider);
    }

    public static ScreenshotTile provideInstance(Provider<QSHost> provider) {
        return new ScreenshotTile(provider.get());
    }

    public static ScreenshotTile_Factory create(Provider<QSHost> provider) {
        return new ScreenshotTile_Factory(provider);
    }
}
