package com.android.systemui.statusbar.phone;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StatusBarIconControllerImpl_Factory implements Factory<StatusBarIconControllerImpl> {
    private final Provider<Context> contextProvider;

    public StatusBarIconControllerImpl_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public StatusBarIconControllerImpl get() {
        return provideInstance(this.contextProvider);
    }

    public static StatusBarIconControllerImpl provideInstance(Provider<Context> provider) {
        return new StatusBarIconControllerImpl(provider.get());
    }

    public static StatusBarIconControllerImpl_Factory create(Provider<Context> provider) {
        return new StatusBarIconControllerImpl_Factory(provider);
    }
}
