package com.android.dialer.precall.impl;

import com.android.dialer.precall.PreCallAction;
import com.google.common.collect.ImmutableList;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PreCallImpl_Factory implements Factory<PreCallImpl> {
    private final Provider<ImmutableList<PreCallAction>> actionsProvider;

    public PreCallImpl_Factory(Provider<ImmutableList<PreCallAction>> provider) {
        this.actionsProvider = provider;
    }

    public Object get() {
        return new PreCallImpl(this.actionsProvider.get());
    }
}
