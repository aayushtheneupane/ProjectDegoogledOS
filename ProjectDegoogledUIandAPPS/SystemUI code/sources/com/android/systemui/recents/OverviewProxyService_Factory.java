package com.android.systemui.recents;

import android.content.Context;
import com.android.systemui.statusbar.NavigationBarController;
import com.android.systemui.statusbar.phone.NavigationModeController;
import com.android.systemui.statusbar.phone.StatusBarWindowController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class OverviewProxyService_Factory implements Factory<OverviewProxyService> {
    private final Provider<Context> contextProvider;
    private final Provider<NavigationBarController> navBarControllerProvider;
    private final Provider<NavigationModeController> navModeControllerProvider;
    private final Provider<DeviceProvisionedController> provisionControllerProvider;
    private final Provider<StatusBarWindowController> statusBarWinControllerProvider;

    public OverviewProxyService_Factory(Provider<Context> provider, Provider<DeviceProvisionedController> provider2, Provider<NavigationBarController> provider3, Provider<NavigationModeController> provider4, Provider<StatusBarWindowController> provider5) {
        this.contextProvider = provider;
        this.provisionControllerProvider = provider2;
        this.navBarControllerProvider = provider3;
        this.navModeControllerProvider = provider4;
        this.statusBarWinControllerProvider = provider5;
    }

    public OverviewProxyService get() {
        return provideInstance(this.contextProvider, this.provisionControllerProvider, this.navBarControllerProvider, this.navModeControllerProvider, this.statusBarWinControllerProvider);
    }

    public static OverviewProxyService provideInstance(Provider<Context> provider, Provider<DeviceProvisionedController> provider2, Provider<NavigationBarController> provider3, Provider<NavigationModeController> provider4, Provider<StatusBarWindowController> provider5) {
        return new OverviewProxyService(provider.get(), provider2.get(), provider3.get(), provider4.get(), provider5.get());
    }

    public static OverviewProxyService_Factory create(Provider<Context> provider, Provider<DeviceProvisionedController> provider2, Provider<NavigationBarController> provider3, Provider<NavigationModeController> provider4, Provider<StatusBarWindowController> provider5) {
        return new OverviewProxyService_Factory(provider, provider2, provider3, provider4, provider5);
    }
}
