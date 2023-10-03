package com.android.systemui.assist;

import android.content.Context;
import com.android.internal.app.AssistUtils;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AssistManager_Factory implements Factory<AssistManager> {
    private final Provider<AssistUtils> assistUtilsProvider;
    private final Provider<ConfigurationController> configurationControllerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceProvisionedController> controllerProvider;
    private final Provider<AssistHandleBehaviorController> handleControllerProvider;
    private final Provider<OverviewProxyService> overviewProxyServiceProvider;

    public AssistManager_Factory(Provider<DeviceProvisionedController> provider, Provider<Context> provider2, Provider<AssistUtils> provider3, Provider<AssistHandleBehaviorController> provider4, Provider<ConfigurationController> provider5, Provider<OverviewProxyService> provider6) {
        this.controllerProvider = provider;
        this.contextProvider = provider2;
        this.assistUtilsProvider = provider3;
        this.handleControllerProvider = provider4;
        this.configurationControllerProvider = provider5;
        this.overviewProxyServiceProvider = provider6;
    }

    public AssistManager get() {
        return provideInstance(this.controllerProvider, this.contextProvider, this.assistUtilsProvider, this.handleControllerProvider, this.configurationControllerProvider, this.overviewProxyServiceProvider);
    }

    public static AssistManager provideInstance(Provider<DeviceProvisionedController> provider, Provider<Context> provider2, Provider<AssistUtils> provider3, Provider<AssistHandleBehaviorController> provider4, Provider<ConfigurationController> provider5, Provider<OverviewProxyService> provider6) {
        return new AssistManager(provider.get(), provider2.get(), provider3.get(), provider4.get(), provider5.get(), provider6.get());
    }

    public static AssistManager_Factory create(Provider<DeviceProvisionedController> provider, Provider<Context> provider2, Provider<AssistUtils> provider3, Provider<AssistHandleBehaviorController> provider4, Provider<ConfigurationController> provider5, Provider<OverviewProxyService> provider6) {
        return new AssistManager_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
