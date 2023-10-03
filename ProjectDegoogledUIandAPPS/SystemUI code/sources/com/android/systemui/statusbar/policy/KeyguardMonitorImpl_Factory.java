package com.android.systemui.statusbar.policy;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class KeyguardMonitorImpl_Factory implements Factory<KeyguardMonitorImpl> {
    private final Provider<Context> contextProvider;

    public KeyguardMonitorImpl_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public KeyguardMonitorImpl get() {
        return provideInstance(this.contextProvider);
    }

    public static KeyguardMonitorImpl provideInstance(Provider<Context> provider) {
        return new KeyguardMonitorImpl(provider.get());
    }

    public static KeyguardMonitorImpl_Factory create(Provider<Context> provider) {
        return new KeyguardMonitorImpl_Factory(provider);
    }
}
