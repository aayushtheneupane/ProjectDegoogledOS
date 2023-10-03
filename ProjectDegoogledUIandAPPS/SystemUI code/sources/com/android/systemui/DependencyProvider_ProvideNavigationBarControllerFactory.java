package com.android.systemui;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.statusbar.NavigationBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DependencyProvider_ProvideNavigationBarControllerFactory implements Factory<NavigationBarController> {
    private final Provider<Context> contextProvider;
    private final Provider<Handler> mainHandlerProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProvideNavigationBarControllerFactory(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        this.module = dependencyProvider;
        this.contextProvider = provider;
        this.mainHandlerProvider = provider2;
    }

    public NavigationBarController get() {
        return provideInstance(this.module, this.contextProvider, this.mainHandlerProvider);
    }

    public static NavigationBarController provideInstance(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return proxyProvideNavigationBarController(dependencyProvider, provider.get(), provider2.get());
    }

    public static DependencyProvider_ProvideNavigationBarControllerFactory create(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return new DependencyProvider_ProvideNavigationBarControllerFactory(dependencyProvider, provider, provider2);
    }

    public static NavigationBarController proxyProvideNavigationBarController(DependencyProvider dependencyProvider, Context context, Handler handler) {
        NavigationBarController provideNavigationBarController = dependencyProvider.provideNavigationBarController(context, handler);
        Preconditions.checkNotNull(provideNavigationBarController, "Cannot return null from a non-@Nullable @Provides method");
        return provideNavigationBarController;
    }
}
