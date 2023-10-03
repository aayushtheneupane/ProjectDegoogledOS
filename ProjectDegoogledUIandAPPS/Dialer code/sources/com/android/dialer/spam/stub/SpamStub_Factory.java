package com.android.dialer.spam.stub;

import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SpamStub_Factory implements Factory<SpamStub> {
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;

    public SpamStub_Factory(Provider<ListeningExecutorService> provider) {
        this.backgroundExecutorServiceProvider = provider;
    }

    public Object get() {
        return new SpamStub(this.backgroundExecutorServiceProvider.get());
    }
}
