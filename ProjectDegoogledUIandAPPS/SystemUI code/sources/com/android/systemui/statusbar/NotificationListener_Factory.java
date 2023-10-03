package com.android.systemui.statusbar;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationListener_Factory implements Factory<NotificationListener> {
    private final Provider<Context> contextProvider;

    public NotificationListener_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public NotificationListener get() {
        return provideInstance(this.contextProvider);
    }

    public static NotificationListener provideInstance(Provider<Context> provider) {
        return new NotificationListener(provider.get());
    }

    public static NotificationListener_Factory create(Provider<Context> provider) {
        return new NotificationListener_Factory(provider);
    }
}
