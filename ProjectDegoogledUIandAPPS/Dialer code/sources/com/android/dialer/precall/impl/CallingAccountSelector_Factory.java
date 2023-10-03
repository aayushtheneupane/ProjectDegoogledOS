package com.android.dialer.precall.impl;

import com.android.dialer.preferredsim.PreferredAccountWorker;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CallingAccountSelector_Factory implements Factory<CallingAccountSelector> {
    private final Provider<PreferredAccountWorker> preferredAccountWorkerProvider;

    public CallingAccountSelector_Factory(Provider<PreferredAccountWorker> provider) {
        this.preferredAccountWorkerProvider = provider;
    }

    public Object get() {
        return new CallingAccountSelector(this.preferredAccountWorkerProvider.get());
    }
}
