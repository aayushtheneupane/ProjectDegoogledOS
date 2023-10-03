package com.android.systemui;

import com.android.systemui.shared.system.PackageManagerWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DependencyProvider_ProvidePackageManagerWrapperFactory implements Factory<PackageManagerWrapper> {
    private final DependencyProvider module;

    public DependencyProvider_ProvidePackageManagerWrapperFactory(DependencyProvider dependencyProvider) {
        this.module = dependencyProvider;
    }

    public PackageManagerWrapper get() {
        return provideInstance(this.module);
    }

    public static PackageManagerWrapper provideInstance(DependencyProvider dependencyProvider) {
        return proxyProvidePackageManagerWrapper(dependencyProvider);
    }

    public static DependencyProvider_ProvidePackageManagerWrapperFactory create(DependencyProvider dependencyProvider) {
        return new DependencyProvider_ProvidePackageManagerWrapperFactory(dependencyProvider);
    }

    public static PackageManagerWrapper proxyProvidePackageManagerWrapper(DependencyProvider dependencyProvider) {
        PackageManagerWrapper providePackageManagerWrapper = dependencyProvider.providePackageManagerWrapper();
        Preconditions.checkNotNull(providePackageManagerWrapper, "Cannot return null from a non-@Nullable @Provides method");
        return providePackageManagerWrapper;
    }
}
