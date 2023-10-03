package com.android.systemui;

import android.util.DisplayMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DependencyProvider_ProvideDisplayMetricsFactory implements Factory<DisplayMetrics> {
    private final DependencyProvider module;

    public DependencyProvider_ProvideDisplayMetricsFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public DisplayMetrics get() {
        return provideInstance(this.module);
    }

    public static DisplayMetrics provideInstance(DependencyProvider dependencyProvider) {
        return proxyProvideDisplayMetrics(dependencyProvider);
    }

    public static DependencyProvider_ProvideDisplayMetricsFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvideDisplayMetricsFactory(dependencyProvider);
    }

    public static DisplayMetrics proxyProvideDisplayMetrics(DependencyProvider dependencyProvider) {
        DisplayMetrics provideDisplayMetrics = dependencyProvider.provideDisplayMetrics();
        Preconditions.checkNotNull(provideDisplayMetrics, "Cannot return null from a non-@Nullable @Provides method");
        return provideDisplayMetrics;
    }
}
