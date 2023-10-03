package com.android.dialer.precall.impl;

import android.support.p002v7.appcompat.R$style;
import com.android.dialer.precall.PreCallAction;
import com.google.common.collect.ImmutableList;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PreCallModule_ProvideActionsFactory implements Factory<ImmutableList<PreCallAction>> {
    private final Provider<CallingAccountSelector> callingAccountSelectorProvider;
    private final Provider<DuoAction> duoActionProvider;

    public PreCallModule_ProvideActionsFactory(Provider<DuoAction> provider, Provider<CallingAccountSelector> provider2) {
        this.duoActionProvider = provider;
        this.callingAccountSelectorProvider = provider2;
    }

    public Object get() {
        CallingAccountSelector callingAccountSelector = this.callingAccountSelectorProvider.get();
        ImmutableList of = ImmutableList.m78of(new PermissionCheckAction(), new MalformedNumberRectifier(ImmutableList.m75of(new UkRegionPrefixInInternationalFormatHandler())), callingAccountSelector, this.duoActionProvider.get(), new AssistedDialAction());
        R$style.checkNotNull1(of, "Cannot return null from a non-@Nullable @Provides method");
        return of;
    }
}
