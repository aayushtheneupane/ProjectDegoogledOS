package com.android.systemui.assist;

import android.content.Context;
import com.android.systemui.ScreenDecorations;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AssistModule_ProvideScreenDecorationsFactory implements Factory<ScreenDecorations> {
    private final Provider<Context> contextProvider;

    public AssistModule_ProvideScreenDecorationsFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public ScreenDecorations get() {
        return provideInstance(this.contextProvider);
    }

    public static ScreenDecorations provideInstance(Provider<Context> provider) {
        return proxyProvideScreenDecorations(provider.get());
    }

    public static AssistModule_ProvideScreenDecorationsFactory create(Provider<Context> provider) {
        return new AssistModule_ProvideScreenDecorationsFactory(provider);
    }

    public static ScreenDecorations proxyProvideScreenDecorations(Context context) {
        ScreenDecorations provideScreenDecorations = AssistModule.provideScreenDecorations(context);
        Preconditions.checkNotNull(provideScreenDecorations, "Cannot return null from a non-@Nullable @Provides method");
        return provideScreenDecorations;
    }
}
