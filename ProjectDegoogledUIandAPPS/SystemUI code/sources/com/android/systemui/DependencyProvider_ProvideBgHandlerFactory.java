package com.android.systemui;

import android.os.Handler;
import android.os.Looper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DependencyProvider_ProvideBgHandlerFactory implements Factory<Handler> {
    private final Provider<Looper> bgLooperProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProvideBgHandlerFactory(DependencyProvider dependencyProvider, Provider<Looper> provider) {
        this.module = dependencyProvider;
        this.bgLooperProvider = provider;
    }

    public Handler get() {
        return provideInstance(this.module, this.bgLooperProvider);
    }

    public static Handler provideInstance(DependencyProvider dependencyProvider, Provider<Looper> provider) {
        return proxyProvideBgHandler(dependencyProvider, provider.get());
    }

    public static DependencyProvider_ProvideBgHandlerFactory create(DependencyProvider dependencyProvider, Provider<Looper> provider) {
        return new DependencyProvider_ProvideBgHandlerFactory(dependencyProvider, provider);
    }

    public static Handler proxyProvideBgHandler(DependencyProvider dependencyProvider, Looper looper) {
        Handler provideBgHandler = dependencyProvider.provideBgHandler(looper);
        Preconditions.checkNotNull(provideBgHandler, "Cannot return null from a non-@Nullable @Provides method");
        return provideBgHandler;
    }
}
