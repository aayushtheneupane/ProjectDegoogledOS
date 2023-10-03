package com.android.systemui;

import android.os.Handler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DependencyProvider_ProvideMainHandlerFactory implements Factory<Handler> {
    private final DependencyProvider module;

    public DependencyProvider_ProvideMainHandlerFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public Handler get() {
        return provideInstance(this.module);
    }

    public static Handler provideInstance(DependencyProvider dependencyProvider) {
        return proxyProvideMainHandler(dependencyProvider);
    }

    public static DependencyProvider_ProvideMainHandlerFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvideMainHandlerFactory(dependencyProvider);
    }

    public static Handler proxyProvideMainHandler(DependencyProvider dependencyProvider) {
        Handler provideMainHandler = dependencyProvider.provideMainHandler();
        Preconditions.checkNotNull(provideMainHandler, "Cannot return null from a non-@Nullable @Provides method");
        return provideMainHandler;
    }
}
