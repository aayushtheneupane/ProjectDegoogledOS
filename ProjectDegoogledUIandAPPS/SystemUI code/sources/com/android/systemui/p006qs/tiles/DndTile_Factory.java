package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.policy.ZenModeController;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.DndTile_Factory */
public final class DndTile_Factory implements Factory<DndTile> {
    private final Provider<ActivityStarter> activityStarterProvider;
    private final Provider<QSHost> hostProvider;
    private final Provider<ZenModeController> zenModeControllerProvider;

    public DndTile_Factory(Provider<QSHost> provider, Provider<ZenModeController> provider2, Provider<ActivityStarter> provider3) {
        this.hostProvider = provider;
        this.zenModeControllerProvider = provider2;
        this.activityStarterProvider = provider3;
    }

    public DndTile get() {
        return provideInstance(this.hostProvider, this.zenModeControllerProvider, this.activityStarterProvider);
    }

    public static DndTile provideInstance(Provider<QSHost> provider, Provider<ZenModeController> provider2, Provider<ActivityStarter> provider3) {
        return new DndTile(provider.get(), provider2.get(), provider3.get());
    }

    public static DndTile_Factory create(Provider<QSHost> provider, Provider<ZenModeController> provider2, Provider<ActivityStarter> provider3) {
        return new DndTile_Factory(provider, provider2, provider3);
    }
}
