package com.android.systemui.statusbar.notification;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationEntryManager_Factory implements Factory<NotificationEntryManager> {
    private final Provider<Context> contextProvider;

    public NotificationEntryManager_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public NotificationEntryManager get() {
        return provideInstance(this.contextProvider);
    }

    public static NotificationEntryManager provideInstance(Provider<Context> provider) {
        return new NotificationEntryManager(provider.get());
    }

    public static NotificationEntryManager_Factory create(Provider<Context> provider) {
        return new NotificationEntryManager_Factory(provider);
    }
}
