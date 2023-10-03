package com.android.dialer.inject;

import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import dagger.internal.Factory;

public final class ContextModule_ProvideContextFactory implements Factory<Context> {
    private final ContextModule module;

    public ContextModule_ProvideContextFactory(ContextModule contextModule) {
        this.module = contextModule;
    }

    public Object get() {
        Context provideContext = this.module.provideContext();
        R$style.checkNotNull1(provideContext, "Cannot return null from a non-@Nullable @Provides method");
        return provideContext;
    }
}
