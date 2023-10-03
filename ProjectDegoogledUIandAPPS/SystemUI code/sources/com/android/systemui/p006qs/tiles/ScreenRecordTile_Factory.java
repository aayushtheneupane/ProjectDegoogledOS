package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.ScreenRecordTile_Factory */
public final class ScreenRecordTile_Factory implements Factory<ScreenRecordTile> {
    private final Provider<QSHost> hostProvider;

    public ScreenRecordTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public ScreenRecordTile get() {
        return provideInstance(this.hostProvider);
    }

    public static ScreenRecordTile provideInstance(Provider<QSHost> provider) {
        return new ScreenRecordTile(provider.get());
    }

    public static ScreenRecordTile_Factory create(Provider<QSHost> provider) {
        return new ScreenRecordTile_Factory(provider);
    }
}
