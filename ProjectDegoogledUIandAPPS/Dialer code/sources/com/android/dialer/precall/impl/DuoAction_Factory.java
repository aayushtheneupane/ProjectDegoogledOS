package com.android.dialer.precall.impl;

import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DuoAction_Factory implements Factory<DuoAction> {
    private final Provider<ListeningExecutorService> uiExecutorProvider;

    public DuoAction_Factory(Provider<ListeningExecutorService> provider) {
        this.uiExecutorProvider = provider;
    }

    public Object get() {
        return new DuoAction(this.uiExecutorProvider.get());
    }
}
