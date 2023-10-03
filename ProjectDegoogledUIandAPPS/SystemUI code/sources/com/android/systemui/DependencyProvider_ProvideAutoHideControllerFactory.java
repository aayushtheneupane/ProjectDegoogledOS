package com.android.systemui;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.statusbar.phone.AutoHideController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DependencyProvider_ProvideAutoHideControllerFactory implements Factory<AutoHideController> {
    private final Provider<Context> contextProvider;
    private final Provider<Handler> mainHandlerProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProvideAutoHideControllerFactory(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        this.module = dependencyProvider;
        this.contextProvider = provider;
        this.mainHandlerProvider = provider2;
    }

    public AutoHideController get() {
        return provideInstance(this.module, this.contextProvider, this.mainHandlerProvider);
    }

    public static AutoHideController provideInstance(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return proxyProvideAutoHideController(dependencyProvider, provider.get(), provider2.get());
    }

    public static DependencyProvider_ProvideAutoHideControllerFactory create(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return new DependencyProvider_ProvideAutoHideControllerFactory(dependencyProvider, provider, provider2);
    }

    public static AutoHideController proxyProvideAutoHideController(DependencyProvider dependencyProvider, Context context, Handler handler) {
        AutoHideController provideAutoHideController = dependencyProvider.provideAutoHideController(context, handler);
        Preconditions.checkNotNull(provideAutoHideController, "Cannot return null from a non-@Nullable @Provides method");
        return provideAutoHideController;
    }
}
