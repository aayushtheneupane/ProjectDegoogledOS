package com.android.dialer.common.concurrent;

import android.support.p002v7.appcompat.R$style;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import dagger.internal.Factory;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;

public final class DialerExecutorModule_ProvideLightweightExecutorFactory implements Factory<ListeningExecutorService> {
    private final Provider<ExecutorService> delegateProvider;

    public DialerExecutorModule_ProvideLightweightExecutorFactory(Provider<ExecutorService> provider) {
        this.delegateProvider = provider;
    }

    public Object get() {
        ListeningExecutorService listeningDecorator = MoreExecutors.listeningDecorator(this.delegateProvider.get());
        R$style.checkNotNull1(listeningDecorator, "Cannot return null from a non-@Nullable @Provides method");
        return listeningDecorator;
    }
}
