package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import com.android.systemui.plugins.ActivityStarter;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.AirplaneModeTile_Factory */
public final class AirplaneModeTile_Factory implements Factory<AirplaneModeTile> {
    private final Provider<ActivityStarter> activityStarterProvider;
    private final Provider<QSHost> hostProvider;

    public AirplaneModeTile_Factory(Provider<QSHost> provider, Provider<ActivityStarter> provider2) {
        this.hostProvider = provider;
        this.activityStarterProvider = provider2;
    }

    public AirplaneModeTile get() {
        return provideInstance(this.hostProvider, this.activityStarterProvider);
    }

    public static AirplaneModeTile provideInstance(Provider<QSHost> provider, Provider<ActivityStarter> provider2) {
        return new AirplaneModeTile(provider.get(), provider2.get());
    }

    public static AirplaneModeTile_Factory create(Provider<QSHost> provider, Provider<ActivityStarter> provider2) {
        return new AirplaneModeTile_Factory(provider, provider2);
    }
}
