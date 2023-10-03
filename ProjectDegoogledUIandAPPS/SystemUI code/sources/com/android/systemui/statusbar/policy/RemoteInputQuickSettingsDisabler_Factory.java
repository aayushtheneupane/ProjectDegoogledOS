package com.android.systemui.statusbar.policy;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RemoteInputQuickSettingsDisabler_Factory implements Factory<RemoteInputQuickSettingsDisabler> {
    private final Provider<ConfigurationController> configControllerProvider;
    private final Provider<Context> contextProvider;

    public RemoteInputQuickSettingsDisabler_Factory(Provider<Context> provider, Provider<ConfigurationController> provider2) {
        this.contextProvider = provider;
        this.configControllerProvider = provider2;
    }

    public RemoteInputQuickSettingsDisabler get() {
        return provideInstance(this.contextProvider, this.configControllerProvider);
    }

    public static RemoteInputQuickSettingsDisabler provideInstance(Provider<Context> provider, Provider<ConfigurationController> provider2) {
        return new RemoteInputQuickSettingsDisabler(provider.get(), provider2.get());
    }

    public static RemoteInputQuickSettingsDisabler_Factory create(Provider<Context> provider, Provider<ConfigurationController> provider2) {
        return new RemoteInputQuickSettingsDisabler_Factory(provider, provider2);
    }
}
