package com.android.systemui.statusbar.notification;

import android.os.Handler;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class VisualStabilityManager_Factory implements Factory<VisualStabilityManager> {
    private final Provider<Handler> handlerProvider;
    private final Provider<NotificationEntryManager> notificationEntryManagerProvider;

    public VisualStabilityManager_Factory(Provider<NotificationEntryManager> provider, Provider<Handler> provider2) {
        this.notificationEntryManagerProvider = provider;
        this.handlerProvider = provider2;
    }

    public VisualStabilityManager get() {
        return provideInstance(this.notificationEntryManagerProvider, this.handlerProvider);
    }

    public static VisualStabilityManager provideInstance(Provider<NotificationEntryManager> provider, Provider<Handler> provider2) {
        return new VisualStabilityManager(provider.get(), provider2.get());
    }

    public static VisualStabilityManager_Factory create(Provider<NotificationEntryManager> provider, Provider<Handler> provider2) {
        return new VisualStabilityManager_Factory(provider, provider2);
    }
}
