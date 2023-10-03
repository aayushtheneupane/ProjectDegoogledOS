package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.DcDimmingTile_Factory */
public final class DcDimmingTile_Factory implements Factory<DcDimmingTile> {
    private final Provider<QSHost> hostProvider;

    public DcDimmingTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public DcDimmingTile get() {
        return provideInstance(this.hostProvider);
    }

    public static DcDimmingTile provideInstance(Provider<QSHost> provider) {
        return new DcDimmingTile(provider.get());
    }

    public static DcDimmingTile_Factory create(Provider<QSHost> provider) {
        return new DcDimmingTile_Factory(provider);
    }
}
