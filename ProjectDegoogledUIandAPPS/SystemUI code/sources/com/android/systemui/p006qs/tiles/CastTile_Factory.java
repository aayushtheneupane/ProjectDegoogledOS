package com.android.systemui.p006qs.tiles;

import com.android.systemui.p006qs.QSHost;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.NetworkController;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.tiles.CastTile_Factory */
public final class CastTile_Factory implements Factory<CastTile> {
    private final Provider<ActivityStarter> activityStarterProvider;
    private final Provider<CastController> castControllerProvider;
    private final Provider<QSHost> hostProvider;
    private final Provider<KeyguardMonitor> keyguardMonitorProvider;
    private final Provider<NetworkController> networkControllerProvider;

    public CastTile_Factory(Provider<QSHost> provider, Provider<CastController> provider2, Provider<KeyguardMonitor> provider3, Provider<NetworkController> provider4, Provider<ActivityStarter> provider5) {
        this.hostProvider = provider;
        this.castControllerProvider = provider2;
        this.keyguardMonitorProvider = provider3;
        this.networkControllerProvider = provider4;
        this.activityStarterProvider = provider5;
    }

    public CastTile get() {
        return provideInstance(this.hostProvider, this.castControllerProvider, this.keyguardMonitorProvider, this.networkControllerProvider, this.activityStarterProvider);
    }

    public static CastTile provideInstance(Provider<QSHost> provider, Provider<CastController> provider2, Provider<KeyguardMonitor> provider3, Provider<NetworkController> provider4, Provider<ActivityStarter> provider5) {
        return new CastTile(provider.get(), provider2.get(), provider3.get(), provider4.get(), provider5.get());
    }

    public static CastTile_Factory create(Provider<QSHost> provider, Provider<CastController> provider2, Provider<KeyguardMonitor> provider3, Provider<NetworkController> provider4, Provider<ActivityStarter> provider5) {
        return new CastTile_Factory(provider, provider2, provider3, provider4, provider5);
    }
}
