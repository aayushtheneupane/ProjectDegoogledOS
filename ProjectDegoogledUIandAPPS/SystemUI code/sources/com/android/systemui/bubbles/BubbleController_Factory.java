package com.android.systemui.bubbles;

import android.content.Context;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.notification.NotificationInterruptionStateProvider;
import com.android.systemui.statusbar.phone.NotificationGroupManager;
import com.android.systemui.statusbar.phone.StatusBarWindowController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.ZenModeController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BubbleController_Factory implements Factory<BubbleController> {
    private final Provider<ConfigurationController> configurationControllerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<BubbleData> dataProvider;
    private final Provider<NotificationGroupManager> groupManagerProvider;
    private final Provider<NotificationInterruptionStateProvider> interruptionStateProvider;
    private final Provider<NotificationLockscreenUserManager> notifUserManagerProvider;
    private final Provider<StatusBarWindowController> statusBarWindowControllerProvider;
    private final Provider<ZenModeController> zenModeControllerProvider;

    public BubbleController_Factory(Provider<Context> provider, Provider<StatusBarWindowController> provider2, Provider<BubbleData> provider3, Provider<ConfigurationController> provider4, Provider<NotificationInterruptionStateProvider> provider5, Provider<ZenModeController> provider6, Provider<NotificationLockscreenUserManager> provider7, Provider<NotificationGroupManager> provider8) {
        this.contextProvider = provider;
        this.statusBarWindowControllerProvider = provider2;
        this.dataProvider = provider3;
        this.configurationControllerProvider = provider4;
        this.interruptionStateProvider = provider5;
        this.zenModeControllerProvider = provider6;
        this.notifUserManagerProvider = provider7;
        this.groupManagerProvider = provider8;
    }

    public BubbleController get() {
        return provideInstance(this.contextProvider, this.statusBarWindowControllerProvider, this.dataProvider, this.configurationControllerProvider, this.interruptionStateProvider, this.zenModeControllerProvider, this.notifUserManagerProvider, this.groupManagerProvider);
    }

    public static BubbleController provideInstance(Provider<Context> provider, Provider<StatusBarWindowController> provider2, Provider<BubbleData> provider3, Provider<ConfigurationController> provider4, Provider<NotificationInterruptionStateProvider> provider5, Provider<ZenModeController> provider6, Provider<NotificationLockscreenUserManager> provider7, Provider<NotificationGroupManager> provider8) {
        return new BubbleController(provider.get(), provider2.get(), provider3.get(), provider4.get(), provider5.get(), provider6.get(), provider7.get(), provider8.get());
    }

    public static BubbleController_Factory create(Provider<Context> provider, Provider<StatusBarWindowController> provider2, Provider<BubbleData> provider3, Provider<ConfigurationController> provider4, Provider<NotificationInterruptionStateProvider> provider5, Provider<ZenModeController> provider6, Provider<NotificationLockscreenUserManager> provider7, Provider<NotificationGroupManager> provider8) {
        return new BubbleController_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }
}
