package com.android.systemui.util.leak;

import com.android.systemui.p006qs.QSHost;
import com.android.systemui.util.leak.GarbageMonitor;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class GarbageMonitor_MemoryTile_Factory implements Factory<GarbageMonitor.MemoryTile> {
    private final Provider<QSHost> hostProvider;

    public GarbageMonitor_MemoryTile_Factory(Provider<QSHost> provider) {
        this.hostProvider = provider;
    }

    public GarbageMonitor.MemoryTile get() {
        return provideInstance(this.hostProvider);
    }

    public static GarbageMonitor.MemoryTile provideInstance(Provider<QSHost> provider) {
        return new GarbageMonitor.MemoryTile(provider.get());
    }

    public static GarbageMonitor_MemoryTile_Factory create(Provider<QSHost> provider) {
        return new GarbageMonitor_MemoryTile_Factory(provider);
    }
}
