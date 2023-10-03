package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.CaffeineTile_Factory */
public final class CaffeineTile_Factory implements Factory<CaffeineTile> {
    private final Provider<QSHost> hostProvider;

    public CaffeineTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public CaffeineTile get() {
        return provideInstance(this.hostProvider);
    }

    public static CaffeineTile provideInstance(Provider<QSHost> provider) {
        return new CaffeineTile(provider.get());
    }

    public static CaffeineTile_Factory create(Provider<QSHost> provider) {
        return new CaffeineTile_Factory(provider);
    }
}
