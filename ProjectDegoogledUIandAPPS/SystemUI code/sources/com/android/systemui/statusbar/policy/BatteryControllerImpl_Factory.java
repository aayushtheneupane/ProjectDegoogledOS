package com.android.systemui.statusbar.policy;

import android.content.Context;
import com.android.systemui.power.EnhancedEstimates;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BatteryControllerImpl_Factory implements Factory<BatteryControllerImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<EnhancedEstimates> enhancedEstimatesProvider;

    public BatteryControllerImpl_Factory(Provider<Context> provider, Provider<EnhancedEstimates> provider2) {
        this.contextProvider = provider;
        this.enhancedEstimatesProvider = provider2;
    }

    public BatteryControllerImpl get() {
        return provideInstance(this.contextProvider, this.enhancedEstimatesProvider);
    }

    public static BatteryControllerImpl provideInstance(Provider<Context> provider, Provider<EnhancedEstimates> provider2) {
        return new BatteryControllerImpl(provider.get(), provider2.get());
    }

    public static BatteryControllerImpl_Factory create(Provider<Context> provider, Provider<EnhancedEstimates> provider2) {
        return new BatteryControllerImpl_Factory(provider, provider2);
    }
}
