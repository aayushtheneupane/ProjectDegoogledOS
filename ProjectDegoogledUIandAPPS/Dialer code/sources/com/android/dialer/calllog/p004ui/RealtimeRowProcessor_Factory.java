package com.android.dialer.calllog.p004ui;

import android.content.Context;
import com.android.dialer.phonelookup.composite.CompositePhoneLookup;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

/* renamed from: com.android.dialer.calllog.ui.RealtimeRowProcessor_Factory */
public final class RealtimeRowProcessor_Factory implements Factory<RealtimeRowProcessor> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final Provider<CompositePhoneLookup> compositePhoneLookupProvider;
    private final Provider<ListeningExecutorService> uiExecutorProvider;

    public RealtimeRowProcessor_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2, Provider<ListeningExecutorService> provider3, Provider<CompositePhoneLookup> provider4) {
        this.appContextProvider = provider;
        this.uiExecutorProvider = provider2;
        this.backgroundExecutorProvider = provider3;
        this.compositePhoneLookupProvider = provider4;
    }

    public Object get() {
        return new RealtimeRowProcessor(this.appContextProvider.get(), this.uiExecutorProvider.get(), this.backgroundExecutorProvider.get(), this.compositePhoneLookupProvider.get());
    }
}
