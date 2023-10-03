package com.android.systemui;

import android.os.Looper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DependencyProvider_ProvideBgLooperFactory implements Factory<Looper> {
    private final DependencyProvider module;

    public DependencyProvider_ProvideBgLooperFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public Looper get() {
        return provideInstance(this.module);
    }

    public static Looper provideInstance(DependencyProvider dependencyProvider) {
        return proxyProvideBgLooper(dependencyProvider);
    }

    public static DependencyProvider_ProvideBgLooperFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvideBgLooperFactory(dependencyProvider);
    }

    public static Looper proxyProvideBgLooper(DependencyProvider dependencyProvider) {
        Looper provideBgLooper = dependencyProvider.provideBgLooper();
        Preconditions.checkNotNull(provideBgLooper, "Cannot return null from a non-@Nullable @Provides method");
        return provideBgLooper;
    }
}
