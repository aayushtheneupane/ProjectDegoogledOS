package com.android.dialer.commandline;

import android.support.p002v7.appcompat.R$style;
import com.android.dialer.commandline.AutoValue_CommandSupplier;
import com.android.dialer.function.Supplier;
import com.google.common.collect.ImmutableMap;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CommandLineModule_ProvideCommandSupplierFactory implements Factory<Supplier<ImmutableMap<String, Command>>> {
    private final Provider<CommandLineModule$AospCommandInjector> aospCommandInjectorProvider;

    public CommandLineModule_ProvideCommandSupplierFactory(Provider<CommandLineModule$AospCommandInjector> provider) {
        this.aospCommandInjectorProvider = provider;
    }

    public Object get() {
        AutoValue_CommandSupplier.Builder builder = new AutoValue_CommandSupplier.Builder();
        this.aospCommandInjectorProvider.get().inject(builder);
        CommandSupplier build = builder.build();
        R$style.checkNotNull1(build, "Cannot return null from a non-@Nullable @Provides method");
        return build;
    }
}
