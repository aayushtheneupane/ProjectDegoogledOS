package com.android.systemui;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.statusbar.policy.PulseController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DependencyProvider_ProvidePulseControllerFactory implements Factory<PulseController> {
    private final Provider<Context> contextProvider;
    private final Provider<Handler> mainHandlerProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProvidePulseControllerFactory(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        this.module = dependencyProvider;
        this.contextProvider = provider;
        this.mainHandlerProvider = provider2;
    }

    public PulseController get() {
        return provideInstance(this.module, this.contextProvider, this.mainHandlerProvider);
    }

    public static PulseController provideInstance(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return proxyProvidePulseController(dependencyProvider, provider.get(), provider2.get());
    }

    public static DependencyProvider_ProvidePulseControllerFactory create(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return new DependencyProvider_ProvidePulseControllerFactory(dependencyProvider, provider, provider2);
    }

    public static PulseController proxyProvidePulseController(DependencyProvider dependencyProvider, Context context, Handler handler) {
        PulseController providePulseController = dependencyProvider.providePulseController(context, handler);
        Preconditions.checkNotNull(providePulseController, "Cannot return null from a non-@Nullable @Provides method");
        return providePulseController;
    }
}
