package com.android.dialer.phonelookup.blockednumber;

import android.content.Context;
import com.android.dialer.calllog.observer.MarkDirtyObserver;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SystemBlockedNumberPhoneLookup_Factory implements Factory<SystemBlockedNumberPhoneLookup> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> executorServiceProvider;
    private final Provider<MarkDirtyObserver> markDirtyObserverProvider;

    public SystemBlockedNumberPhoneLookup_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2, Provider<MarkDirtyObserver> provider3) {
        this.appContextProvider = provider;
        this.executorServiceProvider = provider2;
        this.markDirtyObserverProvider = provider3;
    }

    public Object get() {
        return new SystemBlockedNumberPhoneLookup(this.appContextProvider.get(), this.executorServiceProvider.get(), this.markDirtyObserverProvider.get());
    }
}
