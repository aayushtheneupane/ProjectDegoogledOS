package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SecurityControllerImpl_Factory implements Factory<SecurityControllerImpl> {
    private final Provider<Handler> bgHandlerProvider;
    private final Provider<Context> contextProvider;

    public SecurityControllerImpl_Factory(Provider<Context> provider, Provider<Handler> provider2) {
        this.contextProvider = provider;
        this.bgHandlerProvider = provider2;
    }

    public SecurityControllerImpl get() {
        return provideInstance(this.contextProvider, this.bgHandlerProvider);
    }

    public static SecurityControllerImpl provideInstance(Provider<Context> provider, Provider<Handler> provider2) {
        return new SecurityControllerImpl(provider.get(), provider2.get());
    }

    public static SecurityControllerImpl_Factory create(Provider<Context> provider, Provider<Handler> provider2) {
        return new SecurityControllerImpl_Factory(provider, provider2);
    }
}
