package com.android.systemui;

import android.content.Context;
import android.hardware.SensorPrivacyManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DependencyProvider_ProvideSensorPrivacyManagerFactory implements Factory<SensorPrivacyManager> {
    private final Provider<Context> contextProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProvideSensorPrivacyManagerFactory(DependencyProvider dependencyProvider, Provider<Context> provider) {
        this.module = dependencyProvider;
        this.contextProvider = provider;
    }

    public SensorPrivacyManager get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static SensorPrivacyManager provideInstance(DependencyProvider dependencyProvider, Provider<Context> provider) {
        return proxyProvideSensorPrivacyManager(dependencyProvider, provider.get());
    }

    public static DependencyProvider_ProvideSensorPrivacyManagerFactory create(DependencyProvider dependencyProvider, Provider<Context> provider) {
        return new DependencyProvider_ProvideSensorPrivacyManagerFactory(dependencyProvider, provider);
    }

    public static SensorPrivacyManager proxyProvideSensorPrivacyManager(DependencyProvider dependencyProvider, Context context) {
        SensorPrivacyManager provideSensorPrivacyManager = dependencyProvider.provideSensorPrivacyManager(context);
        Preconditions.checkNotNull(provideSensorPrivacyManager, "Cannot return null from a non-@Nullable @Provides method");
        return provideSensorPrivacyManager;
    }
}
