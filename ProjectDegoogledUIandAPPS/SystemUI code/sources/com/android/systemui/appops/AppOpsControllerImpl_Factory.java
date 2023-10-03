package com.android.systemui.appops;

import android.content.Context;
import android.os.Looper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AppOpsControllerImpl_Factory implements Factory<AppOpsControllerImpl> {
    private final Provider<Looper> bgLooperProvider;
    private final Provider<Context> contextProvider;

    public AppOpsControllerImpl_Factory(Provider<Context> provider, Provider<Looper> provider2) {
        this.contextProvider = provider;
        this.bgLooperProvider = provider2;
    }

    public AppOpsControllerImpl get() {
        return provideInstance(this.contextProvider, this.bgLooperProvider);
    }

    public static AppOpsControllerImpl provideInstance(Provider<Context> provider, Provider<Looper> provider2) {
        return new AppOpsControllerImpl(provider.get(), provider2.get());
    }

    public static AppOpsControllerImpl_Factory create(Provider<Context> provider, Provider<Looper> provider2) {
        return new AppOpsControllerImpl_Factory(provider, provider2);
    }
}
