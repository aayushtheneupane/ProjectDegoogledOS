package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.ReadingModeTile_Factory */
public final class ReadingModeTile_Factory implements Factory<ReadingModeTile> {
    private final Provider<QSHost> hostProvider;

    public ReadingModeTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public ReadingModeTile get() {
        return provideInstance(this.hostProvider);
    }

    public static ReadingModeTile provideInstance(Provider<QSHost> provider) {
        return new ReadingModeTile(provider.get());
    }

    public static ReadingModeTile_Factory create(Provider<QSHost> provider) {
        return new ReadingModeTile_Factory(provider);
    }
}
