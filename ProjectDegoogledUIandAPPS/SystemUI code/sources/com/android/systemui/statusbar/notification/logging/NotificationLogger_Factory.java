package com.android.systemui.statusbar.notification.logging;

import com.android.systemui.UiOffloadThread;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationLogger_Factory implements Factory<NotificationLogger> {
    private final Provider<NotificationEntryManager> entryManagerProvider;
    private final Provider<NotificationLogger.ExpansionStateLogger> expansionStateLoggerProvider;
    private final Provider<NotificationListener> notificationListenerProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;
    private final Provider<UiOffloadThread> uiOffloadThreadProvider;

    public NotificationLogger_Factory(Provider<NotificationListener> provider, Provider<UiOffloadThread> provider2, Provider<NotificationEntryManager> provider3, Provider<StatusBarStateController> provider4, Provider<NotificationLogger.ExpansionStateLogger> provider5) {
        this.notificationListenerProvider = provider;
        this.uiOffloadThreadProvider = provider2;
        this.entryManagerProvider = provider3;
        this.statusBarStateControllerProvider = provider4;
        this.expansionStateLoggerProvider = provider5;
    }

    public NotificationLogger get() {
        return provideInstance(this.notificationListenerProvider, this.uiOffloadThreadProvider, this.entryManagerProvider, this.statusBarStateControllerProvider, this.expansionStateLoggerProvider);
    }

    public static NotificationLogger provideInstance(Provider<NotificationListener> provider, Provider<UiOffloadThread> provider2, Provider<NotificationEntryManager> provider3, Provider<StatusBarStateController> provider4, Provider<NotificationLogger.ExpansionStateLogger> provider5) {
        return new NotificationLogger(provider.get(), provider2.get(), provider3.get(), provider4.get(), provider5.get());
    }

    public static NotificationLogger_Factory create(Provider<NotificationListener> provider, Provider<UiOffloadThread> provider2, Provider<NotificationEntryManager> provider3, Provider<StatusBarStateController> provider4, Provider<NotificationLogger.ExpansionStateLogger> provider5) {
        return new NotificationLogger_Factory(provider, provider2, provider3, provider4, provider5);
    }
}
