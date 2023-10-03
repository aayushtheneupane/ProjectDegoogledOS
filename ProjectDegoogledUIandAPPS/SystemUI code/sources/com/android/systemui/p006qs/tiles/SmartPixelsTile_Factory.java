package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.SmartPixelsTile_Factory */
public final class SmartPixelsTile_Factory implements Factory<SmartPixelsTile> {
    private final Provider<QSHost> hostProvider;

    public SmartPixelsTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public SmartPixelsTile get() {
        return provideInstance(this.hostProvider);
    }

    public static SmartPixelsTile provideInstance(Provider<QSHost> provider) {
        return new SmartPixelsTile(provider.get());
    }

    public static SmartPixelsTile_Factory create(Provider<QSHost> provider) {
        return new SmartPixelsTile_Factory(provider);
    }
}
