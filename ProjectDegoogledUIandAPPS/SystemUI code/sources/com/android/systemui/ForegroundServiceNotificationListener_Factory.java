package com.android.systemui;

import android.content.Context;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ForegroundServiceNotificationListener_Factory implements Factory<ForegroundServiceNotificationListener> {
    private final Provider<Context> contextProvider;
    private final Provider<ForegroundServiceController> foregroundServiceControllerProvider;
    private final Provider<NotificationEntryManager> notificationEntryManagerProvider;

    public ForegroundServiceNotificationListener_Factory(Provider<Context> provider, Provider<ForegroundServiceController> provider2, Provider<NotificationEntryManager> provider3) {
        this.contextProvider = provider;
        this.foregroundServiceControllerProvider = provider2;
        this.notificationEntryManagerProvider = provider3;
    }

    public ForegroundServiceNotificationListener get() {
        return provideInstance(this.contextProvider, this.foregroundServiceControllerProvider, this.notificationEntryManagerProvider);
    }

    public static ForegroundServiceNotificationListener provideInstance(Provider<Context> provider, Provider<ForegroundServiceController> provider2, Provider<NotificationEntryManager> provider3) {
        return new ForegroundServiceNotificationListener(provider.get(), provider2.get(), provider3.get());
    }

    public static ForegroundServiceNotificationListener_Factory create(Provider<Context> provider, Provider<ForegroundServiceController> provider2, Provider<NotificationEntryManager> provider3) {
        return new ForegroundServiceNotificationListener_Factory(provider, provider2, provider3);
    }
}
