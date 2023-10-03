package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.HWKeysTile_Factory */
public final class HWKeysTile_Factory implements Factory<HWKeysTile> {
    private final Provider<QSHost> hostProvider;

    public HWKeysTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public HWKeysTile get() {
        return provideInstance(this.hostProvider);
    }

    public static HWKeysTile provideInstance(Provider<QSHost> provider) {
        return new HWKeysTile(provider.get());
    }

    public static HWKeysTile_Factory create(Provider<QSHost> provider) {
        return new HWKeysTile_Factory(provider);
    }
}
