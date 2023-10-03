package com.android.systemui.tuner;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.util.leak.LeakDetector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TunerServiceImpl_Factory implements Factory<TunerServiceImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<LeakDetector> leakDetectorProvider;
    private final Provider<Handler> mainHandlerProvider;

    public TunerServiceImpl_Factory(Provider<Context> provider, Provider<Handler> provider2, Provider<LeakDetector> provider3) {
        this.contextProvider = provider;
        this.mainHandlerProvider = provider2;
        this.leakDetectorProvider = provider3;
    }

    public TunerServiceImpl get() {
        return provideInstance(this.contextProvider, this.mainHandlerProvider, this.leakDetectorProvider);
    }

    public static TunerServiceImpl provideInstance(Provider<Context> provider, Provider<Handler> provider2, Provider<LeakDetector> provider3) {
        return new TunerServiceImpl(provider.get(), provider2.get(), provider3.get());
    }

    public static TunerServiceImpl_Factory create(Provider<Context> provider, Provider<Handler> provider2, Provider<LeakDetector> provider3) {
        return new TunerServiceImpl_Factory(provider, provider2, provider3);
    }
}
