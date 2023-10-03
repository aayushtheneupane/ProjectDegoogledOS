package com.android.systemui.statusbar.phone;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DarkIconDispatcherImpl_Factory implements Factory<DarkIconDispatcherImpl> {
    private final Provider<Context> contextProvider;

    public DarkIconDispatcherImpl_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public DarkIconDispatcherImpl get() {
        return provideInstance(this.contextProvider);
    }

    public static DarkIconDispatcherImpl provideInstance(Provider<Context> provider) {
        return new DarkIconDispatcherImpl(provider.get());
    }

    public static DarkIconDispatcherImpl_Factory create(Provider<Context> provider) {
        return new DarkIconDispatcherImpl_Factory(provider);
    }
}
