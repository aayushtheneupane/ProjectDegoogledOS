package com.android.systemui.power;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class EnhancedEstimatesImpl_Factory implements Factory<EnhancedEstimatesImpl> {
    private final Provider<Context> contextProvider;

    public EnhancedEstimatesImpl_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public EnhancedEstimatesImpl get() {
        return provideInstance(this.contextProvider);
    }

    public static EnhancedEstimatesImpl provideInstance(Provider<Context> provider) {
        return new EnhancedEstimatesImpl(provider.get());
    }

    public static EnhancedEstimatesImpl_Factory create(Provider<Context> provider) {
        return new EnhancedEstimatesImpl_Factory(provider);
    }
}
