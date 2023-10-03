package com.android.systemui.statusbar.notification.row;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationBlockingHelperManager_Factory implements Factory<NotificationBlockingHelperManager> {
    private final Provider<Context> contextProvider;

    public NotificationBlockingHelperManager_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public NotificationBlockingHelperManager get() {
        return provideInstance(this.contextProvider);
    }

    public static NotificationBlockingHelperManager provideInstance(Provider<Context> provider) {
        return new NotificationBlockingHelperManager(provider.get());
    }

    public static NotificationBlockingHelperManager_Factory create(Provider<Context> provider) {
        return new NotificationBlockingHelperManager_Factory(provider);
    }
}
