package com.google.android.systemui;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationLockscreenUserManagerGoogle_Factory implements Factory<NotificationLockscreenUserManagerGoogle> {
    private final Provider<Context> contextProvider;

    public NotificationLockscreenUserManagerGoogle_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public NotificationLockscreenUserManagerGoogle get() {
        return provideInstance(this.contextProvider);
    }

    public static NotificationLockscreenUserManagerGoogle provideInstance(Provider<Context> provider) {
        return new NotificationLockscreenUserManagerGoogle(provider.get());
    }

    public static NotificationLockscreenUserManagerGoogle_Factory create(Provider<Context> provider) {
        return new NotificationLockscreenUserManagerGoogle_Factory(provider);
    }

    public static NotificationLockscreenUserManagerGoogle newNotificationLockscreenUserManagerGoogle(Context context) {
        return new NotificationLockscreenUserManagerGoogle(context);
    }
}
