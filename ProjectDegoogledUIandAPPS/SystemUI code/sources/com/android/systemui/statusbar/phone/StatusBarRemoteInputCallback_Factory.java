package com.android.systemui.statusbar.phone;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StatusBarRemoteInputCallback_Factory implements Factory<StatusBarRemoteInputCallback> {
    private final Provider<Context> contextProvider;
    private final Provider<NotificationGroupManager> groupManagerProvider;

    public StatusBarRemoteInputCallback_Factory(Provider<Context> provider, Provider<NotificationGroupManager> provider2) {
        this.contextProvider = provider;
        this.groupManagerProvider = provider2;
    }

    public StatusBarRemoteInputCallback get() {
        return provideInstance(this.contextProvider, this.groupManagerProvider);
    }

    public static StatusBarRemoteInputCallback provideInstance(Provider<Context> provider, Provider<NotificationGroupManager> provider2) {
        return new StatusBarRemoteInputCallback(provider.get(), provider2.get());
    }

    public static StatusBarRemoteInputCallback_Factory create(Provider<Context> provider, Provider<NotificationGroupManager> provider2) {
        return new StatusBarRemoteInputCallback_Factory(provider, provider2);
    }
}
