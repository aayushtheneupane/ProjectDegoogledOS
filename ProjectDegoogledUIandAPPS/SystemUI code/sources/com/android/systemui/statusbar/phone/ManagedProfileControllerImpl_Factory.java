package com.android.systemui.statusbar.phone;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ManagedProfileControllerImpl_Factory implements Factory<ManagedProfileControllerImpl> {
    private final Provider<Context> contextProvider;

    public ManagedProfileControllerImpl_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public ManagedProfileControllerImpl get() {
        return provideInstance(this.contextProvider);
    }

    public static ManagedProfileControllerImpl provideInstance(Provider<Context> provider) {
        return new ManagedProfileControllerImpl(provider.get());
    }

    public static ManagedProfileControllerImpl_Factory create(Provider<Context> provider) {
        return new ManagedProfileControllerImpl_Factory(provider);
    }
}
