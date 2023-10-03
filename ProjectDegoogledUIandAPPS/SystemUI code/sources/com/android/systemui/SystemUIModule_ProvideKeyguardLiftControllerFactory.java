package com.android.systemui;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.phone.KeyguardLiftController;
import com.android.systemui.util.AsyncSensorManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SystemUIModule_ProvideKeyguardLiftControllerFactory implements Factory<KeyguardLiftController> {
    private final Provider<AsyncSensorManager> asyncSensorManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;

    public SystemUIModule_ProvideKeyguardLiftControllerFactory(Provider<Context> provider, Provider<StatusBarStateController> provider2, Provider<AsyncSensorManager> provider3) {
        this.contextProvider = provider;
        this.statusBarStateControllerProvider = provider2;
        this.asyncSensorManagerProvider = provider3;
    }

    public KeyguardLiftController get() {
        return provideInstance(this.contextProvider, this.statusBarStateControllerProvider, this.asyncSensorManagerProvider);
    }

    public static KeyguardLiftController provideInstance(Provider<Context> provider, Provider<StatusBarStateController> provider2, Provider<AsyncSensorManager> provider3) {
        return proxyProvideKeyguardLiftController(provider.get(), provider2.get(), provider3.get());
    }

    public static SystemUIModule_ProvideKeyguardLiftControllerFactory create(Provider<Context> provider, Provider<StatusBarStateController> provider2, Provider<AsyncSensorManager> provider3) {
        return new SystemUIModule_ProvideKeyguardLiftControllerFactory(provider, provider2, provider3);
    }

    public static KeyguardLiftController proxyProvideKeyguardLiftController(Context context, StatusBarStateController statusBarStateController, AsyncSensorManager asyncSensorManager) {
        return SystemUIModule.provideKeyguardLiftController(context, statusBarStateController, asyncSensorManager);
    }
}
