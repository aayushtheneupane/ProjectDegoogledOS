package com.android.dialer.calllog.datasources.voicemail;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class VoicemailDataSource_Factory implements Factory<VoicemailDataSource> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;

    public VoicemailDataSource_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2) {
        this.appContextProvider = provider;
        this.backgroundExecutorProvider = provider2;
    }

    public Object get() {
        return new VoicemailDataSource(this.appContextProvider.get(), this.backgroundExecutorProvider.get());
    }
}
