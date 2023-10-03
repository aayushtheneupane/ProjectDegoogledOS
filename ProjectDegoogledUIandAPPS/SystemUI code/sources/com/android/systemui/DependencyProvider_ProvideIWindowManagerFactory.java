package com.android.systemui;

import android.view.IWindowManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DependencyProvider_ProvideIWindowManagerFactory implements Factory<IWindowManager> {
    private final DependencyProvider module;

    public DependencyProvider_ProvideIWindowManagerFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public IWindowManager get() {
        return provideInstance(this.module);
    }

    public static IWindowManager provideInstance(DependencyProvider dependencyProvider) {
        return proxyProvideIWindowManager(dependencyProvider);
    }

    public static DependencyProvider_ProvideIWindowManagerFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvideIWindowManagerFactory(dependencyProvider);
    }

    public static IWindowManager proxyProvideIWindowManager(DependencyProvider dependencyProvider) {
        IWindowManager provideIWindowManager = dependencyProvider.provideIWindowManager();
        Preconditions.checkNotNull(provideIWindowManager, "Cannot return null from a non-@Nullable @Provides method");
        return provideIWindowManager;
    }
}
