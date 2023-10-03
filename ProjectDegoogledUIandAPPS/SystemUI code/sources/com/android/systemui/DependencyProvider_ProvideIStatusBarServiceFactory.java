package com.android.systemui;

import com.android.internal.statusbar.IStatusBarService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DependencyProvider_ProvideIStatusBarServiceFactory implements Factory<IStatusBarService> {
    private final DependencyProvider module;

    public DependencyProvider_ProvideIStatusBarServiceFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public IStatusBarService get() {
        return provideInstance(this.module);
    }

    public static IStatusBarService provideInstance(DependencyProvider dependencyProvider) {
        return proxyProvideIStatusBarService(dependencyProvider);
    }

    public static DependencyProvider_ProvideIStatusBarServiceFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvideIStatusBarServiceFactory(dependencyProvider);
    }

    public static IStatusBarService proxyProvideIStatusBarService(DependencyProvider dependencyProvider) {
        IStatusBarService provideIStatusBarService = dependencyProvider.provideIStatusBarService();
        Preconditions.checkNotNull(provideIStatusBarService, "Cannot return null from a non-@Nullable @Provides method");
        return provideIStatusBarService;
    }
}
