package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.RebootTile_Factory */
public final class RebootTile_Factory implements Factory<RebootTile> {
    private final Provider<QSHost> hostProvider;

    public RebootTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public RebootTile get() {
        return provideInstance(this.hostProvider);
    }

    public static RebootTile provideInstance(Provider<QSHost> provider) {
        return new RebootTile(provider.get());
    }

    public static RebootTile_Factory create(Provider<QSHost> provider) {
        return new RebootTile_Factory(provider);
    }
}
