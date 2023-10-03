package com.android.systemui.statusbar.phone;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class HeadsUpManagerPhone_Factory implements Factory<HeadsUpManagerPhone> {
    private final Provider<KeyguardBypassController> bypassControllerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;

    public HeadsUpManagerPhone_Factory(Provider<Context> provider, Provider<StatusBarStateController> provider2, Provider<KeyguardBypassController> provider3) {
        this.contextProvider = provider;
        this.statusBarStateControllerProvider = provider2;
        this.bypassControllerProvider = provider3;
    }

    public HeadsUpManagerPhone get() {
        return provideInstance(this.contextProvider, this.statusBarStateControllerProvider, this.bypassControllerProvider);
    }

    public static HeadsUpManagerPhone provideInstance(Provider<Context> provider, Provider<StatusBarStateController> provider2, Provider<KeyguardBypassController> provider3) {
        return new HeadsUpManagerPhone(provider.get(), provider2.get(), provider3.get());
    }

    public static HeadsUpManagerPhone_Factory create(Provider<Context> provider, Provider<StatusBarStateController> provider2, Provider<KeyguardBypassController> provider3) {
        return new HeadsUpManagerPhone_Factory(provider, provider2, provider3);
    }
}
