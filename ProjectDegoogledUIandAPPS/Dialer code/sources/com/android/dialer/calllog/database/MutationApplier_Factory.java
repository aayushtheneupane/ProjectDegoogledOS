package com.android.dialer.calllog.database;

import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MutationApplier_Factory implements Factory<MutationApplier> {
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;

    public MutationApplier_Factory(Provider<ListeningExecutorService> provider) {
        this.backgroundExecutorServiceProvider = provider;
    }

    public Object get() {
        return new MutationApplier(this.backgroundExecutorServiceProvider.get());
    }
}
