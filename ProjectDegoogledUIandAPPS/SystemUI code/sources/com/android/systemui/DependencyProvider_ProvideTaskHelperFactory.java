package com.android.systemui;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.statusbar.policy.TaskHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DependencyProvider_ProvideTaskHelperFactory implements Factory<TaskHelper> {
    private final Provider<Context> contextProvider;
    private final Provider<Handler> mainHandlerProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProvideTaskHelperFactory(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        this.module = dependencyProvider;
        this.contextProvider = provider;
        this.mainHandlerProvider = provider2;
    }

    public TaskHelper get() {
        return provideInstance(this.module, this.contextProvider, this.mainHandlerProvider);
    }

    public static TaskHelper provideInstance(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return proxyProvideTaskHelper(dependencyProvider, provider.get(), provider2.get());
    }

    public static DependencyProvider_ProvideTaskHelperFactory create(DependencyProvider dependencyProvider, Provider<Context> provider, Provider<Handler> provider2) {
        return new DependencyProvider_ProvideTaskHelperFactory(dependencyProvider, provider, provider2);
    }

    public static TaskHelper proxyProvideTaskHelper(DependencyProvider dependencyProvider, Context context, Handler handler) {
        TaskHelper provideTaskHelper = dependencyProvider.provideTaskHelper(context, handler);
        Preconditions.checkNotNull(provideTaskHelper, "Cannot return null from a non-@Nullable @Provides method");
        return provideTaskHelper;
    }
}
