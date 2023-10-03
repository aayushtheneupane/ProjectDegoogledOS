package com.android.dialer.theme.base.impl;

import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.theme.base.Theme;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AospThemeModule_ProvideThemeModuleFactory implements Factory<Theme> {
    private final Provider<Context> contextProvider;

    public AospThemeModule_ProvideThemeModuleFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public Object get() {
        AospThemeImpl aospThemeImpl = new AospThemeImpl(this.contextProvider.get());
        R$style.checkNotNull1(aospThemeImpl, "Cannot return null from a non-@Nullable @Provides method");
        return aospThemeImpl;
    }
}
