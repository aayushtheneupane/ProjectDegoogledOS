package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ZenModeControllerImpl_Factory implements Factory<ZenModeControllerImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<Handler> handlerProvider;

    public ZenModeControllerImpl_Factory(Provider<Context> provider, Provider<Handler> provider2) {
        this.contextProvider = provider;
        this.handlerProvider = provider2;
    }

    public ZenModeControllerImpl get() {
        return provideInstance(this.contextProvider, this.handlerProvider);
    }

    public static ZenModeControllerImpl provideInstance(Provider<Context> provider, Provider<Handler> provider2) {
        return new ZenModeControllerImpl(provider.get(), provider2.get());
    }

    public static ZenModeControllerImpl_Factory create(Provider<Context> provider, Provider<Handler> provider2) {
        return new ZenModeControllerImpl_Factory(provider, provider2);
    }
}
