package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.DataSwitchTile_Factory */
public final class DataSwitchTile_Factory implements Factory<DataSwitchTile> {
    private final Provider<QSHost> hostProvider;

    public DataSwitchTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public DataSwitchTile get() {
        return provideInstance(this.hostProvider);
    }

    public static DataSwitchTile provideInstance(Provider<QSHost> provider) {
        return new DataSwitchTile(provider.get());
    }

    public static DataSwitchTile_Factory create(Provider<QSHost> provider) {
        return new DataSwitchTile_Factory(provider);
    }
}
