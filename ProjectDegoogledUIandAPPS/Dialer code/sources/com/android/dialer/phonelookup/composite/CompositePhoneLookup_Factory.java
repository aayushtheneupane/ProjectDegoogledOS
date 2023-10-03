package com.android.dialer.phonelookup.composite;

import android.content.Context;
import com.android.dialer.calllog.CallLogState;
import com.android.dialer.metrics.FutureTimer;
import com.android.dialer.phonelookup.PhoneLookup;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CompositePhoneLookup_Factory implements Factory<CompositePhoneLookup> {
    private final Provider<Context> appContextProvider;
    private final Provider<CallLogState> callLogStateProvider;
    private final Provider<FutureTimer> futureTimerProvider;
    private final Provider<ListeningExecutorService> lightweightExecutorServiceProvider;
    private final Provider<ImmutableList<PhoneLookup>> phoneLookupsProvider;

    public CompositePhoneLookup_Factory(Provider<Context> provider, Provider<ImmutableList<PhoneLookup>> provider2, Provider<FutureTimer> provider3, Provider<CallLogState> provider4, Provider<ListeningExecutorService> provider5) {
        this.appContextProvider = provider;
        this.phoneLookupsProvider = provider2;
        this.futureTimerProvider = provider3;
        this.callLogStateProvider = provider4;
        this.lightweightExecutorServiceProvider = provider5;
    }

    public Object get() {
        return new CompositePhoneLookup(this.appContextProvider.get(), this.phoneLookupsProvider.get(), this.futureTimerProvider.get(), this.callLogStateProvider.get(), this.lightweightExecutorServiceProvider.get());
    }
}
