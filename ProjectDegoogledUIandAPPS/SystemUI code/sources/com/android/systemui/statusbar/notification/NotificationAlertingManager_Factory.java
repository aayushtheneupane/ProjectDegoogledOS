package com.android.systemui.statusbar.notification;

import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.phone.ShadeController;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationAlertingManager_Factory implements Factory<NotificationAlertingManager> {
    private final Provider<NotificationEntryManager> notificationEntryManagerProvider;
    private final Provider<NotificationInterruptionStateProvider> notificationInterruptionStateProvider;
    private final Provider<NotificationListener> notificationListenerProvider;
    private final Provider<NotificationMediaManager> notificationMediaManagerProvider;
    private final Provider<NotificationRemoteInputManager> remoteInputManagerProvider;
    private final Provider<ShadeController> shadeControllerProvider;
    private final Provider<VisualStabilityManager> visualStabilityManagerProvider;

    public NotificationAlertingManager_Factory(Provider<NotificationEntryManager> provider, Provider<NotificationRemoteInputManager> provider2, Provider<VisualStabilityManager> provider3, Provider<ShadeController> provider4, Provider<NotificationInterruptionStateProvider> provider5, Provider<NotificationListener> provider6, Provider<NotificationMediaManager> provider7) {
        this.notificationEntryManagerProvider = provider;
        this.remoteInputManagerProvider = provider2;
        this.visualStabilityManagerProvider = provider3;
        this.shadeControllerProvider = provider4;
        this.notificationInterruptionStateProvider = provider5;
        this.notificationListenerProvider = provider6;
        this.notificationMediaManagerProvider = provider7;
    }

    public NotificationAlertingManager get() {
        return provideInstance(this.notificationEntryManagerProvider, this.remoteInputManagerProvider, this.visualStabilityManagerProvider, this.shadeControllerProvider, this.notificationInterruptionStateProvider, this.notificationListenerProvider, this.notificationMediaManagerProvider);
    }

    public static NotificationAlertingManager provideInstance(Provider<NotificationEntryManager> provider, Provider<NotificationRemoteInputManager> provider2, Provider<VisualStabilityManager> provider3, Provider<ShadeController> provider4, Provider<NotificationInterruptionStateProvider> provider5, Provider<NotificationListener> provider6, Provider<NotificationMediaManager> provider7) {
        return new NotificationAlertingManager(provider.get(), provider2.get(), provider3.get(), DoubleCheck.lazy(provider4), provider5.get(), provider6.get(), provider7.get());
    }

    public static NotificationAlertingManager_Factory create(Provider<NotificationEntryManager> provider, Provider<NotificationRemoteInputManager> provider2, Provider<VisualStabilityManager> provider3, Provider<ShadeController> provider4, Provider<NotificationInterruptionStateProvider> provider5, Provider<NotificationListener> provider6, Provider<NotificationMediaManager> provider7) {
        return new NotificationAlertingManager_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
