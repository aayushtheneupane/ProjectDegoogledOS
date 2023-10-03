package com.android.systemui.statusbar.phone;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StatusBarWindowController_Factory implements Factory<StatusBarWindowController> {
    private final Provider<ConfigurationController> configurationControllerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<KeyguardBypassController> keyguardBypassControllerProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;

    public StatusBarWindowController_Factory(Provider<Context> provider, Provider<StatusBarStateController> provider2, Provider<ConfigurationController> provider3, Provider<KeyguardBypassController> provider4) {
        this.contextProvider = provider;
        this.statusBarStateControllerProvider = provider2;
        this.configurationControllerProvider = provider3;
        this.keyguardBypassControllerProvider = provider4;
    }

    public StatusBarWindowController get() {
        return provideInstance(this.contextProvider, this.statusBarStateControllerProvider, this.configurationControllerProvider, this.keyguardBypassControllerProvider);
    }

    public static StatusBarWindowController provideInstance(Provider<Context> provider, Provider<StatusBarStateController> provider2, Provider<ConfigurationController> provider3, Provider<KeyguardBypassController> provider4) {
        return new StatusBarWindowController(provider.get(), provider2.get(), provider3.get(), provider4.get());
    }

    public static StatusBarWindowController_Factory create(Provider<Context> provider, Provider<StatusBarStateController> provider2, Provider<ConfigurationController> provider3, Provider<KeyguardBypassController> provider4) {
        return new StatusBarWindowController_Factory(provider, provider2, provider3, provider4);
    }
}
