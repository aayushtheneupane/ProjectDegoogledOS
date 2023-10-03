package com.android.systemui.statusbar.notification;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.policy.BatteryController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationInterruptionStateProvider_Factory implements Factory<NotificationInterruptionStateProvider> {
    private final Provider<BatteryController> batteryControllerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<NotificationFilter> filterProvider;
    private final Provider<StatusBarStateController> stateControllerProvider;

    public NotificationInterruptionStateProvider_Factory(Provider<Context> provider, Provider<NotificationFilter> provider2, Provider<StatusBarStateController> provider3, Provider<BatteryController> provider4) {
        this.contextProvider = provider;
        this.filterProvider = provider2;
        this.stateControllerProvider = provider3;
        this.batteryControllerProvider = provider4;
    }

    public NotificationInterruptionStateProvider get() {
        return provideInstance(this.contextProvider, this.filterProvider, this.stateControllerProvider, this.batteryControllerProvider);
    }

    public static NotificationInterruptionStateProvider provideInstance(Provider<Context> provider, Provider<NotificationFilter> provider2, Provider<StatusBarStateController> provider3, Provider<BatteryController> provider4) {
        return new NotificationInterruptionStateProvider(provider.get(), provider2.get(), provider3.get(), provider4.get());
    }

    public static NotificationInterruptionStateProvider_Factory create(Provider<Context> provider, Provider<NotificationFilter> provider2, Provider<StatusBarStateController> provider3, Provider<BatteryController> provider4) {
        return new NotificationInterruptionStateProvider_Factory(provider, provider2, provider3, provider4);
    }
}
