package com.android.systemui;

import android.content.Context;
import com.android.systemui.statusbar.phone.ShadeController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SystemUIDefaultModule_ProvideShadeControllerFactory implements Factory<ShadeController> {
    private final Provider<Context> contextProvider;

    public SystemUIDefaultModule_ProvideShadeControllerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public ShadeController get() {
        return provideInstance(this.contextProvider);
    }

    public static ShadeController provideInstance(Provider<Context> provider) {
        return proxyProvideShadeController(provider.get());
    }

    public static SystemUIDefaultModule_ProvideShadeControllerFactory create(Provider<Context> provider) {
        return new SystemUIDefaultModule_ProvideShadeControllerFactory(provider);
    }

    public static ShadeController proxyProvideShadeController(Context context) {
        ShadeController provideShadeController = SystemUIDefaultModule.provideShadeController(context);
        Preconditions.checkNotNull(provideShadeController, "Cannot return null from a non-@Nullable @Provides method");
        return provideShadeController;
    }
}
